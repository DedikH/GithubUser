package com.example.restaurantreview.data.retrofit

import com.example.restaurantreview.data.responses.FollowersDataItem
import com.example.restaurantreview.data.responses.FollowingDataItem
import com.example.restaurantreview.data.responses.ResponseDetail
import com.example.restaurantreview.data.responses.ResponseUsers
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("users?")
    fun getUsers(
        @Query("q") users: String
    ): Call<ResponseUsers>
}
interface ApiServiceFavorite {
    @GET("users?")
    fun getUsers(
        @Query("q") users: String
    ): Call<ResponseUsers>
}
interface ApiDetail {
    @GET("users/{username}")
    fun getUsersDetail(
        @Path("username") users: String
    ): Call<ResponseDetail>
}
    interface ApiFollowing{
        @GET("users/{username}/following")
        fun getFollowing(
            @Path("username") users: String?
        ): Call<List<FollowingDataItem>>
}
interface ApiFollowers{
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") users: String?
    ): Call<List<FollowersDataItem>>
}