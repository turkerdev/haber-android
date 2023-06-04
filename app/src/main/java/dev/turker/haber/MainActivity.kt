package dev.turker.haber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import dev.turker.haber.article.ArticleList
import dev.turker.haber.ui.theme.HaberAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HaberAppTheme {
                Scaffold(topBar = {
                    TopAppBar(title = {})
                }) { contentPadding ->
                    Box(Modifier.padding(contentPadding)){
                        ArticleList()
                    }
                }
            }
        }
    }
}