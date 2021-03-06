package com.uit.tanks.guis;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IAudioConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.containers.MainContainer;
import com.uit.tanks.models.ObjectAudio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends JFrame implements IAttributeConstants, IActionExitGame,
        IImageConstants, IAudioConstants, IActionMusic {
    private MainContainer mainContainer;
    private ObjectAudio objectAudioGUI;

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
        objectAudioGUI = new ObjectAudio(SOUNDTRACK1);
        objectAudioGUI.loop();
    }

    private void addComponents() {
        add(mainContainer);
    }

    private void setIActions() {
        mainContainer.getStartPanel().setIActionExitGame(this);
        mainContainer.getMainMenuPanel().setIActionExitGame(this);
        mainContainer.getControlsPanel().setIActionMusicGUI(this);
        mainContainer.getMainMenuPanel().setIActionMusicGUI(this);
        mainContainer.getGamePanel().getScorePanel().setIActionMusicGUI(this);
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

    @Override
    public void playMusic() {
        objectAudioGUI.loop();
    }

    @Override
    public void stopMusic() {
        objectAudioGUI.stop();
    }
}