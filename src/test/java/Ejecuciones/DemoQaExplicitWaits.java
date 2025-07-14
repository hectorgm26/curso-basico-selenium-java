package Ejecuciones;

import Escenarios.demoQa.Esc_DemoQaExplicitWait;
import Recursos.Navegador.Pag_Navegador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class DemoQaExplicitWaits {

    public static WebDriver driver;
    Esc_DemoQaExplicitWait escDemoQaExplicitWait;

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
        escDemoQaExplicitWait = new Esc_DemoQaExplicitWait(driver);

    }

    public void lecturaDatos() {

    }

    @Test
    public void ModificarElementos() {
        instanciasBasicas();
        escDemoQaExplicitWait.EsperarElementosNCantidadTiempo();

    }

}
