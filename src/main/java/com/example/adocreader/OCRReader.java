package com.example.adocreader;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class OCRReader {
    public static void readFromImage() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/path/to/tessdata"); // Tesseract yuklenib path-i bura yazilmalidi
        tesseract.setLanguage("eng"); // Dil seçimi
        
        try {
            String text = tesseract.doOCR(new File("/path/to/pdf-page-image.png")); // oxuyacagi shekilin path-i
            System.out.println(text);
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }
}
