package com.rodriguezmauro.jetpackcomposeinstagram.login.data.network

import com.rodriguezmauro.jetpackcomposeinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/531397d0-10f8-4565-9757-49db1cf8e422")
    suspend fun doLogin(): Response<LoginResponse>
}