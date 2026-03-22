package com.example.mynote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(viewModel: NoteViewModel, navController: NavController) {
    val note = viewModel.selectedNote

    Column {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "ArrowBack"
                )
            }
            Spacer(modifier = Modifier.weight(9f))
            IconButton(onClick = { navController.navigate("Create Notes") }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {
                if (note != null) {
                    viewModel.deleteNote(note)
                    navController.popBackStack()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
        Divider()

        if (note != null) {
            Text("Title: ${note.title}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp))
            Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Text("Description: ${note.description}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp))
            Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Text("Content: ${note.content}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp))
            Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Text("Date: ${note.date}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp))
            Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
        } else {
            Text("No note selected")
        }

    }
}