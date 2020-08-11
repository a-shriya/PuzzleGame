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
        String mainMusic = "C:\\Users\\anego\\IdeaProjects\\PuzzleGame\\music_zapsplat_game_music_action_fun_funky_electro_disco_023 (online-audio-converter.com).wav";
        TwoFrames tf = new TwoFrames();
        music.playMusic(mainMusic);
        SwingUtilities.invokeLater(tf::createAndDisplayGUI);
    }
}

