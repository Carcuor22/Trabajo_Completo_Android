import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.dogapp.navegacion.ListaPerros
import com.example.dogapp.navegacion.PantallaDetallePerro
import com.example.dogapp.navegacion.VistaPerros
import com.example.dogapp.screen.PantallaDetallePerroScreen
import com.example.dogapp.screen.PantallaInicioScreen
import com.example.dogapp.screen.PantallaListaPerrosScreen
import com.example.dogapp.screen.PantallaListaPerrosViewModel


@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = VistaPerros
    ) {

        composable<VistaPerros> {
            PantallaInicioScreen(
                navegarAPantallaListaComidas = {
                    navController.navigate(ListaPerros)
                }
            )
        }
        composable<ListaPerros> {
            val viewModel = PantallaListaPerrosViewModel()
            PantallaListaPerrosScreen(
                viewModel = viewModel,
                navigateToDetalle = { perroId ->
                    navController.navigate(PantallaDetallePerro(id))
                },
                navigateToInicio = {
                    navController.navigate(VistaPerros) {
                        popUpTo(VistaPerros) { inclusive = true }
                    }
                }
            )
        }

        composable<PantallaDetallePerro> { backStackEntry ->
            val perroId = backStackEntry.toRoute<PantallaDetallePerro>().id

            PantallaDetallePerroScreen(
                perroId = perroId
            ) {
                navController.navigate(ListaPerros) {
                    popUpTo(ListaPerros) { inclusive = true }
                }
            }
        }



    }
}


