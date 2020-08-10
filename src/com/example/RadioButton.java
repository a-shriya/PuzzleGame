package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadioButton extends  JPanel implements ActionListener
{
    private int dimension = 2;
    public int getDimension()
    {
        return dimension;
    }
    public void setDimension(int dimension)
    {
        this.dimension = dimension;
    }
    static String grid1 = "2X2";
    static String grid2 = "3X3";
    static String grid3 = "4X4";
    static String grid4 = "5X5";
    static String grid5 = "6X6";
    static String grid6 = "7X7";
    static String grid7 = "8X8";
    static String grid8 = "9X9";
    static String grid9 = "10X10";

    JRadioButton button1 = new JRadioButton(grid1);
    JRadioButton button2 = new JRadioButton(grid2);
    JRadioButton button3 = new JRadioButton(grid3);
    JRadioButton button4 = new JRadioButton(grid4);
    JRadioButton button5 = new JRadioButton(grid5);
    JRadioButton button6 = new JRadioButton(grid6);
    JRadioButton button7 = new JRadioButton(grid7);
    JRadioButton button8 = new JRadioButton(grid8);
    JRadioButton button9 = new JRadioButton(grid9);


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

        button5.setActionCommand(grid5);
        button5.setSelected(true);

        button6.setActionCommand(grid6);
        button6.setSelected(true);

        button7.setActionCommand(grid7);
        button7.setSelected(true);

        button8.setActionCommand(grid8);
        button8.setSelected(true);

        button9.setActionCommand(grid9);
        button9.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(button1);
        group.add(button2);
        group.add(button3);
        group.add(button4);
        group.add(button5);
        group.add(button6);
        group.add(button7);
        group.add(button8);
        group.add(button9);


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);

        JPanel radioPanel = new JPanel(new GridLayout(0,1));
        radioPanel.add(button1);
        radioPanel.add(button2);
        radioPanel.add(button3);
        radioPanel.add(button4);
        radioPanel.add(button5);
        radioPanel.add(button6);
        radioPanel.add(button7);
        radioPanel.add(button8);
        radioPanel.add(button9);

        add(radioPanel, BorderLayout.LINE_START);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == button1)
        {
            setDimension(2);
        }

        else if(e.getSource() == button2)
        {
            setDimension(3);
        }

        else if(e.getSource() == button3)
        {
            setDimension(4);
        }

        else if(e.getSource() == button4)
        {
            setDimension(5);
        }

        else if(e.getSource() == button5)
        {
            setDimension(6);
        }

        else if(e.getSource() == button6)
        {
            setDimension(7);
        }

        else if(e.getSource() == button7)
        {
            setDimension(8);
        }

        else if(e.getSource() == button8)
        {
            setDimension(9);
        }

        else if(e.getSource() == button9)
        {
            setDimension(10);
        }
    }

}
