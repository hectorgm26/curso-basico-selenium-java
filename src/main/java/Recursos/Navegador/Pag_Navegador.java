package Recursos.Navegador;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Pag_Navegador {

    // Atributo para almacenar la instancia del WebDriver
    WebDriver driver;

    // Constructor que recibe el WebDriver como parámetro, y cargara de forma estatica el dato que le enviemos
    public Pag_Navegador(WebDriver _driver) {
        this.driver = _driver;
    }

    public WebDriver abrirNavegador(String url) {

        // ✅ Configuramos automáticamente el driver con WebDriverManager, evitando descargarlo manualmente
        WebDriverManager.chromedriver().setup();
        // Si queremos forzar la descarga del driver, y de paso borrar versiones antiguas
        // WebDriverManager.chromedriver().clearDriverCache().forceDownload().setup();

        // ✅ Opciones del navegador
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        // ✅ Instanciamos el navegador con las opciones definidas
        driver = new ChromeDriver(options);

        // ✅ Abrimos la URL deseada
        driver.get(url);

        // ✅ Tiempo de espera.
        // Si el elemento aún no está presente, espera hasta 10 segundos antes de lanzar NoSuchElementException
        // con un implicit wait se hace que se espere x cantidad de tiempo antes que se inicie la prueba, si todavia no ha cargado el elemento del sitio web
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver; // retornamos el valor del driver, para que la clase de test entienda a que estan haciendo referencias los metodos y las aserciones
    }
}
