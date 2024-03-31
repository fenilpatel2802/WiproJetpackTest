package com.wipro.wiprojetpacktest.data.repository

import com.wipro.wiprojetpacktest.data.remote.network.ApiService
import com.wipro.wiprojetpacktest.domain.repository.GetCategoryRepository
import javax.inject.Inject

class GetCategoryRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    GetCategoryRepository {

    override suspend fun getCategory(): List<String> {
        return apiService.getCategoryList()
    }

}