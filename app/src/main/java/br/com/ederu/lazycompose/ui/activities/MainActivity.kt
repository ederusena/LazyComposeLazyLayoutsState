package br.com.ederu.lazycompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.ederu.lazycompose.sampledata.sampleSections
import br.com.ederu.lazycompose.ui.screens.HomeScreen
import br.com.ederu.lazycompose.ui.theme.LazyComposeLazyLayoutsStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyComposeLazyLayoutsStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()

                }
            }
        }
    }
}

@Composable
fun App() {
    LazyComposeLazyLayoutsStateTheme {
        Surface {
            HomeScreen(
                sampleSections
            )
        }
    }
}