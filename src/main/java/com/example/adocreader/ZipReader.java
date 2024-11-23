package com.example.adocreader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class ZipReader {
    public static Path pullPdfFromZipBase64(String base64String){

        try {
            byte[] zipBytes = Base64.getDecoder().decode(base64String);
            Path zipFilePath = Paths.get("decoded.zip");
            Files.write(zipFilePath, zipBytes);

            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath.toFile()))) {
                ZipEntry entry;
                while ((entry = zipInputStream.getNextEntry()) != null) {
                    System.out.println("Found file: " + entry.getName());

                    if (entry.getName().endsWith(".pdf")) {

                        Path pdfFilePath = Paths.get(entry.getName());
                        try (OutputStream out = Files.newOutputStream(pdfFilePath)) {
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = zipInputStream.read(buffer)) > 0) {
                                out.write(buffer, 0, len);
                            }
                        }
                        return pdfFilePath;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String readPdfContent(Path pdfFilePath) {
        try (InputStream inputStream = Files.newInputStream(pdfFilePath)) {

            byte[] pdfBytes = inputStream.readAllBytes();
            System.out.println("PDF olchusu: " + pdfBytes.length + " byte");

            String pdfContent = PDFReaderFromBytes.readPdfFromBytes(pdfBytes);

            if (pdfContent.contains("axtarilan_soz")) {
                System.out.println("Tapildi");
            }
            return pdfContent;
        } catch (IOException e) {
            System.err.println("PDF file oxunanda xeta: " + e.getMessage());
        }
        return null;
    }
}
