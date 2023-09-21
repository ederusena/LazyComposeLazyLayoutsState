package br.com.ederu.lazycompose.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ederu.lazycompose.R
import br.com.ederu.lazycompose.model.Product
import br.com.ederu.lazycompose.ui.theme.LazyComposeLazyLayoutsStateTheme
import coil.compose.AsyncImage
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyComposeLazyLayoutsStateTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen() {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val context = LocalContext.current

        var url by remember { mutableStateOf("") }
        var isUrlError by remember {
            mutableStateOf(false)
        }

        var name by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        val formatterPrice = remember { DecimalFormat("#.##") }
        var modifierTextField = Modifier.fillMaxWidth()
        Spacer(modifier = Modifier)
        Text(
            text = "Criando o produto",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }
        TextField(
            modifier = modifierTextField,
            value = url,
            isError = isUrlError,
            onValueChange = {
                isUrlError = try {
                    if (url.isBlank()) {
                        url = it
                    }
                    false
                } catch (e: IllegalArgumentException) {
                    it.isNotEmpty()
                }
                url = it
            },
            label = {
                Text(text = "Url da imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
        )
        if (isUrlError) {
            Text(
                text = "Url deve ser preenchida",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        TextField(
            modifier = modifierTextField,
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = "Nome do produto")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            ),
        )
        TextField(
            modifier = modifierTextField,
            value = price,
            onValueChange = {
                try {
                    price = formatterPrice.format(BigDecimal(it))
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        price = it
                    }
                }
            },
            label = {
                Text(text = "Preço do produto")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
        )
        TextField(
            modifier = modifierTextField.heightIn(min = 100.dp),
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text(text = "Descrição do produto")
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val convertedPrice = try {
                    BigDecimal(price)

                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        context,
                        "Valor preço incorreto",
                        Toast.LENGTH_LONG
                    ).show()
                    BigDecimal.ZERO
                }
                val product = Product(
                    name = name,
                    image = url,
                    price = convertedPrice,
                    description = description
                )

                Log.i("ProductformActivity", "ProductFormScreen: $product")
            }) {
            Text(text = "Salvar")
            Spacer(modifier = Modifier)
        }
    }
}

@Preview
@Composable
fun ProductFormScreenPreview() {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            ProductFormScreen()
        }
    }
}
