package com.uit.tanks.guis.containers.panels.gamepanels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IIconConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.containers.IActionEnterGame;
import com.uit.tanks.guis.containers.panels.IActionThread;
import com.uit.tanks.managers.ManagerExplosions;
import com.uit.tanks.managers.ManagerImmovableItems;
import com.uit.tanks.utils.MyButton;
import com.uit.tanks.utils.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel implements IAttributeConstants, IIconConstants,
        ActionListener, IActionPlayGame, IImageConstants {
    private ManagerImmovableItems managerImmovableItems;

    private JLabel labelScore;
    private JLabel labelPlayer;
    private JLabel labelPause;

    private JButton btPause;
    private JButton btMainMenu;

    private IActionEnterGame iActionEnterGame;
    private IActionThread iActionThread;
    private IActionShowGame iActionShowGame;

    private int isPause = 0;

    public ScorePanel() {
        initScorePanel();
        initComponents();
        addComponents();
        addEvents();
    }

    private void initScorePanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        setSize(WIDTH_FRAME - WIDTH_PLAY_PANEL, HEIGHT_FRAME);
        setLocation(WIDTH_PLAY_PANEL + 1, 0);
    }

    private void initComponents() {
        initManagersItems();
        initLabelPlayer();
        initButtons();
    }

    private void initManagersItems() {
        managerImmovableItems = new ManagerImmovableItems(MAP_MENU, ROW_MENUMAP, COLUMN_MENUMAP, new ManagerExplosions());
    }

    private void initLabelPlayer() {
        labelScore = new JLabel();
        labelScore.setLocation(100, 120);
        labelScore.setText("<html><p align=\"center\">SCORE: ");
        labelScore.setForeground(Color.GREEN);

        Font fontLabelScore = new Font("Tahoma", Font.BOLD, 45);
        labelScore.setFont(fontLabelScore);

        FontMetrics mtLabelScore = getFontMetrics(fontLabelScore);
        int widthlabelScore = mtLabelScore.stringWidth("SCORE: "); // width = 155, height = 55
        labelScore.setSize(widthlabelScore, 45);

        labelPlayer = new JLabel();
        labelPlayer.setLocation(150, 30);
        labelPlayer.setText("<html><p align=\"center\">PLAYER");
        labelPlayer.setForeground(Color.GREEN);

        Font fontLabelPlayer = new Font("Tahoma", Font.BOLD, 45);
        labelPlayer.setFont(fontLabelPlayer);

        FontMetrics mtLabelPlayer = getFontMetrics(fontLabelPlayer);
        int widthlabelPlayer = mtLabelPlayer.stringWidth("PLAYER");
        labelPlayer.setSize(widthlabelPlayer, 45);
    }

    private void initButtons() {
        btPause = new MyButton(BUTTON_PAUSE, BUTTON_PAUSE1,
                300, 50, 95, 500);
        btPause.setLayout(null);
        labelPause = new MyLabel("OFF", 240, 5);

        btMainMenu = new MyButton(BUTTON_MENU, BUTTON_MENU1,
                300, 50, 95, 650);
    }

    private void addComponents() {
        add(labelScore);
        add(labelPlayer);

        add(btPause);
        add(btMainMenu);

        btPause.add(labelPause);
    }

    private void addEvents() {
        btPause.addActionListener(this);
        btPause.setActionCommand(PAUSE_BUTTON);

        btMainMenu.addActionListener(this);
        btMainMenu.setActionCommand(MAIN_MENU_BUTTON);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        managerImmovableItems.drawAllImmovableItems(graphics2D);
        drawScore(graphics2D);
        drawLifeMyTank(graphics2D);
        drawLifeBird(graphics2D);
        repaint();
    }

    private void drawScore(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Tahoma", Font.BOLD, 45));
        graphics2D.drawString("" + iActionShowGame.getScore(), 300, 160);
    }

    private void drawLifeMyTank(Graphics2D graphics2D) {
        switch (iActionShowGame.getRealLifeMyTank()) {
            case 1: {
                graphics2D.drawImage(MYTANK_UP, 220, 220, 50, 50, null);
                break;
            }
            case 2: {
                graphics2D.drawImage(MYTANK_UP, 183, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 252, 220, 50, 50, null);
                break;
            }
            case 3: {
                graphics2D.drawImage(MYTANK_UP, 151, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 220, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 289, 220, 50, 50, null);
                break;
            }
            case 4: {
                graphics2D.drawImage(MYTANK_UP, 114, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 183, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 252, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 321, 220, 50, 50, null);
                break;
            }
            case 5: {
                graphics2D.drawImage(MYTANK_UP, 82, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 151, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 220, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 289, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 358, 220, 50, 50, null);
                break;
            }
            case 6: {
                graphics2D.drawImage(MYTANK_UP, 45, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 114, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 183, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 252, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 321, 220, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 390, 220, 50, 50, null);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void drawLifeBird(Graphics2D graphics2D) {
        switch (iActionShowGame.getRealLifeBird()) {
            case 1: {
                graphics2D.drawImage(BIRD, 220, 320, 50, 50, null);
                break;
            }
            case 2: {
                graphics2D.drawImage(BIRD, 183, 320, 50, 50, null);
                graphics2D.drawImage(BIRD, 252, 320, 50, 50, null);
                break;
            }
            case 3: {
                graphics2D.drawImage(BIRD, 151, 320, 50, 50, null);
                graphics2D.drawImage(BIRD, 220, 320, 50, 50, null);
                graphics2D.drawImage(BIRD, 289, 320, 50, 50, null);
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idButton = e.getActionCommand();
        switch (idButton) {
            case PAUSE_BUTTON: {
                isPause++;
                if (1 == isPause % 2) {
                    labelPause.setForeground(Color.GREEN);
                    labelPause.setText("<html><p align=\"center\">ON");

                    iActionThread.pauseGame();
                    iActionShowGame.ignorePlayPanel();
                } else {
                    labelPause.setForeground(Color.RED);
                    labelPause.setText("<html><p align=\"center\">OFF");

                    iActionThread.playGame();
                    iActionShowGame.focusPlayPanel();
                }
                break;
            }
            case MAIN_MENU_BUTTON: {
                backMenuMainPanel();
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

    public void setIActionThread(IActionThread iActionThread) {
        this.iActionThread = iActionThread;
    }

    public void setIActionShowGame(IActionShowGame iActionShowGame) {
        this.iActionShowGame = iActionShowGame;
    }

    @Override
    public void backMenuMainPanel() {
        iActionEnterGame.showMainMenuPanel();

        iActionThread.stopGame();

        if (1 == isPause % 2) {
            isPause = 0;
            labelPause.setForeground(Color.RED);
            labelPause.setText("<html><p align=\"center\">OFF");
        }
    }
}