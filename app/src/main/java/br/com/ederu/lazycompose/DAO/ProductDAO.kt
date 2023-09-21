package br.com.ederu.lazycompose.DAO

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import br.com.ederu.lazycompose.model.Product
import br.com.ederu.lazycompose.sampledata.sampleProducts

class ProductDAO {
    companion object {
        private val products = mutableStateListOf<Product>(
            *sampleProducts.toTypedArray()
        )
    }

    fun products() = products.toList()
    fun save(product: Product) {
        products.add(product)
    }
}