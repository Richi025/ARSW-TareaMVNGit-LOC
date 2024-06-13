package edu.escuelaing.arsw.ASE.app;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CountLinesTest {

    private LineCounter lineCounter;
    private String testFilesPath = "src/main/java/edu/escuelaing/arsw/ASE/app";
    private String[] args = {"phy", testFilesPath + "/Example.java"};
    private String[] args1 = {"loc", testFilesPath + "/LineCounter.java"};
    private String[] args2 = {"loc", testFilesPath + "/CounterLines.java"};
    private String[] args3 = {"loc", testFilesPath + "/Example2.java"};

    @Test
    public void testCountPhysicalLines() {
        LineCounter lineCounter = new LineCounter();
        List<File> files = lineCounter.getFiles(args[1]);
        try {
            int count = lineCounter.countPhysicalLines((files.get(0)));
            assertEquals(17, count);
        } catch (Exception e) {
            fail("Error inesperado al contar líneas físicas: " + e.getMessage());
        }
    }

    @Test
    public void testCountLogicalLines() {
        LineCounter lineCounter = new LineCounter();
        List<File> files = lineCounter.getFiles(args1[1]);
        try {
            int count = lineCounter.countLogicalLines((files.get(0)));
            assertEquals(61, count);
        } catch (Exception e) {
            fail("Error inesperado al contar líneas lógicas: " + e.getMessage());
        }
    }

    @Test
    public void testGetFiles() {
        LineCounter lineCounter = new LineCounter();
        String testDirectory = testFilesPath;
        try {
            List<File> files = lineCounter.getFiles(testDirectory);
            assertEquals(4, files.size());
        } catch (Exception e) {
            fail("Error inesperado al obtener archivos: " + e.getMessage());
        }
    }

    @Test
    public void testErrorHandling() {
        LineCounter lineCounter = new LineCounter();
        File nonExistentFile = new File("nonexistentfile.java");
        try {
            int count = lineCounter.countPhysicalLines(nonExistentFile);
            assertNotEquals(-1, count, "Se esperaba que el manejo de errores devolviera 0 líneas para un archivo inexistente");
        } catch (Exception e) {
            fail("Error inesperado al manejar archivo inexistente: " + e.getMessage());
        }
    }

    @Test
    public void testConsoleOutputLogical() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            String[] args = {"loc", testFilesPath};
            CountLines.main(args);
            String consoleOutput = outputStream.toString();

            String expectedOutput = normalizeOutput("CountLines.java: 30 lineas\n" +
                    "Example.java: 6 lineas\n" +
                    "Example2.java: 16 lineas\n" +
                    "LineCounter.java: 61 lineas\n" +
                    "Total: 113 lineas\n");
            String actualOutput = normalizeOutput(consoleOutput);

            assertEquals(expectedOutput, actualOutput, "La salida por consola no coincide con la esperada");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testConsoleOutputPhysical() {
        PrintStream originalOut = System.out;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            String[] args = {"phy", testFilesPath};
            CountLines.main(args);
            String consoleOutput = outputStream.toString();

            String expectedOutput = normalizeOutput("CountLines.java: 44 lineas\n" +
                    "Example.java: 17 lineas\n" +
                    "Example2.java: 41 lineas\n" +
                    "LineCounter.java: 90 lineas\n" +
                    "Total: 192 lineas\n");
            String actualOutput = normalizeOutput(consoleOutput);

            assertEquals(expectedOutput, actualOutput, "La salida por consola no coincide con la esperada");
        } finally {
            System.setOut(originalOut);
        }
    }

    private String normalizeOutput(String output) {
        return output.trim().replaceAll("\\r\\n", "\n");
    }
}
