package DogoConsultaPartidos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Rec_Navegador {

    WebDriver driver;

    public ChromeDriver abrirNavegador(String url) {

        System.out.println("Hola mundo de la automatizacion");
        WebDriverManager.chromedriver().setup(); // libreria que permite descargar el driver de Chrome automáticamente

        // opciones del navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // Iniciar maximizado
        options.addArguments("-incognito"); // Modo incógnito
        options.addArguments("--remote-allow-origins=*"); // Permitir orígenes remotos (OBLIGATORIO ACTUALMENTE)

        driver = new ChromeDriver(options);
        driver.get(url); // Abrir la URL especificada
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(5)); // Esperar implícitamente hasta 5 minutos para que los elementos estén disponibles sin cerrarse el navegador

        return (ChromeDriver) driver; // retorna el chromedriver para poderlo utilizar en otras clases y metodos
    }
}