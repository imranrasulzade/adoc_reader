package com.example.adocreader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PDFReaderFromBytes {

    public static String readPdfFromBytes(byte[] pdfBytes) throws IOException {
        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(pdfBytes))) {
            if (!document.isEncrypted()) {
                PDFTextStripper pdfStripper = new PDFTextStripper();
                return pdfStripper.getText(document);
            } else {
                throw new IOException("PDF is encrypted and cannot be read.");
            }
        }
    }
}