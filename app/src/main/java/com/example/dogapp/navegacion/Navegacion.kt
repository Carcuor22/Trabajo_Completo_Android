import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogapp.navegacion.VistaPerros
import com.example.dogapp.screen.AuthViewModel
import com.example.dogapp.screen.LoginScreen
import com.example.dogapp.screen.PantallaInicioScreen
import com.example.dogapp.screen.RegisterScreen
import com.example.dogapp.screen.ResetPasswordScreen

@Composable
fun Navegacion() {
    val navController = rememberNavController()
    val authViewModel = AuthViewModel()

    NavHost(
        navController = navController,
        startDestination = VistaPerros
    ) {
        // Pantalla de inicio
        composable(VistaPerros) {
            PantallaInicioScreen(
                navegarAPantallaListaPerros = { navController.navigate("ListaPerros") },
                navegarALogin = { navController.navigate("login") },
                navegarARegistro = { navController.navigate("register") },
                navegarAResetPassword = { navController.navigate("resetPassword") }

            )
        }

        // Pantalla de inicio de sesión
        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                navegarARegistro = { navController.navigate("register") },
                navegarAResetPassword = { navController.navigate("resetPassword") },
                navegarAHome = { navController.navigate(VistaPerros) } // Redirige a Home
            )
        }

        // Pantalla de registro
        composable("register") {
            RegisterScreen(
                viewModel = authViewModel,
                navegarALogin = { navController.navigate("login") }
            )
        }

        // Pantalla de restablecimiento de contraseña
        composable("resetPassword") {
            ResetPasswordScreen(
                viewModel = authViewModel,
                navegarALogin = { navController.navigate("login") }
            )
        }
    }
}
