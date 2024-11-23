package com.example.adocreader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class AdocReaderApplication implements CommandLineRunner {

    public static String readTextFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    public static void main(String[] args) {
        SpringApplication.run(AdocReaderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            String filePath = "src/main/resources/numune_adoc.txt";
            String content = readTextFile(filePath);
            MyDoc myDoc = JsonToMyDocMapper.getMyDoc(content);
            Path path = ZipReader.pullPdfFromZipBase64(myDoc.getReyestr());
            String pdfContent = ZipReader.readPdfContent(path);
            System.out.println(pdfContent);



        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Xeta: " + e.getMessage());
        }
    }
}
