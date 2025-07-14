package Recursos.Data.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.table.DefaultTableModel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class Dat_Excel {

    // Instancias para trabajar con Excel y Apache POI
    private DefaultTableModel dataTable;
    FileInputStream excelFile;
    Workbook workbook;
    Sheet sheet; // hoja con la que se interactuara
    FileOutputStream fos;

    // Hoja con la que trabajare
    String sheetName = "Hoja1";

    // La ruta donde se encuentra el archivo Excel
    public String excelFilePath = "C:/Users/Personal/Documents/Cursos/Curso-Selenium/curso-selenium-basico/src/main/java/Recursos/Data/Insumos/testing.xlsx";

    // Con que numero de fila quiero trabajar (indice 0, primera posicion despues del encabezado)
    int numeroFila = 0;

    // Conexion Excel
    //region
    public void excelAbrirConexion(boolean actualizar) {
        try {
            // Abrimos el archivo Excel
            excelFile = new FileInputStream(excelFilePath);

            // Creamos una instancia de Workbook para trabajar con el archivo Excel para leer y escribir
            workbook = new XSSFWorkbook(excelFile);

            // Sheet sheet = workbook.getSheetAt(0); // Ubicarse en la primera hoja del libro excel solo por posicion
            sheet = workbook.getSheet(sheetName); // Ubicarse en la hoja por su nombre

            if (actualizar) {
                // Preparamos el archivo para escritura, es decir, para escribir y guardar los cambios
                fos = new FileOutputStream(excelFilePath);
            }

        } catch (Exception e) {
            System.err.println("Error al abrir el archivo Excel: " + e.getMessage());
        }
    }

    public void excelCerrarConexion() {
        try {
            workbook.close();
            excelFile.close();
        } catch (Exception e) {
            System.err.println("Error al cerrar el archivo Excel: " + e.getMessage());
        }
    }
    //endregion

    // Obtener informacion excel
    //region
    public DefaultTableModel excelTabla() {
        // Instancia el modelo de tabla que se usará para almacenar los datos del Excel
        dataTable = new DefaultTableModel();

        // Obtiene un iterador que recorre todas las filas de la hoja actual
        Iterator<Row> rowIterator = sheet.iterator();

        // Obtiene la primera fila (que se usará como encabezado de columnas)
        Row headerRow = rowIterator.next(); // ← esta es la primera fila (encabezado)

        // Iteramos sobre las celdas de la fila de encabezado para capturar los nombres de las columnas
        Iterator<Cell> headerCellIterator = headerRow.cellIterator();
        while (headerCellIterator.hasNext()) {
            Cell cell = headerCellIterator.next(); // obtenemos la celda actual del encabezado
            dataTable.addColumn(cell.getStringCellValue()); // usamos el contenido de la celda como nombre de columna
        }

        // Ahora recorremos el resto de las filas (datos debajo del encabezado)
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next(); // obtenemos la siguiente fila de datos

            // Creamos un arreglo del mismo tamaño que la cantidad de columnas
            Object[] rowData = new Object[dataTable.getColumnCount()];

            // Obtenemos un iterador de las celdas de esta fila
            Iterator<Cell> cellIterator = row.cellIterator();
            int columnIndex = 0; // lleva el control de la posición en el arreglo rowData

            // Recorremos las celdas de la fila
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); // obtenemos la celda actual

                // Establecemos el tipo de la celda como STRING para evitar errores de tipo
                cell.setCellType(CellType.STRING);

                // Guardamos el valor de la celda en el arreglo de datos, en la columna correspondiente
                rowData[columnIndex] = cell.getStringCellValue();
                columnIndex++; // avanzamos a la siguiente columna
            }

            // Agregamos la fila al modelo de tabla
            dataTable.addRow(rowData);
        }

        // Devolvemos el modelo de tabla ya lleno con los datos del Excel
        return dataTable;
    }
    //endregion

    // Metodo para obtener el valor de una celda especifica basado en el numero de fila y el nombre de la columna
    // region
    public Object obtenerValorColumna(int rowIndex, String columnName) {
        int columnIndex = dataTable.findColumn(columnName); // Obtenemos el índice de la columna por su nombre

        if (columnIndex == -1) {
            System.err.println("Columna no encontrada: " + columnName);
            return null; // Retorna null si la columna no existe
        }

        // El primer argumento es el índice de la fila, y el segundo es el índice de la columna
        return dataTable.getValueAt(rowIndex, columnIndex); // Retorna el valor de la celda en la fila y columna especificadas
    }
    // endregion

    // metodo para obtener cantidad de filas del excel
    public int obtenerFilas() {
        excelAbrirConexion(false);
        int cantidadFilas = 0;

        // iterar a traves de las filas en la hoja - ITERARA TODAS LAS FILAS QUE SE LLENAN, Y ASIGNARA UN NUMERO BASADO EN LAS FILAS QUE ENCUENTRE
        for (Row row : sheet) {
            //verificar si la fila no esta vacía
            if (row != null) {
                //iterar a traves de las celdas en la fila
                boolean rowHasData = false;
                for (Cell cell : row) {
                    if (cell != null && cell.getCellType() != CellType.BLANK) {
                        // la celda no esta vacia o nula
                        rowHasData = true;
                        break;
                    }
                }
                // si la fila tiene al menos una celda con datos, incrementa el contador
                if (rowHasData) {
                    cantidadFilas++;
                }
            }
        }
        cantidadFilas--; // SE RESTARA EL VALOR DE 1 PARA NO CONTAR ENCABEZADOS
        excelCerrarConexion();
        return cantidadFilas; // Retorna la cantidad de filas con datos en la hoja
    }


    // ********************** USAR EL EXCEL TESTING2.XLSX **************************

    // Que empiece a contar desde la celda A, es decir la columna 0
    int posicionCelda = 0;
    XSSFRow filaActualizar; // Fila que se actualizara

    // metodo para actualizar una celda especifica, en este caso, poner ok al resultado de la prueba
    public void EscrituraDatos(Integer numeroFila, String columnaActualizar, String valorNuevo) {
        excelAbrirConexion(true);
        posicionCelda = PosicionColumna(numeroFila, columnaActualizar); // obtenemos la posicion de la columna a actualizar
        ActualizarCelda(valorNuevo); // actualizamos la celda con el valor nuevo
        excelCerrarConexion();
    }

    public int PosicionColumna(Integer numeroFila, String columnaActualizar) {
        numeroFila++;

        // Obtener todos los resultados del encabezado
        XSSFSheet hojaExcel = (XSSFSheet) sheet;
        XSSFRow filaEncabezado = hojaExcel.getRow(numeroFila - 1); // obtenemos la fila del encabezado
        filaActualizar = hojaExcel.getRow(numeroFila - 1);

        // Con el for se recorrera cada columna hasta llegar a la ultima columna con texto
        // y cada vez que se repita el ciclo se aumentara el contador para seguir con la siguiente columna
        for (int i = 0; i < filaEncabezado.getLastCellNum(); i++) {
            // Se obtiene el nombre de la celda en la fila del encabezado
            String prueba = hojaExcel.getRow(0).getCell(i).toString();
            if (prueba.equals(columnaActualizar)) {
                // ese numero de posicion se guardara en la variable posicionCelda
                posicionCelda = i;
                break;
            }
        }
        return posicionCelda;
    }

    public void ActualizarCelda(String valorNuevo) {
        if (posicionCelda != 0) {

            // Buscamos la celda "resultado"
            XSSFCell celda = filaActualizar.createCell(posicionCelda++);
            // actualizamos el valor de la celda con exitoso o fallido
            celda.setCellValue(valorNuevo);

            try {
                //guardamos los cambios en el archivo si es necesario
                if (fos != null) {
                    workbook.write(fos);
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Celda actualizada correctamente");
        } else {
            System.err.println("No se pudo encontrar la columna: " + posicionCelda);
        }
    }
}
