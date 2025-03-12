package com.example.data.repository

import com.example.data.source.remote.HomeRemoteDataSource
import com.example.data.util.checkResponse

class HomeRepository(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) {
    suspend fun getHomeDto() = checkResponse { homeRemoteDataSource.getHomeDto() }

    suspend fun seeAll(url: String) = checkResponse { homeRemoteDataSource.seeAll(url) }

    suspend fun getPostsFromCategory(categoryId: String) =
        checkResponse { homeRemoteDataSource.getPostsFromCategory(categoryId) }
}
