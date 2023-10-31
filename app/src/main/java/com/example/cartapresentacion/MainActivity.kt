package com.example.cartapresentacion


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import com.example.cartapresentacion.ui.theme.CartaPresentacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartaPresentacionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFddc68b)
                ) {
                    Presentacion(
                        centro = "Virgen de la Paloma",
                        estudio = "Desarrollo de Aplicaciones Multiplataforma",
                        "+34 674650411",
                        "Luucasrsz"
                    )
                }
            }
        }
    }
}

@Composable
fun Presentacion(
    centro: String,
    estudio: String,
    telefono: String,
    github: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Titulo()
        Cuerpo(estudio, centro, telefono, github)

    }

}

@Composable
fun Titulo(modifier: Modifier = Modifier) {
    val imagen = painterResource(R.drawable.fotoperfil)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Lucas",
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            fontFamily = FontFamily.Cursive

        )

        Text(
            text = "Rodríguez",
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            fontFamily = FontFamily.Cursive


        )
        Image(
            painter = imagen,
            modifier = modifier.size(300.dp),
            contentDescription = null
        )
    }

}

@Composable
fun Cuerpo(
    estudio: String,
    centro: String,
    telefono: String,
    github: String,
    modifier: Modifier = Modifier
) {
    val logoLinkedin = painterResource(R.drawable.logolinkedin)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = estudio,
            modifier = modifier.padding(bottom = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center

        )

        Text(
            text = centro,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )

        var isExpanded by remember { mutableStateOf(false) }


        Button(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = if (isExpanded) "Información Personal" else "Información Profesional")
        }

        if (isExpanded) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Image(
                        painter = logoLinkedin,
                        modifier = modifier.size(22.dp),
                        contentDescription = null
                    )
                    Text(
                        text = "Lucas Rodríguez",
                        modifier = modifier.padding(start = 15.dp, bottom = 15.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,

                        )
                }
                Row {
                    Icon(Icons.Default.Email, contentDescription = null)
                    Text(
                        text = " lucasrodriguez@gmail.com",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,

                        )
                }

            }

        } else {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Row {
                    Icon(Icons.Default.LocationOn, contentDescription = null)
                    Text(
                        text = " Madrid",
                        modifier = modifier.padding(bottom = 20.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,

                        )

                }
                Row {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)

                    Text(
                        text = " lucas.rsz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,

                        )
                }

            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxSize()
        ) {
            BotonContactar(telefono)
            BotonGitHub(github)

        }

    }


}

@Composable
private fun BotonContactar(telefono: String) {
    val callLink = "tel: $telefono"
    val localContext = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse((callLink))
            startActivity(localContext, intent, null)


        },
    ) {
        Text(
            text = "CONTÁCTAME!",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textDecoration = TextDecoration.Underline,

            )
    }
}

@Composable
private fun BotonGitHub(usuario: String, modifier: Modifier = Modifier) {
    val githubLink = "https://github.com/$usuario"


    val openLink =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { _ ->

        }

    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, githubLink.toUri())
            openLink.launch(intent)
        },
        modifier = modifier
    ) {
        Text(
            text = "GITHUB!",
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            textDecoration = TextDecoration.Underline,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CartaPresentacionTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFddc68b)
        ){
            Presentacion(
                "Virgen de la Paloma",
                "Desarrollo de Aplicaciones Multiplataforma",
                "+34 674650411",
                "Luucasrsz"
            )
        }

    }
}

