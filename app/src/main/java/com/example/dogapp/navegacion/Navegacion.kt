import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogapp.screen.AnimalesScreen
import com.example.dogapp.viewModel.AuthViewModel
import com.example.dogapp.screen.LoginScreen
import com.example.dogapp.screen.PantallaInicioScreen
import com.example.dogapp.screen.RegisterScreen
import com.example.dogapp.screen.ResetPasswordScreen
import com.example.dogapp.screen.PantallaFirestoreUsuarios
import com.example.dogapp.viewModel.FirestoreViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dogapp.screen.AgregarMascotaScreen
import com.example.dogapp.screen.AgregarUsuarioScreen
import com.example.dogapp.screen.BorrarMascotaScreen
import com.example.dogapp.screen.BorrarUsuarioScreen
import com.example.dogapp.screen.BuscarMascotaScreen
import com.example.dogapp.screen.BuscarUsuarioScreen
import com.example.dogapp.screen.EditarMascotaScreen
import com.example.dogapp.screen.EditarUsuarioScreen
import com.example.dogapp.screen.PantallaInicioPostLogin
import com.example.dogapp.screen.PantallaMascotas
import com.example.dogapp.viewModel.AuthViewModelFactory

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(LocalContext.current.applicationContext as Application))
    val firestoreViewModel: FirestoreViewModel = viewModel()




    NavHost(
        navController = navController,
        startDestination = "pantalla_inicio"
    ) {
        composable("pantalla_inicio") {
            PantallaInicioScreen(
                navegarALogin = { navController.navigate("login") },
                navegarARegistro = { navController.navigate("register") },
                navegarAResetPassword = { navController.navigate("resetPassword") },
                navegarAGestionFirestore = { navController.navigate("firestore") }
            )
        }

        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                navegarARegistro = { navController.navigate("register") },
                navegarAResetPassword = { navController.navigate("resetPassword") },
                navegarAHome = {
                    navController.navigate("inicio_post_login") {
                        popUpTo("login") { inclusive = true } // Evita que el usuario regrese a login
                    }
                }
            )
        }

        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                navegarALogin = { navController.navigate("login") }
            )
        }

        composable("resetPassword") {
            ResetPasswordScreen(
                viewModel = authViewModel,
                navegarALogin = { navController.navigate("login") }
            )
        }

        composable("animales") {
            AnimalesScreen()
        }

        composable("inicio_post_login") {
            PantallaInicioPostLogin(navController)  // Nueva pantalla de inicio con botones
        }

        // 🔹 Pantalla de Firestore (CRUD de la base de datos)
        composable(route = "usuarios") {
            PantallaFirestoreUsuarios(navController = navController, viewModel = firestoreViewModel)
        }

        composable("agregar_usuario") {
            AgregarUsuarioScreen(navController = navController)
        }

        composable("borrar_usuario") {
            BorrarUsuarioScreen(navController = navController)
        }

        composable("editar_usuario") {
            EditarUsuarioScreen(navController = navController)
        }

        composable("buscar_usuario") {
            BuscarUsuarioScreen(navController = navController)
        }

        composable(route = "mascotas") {
            PantallaMascotas(navController = navController, viewModel = firestoreViewModel)
        }

        composable("agregar_mascota") {
            AgregarMascotaScreen(navController = navController)
        }

        composable("borrar_mascota") {
            BorrarMascotaScreen(navController = navController)
        }

        composable("editar_mascota") {
            EditarMascotaScreen(navController = navController)
        }

        composable("buscar_mascota") {
            BuscarMascotaScreen(navController = navController)
        }


    }
}
