package Ejecuciones;

import Recursos.Data.Propiedades.Dat_LecturaProperties;
import org.junit.jupiter.api.Test;

public class ConsultaProperties {

    @Test
    public void probarDatos() {
        Dat_LecturaProperties properties = new Dat_LecturaProperties();

        // Se consulta en el primer argumento por el nombre del dato en el archivo properties, y traera su valor
        // y el segundo parametro es el nombre del archivo properties, que va a una ruta especifica
        String ruta = properties.retornarDatos("rutaFisica", "General");

        String archivo2 = properties.retornarDatos("prueba", "Especificas");

        System.out.println("Ruta Fisica: " + ruta);
        System.out.println("Archivo 2: " + archivo2);
    }
}
