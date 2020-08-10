package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButton extends  JPanel implements ActionListener
{
    private int dimension;
    public int getDimension()
    {
        return dimension;
    }
    static String grid1 = "2X2";
    static String grid2 = "3X3";
    static String grid3 = "4X4";
    static String grid4 = "5X5";
    static String grid5 = "5X5";
    static String grid6 = "5X5";
    static String grid7 = "5X5";
    static String grid8 = "5X5";
    static String grid9 = "5X5";

    JRadioButton button1 = new JRadioButton(grid1);
    JRadioButton button2 = new JRadioButton(grid2);
    JRadioButton button3 = new JRadioButton(grid3);
    JRadioButton button4 = new JRadioButton(grid4);


    public RadioButton()
    {
        super(new BorderLayout());



        button1.setActionCommand(grid1);
        button1.setSelected(true);



        button2.setActionCommand(grid2);
        button2.setSelected(true);



        button3.setActionCommand(grid3);
        button3.setSelected(true);



        button4.setActionCommand(grid4);
        button4.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        JPanel radioPanel = new JPanel(new GridLayout(0,1));
        radioPanel.add(button1);
        radioPanel.add(button2);
        radioPanel.add(button3);
        radioPanel.add(button4);

        add(radioPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button1)
        {
            dimension = 2;
        }

        else if(e.getSource() == button2)
        {
            dimension = 3;
        }

        else if(e.getSource() == button3)
        {
            dimension = 4;
        }

        else if(e.getSource() == button4)
        {
            dimension = 5;
        }
    }

}
