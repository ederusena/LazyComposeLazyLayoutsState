package br.com.ederu.lazycompose.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(searchText: String, onSearchChange: (String) -> Unit) {


    OutlinedTextField(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        value = searchText,
        shape = RoundedCornerShape(30.dp),
        trailingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
        },
        placeholder = {
            Text(text = "Digite o produto")
        },
        label = { Text(text = "Pesquisar") },
        onValueChange = {
            onSearchChange(it)
        }
    )
}