package com.uit.tanks.guis.containers.panels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IAudioConstants;
import com.uit.tanks.common.IIconConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.IActionExitGame;
import com.uit.tanks.guis.IActionMusic;
import com.uit.tanks.guis.containers.IActionEnterGame;
import com.uit.tanks.models.ObjectAudio;
import com.uit.tanks.utils.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel implements IAttributeConstants, IIconConstants,
        IImageConstants, ActionListener, IAudioConstants, IActionMusic {
    private JButton bt1Player;
    private JButton btControls;
    private JButton btExit;

    private JLabel labelMainMenu;

    private IActionEnterGame iActionEnterGame;
    private IActionExitGame iActionExitGame;
    private IActionThread iActionThread;
    private IActionMusic iActionMusicGUI;

    private ObjectAudio objectAudioPlay;

    public MainMenuPanel() {
        initMenuPanel();
        initComponents();
        addComponents();
        addEvents();
        objectAudioPlay = new ObjectAudio(SOUNDTRACK2);
    }

    private void initMenuPanel() {
        setLayout(null);
    }

    private void initComponents() {
        initLabelMenu();
        initButtons();
    }

    private void initLabelMenu() {
        labelMainMenu = new JLabel();
        labelMainMenu.setLocation(483, 50);
        labelMainMenu.setText("<html><p align=\"center\">MAIN MENU");
        labelMainMenu.setForeground(Color.BLUE);

        Font fontLabelMenu = new Font("Tahoma", Font.BOLD, 50);
        labelMainMenu.setFont(fontLabelMenu);

        FontMetrics mtLabelTutorial = getFontMetrics(fontLabelMenu);
        int widthlabelMenu = mtLabelTutorial.stringWidth(" MAIN MENU ");
        labelMainMenu.setSize(widthlabelMenu, 50);
    }

    private void initButtons() {
        bt1Player = new MyButton(BUTTON_1PLAYER, BUTTON_1PLAYER1,
                300, 50, 488, 225);
        btControls = new MyButton(BUTTON_CONTROLS, BUTTON_CONTROLS1,
                300, 50, 488, 325);
        btExit = new MyButton(BUTTON_EXIT, BUTTON_EXIT1,
                300, 50, 488, 425);
    }

    private void addComponents() {
        add(labelMainMenu);
        add(bt1Player);
        add(btControls);
        add(btExit);
    }

    private void addEvents() {
        bt1Player.addActionListener(this);
        bt1Player.setActionCommand(ONE_PLAYER_BUTTON);

        btControls.addActionListener(this);
        btControls.setActionCommand(CONTROLS_BUTTON);

        btExit.addActionListener(this);
        btExit.setActionCommand(EXIT_BUTTON);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(BACKGROUND2, 0, 0, WIDTH_FRAME, HEIGHT_FRAME, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idButton = e.getActionCommand();
        switch (idButton) {
            case ONE_PLAYER_BUTTON: {
                iActionMusicGUI.stopMusic();
                objectAudioPlay.loop();
                iActionEnterGame.showGamePanel();
                iActionThread.startGame(new Thread(iActionEnterGame.addRunnable()));
                iActionThread.setIsImperishable(false);
                break;
            }
            case CONTROLS_BUTTON: {
                iActionEnterGame.showControlsPanel();
                break;
            }
            case EXIT_BUTTON: {
                iActionExitGame.exitGame();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setIActionEnterGame(IActionEnterGame iActionEnterGame) {
        this.iActionEnterGame = iActionEnterGame;
    }

    public void setIActionExitGame(IActionExitGame iActionExitGame) {
        this.iActionExitGame = iActionExitGame;
    }

    public void setIActionThread(IActionThread iActionThread) {
        this.iActionThread = iActionThread;
    }

    public void setIActionMusicGUI(IActionMusic iActionMusicGUI) {
        this.iActionMusicGUI = iActionMusicGUI;
    }

    @Override
    public void playMusic() {
        objectAudioPlay.loop();
    }

    @Override
    public void stopMusic() {
        objectAudioPlay.stop();
    }
}