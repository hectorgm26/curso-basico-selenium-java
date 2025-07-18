package Recursos.Evidencias;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Rec_Evidencias {

    WebDriver driver;

    public Rec_Evidencias(WebDriver _driver) {
        this.driver = _driver;
    }

    public void ResaltarElementos(String xpath, String color) {
        WebElement elemento = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // funciona cuando el usuario haya seleccionado un color
        if (color.equals("r")) {
            js.executeScript("arguments[0].style.border='3px solid red';", elemento);
        } else {
            js.executeScript("arguments[0].style.border='3px solid back';", elemento);
        }
    }
}
