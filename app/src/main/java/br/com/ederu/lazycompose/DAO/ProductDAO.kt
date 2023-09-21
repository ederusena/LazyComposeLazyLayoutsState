package br.com.ederu.lazycompose.DAO

import br.com.ederu.lazycompose.sampledata.sampleProducts

class ProductDAO {
    companion object {
        private val products = sampleProducts.toMutableList()
    }

    fun products() = products.toList()
}