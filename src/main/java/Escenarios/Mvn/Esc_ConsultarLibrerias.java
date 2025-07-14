package Escenarios.Mvn;

import Recursos.Utilidades.Rec_Global;
import org.openqa.selenium.WebDriver;

public class Esc_ConsultarLibrerias {

    WebDriver driver;

    public Esc_ConsultarLibrerias(WebDriver _driver) {
        this.driver = _driver;
    }

    public void ConsultarLibreriaMvn(WebDriver _driver) {

        Rec_Global rec_global = new Rec_Global(_driver);
        rec_global.Escribir("Selenium", "//*[@id=\"query\"]");
        rec_global.Click("//*[@type='submit']");
    }
}
