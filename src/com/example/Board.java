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
    JButton[][] squares = new JButton[4][4];
    JButton timer1 = new JButton();
    JButton moves = new JButton();

    public Board()
    {
        Countdown countdown = new Countdown();
        countdown.startCountdown();
        Container container = getContentPane();
        container.setLayout((new GridLayout(5, 4)));

        container.add(new Label("Time"));
        container.add(timer1);
        container.add(new Label("Number of moves"));
        container.add(moves);
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                JButton num = new JButton();
                squares[i][j] = num;
                container.add(num);
            }
        }

        ButtonListener pushed = new ButtonListener();

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                squares[i][j].addActionListener(pushed);
            }
        }
    }

    public void scramble()
    {
        boolean[] used = new boolean[16];

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                int val = (int) (16 * Math.random());

                while (used[val])
                {
                    val = (int) (16 * Math.random());
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

            if (i < 3 && squares[i + 1][j].getBackground() == Color.blue)
                empty = squares[i + 1][j];
            if (i > 0 && squares[i - 1][j].getBackground() == Color.blue)
                empty = squares[i - 1][j];
            if (j < 3 && squares[i][j + 1].getBackground() == Color.blue)
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


//    void printResult()
//    {
//        while(isGameOver()) {
//            System.out.println("You Won!!!!!!!!!");
//        }
//    }


    boolean isGameOver()
    {
        boolean checkGameOver = true;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (squares[i][j].getText().equals((i * 4 + j + 1) + ""))
                {
                    checkGameOver = true;
                }
                else
                    {
                    checkGameOver = false;
                    break;
                }
            }
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
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
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