package com.uit.tanks.utils;

import com.uit.tanks.common.IAudioConstants;
import com.uit.tanks.models.ObjectAudio;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton implements IAudioConstants {
    private ObjectAudio objectAudio;

    public MyButton(Icon iconButton, Icon iconButton1,
                    int widthButton, int heightButton, int xButton, int yButton) {
        setIcon(iconButton);
        setContentAreaFilled(false);
        setLocation(xButton, yButton);
        setSize(widthButton, heightButton);

        objectAudio = new ObjectAudio(CLICK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(iconButton1);
                objectAudio.play();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(iconButton);
            }
        });
    }
}