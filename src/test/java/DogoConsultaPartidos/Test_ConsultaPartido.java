package DogoConsultaPartidos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Test_ConsultaPartido {

    static WebDriver driver;
    static String url = "https://www.google.cl/";

    @BeforeAll
    public static void antes() {
        // Instanciamos e inicializamos el puente de Selenium con el navegador
        Rec_Navegador rec_navegador = new Rec_Navegador();
        driver = rec_navegador.abrirNavegador(url); // el driver es igual al driver retornado por el metodo abrirNavegador de la clase Rec_Navegador
    }

    @Test
    public void imprimirResultadoPartido() {
        Pag_ConsultaPartido pag_consultaPartido = new Pag_ConsultaPartido(driver);
        String equipos = "Chile, Argentina, Brasil";
        String[] equipo = equipos.split(",");

        for (String seleccion : equipo) {
            driver.get(url); // Navegamos a la URL antes de cada consulta
            pag_consultaPartido.consultarPartidosMundial(seleccion.trim()); // Llamamos al método para consultar el partido
        }
    }
}
