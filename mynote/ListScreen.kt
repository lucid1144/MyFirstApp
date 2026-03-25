package com.example.mynote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController, viewModel: TaskViewModel) {
    val completedTasks = viewModel.taskList.filter { it.isCompleted }
    Column(
        modifier = Modifier.padding(start = 16.dp)
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }
        Box (
            modifier = Modifier.fillMaxSize(),
        ) {
            if (completedTasks.isEmpty()) {
                Text(
                    text = "No completed tasks yet!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)

                    )
            } else {
                LazyColumn {
                    items(completedTasks) { task ->
                        TaskCard(
                            task = task, navController = navController,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}