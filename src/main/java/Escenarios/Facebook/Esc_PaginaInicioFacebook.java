package Escenarios.Facebook;

import Recursos.Utilidades.Rec_Global;
import org.openqa.selenium.WebDriver;

public class Esc_PaginaInicioFacebook {

    public static WebDriver driver;

    public Esc_PaginaInicioFacebook(WebDriver _driver) {
        driver = _driver;
    }

    public void ConsultarIngresoFacebook(String clave, String usuario) {

        Rec_Global rec_global = new Rec_Global(driver);

        String correo = "//*[@name='email']";
        String pass = "//*[@name='pass']";

        // Primer parametro el texto a escribir, y el segundo el xpath del elemento
        rec_global.Escribir(usuario, correo);
        rec_global.Escribir(clave, pass);
        // rec_global.Click("//*[@type='submit']");
    }
}
