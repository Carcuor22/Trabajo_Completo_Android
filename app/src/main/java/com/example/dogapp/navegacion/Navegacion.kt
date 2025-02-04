import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogapp.screen.AnimalesScreen
import com.example.dogapp.viewModel.AuthViewModel
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
        startDestination = "pantalla_inicio"
    ) {
        composable("pantalla_inicio") {
            PantallaInicioScreen(
                navegarAPantallaListaPerros = { navController.navigate("animales") },
                navegarALogin = { navController.navigate("login") },
                navegarARegistro = { navController.navigate("register") },
                navegarAResetPassword = { navController.navigate("resetPassword") }
            )
        }

        composable("login") {
            LoginScreen(
                viewModel = authViewModel,
                navegarARegistro = { navController.navigate("register") },
                navegarAResetPassword = { navController.navigate("resetPassword") },
                navegarAHome = { navController.navigate("animales") } // Ahora redirige a la lista de animales
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
    }

}