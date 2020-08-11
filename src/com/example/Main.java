package com.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Music music = new Music();
        String mainMusic = "C:\\Users\\srini\\IdeaProjects\\PuzzleGame\\music_zapsplat_game_music_action_fun_funky_electro_disco_023 (online-audio-converter.com).wav";
        Twoframes tf = new Twoframes();
        music.playMusic(mainMusic);
        SwingUtilities.invokeLater(tf::createAndDisplayGUI);
        HighScore highScore = new HighScore();
        String high = highScore.getHighScore();
        System.out.println("Least Move " + high.charAt(0));
        System.out.println("Least Time Taken " + high.charAt(1));
        System.out.println("HighestScorer is " + high.substring(2));
    }
}
