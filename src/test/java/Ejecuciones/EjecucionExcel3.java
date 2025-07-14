package Ejecuciones;

import Entidades.Facebook.Ent_RegistroFacebook;
import Escenarios.Facebook.Esc_RegistroFacebook;
import Recursos.Data.Excel.Dat_Excel;
import Recursos.Navegador.Pag_Navegador;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import javax.swing.table.DefaultTableModel;

public class EjecucionExcel3 {

    public static WebDriver driver;

    static Dat_Excel dat_excel;
    private DefaultTableModel dataTable;
    Ent_RegistroFacebook ent_registro;
    Esc_RegistroFacebook esc_Registrof;

    static int numeroFilas = 0; // posicion 0 despues de contar los encabezados o headers

    @BeforeAll
    public static void preEjecucion() {
        dat_excel = new Dat_Excel();
        numeroFilas = dat_excel.obtenerFilas();
    }

    public void AbrirNavegador() {
        Pag_Navegador pag_navegador = new Pag_Navegador(driver);
        driver = pag_navegador.abrirNavegador("https://www.facebook.com/?locale=es_LA");
    }

    public static void CerrarNavegador() {
        driver.close();
        driver.quit(); // cierra el navegador y finaliza la ejecucion
    }

    public void instanciasBasicas() {
        ent_registro = new Ent_RegistroFacebook();
        esc_Registrof = new Esc_RegistroFacebook(driver);
    }

    public void lecturaDatos(int numeroFila) {

        dat_excel.excelAbrirConexion(false);
        dataTable = dat_excel.excelTabla(); // se obtiene la informacion de la hoja de excel

        ent_registro.setNombre(dat_excel.obtenerValorColumna(numeroFila, "nombre").toString());
        ent_registro.setApellido(dat_excel.obtenerValorColumna(numeroFila, "apellido").toString());
        ent_registro.setCorreo(dat_excel.obtenerValorColumna(numeroFila, "correo").toString());
        ent_registro.setClave(dat_excel.obtenerValorColumna(numeroFila, "clave").toString());
        ent_registro.setDiaNacimiento(dat_excel.obtenerValorColumna(numeroFila, "DiaNacimiento").toString());
        ent_registro.setMesNacimiento(dat_excel.obtenerValorColumna(numeroFila, "MesNacimiento").toString());
        ent_registro.setAnioNacimiento(dat_excel.obtenerValorColumna(numeroFila, "AnioNacimiento").toString());
        ent_registro.setSexo(dat_excel.obtenerValorColumna(numeroFila, "sexo").toString());

        dat_excel.excelCerrarConexion();
    }

    @Test
    public void ModificarElementos() {

        for (int numeroFila = 0; numeroFila < numeroFilas; numeroFila++) {

            AbrirNavegador();
            instanciasBasicas();
            lecturaDatos(numeroFila);
            esc_Registrof.RegistroFacebook(ent_registro);

            CerrarNavegador();
        }
    }

}
