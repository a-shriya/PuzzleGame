package com.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;



public class Board extends JFrame
{
    //Global declaration
    private String highScorer;
    String name ;
    String musicMove = "C:\\Users\\anego\\IdeaProjects\\PuzzleGame\\multimedia_button_click_025 (online-audio-converter.com).wav";
    String winMusic = "C:\\Users\\anego\\IdeaProjects\\PuzzleGame\\little_robot_sound_factory_Jingle_Win_Synth_02 (online-audio-converter.com).wav";
    String errorMusic = "C:\\Users\\anego\\IdeaProjects\\PuzzleGame\\zapsplat_multimedia_alert_error_002_26393 (online-audio-converter.com).wav";


    JButton[][] squares = new JButton[10][10];
    JButton timer1 = new JButton();
    JButton moves = new JButton();
    JButton undoButton = new JButton("Undo");
    JButton emptyFinal = null;

    static int numberOfMoves = 0;
    private final int dimension;
    int iFinal = -1;
    int jFinal = -1;
    int iEmpty = -1;
    private int leastMove = 100;
    private int leastTimeTaken = 100;
    int jEmpty = -1;

    Music music1 = new Music();
    TwoFrames twoframes = new TwoFrames();
    HighScore highScore = new HighScore();
    Board.Countdown countdown = new Board.Countdown();
    JFrame frame = new JFrame();
    UndoListener undo = new UndoListener();

    //Constructor

    public Board(int dimension, String name)
    {
        JPanel jPanel2 = new JPanel();
        this.dimension = dimension;
        this.name = name;
        countdown.startCountdown();
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

        Board.ButtonListener pushed = new Board.ButtonListener();
        for (int i = 0; i < dimension; i++)
        {
            for (int j = 0; j < dimension; j++)
            {
                JButton num = new JButton();
                squares[i][j] = num;
                num.setFont(bigFont);
                jPanel2.add(num);
                squares[i][j].addActionListener(pushed);
            }
        }

        frame.getContentPane().add(BorderLayout.NORTH, jPanel);
        frame.getContentPane().add(BorderLayout.CENTER, jPanel2);


        if(dimension < 5)
            frame.setSize(400, 400);
        else
            frame.setSize(1000,1000);
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



    public void moveSquare(int i, int j) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        JButton empty = null;

        if (i < dimension - 1 && squares[i + 1][j].getBackground() == Color.blue)
        {
            empty = squares[i + 1][j];
            iEmpty = i + 1;
            jEmpty = j;
            music1.playMusic(musicMove);
        }

        if (i > 0 && squares[i - 1][j].getBackground() == Color.blue)
        {
            empty = squares[i - 1][j];
            iEmpty = i - 1;
            jEmpty = j;
            music1.playMusic(musicMove);
        }

        if (j < dimension - 1 && squares[i][j + 1].getBackground() == Color.blue)
        {
            empty = squares[i][j + 1];
            iEmpty = i;
            jEmpty = j + 1;
            music1.playMusic(musicMove);
        }

        if (j > 0 && squares[i][j - 1].getBackground() == Color.blue)
        {
            empty = squares[i][j - 1];
            iEmpty = i;
            jEmpty = j - 1;
            music1.playMusic(musicMove);
        }

        if (empty == null)
        {
            music1.playMusic(errorMusic);
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


    //Getters & Setters

    public int getLeastMove()
    {
        return leastMove;
    }

    public void setLeastMove(int leastMove)
    {
        this.leastMove = leastMove;
    }

    public int getLeastTimeTaken()
    {
        return leastTimeTaken;
    }

    public void setLeastTimeTaken(int leastTimeTaken)
    {
        this.leastTimeTaken = leastTimeTaken;
    }


    public String getHighScorer()
    {
        return highScorer;
    }

    public void setHighScorer(String highScorer)
    {
        this.highScorer = highScorer;
    }



    public void checkHighestScore(int time, int move)
    {
        if(getLeastTimeTaken() > time && getLeastMove() > move)
        {
            setHighScorer(name);
            setLeastMove(move);
            setLeastTimeTaken(time);
            highScore.setHighScore(getLeastTimeTaken(),getLeastMove(),getHighScorer());
            String high = highScore.getHighScore();

            System.out.println("Least Move " + high.charAt(0));
            System.out.println("Least Time Taken " + high.charAt(1));
            System.out.println("Highest Scorer is " + high.substring(2));

            setName(high.substring(2));
            setLeastTimeTaken(high.charAt(1));
            setLeastMove(high.charAt(0));
        }
    }


    //Inner Class

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
                        try
                        {
                            moveSquare(i, j);
                        }
                        catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException)
                        {
                            unsupportedAudioFileException.printStackTrace();
                        }

                        break outer;
                    }
                }
            }

            //Check winner
            boolean check;
            int count = 0;
            for (int j = 0; j < dimension*dimension; j++)
            {
                int row = j / dimension;
                int col = j % dimension;

                check = squares[row][col].getText().equals(String.valueOf(row*dimension + col + 1));

                if(check)
                {
                    count++;
                }
            }


            if(count == (dimension * dimension - 1))
            {
                try
                {
                    music1.playMusic(winMusic);
                }
                catch (IOException | UnsupportedAudioFileException | LineUnavailableException ioException)
                {
                    ioException.printStackTrace();
                }

                checkHighestScore(Integer.parseInt(timer1.getText()), Integer.parseInt(moves.getText()));
                JOptionPane.showMessageDialog(Board.this, "You Have Won, " + getName() + "!");
            }
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