package edu.escuelaing.arsw.ASE.app;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class LineCounter {

    /**
     * Returns a list of files found at the specified path.
     * If the path is a directory, all files and subdirectories within it will be included.
     * If the path is a file, only that file will be included in the list.
     *
     * @param route the path of the directory or file from which to get the files
     * @return a list of files found at the specified path
     */
    public List<File> getFiles(String route) {

        List<File> files = new ArrayList<>();

        try {
            Path startPath = Paths.get(route);
            if (Files.isDirectory(startPath)) {
                // If it's a directory, traverse all files and subdirectories within it
                Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        files.add(file.toFile());
                        // Continue visiting other files and subdirectories
                        return FileVisitResult.CONTINUE;
                    }
                });
            } else {
                files.add(startPath.toFile());
            }
        } catch (IOException e) {
            System.err.println("Error al procesar los archivos: " + e.getMessage());
        }
        return files;
    }

    /**
     * Counts the number of physical lines in the given file.
     *
     * @param file The File object for which to count the physical lines.
     * @return The number of physical lines in the file.
     */
    public int countPhysicalLines(File file) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.err.println("Error al contar líneas físicas: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Counts the number of logical lines in the given file.
     *
     * @param file The File object for which to count the logical lines.
     * @return The number of logical lines in the file.
     */
    public int countLogicalLines(File file) {
        int lines = 0;
        boolean inBlockComment = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (inBlockComment) {
                    if (line.endsWith("*/")) {
                        inBlockComment = false;
                    }
                } else if (line.startsWith("/*")) {
                    inBlockComment = true;
                } else if (!line.isEmpty() && !line.startsWith("//")) {
                    lines++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al contar líneas lógicas: " + e.getMessage());
        }
        return lines;
    }
}
