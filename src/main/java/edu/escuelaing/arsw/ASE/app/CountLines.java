package edu.escuelaing.arsw.ASE.app;

import java.io.File;
import java.util.List;

public class CountLines {

    /**
     * Main method that is executed when the application starts.
     *
     * @param args Command-line arguments.
     *             The first argument should be the counting mode ("phy" for physical lines, "loc" for logical lines).
     *             The second argument should be the path  of the files.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java -jar TareaMVNGit-LOC-1.0-SNAPSHOT.jar (phy|loc) (rutaArchivo)");
            return;
        }

        String mode = args[0];
        String filePattern = args[1];

        LineCounter lineCounter = new LineCounter();
        List<File> files = lineCounter.getFiles(filePattern);

        int totalLines = 0;
        for (File file : files) {
            int count = 0;
            if (mode.equals("phy")) {
                count = lineCounter.countPhysicalLines(file);
            } else if (mode.equals("loc")) {
                count = lineCounter.countLogicalLines(file);
            } else {
                System.out.println("Modo desconocido: " + mode + " pruebe con phy o loc");
                return;
            }
            totalLines += count;
            System.out.println(file.getName() + ": " + count + " lineas");
        }
        System.out.println("Total: " + totalLines + " lineas");

    }
}
