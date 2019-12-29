package com.uit.tanks.utils;

import javax.swing.*;
import java.awt.*;

public class MyImageIcon {
    public static Image getImage(String pathImage) {
        return new ImageIcon(MyImageIcon.class.getResource(pathImage)).getImage();
    }

    public static Icon getIcon(String pathIcon) {
        return new ImageIcon(MyImageIcon.class.getResource(pathIcon));
    }
}