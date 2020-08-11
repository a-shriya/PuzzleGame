package com.example;

import java.io.*;
import java.util.Scanner;

public class HighScore
{
    void setHighScore(int leastTimeTaken, int leastMove, String highScorer)
    {
        String fileName = "HighScorePuzzleGame.txt";
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName));
            out.write(Integer.toString(leastMove));
            out.write(Integer.toString(leastTimeTaken));
            out.write(highScorer);
            out.close();
        }
        catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }
    }
    String getHighScore()
    {
        try {
            File myObj = new File("C:\\Users\\srini\\IdeaProjects\\PuzzleGame\\HighScorePuzzleGame.txt");
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            myReader.close();
            return data;
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return " ";
    }
}
