package com.wipro.wiprojetpacktest.domain.use_cases

import com.wipro.wiprojetpacktest.core.common.Resource
import com.wipro.wiprojetpacktest.domain.model.product.ProductItem
import com.wipro.wiprojetpacktest.domain.repository.GetProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryWiseProductUseCase @Inject constructor(private val getProductRepository: GetProductRepository) {

    operator fun invoke(categoryName: String): Flow<Resource<List<ProductItem>>> = flow {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success(
                    data = getProductRepository.getCategoryWiseProductList(
                        categoryName
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

}