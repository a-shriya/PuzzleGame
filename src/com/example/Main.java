package com.example;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        Board gameBoard = new Board(2);
        gameBoard.setTitle("ARRANGE PUZZLE GAME");
        gameBoard.printResult();
        gameBoard.scramble ();
    }
}
