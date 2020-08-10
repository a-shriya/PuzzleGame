package com.example;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Countdown
{
    final java.util.Timer timer = new Timer();
     JButton startCountdown()
    {
        JButton timer1 = new JButton();

        timer.scheduleAtFixedRate(new TimerTask()
        {
            int i = Integer.parseInt(String.valueOf(0));

            public void run()
            {
                System.out.println(++i + "");
            }
        }, 0, 1000);
        return timer1;
    }
}
