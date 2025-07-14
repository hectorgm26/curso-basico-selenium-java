package Escenarios.Facebook;

import Entidades.Facebook.Ent_RegistroFacebook;
import Recursos.Utilidades.Rec_Global;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Esc_RegistroFacebook {

    public static WebDriver driver;

    public Esc_RegistroFacebook(WebDriver _driver) {
        driver = _driver;
    }

    // Los datos que se necesitaran seran los de la entidad y su instancia, sin poner todos los atributos
    public void RegistroFacebook(Ent_RegistroFacebook ent_registro) {

        Rec_Global rec_global = new Rec_Global(driver);

        String btn_crearCuenta = "//*[@data-testid='open-registration-form-button']";

        String inp_nombre = "//*[@name='firstname']";
        String inp_apellido = "//*[@name='lastname']";
        String inp_correo = "//*[@name='reg_email__']";
        String inp_pass = "//*[@id='password_step_input']";

        // Para los xpath de inputs con lista de opciones, se usa el del select
        String list_dia = "//*[@name='birthday_day']";
        String list_anio = "//*[@name='birthday_year']";
        String list_mes = "//*[@name='birthday_month']";

        // Hacemos click en el boton de crear cuenta
        // Espera hasta 10 segundos a que el botón "Crear cuenta nueva" sea visible y clickeable en el DOM
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Una vez que el botón está listo, se hace clic sobre él de forma segura
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_crearCuenta))).click();

        // Primer parametro el texto a escribir, y el segundo el xpath del elemento
        rec_global.Escribir(ent_registro.getNombre(), inp_nombre);
        rec_global.Escribir(ent_registro.getApellido(), inp_apellido);
        rec_global.Escribir(ent_registro.getCorreo(), inp_correo);
        rec_global.Escribir(ent_registro.getClave(), inp_pass);

        rec_global.ListaValor(ent_registro.getDiaNacimiento(), list_dia);
        rec_global.ListaValor(ent_registro.getAnioNacimiento(), list_anio);

        // Para el mes se busca por texto, ya que el value del option no tiene el mismo valor que el texto visible
        rec_global.ListaTexto(ent_registro.getMesNacimiento(), list_mes);

        // Selecciona el género (Mujer, Hombre, o Personalizado)
        SeleccionGenero(ent_registro.getSexo());

        // Solo si el sexo es "Personalizado", se habilitan y completan campos adicionales
        if (!ent_registro.getSexo().equalsIgnoreCase("Mujer") && !ent_registro.getSexo().equalsIgnoreCase("Hombre")) {
            String generoPersonalizado = "//*[@name='custom_gender']";
            rec_global.Escribir(ent_registro.getSexo(), generoPersonalizado);

            String inp_pronombre = "//*[@name='preferred_pronoun']";
            rec_global.ListaContainsTexto(ent_registro.getPronombre(), inp_pronombre);
        }
    }

    // Para el sexo, solo tenemos 3 opciones (una condicion da opciones) - se usan los inputs
    public void SeleccionGenero(String genero) {

        Rec_Global rec_global = new Rec_Global(driver);

        String inp_mujer = "//*[@name='sex' and @value='1']";
        String inp_hombre = "//*[@name='sex' and @value='2']";
        String inp_otro = "//*[@name='sex' and @value='-1']";

        // Dependiendo del genero que se reciba, se hara click en el input correspondiente
        if (genero.equals("Mujer")) {
            rec_global.Click(inp_mujer);
        } else if (genero.equals("Hombre")) {
            rec_global.Click(inp_hombre);
        } else {
            rec_global.Click(inp_otro);
        }
    }
}
