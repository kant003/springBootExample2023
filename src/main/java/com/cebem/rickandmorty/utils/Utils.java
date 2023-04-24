package com.cebem.rickandmorty.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static boolean isPalindrome(String word){
        String inverseWord = new StringBuilder(word)
            .reverse()
            .toString();
        return inverseWord.equalsIgnoreCase(word);
    }

    public static void writeOnDisk(String fileName, String info) throws IOException{
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName, true);
            fw.write(info);
        } finally{
            if(fw != null) fw.close();
        }
    }

    public static String readFromDisk(String fileName) 
                                throws FileNotFoundException, IOException{
        FileReader fr = null;
        BufferedReader br = null;
        try{
            fr = new FileReader("datos.txt");
            br = new BufferedReader(fr);
            String line;
            String content = "<ul>";
            while(  (line=br.readLine()) != null  ){
                content += "<li>"+ line+"</li>";
            }
            content += "</ul>";
            return content;
        }finally{
            if(fr!=null) fr.close();
            if(br!=null) br.close();
        }
    }

    public static void clearFile(String fileName) throws IOException{
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName, false); 
            fw.write("");
        } finally{
            if(fw != null) fw.close();
        }
    }

    public static boolean deleteFromDisk(String fileName){
        File f = new File(fileName);
        return f.delete();
    }

    public static float maxOfElements(float ...numeros) throws NumberFormatException{
        if(numeros == null || numeros.length==0)
            throw new NumberFormatException();

        float elMayor=numeros[0];
        for(int i=1;i<numeros.length;i++){
            if(numeros[i]>elMayor) elMayor = numeros[i];
        }
        return elMayor;
    }

    public static String capitalizeText(String text){
        String[] words = text.split(" ");
        String capitalizedText = "";
        for(String word : words){
          char fistLetter = Character.toUpperCase(word.charAt(0));
          String rest = word.substring(1).toLowerCase();
          capitalizedText += fistLetter + rest + " ";
        }
        return capitalizedText;
    }

    public static int getRandomValue(int max){
        return (int) Math.floor( Math.random() * max );
    }
}
