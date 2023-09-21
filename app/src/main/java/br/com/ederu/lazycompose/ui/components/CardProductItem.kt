package br.com.ederu.lazycompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.ederu.lazycompose.R
import br.com.ederu.lazycompose.extensions.toBrazilianCurrency
import br.com.ederu.lazycompose.model.Product
import br.com.ederu.lazycompose.sampledata.sampleProducts
import br.com.ederu.lazycompose.ui.theme.Indigo400Light
import br.com.ederu.lazycompose.ui.theme.LazyComposeLazyLayoutsStateTheme
import coil.compose.AsyncImage
import java.math.BigDecimal


@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .heightIn(150.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Indigo400Light)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            product.description?.let {
                Text(
                    text = product.description,
                    Modifier
                        .padding(16.dp)
                )
            }

        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "Banana",
                    price = BigDecimal("9.99")
                ),
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionPreview() {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "Banana",
                    price = BigDecimal("9.99"),
                    description = "Como que pode o cara querer comprar banana se não existe isso aqui embaixo dagua"
                ),
            )
        }
    }
}