package com.example.mynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNoteApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNoteApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val viewModel: NoteViewModel = viewModel()


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = currentRoute != "Search" && currentRoute != "Create Notes"
                && currentRoute != "Note Details",
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "My Notes",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                NavigationDrawerItem(
                    selected = currentRoute == "All Notes",
                    label = { Text(text = "All Notes") },
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            if (currentRoute != "All Notes") navController.navigate("All Notes")
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Email, contentDescription = null) }
                )
                Spacer(modifier = Modifier.height(12.dp))

                NavigationDrawerItem(
                    selected = currentRoute == "Settings",
                    label = { Text(text = "Settings") },
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            if (currentRoute != "Settings") navController.navigate("Settings")
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
                )
                Spacer(modifier = Modifier.height(12.dp))

                NavigationDrawerItem(
                    selected = currentRoute == "Trash",
                    label = { Text(text = "Trash") },
                    onClick = {
                        scope.launch {
                            drawerState.close()
                            if (currentRoute != "Trash") navController.navigate("Trash")
                        }
                    },
                    icon = { Icon(imageVector = Icons.Default.Delete, contentDescription = null) }
                )
            }
        }
    ) {
        Column {
            if (currentRoute != "Search" && currentRoute != "Create Notes"
                && currentRoute != "Note Details") {
                Text(
                    text = "My Notes",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 100.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            Scaffold(
                topBar = {
                    if (currentRoute != "Search" && currentRoute != "Create Notes"
                        && currentRoute != "Note Details") {
                        TopAppBar(
                            title = { Text(text = "") },
                            actions = {
                                IconButton(onClick = {
                                    scope.launch { drawerState.close() }
                                    navController.navigate("Search")
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Default.MoreVert,
                                        contentDescription = "More",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu",
                                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                                    )
                                }
                            }
                        )
                    }
                },
                floatingActionButton = {
                    if (currentRoute == "All Notes") {
                        FloatingActionButton(
                            onClick = { navController.navigate("Create Notes") },
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(bottom = 35.dp, end = 16.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = "All Notes",
                    modifier = Modifier.padding(padding)
                ) {
                    composable("All Notes") {
                        AllNotesScreen(notesList = viewModel.notes, navController = navController, viewModel = viewModel)
                    }
                    composable("Settings") {
                        Text("Settings Screen", modifier = Modifier.padding(16.dp))
                    }
                    composable("Trash") {
                        Text("Trash Screen", modifier = Modifier.padding(16.dp))
                    }
                    composable("Search") {
                        SearchScreen(navController = navController)
                    }
                    composable("Create Notes") {
                        CreateNoteScreen(navController = navController, viewModel = viewModel)
                    }
                    composable("Note Details") {
                        NoteDetailScreen(viewModel = viewModel, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun AllNotesScreen(notesList: List<Note>, navController: NavController, viewModel: NoteViewModel) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(notesList) { note ->
            NoteCard(title = note.title, viewModel = viewModel,
                navController = navController, note = note)
        }
    }
}

@Composable
fun NoteCard(title: String, viewModel: NoteViewModel, navController: NavController, note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.selectedNote(note)
            navController.navigate("Note Details") }
            .height(90.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, top = 13.dp)
        )
        Text(
        text = note.content,
        fontSize = 16.sp,
        modifier = Modifier.padding(start = 16.dp, top = 13.dp)
        )
        Text(
            text = note.date,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp, top = 13.dp)
        )
    }
}