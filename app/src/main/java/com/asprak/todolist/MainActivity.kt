package com.asprak.todolist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asprak.todolist.ui.theme.TodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme { 
                Content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content() {
    val context = LocalContext.current

    var counter1 by remember { mutableIntStateOf(0) }
    val counter2 = remember { mutableIntStateOf(0) }
    var isShow by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Todo List")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val explicitIntent = Intent(context, CreateTodoActivity::class.java)
                    context.startActivity(explicitIntent)
                }
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(visible = isShow) {
                Text("Counter value = ${counter2.intValue}")
            }

            Row(modifier = Modifier.padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = {
                    counter1++
                    counter2.intValue++
                }) {
                    Text("Increment")
                }
                Button(onClick = {
                    counter1--
                    counter2.intValue--
                }) {
                    Text("Decrement")
                }
            }

            OutlinedButton(onClick = { isShow = !isShow}) {
                Text(when (isShow) {
                    true -> "Hide Counter"
                    false -> "Show Counter"
                })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ContentPreview() {
    Content()
}