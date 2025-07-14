package Ejecuciones;

import Escenarios.Mvn.Esc_ConsultarLibrerias;
import Recursos.Navegador.Pag_Navegador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class BusquedaGoogle {

    // es estatico para que se pueda utilizar en los metodos estaticos de JUnit para el setup
    static WebDriver driver;

    @BeforeAll
    public static void preEjecucion() {
        // Instancia de la clase Pag_Navegador que maneja el navegador y el webdriver con sus metodos
        Pag_Navegador pag_navegador = new Pag_Navegador(driver);

        // almacenamos el valor del driver retornado por el metodo abrirNavegador, para luego utilizarlo en los test
        driver = pag_navegador.abrirNavegador("https://mvnrepository.com/");
    }

    @AfterAll
    public static void postEjecucion() {
        driver.close();
    }

    @Test
    public void RealizarConsultaGoogle() {
        Esc_ConsultarLibrerias escConsultarLibrerias = new Esc_ConsultarLibrerias(driver);
        escConsultarLibrerias.ConsultarLibreriaMvn(driver);

        try {
            // Tiempo en que el navegador se quedara estatico
            Thread.sleep(2000);
        } catch (Exception fallo) {
            System.err.println("Error al esperar: " + fallo.getMessage());
        }

    }

}
