/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.archivos.simple;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author avbravo
 */
public class FileSplitter {
     // Método para dividir un archivo en partes más pequeñas
    public static void splitFile(File inputFile, String outputDir, int chunkSize) throws IOException {
        if (!Files.exists(Paths.get(outputDir))) {
            Files.createDirectories(Paths.get(outputDir));
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
            byte[] buffer = new byte[chunkSize];
            int bytesRead;
            int partNumber = 1;

            while ((bytesRead = inputStream.read(buffer)) > 0) {
                // Crear un nuevo archivo para cada parte
                File outputFile = new File(outputDir, inputFile.getName() + ".part" + partNumber);
                try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("Parte " + partNumber + " creada: " + outputFile.getName());
                partNumber++;
            }
        }
    }

    // Método para unir los archivos divididos en un solo archivo
    public static void mergeFiles(String inputDir, String outputFilePath,String outputFilePathOutput ) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFilePathOutput))) {
            int partNumber = 1;
            File inputFile;
           File nputFile = new File(inputDir, new File(outputFilePath).getName() + ".part" + partNumber);
           
             System.out.println("Ruta absoluta: " + nputFile.getAbsolutePath());
           if(nputFile == null){
               System.out.println("es null ");
           }else{
                 if(nputFile.exists()){
                     System.out.println("\t exists()");
                 }else{
                     System.out.println("\t not exits");
                 }
           }
                   
            while ((inputFile = new File(inputDir, new File(outputFilePath).getName() + ".part" + partNumber)).exists()) {
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile))) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                System.out.println("Parte " + partNumber + " unida: " + inputFile.getName());
                partNumber++;
            }
        }
    } 
    
      // Método principal
    public static void main(String[] args) {
        try {
            // Configuración inicial
            String fileName = "archivo.pdf"; // Cambia esto al nombre de tu archivo (PDF, DOCX, imagen, etc.)
            File inputFile = new File(fileName);
            String outputDir = "partes_divididas";
//            String outputFilePath = "archivo_unido." + getFileExtension(fileName); // Mantener la extensión original
            String outputFilePath = "archivo." + getFileExtension(fileName); // Mantener la extensión original
            String outputFilePathOutput = "archivo_merge." + getFileExtension(fileName); // Mantener la extensión original
            int chunkSize = 1024 * 1024; // Tamaño de cada parte (1 MB)

            // Validar que el archivo exista
            if (!inputFile.exists()) {
                System.out.println("El archivo no existe: " + inputFile.getAbsolutePath());
                return;
            }

            // Dividir el archivo
            System.out.println("Dividiendo archivo...");
            splitFile(inputFile, outputDir, chunkSize);
            System.out.println("Archivo dividido exitosamente en partes.");

            // Unir los archivos
            System.out.println("Uniendo partes...");
            mergeFiles(outputDir, outputFilePath,outputFilePathOutput);
            System.out.println("Archivos unidos exitosamente en: " + outputFilePathOutput);
            
            /**
             * Dividiendo archivo de musica
             * 
             */
            
          fileName = "musica.mp3"; // Cambia esto al nombre de tu archivo (PDF, DOCX, imagen, etc.)
          inputFile = new File(fileName);
          outputDir = "partes_divididas";
//            String outputFilePath = "archivo_unido." + getFileExtension(fileName); // Mantener la extensión original
            outputFilePath = "musica." + getFileExtension(fileName); // Mantener la extensión original
           outputFilePathOutput = "musica_merge." + getFileExtension(fileName); // Mantener la extensión original
          chunkSize = 1024 * 1024; // Tamaño de cada parte (1 MB)

            // Validar que el archivo exista
            if (!inputFile.exists()) {
                System.out.println("El archivo no existe: " + inputFile.getAbsolutePath());
                return;
            }

            // Dividir el archivo
            System.out.println("Dividiendo archivo..."+ fileName);
            splitFile(inputFile, outputDir, chunkSize);
            System.out.println("Archivo "  + fileName+" dividido exitosamente en partes.");

            // Unir los archivos
            System.out.println("Uniendo partes...");
            mergeFiles(outputDir, outputFilePath,outputFilePathOutput);
            System.out.println("Archivos unidos exitosamente en: " + outputFilePathOutput);

            
          /**
           * Imagen
           */ 
            
            fileName = "imagen.png"; // Cambia esto al nombre de tu archivo (PDF, DOCX, imagen, etc.)
          inputFile = new File(fileName);
          outputDir = "partes_divididas";
//            String outputFilePath = "archivo_unido." + getFileExtension(fileName); // Mantener la extensión original
            outputFilePath = "imagen." + getFileExtension(fileName); // Mantener la extensión original
           outputFilePathOutput = "imagen_merge." + getFileExtension(fileName); // Mantener la extensión original
          chunkSize = 1024 *1024; // Tamaño de cada parte (1 MB)

            // Validar que el archivo exista
            if (!inputFile.exists()) {
                System.out.println("El archivo no existe: " + inputFile.getAbsolutePath());
                return;
            }

            // Dividir el archivo
            System.out.println("Dividiendo archivo..."+ fileName);
            splitFile(inputFile, outputDir, chunkSize);
            System.out.println("Archivo "  + fileName+" dividido exitosamente en partes.");

            // Unir los archivos
            System.out.println("Uniendo partes...");
            mergeFiles(outputDir, outputFilePath,outputFilePathOutput);
            System.out.println("Archivos unidos exitosamente en: " + outputFilePathOutput);
            
            
        } catch (IOException e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método auxiliar para obtener la extensión de un archivo
    private static String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1) {
            return ""; // No hay extensión
        }
        return fileName.substring(lastIndex + 1);
    }
}
