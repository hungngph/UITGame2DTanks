package com.uit.tanks.guis.containers.panels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IIconConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.IActionMusic;
import com.uit.tanks.guis.containers.IActionEnterGame;
import com.uit.tanks.utils.MyButton;
import com.uit.tanks.utils.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel implements IAttributeConstants, IIconConstants,
        IImageConstants, ActionListener, IOnMusic {
    private JButton btLeft;
    private JButton btRight;
    private JButton btUp;
    private JButton btDown;
    private JButton btCtrl;
    private JButton btPlayer1;

    private JButton btMainMenu;
    private JButton btMusic;

    private JLabel labelControls;
    private JLabel labelMusic;

    private IActionEnterGame iActionEnterGame;
    private IActionMusic iActionMusicGUI;

    private int onMusic = 0;

    public ControlsPanel() {
        initControlsPanel();
        initComponents();
        addComponents();
        addEvents();
    }

    private void initControlsPanel() {
        setLayout(null);
    }

    private void initComponents() {
        initLabelControls();
        initButtons();
    }

    private void initLabelControls() {
        labelControls = new JLabel();
        labelControls.setLocation(490, 30);
        labelControls.setText("<html><p align=\"center\">CONTROLS");
        labelControls.setForeground(Color.BLUE);

        Font fontLabelControls = new Font("Tahoma", Font.BOLD, 50);
        labelControls.setFont(fontLabelControls);

        FontMetrics mtLabelControls = getFontMetrics(fontLabelControls);
        int widthlabelControls = mtLabelControls.stringWidth("CONTROLS");
        labelControls.setSize(widthlabelControls, 50);
    }

    private void initButtons() {
        btLeft = new MyButton(BUTTON_LEFT, BUTTON_LEFT1,
                80, 80, 498, 245);
        btRight = new MyButton(BUTTON_RIGHT, BUTTON_RIGHT1,
                80, 80, 698, 245);
        btUp = new MyButton(BUTTON_UP, BUTTON_UP1,
                80, 80, 598, 145);
        btDown = new MyButton(BUTTON_DOWN, BUTTON_DOWN1,
                80, 80, 598, 245);
        btCtrl = new MyButton(BUTTON_CTRL, BUTTON_CTRL1,
                80, 80, 398, 245);

        btMainMenu = new MyButton(BUTTON_MENU, BUTTON_MENU1,
                300, 50, 168, 587);

        btMusic = new MyButton(BUTTON_MUSIC, BUTTON_MUSIC1,
                300, 50, 808, 587);
        btMusic.setLayout(null);
        labelMusic = new MyLabel("ON", 240, 5);
    }

    private void addComponents() {
        add(btLeft);
        add(btRight);
        add(btUp);
        add(btDown);
        add(btCtrl);
        add(btMainMenu);
        add(btMusic);
        add(labelControls);
        btMusic.add(labelMusic);
    }

    private void addEvents() {
        btMainMenu.addActionListener(this);
        btMainMenu.setActionCommand(MAIN_MENU_BUTTON);

        btMusic.addActionListener(this);
        btMusic.setActionCommand(MUSIC_BUTTON);
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
            case MAIN_MENU_BUTTON: {
                iActionEnterGame.showMainMenuPanel();
                break;
            }
            case MUSIC_BUTTON: {
                onMusic++;
                if (1 == onMusic % 2) {
                    labelMusic.setForeground(Color.RED);
                    labelMusic.setText("<html><p align=\"center\">OFF");

                    iActionMusicGUI.stopMusic();
                } else {
                    labelMusic.setForeground(Color.GREEN);
                    labelMusic.setText("<html><p align=\"center\">ON");

                    iActionMusicGUI.playMusic();
                }
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

    public void setIActionMusicGUI(IActionMusic iActionMusicGUI) {
        this.iActionMusicGUI = iActionMusicGUI;
    }

    @Override
    public int getOnMusic() {
        return onMusic;
    }
}