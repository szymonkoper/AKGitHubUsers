package com.szymonkoper.akgithubusers.akgithubusers.model

data class Owner(
    val login: String,
    val name: String,
    val avatarUrl: String, // Uri?
    val totalRepos: Int
)
