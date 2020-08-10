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
        music.playMusic("C:\\Users\\anego\\IdeaProjects\\PuzzleGame\\music_zapsplat_game_music_action_fun_funky_electro_disco_023 (online-audio-converter.com).wav");
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                TwoFrames tf = new TwoFrames();
                tf.createAndDisplayGUI();
            }
        });
    }

        static class TwoFrames
        {
            String y = "";
            // Making our first JFrame.
            JFrame frame1 = new JFrame("WELCOME");
            // Declaring our second JFrame.
            JFrame frame2 ;

            String s = new String();
            public void createAndDisplayGUI()
            {
                // Used to close the JFrame graciously.
                frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Used to position the JFrame at the middle of the screen.
                //frame1.setLocationRelativeTo(null);

                // Use this instead for placing windows, as determined by the OS.
                frame1.setLocationByPlatform(true);

                // Calling this method to create our frame2.
                makeNewFrame();

                // Button to show the second JFrame.
                JButton showButton = new JButton("PLAY GAME");
                RadioButton radioButton = new RadioButton();
                JComponent newContentPane = radioButton;
                newContentPane.setOpaque(true);
                frame1.setContentPane(newContentPane);


//                JTextField textField = new JTextField();
//                s = textField.getText();
//                frame1.add(textField);
//                y = textField.getText();
//                System.out.print(y);
                frame1.add(showButton, BorderLayout.PAGE_END);
                frame1.pack();
                frame1.setVisible(true);
                showButton.addActionListener(new ActionListener()
                {

                    public void actionPerformed(ActionEvent ae)
                    {

                        // Checking if the frame2 is already visible
                        // on the screen, if YES then we won't
                        // create a new frame, else a new frame
                        // will be created. This will prevent multiple
                        // JFrame to be created at the click of this button.
                        if (!(frame2.isShowing()))
                        {
                            // If  you had already disposed it previously
                            // by clicking the hide Button on the other frame
                            // then the click on this button will recreate
                             //a new frame to be displayed.
                            makeNewFrame();
                            frame2.setVisible(true);
                        }
                        int dimension = radioButton.getDimension();
        Board gameBoard = new Board(dimension);
        gameBoard.setTitle("ARRANGE PUZZLE GAME");
        gameBoard.scramble();
                    }
                });

                // Adding the button to the South side of the frame1.
            }

            private void makeNewFrame()
            {
                frame2 = new JFrame("FRAME 2");
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame2.setLocationByPlatform(true);

                JLabel button = new JLabel(s + "");


                // Creating a JButton to be shown on the JFrame.
                JButton hideButton = new JButton("HIDE FRAME");
                hideButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent ae)
                    {
                        // On the click of this button, frame2 will
                        // disappear and HAI will be displayed on the console.
                        frame2.dispose();
                        System.out.println("HAI");

                    }
                });

                // Adding the button to the South side of the frame1.
                frame2.add(button);
                frame2.add(hideButton, BorderLayout.PAGE_END);
                frame2.pack();
            }
//
//
//                /* Here we are Scheduling a JOB for
//                 * Event Dispatcher Thread, since Swing
//                 * is not Thread Safe. This is used to place
//                 * the code which is responsible for
//                 * creating and displaying your GUI.


            }
    }
