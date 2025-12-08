package com.sample;

import java.io.*;
import java.util.*;

public class CSVLoader {

    public static Map<String, String> loadQuestions(String fileName) throws Exception {
        Map<String, String> map = new LinkedHashMap<>();
        InputStream is = CSVLoader.class.getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] p = line.split(",", 2);
            map.put(p[0].trim(), p[1].trim());
        }
        return map;
    }

    public static Map<String, String[]> loadDrinks(String fileName) throws Exception {
        // symbol - [DisplayName, ImageFileNameOrEmpty]
        Map<String, String[]> map = new LinkedHashMap<>();
        InputStream is = CSVLoader.class.getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;
            String[] p = line.split(",", 3);
            String symbol = p[0].trim(); 
            String name = p[1].trim(); 
            String img = p.length >= 3 ? p[2].trim() : ""; //get image location, otherwise null
            map.put(symbol, new String[]{name, img});
        }
        return map;
    }
}