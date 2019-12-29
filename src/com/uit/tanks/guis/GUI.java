package com.uit.tanks.guis;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.containers.MainContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame implements IAttributeConstants, IActionExitGame,
        IImageConstants {
    private MainContainer mainContainer;

    public GUI() throws HeadlessException {
        initGUI();
        initComponents();
        addComponents();
        setIActions();
        addEventClosing();
    }

    private void initGUI() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        setIconImage(TANK);
        setUndecorated(false);
        setTitle("UIT Game2D Tanks");
        setSize(WIDTH_FRAME + 6, HEIGHT_FRAME + 35);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    private void initComponents() {
        mainContainer = new MainContainer();
    }

    private void addComponents() {
        add(mainContainer);
    }

    private void setIActions() {
        mainContainer.getStartPanel().setIActionExitGame(this);
        mainContainer.getMainMenuPanel().setIActionExitGame(this);
    }

    private void addEventClosing() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int click = JOptionPane.showConfirmDialog(GUI.this, "Do you want to exit?",
                        "Exit", JOptionPane.YES_NO_CANCEL_OPTION);
                if (click == JOptionPane.YES_OPTION) {
                    exitGame();
                }
            }
        });
    }

    @Override
    public void exitGame() {
        System.exit(0);
    }
}