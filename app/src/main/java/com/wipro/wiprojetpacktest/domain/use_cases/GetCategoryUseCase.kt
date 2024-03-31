package com.wipro.wiprojetpacktest.domain.use_cases

import com.wipro.wiprojetpacktest.core.common.Resource
import com.wipro.wiprojetpacktest.domain.repository.GetCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val categoryRepository: GetCategoryRepository) {

    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = categoryRepository.getCategory()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}