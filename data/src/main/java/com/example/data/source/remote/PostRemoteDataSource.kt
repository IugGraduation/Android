package com.example.data.source.remote

import com.example.data.model.response.ApiResponseDto
import com.example.data.model.response.PostItemDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PostRemoteDataSource {


//    @GET("products")
//    suspend fun getProducts(@Query("limit") amount: Int? = null): Response<List<ProductResponse>>

    @GET("post/{post_id}")
    suspend fun getPost(@Path("post_id") postId: String): Response<ApiResponseDto<PostItemDto>>

    @Multipart
    @POST("post/store")
    suspend fun addPost(
        @Part images: List<MultipartBody.Part>?,
        @Part("name") name: RequestBody,
        @Part("place") place: RequestBody,
        @Part("details") details: RequestBody,
        @Part("category_uuid") categoryUuid: RequestBody,
        @Part fcategory: List<MultipartBody.Part>?
    ): Response<ApiResponseDto<Any>>

    @POST("post/update")
    suspend fun updatePost(@Body body: PostItemDto): Response<ApiResponseDto<Any>>

    @DELETE("post/{post_id}/delete")
    suspend fun deletePost(@Path("post_id") postId: String): Response<ApiResponseDto<Any>>

}