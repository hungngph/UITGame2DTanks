package com.uit.tanks.guis.containers;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.guis.containers.panels.*;
import com.uit.tanks.guis.containers.panels.gamepanels.GamePanel;

import javax.swing.*;
import java.awt.*;

public class MainContainer extends JPanel implements IAttributeConstants, IActionEnterGame {
    private StartPanel startPanel;
    private MainMenuPanel mainMenuPanel;
    private ControlsPanel controlsPanel;
    private GamePanel gamePanel;

    private CardLayout cardLayout;

    public MainContainer() {
        initMainContainer();
        initComponents();
        addComponents();
        setIActions();
    }

    private void initMainContainer() {
        setBackground(Color.CYAN);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        cardLayout.show(this, START_PANEL);
    }

    private void initComponents() {
        startPanel = new StartPanel();
        mainMenuPanel = new MainMenuPanel();
        controlsPanel = new ControlsPanel();
        gamePanel = new GamePanel();
    }

    private void addComponents() {
        add(startPanel, START_PANEL);
        add(mainMenuPanel, MAIN_MENU_PANEL);
        add(controlsPanel, CONTROLS_PANEL);
        add(gamePanel, GAME_PANEL);
    }

    private void setIActions() {
        startPanel.setIActionEnterGame(this);
        mainMenuPanel.setIActionEnterGame(this);
        controlsPanel.setIActionEnterGame(this);
        gamePanel.getScorePanel().setIActionEnterGame(this);
        mainMenuPanel.setIActionThread(gamePanel.getPlayPanel());

        gamePanel.getScorePanel().setIActionMusicPlay1(mainMenuPanel);
        gamePanel.getScorePanel().setIOnMusic(controlsPanel);
    }

    public StartPanel getStartPanel() {
        return startPanel;
    }

    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public ControlsPanel getControlsPanel() {
        return controlsPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    @Override
    public void showMainMenuPanel() {
        cardLayout.show(this, MAIN_MENU_PANEL);
        mainMenuPanel.requestFocus();
    }

    @Override
    public void showControlsPanel() {
        cardLayout.show(this, CONTROLS_PANEL);
        controlsPanel.requestFocus();
    }

    @Override
    public void showGamePanel() {
        cardLayout.show(this, GAME_PANEL);
        gamePanel.getPlayPanel().setFocusable(true);
        gamePanel.getPlayPanel().requestFocus();
    }

    @Override
    public Runnable addRunnable() {
        return gamePanel.getPlayPanel();
    }
}