package dev.turker.haber.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import dev.turker.haber.ApiService
import dev.turker.haber.NewComment
import dev.turker.haber.ui.theme.Shapes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentWrite(articleId:String, refreshComments: ()->Unit){
    var comment by remember { mutableStateOf("") }
    var focusManager = LocalFocusManager.current

    fun post(){
        CoroutineScope(Dispatchers.IO).launch {
            ApiService().article.postComment(NewComment(articleId,comment)).execute()
            comment = ""
            refreshComments()
            focusManager.clearFocus()
        }
    }

    Column {
        TextField(
            value = comment,
            onValueChange = {comment=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                .shadow(elevation = 3.dp, shape = Shapes.medium),
            label = {
                Text("Make a comment")
            },
            trailingIcon = {
            IconButton(
                onClick = {post()},
                enabled = comment.isNotEmpty(),
                modifier = Modifier.padding(end = 4.dp)) {
                    Icon(Icons.Filled.Send,"")
            }
        })
    }
}
