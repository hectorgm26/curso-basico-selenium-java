package Ejecuciones;

import Entidades.Facebook.Ent_RegistroFacebook;
import Escenarios.Facebook.Esc_RegistroFacebook;
import Recursos.Navegador.Pag_Navegador;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class RegistroFacebook {

    // AL HACERLO ESTATICO, permitimos que quede guardado en memoria, y no tengamos que instanciarlo cada vez que se necesite
    public static WebDriver driver;

    Ent_RegistroFacebook ent_registro; // creamos solamente las instancia
    Esc_RegistroFacebook esc_Registrof;

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
        esc_Registrof = new Esc_RegistroFacebook(driver);
        ent_registro = new Ent_RegistroFacebook();
        lecturaDatos();

    }

    // Darle valor a las entidades
    public void lecturaDatos() {
        ent_registro.setNombre("Juan");
        ent_registro.setApellido("Perez");
        ent_registro.setCorreo("uwu@uwu.cl");
        ent_registro.setClave("12345678ggg33+");
        ent_registro.setDiaNacimiento("16");
        ent_registro.setMesNacimiento("sep");
        ent_registro.setAnioNacimiento("1997");
        ent_registro.setSexo("Gatito");
        ent_registro.setPronombre("Neutro");
    }

    @Test
    public void RealizarRegistroIngreso() {
        instanciasBasicas(); // llamamos al metodo que inicializa las instancias de la entidad y escenario

        // llamamos a la entidad
        esc_Registrof.RegistroFacebook(ent_registro);

        try {
            Thread.sleep(2000);
        } catch (Exception fallo) {
            System.err.println("Error al esperar: " + fallo.getMessage());
        }
    }

}
