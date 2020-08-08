package com.example;
import java.util.Timer;
import javax.swing.*;
import java.util.TimerTask;

public class Main
{
    public static void main(String[] args)
    {

        Board gameBoard = new Board();
        gameBoard.setTitle ("ARRANGE PUZZLE GAME");
        gameBoard.setSize( 400,  400);
        gameBoard.setVisible( true);
        gameBoard.scramble ();
        gameBoard.printResult();
        gameBoard.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    }
}
