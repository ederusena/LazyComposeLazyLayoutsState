package br.com.ederu.lazycompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.ederu.lazycompose.model.Product
import br.com.ederu.lazycompose.sampledata.sampleProducts
import br.com.ederu.lazycompose.sampledata.sampleSections
import br.com.ederu.lazycompose.ui.components.CardProductItem
import br.com.ederu.lazycompose.ui.components.ProductsSection
import br.com.ederu.lazycompose.ui.components.SearchTextField
import br.com.ederu.lazycompose.ui.theme.LazyComposeLazyLayoutsStateTheme

class HomeScreenUiState(searchText: String = "") {
    var textInputSearch: String by mutableStateOf("")
        private set

    val filteredProducts: List<Product> get() =
        if (textInputSearch.isNotBlank()) {
            sampleProducts.filter { product ->
                product.name
                    .contains(textInputSearch, ignoreCase = true) ||
                        product.description?.contains(textInputSearch, ignoreCase = true) ?: false
            }
        } else emptyList()

    fun isShowSection(): Boolean {
        return textInputSearch.isBlank()
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        textInputSearch = searchText
    }
}
@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val text = state.textInputSearch
        val searchedProducts = remember(text) {
            state.filteredProducts
        }

        SearchTextField(
            searchText = text,
            onSearchChange = state.onSearchChange
        )

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSection()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(title = title, products = products)
                    }
                }
            } else {
                items(searchedProducts) {
                    CardProductItem(
                        product = it,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithTextSearchPreview() {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            HomeScreen(
                sampleSections,
                state = HomeScreenUiState(searchText = "a")
            )
        }
    }
}