package com.example.postrequest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.postrequest.api.JobApiInstance
import com.example.postrequest.api.User
import com.example.postrequest.ui.theme.PostRequestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var name by remember {
                mutableStateOf("")
            }
            var job by remember {
                mutableStateOf("")
            }
            PostRequestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Form(name, job, { name = it }, { job = it }, Modifier) {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                val user = User(name, job)
                                val response = JobApiInstance.jobApi.sendUserData(user)
                                Log.d("DEBUG_ANKIT", "onCreate: ${response.body()}")
                            } catch (e: Exception) {
                                Log.d("DEBUG_ANKIT", "onCreate: ${e.localizedMessage}")
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Form(
    name: String,
    job: String,
    nameUpdateHandler: (String) -> Unit,
    jobUpdateHandler: (String) -> Unit,
    modifier: Modifier = Modifier,
    onButtonClickListener: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            value = name,
            onValueChange = { nameUpdateHandler(it) },
            label = { Text(text = "User Name") })
        OutlinedTextField(
            value = job,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            onValueChange = { jobUpdateHandler(it) },
            label = { Text(text = "Job title") })
        OutlinedButton(
            onClick = { onButtonClickListener() },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small
        ) {
            Text(text = "Submit", fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PostRequestTheme {
        Form("", "", {}, {}, Modifier, {})
    }
}