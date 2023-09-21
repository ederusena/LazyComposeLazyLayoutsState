package br.com.ederu.lazycompose.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ederu.lazycompose.sampledata.sampleSections
import br.com.ederu.lazycompose.ui.screens.HomeScreen
import br.com.ederu.lazycompose.ui.theme.Indigo400
import br.com.ederu.lazycompose.ui.theme.Indigo400Light
import br.com.ederu.lazycompose.ui.theme.LazyComposeLazyLayoutsStateTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyComposeLazyLayoutsStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(onFabClick = {
                        startActivity(Intent(this, ProductFormActivity::class.java))
                    })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(onFabClick: () -> Unit = {}) {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        contentColor = MaterialTheme.colorScheme.onSurface,
                        containerColor = Indigo400,
                        shape = RoundedCornerShape(50),
                        onClick = onFabClick
                    ) {
                        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
                    }
                }
            ) {
                Box(modifier = Modifier.padding(it)){
                    HomeScreen(
                        sampleSections
                    )
                }
            }

        }
    }
}