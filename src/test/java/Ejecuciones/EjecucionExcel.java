package Ejecuciones;

import Recursos.Data.Excel.Dat_Excel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import javax.swing.table.DefaultTableModel;

public class EjecucionExcel {

    public static WebDriver driver;
    Dat_Excel dat_excel;
    private DefaultTableModel dataTable;

    @BeforeAll
    public static void preEjecucion() {

    }

    @AfterAll
    public static void postEjecucion() {
        // driver.close();
    }

    public void instanciasBasicas() {
        lecturaDatos();
        dat_excel = new Dat_Excel();

    }

    public void lecturaDatos() {

    }

    @Test
    public void ModificarElementos() {
        instanciasBasicas();
        dat_excel.excelAbrirConexion(false);

        // Con esto podremos obtener y consultar los datos de la hoja de Excel basados en las posiciones de las celdas
        dataTable = dat_excel.excelTabla();

        dat_excel.excelCerrarConexion();

        try {
            Thread.sleep(2000);
        } catch (Exception fallo) {
            System.err.println("Error al esperar: " + fallo.getMessage());
        }
    }

}
