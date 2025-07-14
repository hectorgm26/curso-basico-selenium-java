package Ejecuciones;

import Entidades.Facebook.Ent_LoginFacebook;
import Escenarios.Facebook.Esc_PaginaInicioFacebook;
import Recursos.Navegador.Pag_Navegador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class PruebaFacebook {

    static WebDriver driver;
    Ent_LoginFacebook entLoginf; // creamos solamente las instancia
    Esc_PaginaInicioFacebook escPaginaInicio;

    @BeforeAll
    public static void preEjecucion() {
        Pag_Navegador pag_navegador = new Pag_Navegador(driver);
        driver = pag_navegador.abrirNavegador("https://www.facebook.com/?locale=es_LA");
    }

    @AfterAll
    public static void postEjecucion() {
        // driver.close();
    }

    public void instanciasBasicas() {
        entLoginf = new Ent_LoginFacebook(); // inicializamos la instancia de la entidad como parametro

        lecturaDatos();

        escPaginaInicio = new Esc_PaginaInicioFacebook(driver); // para poder usar la clave y el usuario
    }

    public void lecturaDatos() {
        entLoginf.setClave("123");
        entLoginf.setUsuario("usuarioActualizado");
    }

    @Test
    public void RealizarConsultaIngreso() {
        instanciasBasicas(); // llamamos al metodo que inicializa las instancias de la entidad y escenario
        escPaginaInicio.ConsultarIngresoFacebook(entLoginf.getClave().toString(), entLoginf.getUsuario().toString());

        try {
            Thread.sleep(2000);
        } catch (Exception fallo) {
            System.err.println("Error al esperar: " + fallo.getMessage());
        }
    }

}
