package com.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        Music music = new Music();
        String mainMusic = "C:\\Users\\srini\\IdeaProjects\\PuzzleGame\\music_zapsplat_game_music_action_fun_funky_electro_disco_023 (online-audio-converter.com).wav";

        music.playMusic(mainMusic);
        SwingUtilities.invokeLater(() ->
        {
            TwoFrames tf = new TwoFrames();
            tf.createAndDisplayGUI();
        });
    }

    static class TwoFrames
    {
        String y = "";
        // Making our first JFrame.
        JFrame frame1 = new JFrame("WELCOME");
        // Declaring our second JFrame.
        JFrame frame2 ;
        public void createAndDisplayGUI()
        {
            // Used to close the JFrame graciously.
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Use this instead for placing windows, as determined by the OS.
            frame1.setLocationByPlatform(true);

            // Button to show the second JFrame.
            JButton showButton = new JButton("PLAY GAME");
            RadioButton radioButton = new RadioButton();
            JComponent newContentPane = radioButton;
            newContentPane.setOpaque(true);
            frame1.setContentPane(newContentPane);
            frame1.add(showButton, BorderLayout.PAGE_END);
            frame1.pack();
            frame1.setVisible(true);
            showButton.addActionListener(ae ->
            {
                int dimension = radioButton.getDimension();
                Board gameBoard = new Board(dimension);
                gameBoard.setTitle("ARRANGE PUZZLE GAME");
                gameBoard.scramble();
            });
        }
    }
}
