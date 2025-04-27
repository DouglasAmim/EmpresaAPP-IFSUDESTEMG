package com.doulgasamim.trabalhoavaliativo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.VisualTransformation
import android.content.Intent
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaLogin()
        }
    }
}

@Composable
fun TelaLogin() {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "App da Empresa", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") },
                    visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val imagem = if (senhaVisivel) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                            Icon(imageVector = imagem, contentDescription = null)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    if (email.isBlank() || senha.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Campo de e-mail ou senha não pode ficar vazio")
                        }
                    } else if (email == "heudes@heudes.com" && senha == "123") {
                        scope.launch {
                            snackbarHostState.showSnackbar("Login realizado com sucesso!")
                        }
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar("Login inválido!")
                        }
                    }
                }) {
                    Text("Entrar")
                }

                Spacer(modifier = Modifier.height(10.dp))
                TextButton(onClick = {
                    context.startActivity(Intent(context, CadastroActivity::class.java))
                }) {
                    Text("Cadastrar")
                }

            }
        }
    }

}