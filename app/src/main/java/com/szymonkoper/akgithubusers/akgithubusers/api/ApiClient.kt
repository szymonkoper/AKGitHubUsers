package com.szymonkoper.akgithubusers.akgithubusers.api

import com.apollographql.apollo.ApolloClient
import com.szymonkoper.akgithubusers.akgithubusers.Consts
import okhttp3.OkHttpClient
import SearchQuery
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.response.CustomTypeAdapter
import com.apollographql.apollo.response.CustomTypeValue
import com.szymonkoper.akgithubusers.akgithubusers.model.owner.Owner
import type.CustomType
import java.net.URI
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class ApiClient {
    suspend fun searchOwners(login: String): List<Owner> {
        val query = SearchQuery.builder()
            .searchText(login)
            .build()

        return suspendCoroutine { continuation ->
            apollo.query(query).enqueue(object : ApolloCall.Callback<SearchQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(response: Response<SearchQuery.Data>) {
                    val nodes = response.data()?.search()?.nodes() ?: emptyList()
                    val owners = nodes
                        .mapNotNull { it as? SearchQuery.AsUser }
                        .mapNotNull(::mapNodeToOwner)

                    continuation.resume(owners)
                }
            })
        }
    }

    private fun mapNodeToOwner(node: SearchQuery.AsUser): Owner? {
        return Owner(
            node.login(),
            node.name() ?: "",
            node.avatarUrl().toString(),
            node.repositories().totalCount().toInt()
        )
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/graphql"

        private val okHttpClient by lazy {
            OkHttpClient.Builder()
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor { it.proceed(it.request().newBuilder().header("Authorization", "Bearer ${Consts.GitHubToken}").build()) }
                .build()
        }

        private val apollo by lazy {
            ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .addCustomTypeAdapter(CustomType.URI, uriAdapter)
                .addCustomTypeAdapter(CustomType.DATETIME, dateTimeAdapter)
                .build()
        }

        private val uriAdapter by lazy {
            object : CustomTypeAdapter<URI> {
                override fun decode(value: CustomTypeValue<*>): URI {
                    return URI.create(value.value.toString())
                }

                override fun encode(value: URI): CustomTypeValue<*> {
                    return CustomTypeValue.GraphQLString(value.toString())
                }
            }
        }

        private val dateTimeAdapter = object : CustomTypeAdapter<ZonedDateTime> {
            override fun decode(value: CustomTypeValue<*>): ZonedDateTime {
                val dateFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
                return ZonedDateTime.parse(value.value.toString(), dateFormatter)
            }

            override fun encode(value: ZonedDateTime): CustomTypeValue<*> {
                val dateFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
                return CustomTypeValue.GraphQLString(dateFormatter.format(value))
            }

        }
    }
}