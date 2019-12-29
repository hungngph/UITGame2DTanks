package com.uit.tanks.guis.containers.panels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IIconConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.containers.IActionEnterGame;
import com.uit.tanks.utils.MyButton;
import com.uit.tanks.utils.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlsPanel extends JPanel implements IAttributeConstants, IIconConstants,
        IImageConstants, ActionListener {
    private JButton btLeft;
    private JButton btRight;
    private JButton btUp;
    private JButton btDown;
    private JButton btCtrl;
    private JButton btPlayer1;

    private JButton btMainMenu;

    private JLabel labelControls;
    private JLabel labelMusic;

    private IActionEnterGame iActionEnterGame;

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
        labelControls.setLocation(483, 30);
        labelControls.setText("<html><p align=\"center\">CONTROLS");
        labelControls.setForeground(Color.RED);

        Font fontLabelControls = new Font("Tahoma", Font.BOLD, 50);
        labelControls.setFont(fontLabelControls);

        FontMetrics mtLabelControls = getFontMetrics(fontLabelControls);
        int widthlabelControls = mtLabelControls.stringWidth("CONTROLS");
        labelControls.setSize(widthlabelControls, 50);
    }

    private void initButtons() {
        btLeft = new MyButton(BUTTON_LEFT, BUTTON_LEFT1,
                80, 80, 738, 195);
        btRight = new MyButton(BUTTON_RIGHT, BUTTON_RIGHT1,
                80, 80, 938, 195);
        btUp = new MyButton(BUTTON_UP, BUTTON_UP1,
                80, 80, 838, 95);
        btDown = new MyButton(BUTTON_DOWN, BUTTON_DOWN1,
                80, 80, 838, 195);
        btCtrl = new MyButton(BUTTON_CTRL, BUTTON_CTRL1,
                80, 80, 638, 195);
        btPlayer1 = new MyButton(BUTTON_PLAYER1, BUTTON_PLAYER11,
                300, 50, 268, 155);

        btMainMenu = new MyButton(BUTTON_MENU, BUTTON_MENU1,
                300, 50, 168, 587);

        labelMusic = new MyLabel("ON", 240, 5);
    }

    private void addComponents() {
        add(btLeft);
        add(btRight);
        add(btUp);
        add(btDown);
        add(btCtrl);
        add(btPlayer1);

        add(btMainMenu);

        add(labelControls);
    }

    private void addEvents() {
        btMainMenu.addActionListener(this);
        btMainMenu.setActionCommand(MAIN_MENU_BUTTON);

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

            default: {
                break;
            }
        }
    }

    public void setIActionEnterGame(IActionEnterGame iActionEnterGame) {
        this.iActionEnterGame = iActionEnterGame;
    }

}