package Ejecuciones;

import Escenarios.demoQa.Esc_DemoQa;
import Recursos.Navegador.Pag_Navegador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class DemoQaModificarPropiedades {

    public static WebDriver driver;
    Esc_DemoQa escDemoQa;

    @BeforeAll
    public static void preEjecucion() {
        Pag_Navegador pag_navegador = new Pag_Navegador(driver);
        driver = pag_navegador.abrirNavegador("https://demoqa.com/elements");
    }

    @AfterAll
    public static void postEjecucion() {
        // driver.close();
    }

    public void instanciasBasicas() {

        lecturaDatos();
        escDemoQa = new Esc_DemoQa(driver);

    }

    public void lecturaDatos() {

    }

    @Test
    public void ModificarElementos() {
        instanciasBasicas();
        escDemoQa.ModificacionElementos();

        try {
            Thread.sleep(2000);
        } catch (Exception fallo) {
            System.err.println("Error al esperar: " + fallo.getMessage());
        }
    }

}
