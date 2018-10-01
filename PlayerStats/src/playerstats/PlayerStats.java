/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerstats;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Justin
 */
public class PlayerStats {
    
    /**
     * 
     * @param vNames    The array where names will be stored.
     * @param vScores   The array where scores will be stored.
     * @param vFileName The file name.
     */
    public static void readFile(String[] vNames, int[] vScores, String vFileName){
        String currLine = "";
        String name = "";
        int score = 0;
        int index = 0;
        try {
            Scanner reader = new Scanner(new File(vFileName));
            
            while (reader.hasNext()){
                currLine = reader.nextLine();
                name = currLine.substring(0, currLine.indexOf(" "));
                score = Integer.parseInt(currLine.substring(currLine.indexOf(" ") + 1));
                
                vNames[index] = name;
                vScores[index] = score;
                index++;
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PlayerStats.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("File was not found in main method.");
        }
    }
    
    /**
     * 
     * @param vNames  The array of names
     * @param vScores The array of scores
     */
    public static void printArrays(String[] vNames, int[] vScores){
        String name = "";
        String output = "";
        String buffer = "------------------";
        int score = 0;
        
        int numElements = Array.getLength(vNames);
        
        System.out.println(buffer);
        System.out.println("|Name      |Score|");
        System.out.println(buffer);
        for (int i = 0; i < numElements; i++){
            name = vNames[i];
            score = vScores[i];
            
            output = String.format("|%-10s| %4d|", name, score);
            System.out.println(output);
            System.out.println(buffer);
        }
    }
    
    /**
     * 
     * @param vScores The array of scores.
     * @return Returns the average score.
     */
    public static double AvgScore(int[] vScores){
        double avgScore = 0.0;
        
        for (int score: vScores){
            avgScore += score;
        }
        avgScore /= 80.0;
        
        return avgScore;
    }
    
    public static void sortArrays(String[] vNames, int[] vScores){
        int minIndex = 0;
        int maxValue = -1;
        int maxIndex = -1;
        
        int tempScore = 0;
        String tempName = "";
        
        while (minIndex < 80){
            maxValue = -1;
            maxIndex = -1;
            for (int i = minIndex; i < 80; i++){
                if (vScores[i] > maxValue){
                    maxValue = vScores[i];
                    maxIndex = i;
                }
            }
            
            tempScore = vScores[minIndex];
            tempName = vNames[minIndex];
            
            vScores[minIndex] = maxValue;
            vNames[minIndex] = vNames[maxIndex];
            
            vScores[maxIndex] = tempScore;
            vNames[maxIndex] = tempName;
            
            minIndex++;
        }
    };
    
    public static void topPlayers(String[] vNames, int[] vScores, int vNumPlayers){
    String name = "";
    String output = "";
    String buffer = "------------------";
    int score = 0;
    
    System.out.println("Outputtting the top " + vNumPlayers + " players");
    System.out.println(buffer);
    System.out.println("|Name      |Score|");
    System.out.println(buffer);
    
    for (int i = 0; i < vNumPlayers; i++){
        name = vNames[i];
        score = vScores[i];
        
        output = String.format("|%-10s| %4d|", name, score);
        
        System.out.println(output);
        System.out.println(buffer);
    }
    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String[] playerNames = new String[80];
        int[] playerScores = new int[80];
        
        Scanner keyboard = new Scanner(System.in);
        
        String fileName = "";
        
        fileName = args[0];
         
        readFile(playerNames, playerScores, fileName);
        // printArrays(playerNames, playerScores);
        // System.out.println(AvgScore(playerScores));
        sortArrays(playerNames, playerScores);
        topPlayers(playerNames, playerScores, 10);
    }
    
}
