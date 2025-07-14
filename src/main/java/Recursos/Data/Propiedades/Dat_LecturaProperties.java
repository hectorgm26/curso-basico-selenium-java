package Recursos.Data.Propiedades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Dat_LecturaProperties {

    public String retornarDatos(String dato, String propiedades) {
        String datoNuevo = null;
        String archivoConfig = null;

        try {
            // alamacenara toda la info del archivo propiedades
            Properties datosPropiedades = new Properties();
            InputStream datos = null;

            archivoConfig = retornarRuta(propiedades);

            // lectura del x archivo properties
            datos = new FileInputStream(archivoConfig);
            // se almacenan los datos en el archivo
            datosPropiedades.load(datos);

            // obtener un valor en especifico de ese archivo properties, que es la solicitada en el metodo
            datoNuevo = datosPropiedades.getProperty(dato);

        } catch (Exception e) {
            System.out.println("Error al cargar el archivo de propiedades: " + e.getMessage());
        }
        return datoNuevo;
    }

    public String retornarRuta(String propiedades) {
        String rutaConfig = null;
        String rutaGeneral = "src/main/java/Recursos/Data/Propiedades/";

        switch (propiedades) {
            case "General":
                rutaConfig = rutaGeneral + "General.properties";
                break;
            case "Especificas":
                rutaConfig = rutaGeneral + "Especificas.properties";
                break;
        }
        return rutaConfig;
    }
}
