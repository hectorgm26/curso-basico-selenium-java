package Recursos.Utilidades;

import Recursos.Evidencias.Rec_Evidencias;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Rec_Global {

    WebDriver driver;

    public Rec_Global(WebDriver _driver) {
        this.driver = _driver;
    }

    public void Click(String xpath) {

        Rec_Evidencias recEvidencias = new Rec_Evidencias(driver);

        try {
            driver.findElement(By.xpath(xpath)).click();


            recEvidencias.ResaltarElementos(xpath, "r");
            recEvidencias.ResaltarElementos(xpath, "n");

        } catch (Exception e) {
            WebElement elemento = driver.findElement(By.xpath(xpath));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", elemento);
            recEvidencias.ResaltarElementos(xpath, "n");
        }
    }

    public void Escribir(String texto, String xpath) {
        driver.findElement(By.xpath(xpath)).clear(); // Limpiar el campo antes de escribir
        driver.findElement(By.xpath(xpath)).sendKeys(texto);
    }

    public void ListaValor(String valor, String xpath) {
        Select lista = new Select(driver.findElement(By.xpath(xpath)));
        lista.selectByValue(valor); // se hara referencia al valor que contenga mi elemento (se usa en inputs select con options)
    }

    public void ListaTexto(String valor, String xpath) {
        Select lista = new Select(driver.findElement(By.xpath(xpath)));
        lista.selectByVisibleText(valor);
    }

    // Método que selecciona una opción dentro de un <select> cuyo texto visible contenga
    // una parte del texto que se pasa como parámetro
    public void ListaContainsTexto(String textoParcial, String xpath) {
        Select lista = new Select(driver.findElement(By.xpath(xpath)));
        boolean encontrado = false;

        // Recorre todas las opciones disponibles dentro del <select>
        for (WebElement opcion : lista.getOptions()) {
            // Obtiene el texto visible de la opción,
            // lo limpia de espacios y lo convierte a minúsculas para comparar sin sensibilidad a mayúsculas
            String texto = opcion.getText().trim().toLowerCase();

            // Verifica si el texto de esta opción contiene el texto parcial, también en minúsculas
            if (texto.contains(textoParcial.toLowerCase())) {
                // Si encuentra coincidencia, selecciona esa opción usando el texto completo exacto
                lista.selectByVisibleText(opcion.getText());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new NoSuchElementException("No se encontró ninguna opción que contenga: " + textoParcial);
        }
    }


    // ****************** MODIFICACION DE ATRIBUTOS CON JAVASCRIPT ******************

    public void JsModificarAtributo(String xpath, String atributo, String variable) {
        WebElement elemento = driver.findElement(By.xpath(xpath));
        // Castea el WebDriver a JavascriptExecutor, lo que permite ejecutar scripts JS en el contexto de la página cargada
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Ejecuta un script JavaScript que modifica el atributo del elemento HTML en la página
        // - "arguments[0]" es una forma de referirse al primer parámetro que pasamos después de la coma (en este caso, "elemento")
        // - setAttribute(atributo, variable) asigna un nuevo valor al atributo el cual sera el de la variable
        // - JavaScript verá esa línea así: elemento.setAttribute('atributo', 'valor');
        // - Es como decirle al navegador: "al elemento que te paso, cámbiale este atributo por este nuevo valor"
        js.executeScript("arguments[0].setAttribute('" + atributo + "', '" + variable + "');", elemento);
    }

    public void JsRemoverAtributo(String xpath, String atributo) {
        WebElement elemento = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('" + atributo + "');", elemento);
    }

    
    // ****************** ESPERAS EXPLICITAS ******************
    // Este método espera explícitamente a que un elemento sea clickeable en la página.
    public void waitExplicitClick(String xpath, Integer minutos) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMinutes(minutos));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }
}