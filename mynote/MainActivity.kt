package com.example.mynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.Room

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTaskApp()
        }
    }
}
@Composable
fun MyTaskApp() {
    val navController = rememberNavController()
    var searchQuery by remember { mutableStateOf("") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val viewModel: TaskViewModel = viewModel()

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        if (currentRoute == "home") {
            Text(
                text = "TaskApp",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            )
        }
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            if (currentRoute == "home") {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    placeholder = { Text("Search your tasks...") },
                    modifier = Modifier.weight(1f)
                )
            }

        }
        Scaffold(
            bottomBar = {
                BottomBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = ("home"),
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") {
                    HomeScreen(navController = navController, viewModel = viewModel)
                }
                composable("task") {
                    ListScreen(navController = navController, viewModel = viewModel)
                }
                composable("add") {
                    AddTaskScreen(navController, viewModel = viewModel)
                }
                composable("detail") {
                    DetailScreen(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}
@Composable
fun HomeScreen(navController: NavController, viewModel: TaskViewModel) {
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(viewModel.taskList) { task ->
            TaskCard(task, navController = navController, viewModel = viewModel)
        }
    }
}
@Composable
fun TaskCard(task: Task, navController: NavController, viewModel: TaskViewModel) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                viewModel.selectTask(task)
                navController.navigate("detail") },
        shape = RoundedCornerShape(16.dp),
        elevation  = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            Text(text = task.description,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
                )
            Text(text = "Status: ${if (task.isCompleted) "Completed" else "Pending"}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
                )
        }
    }
}
@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Row(
        modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 40.dp)

    ) {
        BottomNavItem(icon = Icons.Default.Home,
            label = "Home",
            onClick = { navController.navigate("home") },
            selected = currentRoute == "home")
        Spacer(modifier = Modifier.weight(1f))

        BottomNavItem(icon = Icons.Default.Add,
            label = "Add",
            onClick = { navController.navigate("add") },
            selected = currentRoute == "add")
        Spacer(modifier = Modifier.weight(1f))

        BottomNavItem(icon = Icons.Default.List,
            label = "List",
            onClick = { navController.navigate("task") },
            selected = currentRoute == "task")

    }
}
@Composable
fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
            .size(60.dp),
           // .background(if (selected) Color.Gray else Color.Transparent),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected)Color.Black else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = if (selected)Color.Black else Color.Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}