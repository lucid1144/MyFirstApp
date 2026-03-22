package com.example.mynote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateNoteScreen(navController: NavController, viewModel: NoteViewModel) {
    val title = remember { mutableStateOf("") }
    val notes = remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "ArrowBack"
                )
            }
            OutlinedTextField(
                value = title.value,
                onValueChange = { title.value = it },
                placeholder = { Text("Title", color = Color.LightGray, fontSize = 20.sp)},
                modifier = Modifier.width(200.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )

            }
            IconButton(onClick = {
                if (title.value.isNotEmpty() && notes.value.isNotEmpty()) {
                    val newNote = Note(
                        id = viewModel.notes.size + 1,
                        title = title.value,
                        content = notes.value,
                        date = "Today",
                        description = ""
                    )
                    viewModel.addNote(newNote)
                    navController.popBackStack()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Done",
                )
            }
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        Divider()

        TextField(
            value = notes.value,
            onValueChange = { notes.value = it },
            placeholder = { Text("Compose Note...", color = Color.LightGray, fontSize = 20.sp,
                modifier = Modifier.height(300.dp)) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent)
        )



    }
}