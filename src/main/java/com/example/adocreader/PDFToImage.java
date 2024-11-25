package com.example.adocreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PDFToImage {
    public static void pdfToImage() {
        String pdfPath = "src/main/resources/autogenerateddocument-30685626-3bf7-4cb7-ab7b-7c26582442d3.pdf"; // PDF path
        String outputDir = "src/main/resources/";   // shekilin yazilacagi path

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); page++) {
                // Sehife nomresi 1 den bashlayaraq adlandirilir
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300); // DPI keyfiyyetini artirmaq olar (300 recommend)
                String fileName = outputDir + "/page-" + (page + 1) + ".png"; // PNG formatında save olunacaq
                ImageIO.write(bim, "png", new File(fileName));
                System.out.println("Sehife " + (page + 1) + " ugurla save olundu: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
