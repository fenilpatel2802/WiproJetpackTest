package com.wipro.wiprojetpacktest.domain.repository

interface GetCategoryRepository {

    suspend fun getCategory(): List<String>

}