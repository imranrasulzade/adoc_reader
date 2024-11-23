package com.example.adocreader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToMyDocMapper {

    public static MyDoc getMyDoc(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(jsonString);

            MyDoc myDoc = new MyDoc();

            if (rootNode.has("sehadetname")) {
                myDoc.setSehadetname(rootNode.get("sehadetname").get("fileContent").asText());
            }
            if (rootNode.has("nizamname")) {
                myDoc.setNizamname(rootNode.get("nizamname").get("fileContent").asText());
            }
            if (rootNode.has("reyestr")) {
                myDoc.setReyestr(rootNode.get("reyestr").get("fileContent").asText());
            }
//            System.out.println(myDoc);
            return myDoc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}