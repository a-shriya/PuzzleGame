package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JFrame
{
    static int numberOfMoves = 0;
    private int dimension;
    static int count;
    JButton[][] squares = new JButton[10][10];
    JButton timer1 = new JButton();
    JButton moves = new JButton();

    public Board(int dimension)
    {

        this.dimension = dimension;

        JPanel jPanel2 = new JPanel();

        Board.Countdown countdown = new Board.Countdown();
        countdown.startCountdown();
        jPanel2.setLayout((new GridLayout(dimension, dimension)));


        Font bigFont = new Font("serif", Font.BOLD, 16);
        Font smallFont = new Font("serif", Font.BOLD, 14);

        JPanel jPanel = new JPanel();
        Label label1 = new Label("Time   :");
        label1.setFont(smallFont);
        Label label2 = new Label("   Number of moves :");
        label2.setFont(smallFont);
        jPanel.add(label1);
        moves.setText("0");
        timer1.setFont(bigFont);
        moves.setFont(bigFont);
        jPanel.add(timer1);
        jPanel.add(label2);
        jPanel.add(moves);


        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                JButton num = new JButton();
                squares[i][j] = num;
                jPanel2.add(num);
            }
        }

        Board.ButtonListener pushed = new Board.ButtonListener();

        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                squares[i][j].addActionListener(pushed);
            }
        }

        JFrame frame = new JFrame();
        frame.getContentPane().add(BorderLayout.NORTH, jPanel);
        frame.getContentPane().add(BorderLayout.CENTER, jPanel2);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void scramble()
    {
        boolean[] used = new boolean[dimension*dimension];

        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                int val = (int) (dimension*dimension * Math.random());

                while (used[val])
                {
                    val = (int) (dimension*dimension * Math.random());
                }

                used[val] = true;

                if (val != 0)
                {
                    squares[i][j].setText("" + val);
                    squares[i][j].setBackground(Color.lightGray);
                }
                else
                {
                    squares[i][j].setBackground(Color.blue);
                }
            }
        }
    }



    public void moveSquare(int i, int j)
    {
        JButton empty = null;

        if (i < dimension-1 && squares[i + 1][j].getBackground() == Color.blue)
            empty = squares[i + 1][j];
        if (i > 0 && squares[i - 1][j].getBackground() == Color.blue)
            empty = squares[i - 1][j];
        if (j < dimension-1 && squares[i][j + 1].getBackground() == Color.blue)
            empty = squares[i][j + 1];
        if (j > 0 && squares[i][j - 1].getBackground() == Color.blue)
            empty = squares[i][j - 1];

        if (empty == null)
        {
            return;
        }
        else
        {
            empty.setText(squares[i][j].getText());
            empty.setBackground(Color.lightGray);
            squares[i][j].setText("");
            squares[i][j].setBackground(Color.blue);
        }
        numberOfMoves++;
        moves.setText(String.valueOf(numberOfMoves));
    }


    void printResult()
    {
        if(count == dimension-1) {
            System.out.println("You Won!!!!!!!!!");
        }
    }


    boolean isGameOver()
    {
        boolean checkGameOver = true;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (squares[i][j].getText().equals(String.valueOf((i * dimension) + j + 1)) && squares[dimension][dimension].getBackground() == Color.blue)
                {
                    checkGameOver = true;
                    count ++;
                    System.out.println("Won");
                } else {
                    checkGameOver = false;
                }
            }
        }
        if(count == dimension-1) {
            System.out.println("You Won!!!!!!!!!");
        }

        return checkGameOver;

    }

    class ButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object square = e.getSource();

            outer:
            for (int i = 0; i < dimension; i++)
            {
                for (int j = 0; j < dimension; j++)
                {
                    if (squares[i][j] == square)
                    {
                        moveSquare(i, j);
                        break outer;
                    }
                }
            }
        }
    }

    class Countdown
    {
        final java.util.Timer timer = new Timer();
        void startCountdown()
        {

            timer.scheduleAtFixedRate(new TimerTask()
            {
                int i = Integer.parseInt(String.valueOf(0));

                public void run()
                {
                    timer1.setText(++i + "");
                }
            }, 0, 1000);
        }
    }
}
