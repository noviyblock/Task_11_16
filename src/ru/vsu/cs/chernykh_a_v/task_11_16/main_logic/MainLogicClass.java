package ru.vsu.cs.chernykh_a_v.task_11_16.main_logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainLogicClass {

    public static String readAllLinesFromFile(String fileName){
        String content = null;
        try {
            content = readFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeUpdateDataInFile(String data, String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        PrintWriter pw = new PrintWriter(file);
        pw.println(data);
        pw.close();
        System.out.println("Output file path: " + Paths.get(fileName).toAbsolutePath());
    }

    public static String deleteCommasFromFileText(String data){
        data = data.replaceAll("//(.)*", "");
        data = data.replaceAll("/\\*(.|\\n)*?\\*/","");
        return data;
    }

    private static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path).toAbsolutePath(), StandardCharsets.UTF_8);
    }
}
