package com.example.component;

import com.example.dto.DataTask;

import java.io.*;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Performer {
    public static String task(String str1, String str2){
        Pattern pattern = Pattern.compile("\\s*(\\s|,|!|\\.)\\s*");

        LinkedList<String> list = new LinkedList<>();
        String[] a1 = pattern.split(str1);
        String[] a2 = pattern.split(str2);

        String res = "";
        int i;
        for (String str: a1) {
            i = 0;
            while (i < a2.length){
                if(a2[i].indexOf(str) != -1){
                    if(list.indexOf(a2[i]) == -1) {
                        list.add(a2[i]);
                        res += " " + str;
                    }
                    i = a2.length;
                }
                i++;
            }
        }
        return res;
    }

    public static String task(String a1) {
        Long a = Long.parseLong(a1);
        long i = 10;
        long m = a % i;
        a = a - m;
        String res = "" + m;
        while (a != 0) {
            i *= 10;
            m = a % i;
            a = a - m;
            if (m != 0) {
                res = m + " + " + res;
            }
        }
        return res;
    }

    public static void save(String filename, DataTask dataTask){
        try {
            File dir = new File("saves");
            if(!dir.isDirectory()){
                dir.mkdir();
            }
            FileWriter fileWriter = new FileWriter("saves\\" + filename);
            String type = dataTask.getType();
            fileWriter.write(type + "\n");
            fileWriter.write(dataTask.getInputData1() + "\n");
            if(type.equals("Задача 1")){
                fileWriter.write(dataTask.getInputData2() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e){
            System.err.println(e);
        }
    }

    public static DataTask load(String filename){
        DataTask dataTask = new DataTask();
        try {
            File dir = new File("saves");
            if(!dir.isDirectory()){
                dir.mkdir();
            }
            FileReader fileReader = new FileReader("saves\\" + filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String type = bufferedReader.readLine();
            dataTask.setType(type);
            dataTask.setInputData1(bufferedReader.readLine());
            if(type.equals("Задача 1")) {
                dataTask.setInputData2(bufferedReader.readLine());
            }
            fileReader.close();
        } catch (IOException e){
            System.err.println(e);
        }
        return dataTask;
    }

    public static boolean delete(String filename){
        File file = new File("saves\\" + filename);

        return file.delete();

    }

    public static LinkedList<String> getListFiles(){
        LinkedList<String> res = new LinkedList<String>();
        File dir = new File("saves");
        if(dir.isDirectory()){
            for(File file : dir.listFiles()){
                res.add(file.getName());
            }
        }
        return res;
    }
}
