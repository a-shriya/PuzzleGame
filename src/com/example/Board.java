package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Board extends JFrame implements Serializable
{
    static int numberOfMoves = 0;
    private int dimension;
    JButton[][] squares = new JButton[10][10];
    JButton timer1 = new JButton();
    JButton moves = new JButton();
    JButton undoButton = new JButton("undo");
    JFrame frame = new JFrame();
    JButton emptyFinal = null;
    int iFinal = -1;
    int jFinal = -1;
    int iEmpty = -1;
    int jEmpty = -1;




    public Board(int dimension)
    {
        JPanel jPanel2 = new JPanel();
        this.dimension = dimension;


        Board.Countdown countdown = new Board.Countdown();
        countdown.startCountdown();
        UndoListener undo = new UndoListener();
        undoButton.addActionListener(undo);
        jPanel2.setLayout((new GridLayout(dimension, dimension)));


        Font bigFont = new Font("serif", Font.BOLD, 14);
        Font smallFont = new Font("serif", Font.BOLD, 12);

        JPanel jPanel = new JPanel();
        Label label1 = new Label("Time   :");
        label1.setFont(smallFont);
        Label label2 = new Label("   Number of moves :");
        label2.setFont(smallFont);
        jPanel.add(label1);
        moves.setText("0");
        timer1.setFont(bigFont);
        moves.setFont(bigFont);
        undoButton.setFont(bigFont);
        jPanel.add(timer1);
        jPanel.add(label2);
        jPanel.add(moves);
        jPanel.add(undoButton);








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

        frame.getContentPane().add(BorderLayout.NORTH, jPanel);
        frame.getContentPane().add(BorderLayout.CENTER, jPanel2);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void scramble()
    {
        boolean[] used = new boolean[dimension * dimension];

        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                int val = (int) (dimension * dimension * Math.random());

                while (used[val])
                {
                    val = (int) (dimension * dimension * Math.random());
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

        if (i < dimension - 1 && squares[i + 1][j].getBackground() == Color.blue)
        {
            empty = squares[i + 1][j];
            iEmpty = i + 1;
            jEmpty = j;
        }
        if (i > 0 && squares[i - 1][j].getBackground() == Color.blue)
        {
            empty = squares[i - 1][j];
            iEmpty = i - 1;
            jEmpty = j;
        }
        if (j < dimension - 1 && squares[i][j + 1].getBackground() == Color.blue)
        {
            empty = squares[i][j + 1];
            iEmpty = i;
            jEmpty = j + 1;
        }
        if (j > 0 && squares[i][j - 1].getBackground() == Color.blue)
        {
            empty = squares[i][j - 1];
            iEmpty = i;
            jEmpty = j - 1;
        }

        if (empty == null)
        {
            return;
        }
        else
        {
            emptyFinal = empty;
            iFinal = i;
            jFinal = j;
            empty.setText(squares[i][j].getText());
            empty.setBackground(Color.lightGray);
            squares[i][j].setText("");
            squares[i][j].setBackground(Color.blue);
        }
        numberOfMoves++;
        moves.setText(String.valueOf(numberOfMoves));


    }


    void undo()
    {
        if( iEmpty != -1 && jEmpty != -1)
        {
            try
            {
                squares[iFinal][jFinal].setText(squares[iEmpty][jEmpty].getText());
                squares[iFinal][jFinal].setBackground(Color.lightGray);
                squares[iEmpty][jEmpty].setText("");
                squares[iEmpty][jEmpty].setBackground(Color.blue);
                int temp1, temp2;
                temp1 = iEmpty;
                temp2 = jEmpty;
                iEmpty = iFinal;
                jEmpty = jFinal;
                jFinal = temp2;
                iFinal = temp1;
            }
            catch (NullPointerException ignored)
            {
            }
        }

    }

    class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Object square = e.getSource();

            outer:
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {

                    if (squares[i][j] == square) {
                        moveSquare(i, j);
                        break outer;
                    }
                }
            }

            boolean check = true;
            int count = 0;
            for (int i = 0; i < dimension*dimension; i++)
            {
                int row = i / dimension;
                int col = i % dimension;

                check = squares[row][col].getText().equals(String.valueOf(row*dimension + col + 1));
                if(check)
                {
                    count++;
                }
            }
            if(count == (dimension*dimension -1))
                JOptionPane.showMessageDialog(Board.this,"!!!you won!!!");

        }
    }



    class UndoListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            undo();
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
