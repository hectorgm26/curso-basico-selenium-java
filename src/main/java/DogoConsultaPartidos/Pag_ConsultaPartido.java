package DogoConsultaPartidos;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Pag_ConsultaPartido {

    WebDriver driver;

    public Pag_ConsultaPartido(WebDriver _driver) {
        this.driver = _driver;
    }

    public void consultarPartidosMundial(String equipo) {

        Rec_Global rec_global = new Rec_Global(driver);
        String inpBarraBusqueda = "//*[@name='q']";
        String txtSeleccion = "/html/body/div[3]/div/div[12]/div[1]/div[2]/div[2]/div/div/div[1]/div/div[5]/block-component/div/div[1]/div/div/div/div[1]/div/div/div/div/div/div/div[1]/div/div/div[1]/div/div[2]/div[2]";

        rec_global.Escribir("Resultado ultimo partido " + equipo, inpBarraBusqueda);

        // Simula la pulsación de la tecla Control + A para seleccionar el texto completo de la barra de búsqueda
        rec_global.EscribirTeclaControl("A", inpBarraBusqueda);

        // y luego se simula la pulsación de la tecla Enter para enviar la búsqueda
        rec_global.EscribirTeclaEspecial(Keys.ENTER, inpBarraBusqueda);

        // Esta espera explicita asegura que el elemento del xpath este completamente cargado antes de intentar acceder a él
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(txtSeleccion)));

        String nombreSeleccion = rec_global.getText(txtSeleccion);
        System.out.println("Nombre de la selección: " + nombreSeleccion);
    }
}
