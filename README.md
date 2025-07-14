# Curso Básico de Selenium con Java

Este repositorio contiene un curso introductorio de automatización de pruebas web utilizando Selenium WebDriver con Java. A través de ejemplos prácticos, se abordan los fundamentos de Selenium, incluyendo la integración con JUnit para pruebas unitarias y Apache POI para la manipulación de archivos Excel.

## 📚 Contenido del Curso

- Introducción a Selenium WebDriver
- Localización de elementos web con XPath
- Esperas implícitas y explícitas
- Automatización de formularios y validaciones
- Manejo de datos desde archivos Excel con Apache POI
- Buenas prácticas para pruebas automatizadas
- Configuración moderna del driver con WebDriverManager

## 🛠️ Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal
- **Selenium WebDriver**: Herramienta para la automatización de navegadores web
- **JUnit**: Framework para la ejecución de pruebas unitarias
- **Apache POI**: Biblioteca para el manejo de archivos Excel
- **Maven**: Herramienta de gestión de proyectos y dependencias
- **WebDriverManager**: Utilidad moderna para la gestión automática del WebDriver

## 🔧 WebDriverManager

✅ **Nota importante:** Se utiliza la dependencia `WebDriverManager` para evitar la descarga manual del WebDriver compatible con el navegador. Esto permite:

* No referenciar rutas físicas específicas
* Evitar errores de compatibilidad entre versiones del navegador y el WebDriver
* Asegurar que cada miembro del equipo utilice automáticamente la versión adecuada del driver
* Mejorar la colaboración y evitar fallos en los tests por diferencias en el entorno

**Ejemplo de uso:**

```java
// ✅ Configuramos automáticamente el driver con WebDriverManager, evitando descargarlo manualmente
WebDriverManager.chromedriver().setup();
```

**Dependencia en el pom.xml:**

```xml
<!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>6.1.0</version>
</dependency>
```

## 🔎 XPath: Absolutos y Relativos

En Selenium, XPath es uno de los métodos más comunes para localizar elementos en una página web. Existen dos tipos principales:

### ✅ Full XPath (Absoluto)
- Ruta completa desde la raíz del documento hasta el elemento
- Muy frágil: cualquier cambio en la estructura rompe el XPath
- Se obtiene desde DevTools con: Right-click en el elemento → Copy → Copy full XPath

### ✅ XPath Relativo
- Ruta más corta y flexible, basada en atributos únicos o estructuras parciales
- Se obtiene desde DevTools con: Right-click en el elemento → Copy → Copy XPath

## 📌 Estructura Básica de un XPath

```xpath
//*[@nombreDelAtributo='ValorDelAtributo']
```

## 🧠 Casos con Elementos Repetidos

Cuando varios elementos comparten el mismo atributo y valor, se recomienda:

1. Basarse en el contenedor de esos elementos
2. Combinar XPaths o usar expresiones específicas:

```xpath
//*[@id='contenedor']//button[@class='btn btn-primary']
```

O por niveles:

```xpath
//*[@id='contenedor']//*[@class='card']//a[1]
```

## 🛠️ Otros ejemplos útiles:

### Buscar por múltiples atributos:
```xpath
//*[@id='form-flights' and @name='form-flights']
```

### Buscar por tipo de elemento:
```xpath
//div[@class='uwu xd']//span[@class='sc_button_title']
```

### Buscar por texto:
```xpath
//*[contains(text(), 'Texto que quiero buscar')]
```

## ⚠️ Buscar por posición (nodos)

No se recomienda, ya que depende del orden en el DOM. Sin embargo, puede ser necesario en ciertos casos:

```xpath
(//*[@class='btn-primary'])[1]
```

### ✅ Soluciones para muchos resultados:

**Solución 1: Usar posición**
```xpath
(//span[@class='sc_button_title'])[1]
```

**Solución 2: Usar contains() para simplificar y aumentar flexibilidad**
```xpath
(//a[contains(@class, 'sc_button')]//span[contains(@class, 'sc_button_title')])[1]
```

## ⏱️ Esperas en Selenium

Las esperas son fundamentales para sincronizar la ejecución del script con el tiempo de carga de los elementos web. Existen dos tipos principales:

### 1️⃣ Implicit Wait
- Se aplica de forma global
- Selenium esperará un tiempo determinado antes de lanzar una excepción si no encuentra un elemento

```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
```

### 2️⃣ Explicit Wait
- Espera condiciones específicas antes de continuar
- Muy útil para validar visibilidad, clicabilidad, presencia, etc.

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.elementToBeClickable(By.id("boton"))).click();
```

## 📋 Ejemplos comunes de Explicit Wait:

### elementToBeClickable
Espera a que el elemento sea visible y esté habilitado para hacer clic
```java
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='enviar']"))).click();
```

### visibilityOfElementLocated
Espera a que el elemento sea visible en la página
```java
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mensajeExito")));
```

### presenceOfElementLocated
Espera a que el elemento esté presente en el DOM (no necesariamente visible)
```java
wait.until(ExpectedConditions.presenceOfElementLocated(By.name("correo")));
```

### elementToBeSelected
Espera a que el elemento (checkbox, radio button) esté seleccionado
```java
wait.until(ExpectedConditions.elementToBeSelected(By.id("aceptarTerminos")));
```

### textToBePresentInElementLocated
Espera a que un texto específico aparezca en el elemento
```java
wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("estado"), "Completado"));
```

### alertIsPresent
Espera a que aparezca una alerta JavaScript
```java
wait.until(ExpectedConditions.alertIsPresent());
```

### Lambda personalizada
Permite crear condiciones personalizadas de espera
```java
wait.until(driver -> driver.findElement(By.id("campo")).isDisplayed() &&
                      driver.findElement(By.id("campo")).isEnabled());
```

### invisibilityOfElementLocated
Espera a que el elemento desaparezca o no sea visible
```java
wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
```

## 🚀 Cómo Empezar

1. **Clonar el repositorio**:
```bash
git clone https://github.com/hectorgm26/curso-basico-selenium-java.git
```

2. **Importar el proyecto** en tu IDE favorito (por ejemplo, IntelliJ IDEA o Eclipse) como un proyecto Maven.

3. **Instalar dependencias**: Maven descargará automáticamente las dependencias especificadas en `pom.xml`.

4. **Ejecutar las pruebas**: Navega a las clases de prueba y ejecútalas directamente desde tu IDE o utilizando Maven.

## ✅ Requisitos Previos

- **Java JDK 8 o superior** instalado
- **Maven** instalado y configurado
- Conocimientos básicos de Java y programación orientada a objetos

## 📌 Notas Adicionales

- Los archivos `Creacion de XPaths.txt` y `TiposEspera.txt` contienen anotaciones y ejemplos útiles sobre la localización de elementos y la implementación de esperas en Selenium.
- Se recomienda revisar la documentación oficial de Selenium WebDriver para profundizar en los conceptos presentados.

## 🤝 Contribuciones

¡Bienvenido a colaborar! Puedes enviar sugerencias, mejoras o ejemplos adicionales mediante un pull request.

## 📄 Licencia

Este proyecto se distribuye bajo la licencia MIT.
