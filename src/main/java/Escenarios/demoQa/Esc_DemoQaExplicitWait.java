package Escenarios.demoQa;

import Recursos.Utilidades.Rec_Global;
import org.openqa.selenium.WebDriver;

public class Esc_DemoQaExplicitWait {

    public static WebDriver driver;

    public Esc_DemoQaExplicitWait(WebDriver _driver) {
        driver = _driver;
    }

    public void EsperarElementosNCantidadTiempo() {
        Rec_Global recGlobal = new Rec_Global(driver);

        String div_PracticeForm = "/html/body/div[2]/div/div/div/div[1]/div/div/div[2]/div/ul/li/span";

        recGlobal.waitExplicitClick(div_PracticeForm, 5);

        recGlobal.JsModificarAtributo(div_PracticeForm, "style", "background-color: red; color: white;");


    }

}
