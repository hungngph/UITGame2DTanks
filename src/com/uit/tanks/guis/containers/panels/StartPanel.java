package com.uit.tanks.guis.containers.panels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IIconConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.IActionExitGame;
import com.uit.tanks.guis.containers.IActionEnterGame;
import com.uit.tanks.utils.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel implements IAttributeConstants, IIconConstants,
        IImageConstants, ActionListener {
    private JButton btStart;
    private JButton btExit;

    private JLabel labelStart;

    private IActionEnterGame iActionEnterGame;
    private IActionExitGame iActionExitGame;

    public StartPanel() {
        initStartPanel();
        initComponents();
        addComponents();
        addEvents();
    }

    private void initStartPanel() {
        setLayout(null);
    }

    private void initLabelStart() {
        labelStart = new JLabel();
        labelStart.setLocation(223, 50);
        labelStart.setText("<html><p align=\"center\">Welcome To UITGame2DTanks !");
        labelStart.setForeground(Color.BLUE);

        Font fontLabelMenu = new Font("Tahoma", Font.BOLD, 50);
        labelStart.setFont(fontLabelMenu);

        FontMetrics mtLabelTutorial = getFontMetrics(fontLabelMenu);
        int widthlabelMenu = mtLabelTutorial.stringWidth(" Welcome To UITGame2DTanks ! ");
        labelStart.setSize(widthlabelMenu, 50);
    }

    private void initComponents() {
        initLabelStart();
        btStart = new MyButton(BUTTON_START, BUTTON_START1,
                300, 50, 488, 425);
        btExit = new MyButton(BUTTON_EXIT, BUTTON_EXIT1,
                300, 50, 488, 525);
    }

    private void addComponents() {
        add(labelStart);
        add(btStart);
        add(btExit);
    }

    private void addEvents() {
        btStart.addActionListener(this);
        btStart.setActionCommand(START_BUTTON);

        btExit.addActionListener(this);
        btExit.setActionCommand(EXIT_BUTTON);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(BACKGROUND1, 0, 0, WIDTH_FRAME, HEIGHT_FRAME, null);
        graphics2D.drawImage(LOGO_UIT, 520, 115, 250, 200, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idButton = e.getActionCommand();
        switch (idButton) {
            case START_BUTTON: {
                iActionEnterGame.showMainMenuPanel();
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
}