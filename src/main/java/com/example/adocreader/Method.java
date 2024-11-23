package com.example.adocreader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Method {
    public static void test() throws IOException {
        // ADOC pathi burda
        String adocFilePath = "src/main/resources/myadoc.adoc";

        // ADOC oxu
        String content = new String(Files.readAllBytes(Paths.get(adocFilePath)));

        // Base64 kodu regex ile cixar
        Pattern pattern = Pattern.compile("<<<PDF_START>>>(.*?)<<<PDF_END>>>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(content);

        if (matcher.find()) {
            String base64Pdf = matcher.group(1).trim();

            // Base64 u ac ve pdfe yaz
            byte[] pdfBytes = Base64.getDecoder().decode(base64Pdf);
            Path outputPath = Paths.get("src/main/resources/output.pdf");
            Files.write(outputPath, pdfBytes);

            System.out.println("PDF acildi ve save olundu: " + outputPath.toAbsolutePath());
        } else {
            System.out.println("PDF base64 tapilmadi.");
        }
    }
}
