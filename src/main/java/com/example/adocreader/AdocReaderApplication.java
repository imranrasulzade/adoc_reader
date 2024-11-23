package com.example.adocreader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class AdocReaderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdocReaderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
