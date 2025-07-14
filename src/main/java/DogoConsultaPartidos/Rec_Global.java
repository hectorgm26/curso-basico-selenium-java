package DogoConsultaPartidos;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Rec_Global {

    WebDriver driver;

    public Rec_Global(WebDriver _driver) {
        this.driver = _driver;
    }

    public void Escribir(String texto, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(texto);
    }

    // Este metodo permite simular la pulsación de una tecla de control (como Enter, Tab, etc.)
    public void EscribirTeclaControl(String tecla, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL, tecla);
    }

    // Este metodo permite obtener el texto de un elemento web utilizando su xpath
    public String getText(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public void EscribirTeclaEspecial(Keys tecla, String xpath) {
        driver.findElement(By.xpath(xpath)).sendKeys(tecla);
    }

}
