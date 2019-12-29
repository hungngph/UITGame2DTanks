package com.uit.tanks.utils;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {

    public MyButton(Icon iconButton, Icon iconButton1,
                    int widthButton, int heightButton, int xButton, int yButton) {
        setIcon(iconButton);
        setContentAreaFilled(false);
        setLocation(xButton, yButton);
        setSize(widthButton, heightButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(iconButton1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(iconButton);
            }
        });
    }
}