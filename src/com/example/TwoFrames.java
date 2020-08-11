package com.example;

import java.awt.*;
import javax.swing.*;

public class TwoFrames
{
    private String name;
    RadioButton radioButton = new RadioButton();

    // first JFrame.
    JFrame frame1 = new JFrame("WELCOME");
    // second JFrame.
    JFrame frame2;

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }


    public void createAndDisplayGUI()
    {
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLocationByPlatform(true);

        // Button to show the second JFrame.
        JButton showButton = new JButton("Enter your name to play");
        frame1.setContentPane(radioButton);
        frame1.add(showButton, BorderLayout.PAGE_END);
        frame1.pack();
        frame1.setVisible(true);
        frame1.setSize(400,400);

        showButton.addActionListener(ae ->
        {
            frame1.dispose();
            radioButton.setOpaque(true);
            makeNewFrame();
            frame2.setVisible(true);
            frame2.setSize(400,400);
        });
    }

    private void makeNewFrame()
    {
        frame2 = new JFrame("Enter Your Name");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLocationByPlatform(true);


        // Creating a JButton to be shown on the JFrame.
        JButton hideButton = new JButton("Play Game");
        JLabel greetings = new JLabel("Please enter your name");

        JTextField nameField = new JTextField();

        hideButton.addActionListener(ae ->
        {
            setName(nameField.getText());
            frame2.dispose();
            int dimension = radioButton.getDimension();
            Board gameBoard = new Board(dimension, getName());
            gameBoard.setTitle("ARRANGE PUZZLE GAME");
            gameBoard.scramble();
        });

        frame2.add(nameField);
        frame2.add(hideButton, BorderLayout.PAGE_END);
        frame2.pack();
    }

}
