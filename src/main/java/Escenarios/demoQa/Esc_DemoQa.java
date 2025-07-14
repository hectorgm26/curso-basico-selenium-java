package Escenarios.demoQa;

import Recursos.Utilidades.Rec_Global;
import org.openqa.selenium.WebDriver;

public class Esc_DemoQa {

    public static WebDriver driver;

    public Esc_DemoQa(WebDriver _driver) {
        driver = _driver;
    }

    public void ModificacionElementos() {
        Rec_Global recGlobal = new Rec_Global(driver);

        String div_Elements = "//*[@id=\"app\"]/div/div/div/div[1]/div/div/div[1]/span/div";
        recGlobal.JsModificarAtributo(div_Elements, "style", "background-color: red; color: white;");

        String span_WebTables = "//span[text()='Web Tables']";
        String inp_FiltroTables = "//*[@id=\"searchBox\"]";
        recGlobal.Click(span_WebTables);
        recGlobal.JsRemoverAtributo(inp_FiltroTables, "autocomplete");
        recGlobal.JsRemoverAtributo(inp_FiltroTables, "placeholder");

    }

}
