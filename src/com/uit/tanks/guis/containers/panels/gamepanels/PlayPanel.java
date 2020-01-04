package com.uit.tanks.guis.containers.panels.gamepanels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.containers.panels.IActionThread;
import com.uit.tanks.managers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayPanel extends JPanel implements IAttributeConstants, IImageConstants,
        Runnable, IActionThread, IActionShowGame {
    private ManagerExplosions managerExplosions;

    private ManagerImmovableItems managerImmovableItems;
    private ManagerBird managerBird;
    private ManagerHearts managerHearts;

    private ManagerBombs managerBombs;

    private ManagerBullets managerMyBullets;
    private ManagerBullets managerEnemyBullets;

    private ManagerMyTanks managerMyTank;
    private ManagerEnemyTanks managerEnemyTanks;

    private IActionPlayGame iActionPlayGame;

    private int time = 0;

    private boolean isPause;
    private boolean isStop;
    private boolean isImperishable;

    private boolean[] press;

    public PlayPanel() {
        initPlayPanel();
        initComponents();

        pressKeyToControlMyTank();
    }

    private void initPlayPanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        setSize(757, HEIGHT_FRAME);
        setLocation(0, 0);
    }

    private void initComponents() {
        initManagersGameItems();
        press = new boolean[5];
    }

    private void initManagersGameItems() {
        managerExplosions = new ManagerExplosions();

        managerImmovableItems = new ManagerImmovableItems(MAP_PLAY + 1 + ".txt", //(new Random().nextInt(5) + 1)
                ROW_PLAYMAP, COLUMN_PLAYMAP, managerExplosions);
        managerBird = new ManagerBird();
        managerHearts = new ManagerHearts(managerExplosions);

        managerBombs = new ManagerBombs(managerExplosions);

        managerMyBullets = new ManagerBullets();
        managerEnemyBullets = new ManagerBullets();

        managerMyTank = new ManagerMyTanks();
        managerEnemyTanks = new ManagerEnemyTanks();
    }

    private void pressKeyToControlMyTank() {
        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                setActionTank(keyEvent, true);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                setActionTank(keyEvent, false);
            }
        };
        addKeyListener(keyListener);
    }

    private void setActionTank(KeyEvent keyEvent, boolean isPress) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                press[LEFT] = isPress;
                break;
            }
            case KeyEvent.VK_RIGHT: {
                press[RIGHT] = isPress;
                break;
            }
            case KeyEvent.VK_UP: {
                press[UP] = isPress;
                break;
            }
            case KeyEvent.VK_DOWN: {
                press[DOWN] = isPress;
                break;
            }
            case KeyEvent.VK_CONTROL: {
                press[FIRE] = isPress;
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;

        managerHearts.drawAllHearts(graphics2D);
        managerBombs.drawAllBombs(graphics2D);

        managerMyTank.drawMyTank(graphics2D);
        managerEnemyTanks.drawAllEnemyTanks(graphics2D);

        managerImmovableItems.drawAllImmovableItems(graphics2D);

        managerMyBullets.drawAllBullets(graphics2D);
        managerEnemyBullets.drawAllBullets(graphics2D);

        managerBird.drawBird(graphics2D);

        managerExplosions.drawExplosion(graphics2D);
    }

    @Override
    public void run() {
        while (!isStop) {
            actionMyTank();
            actionMovableItems();
            actionWinGame();
            if (!isImperishable) {
                actionLooseGame();
            }
            synchronized (PlayPanel.this) {
                if (isPause) {
                    try {
                        PlayPanel.this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                PlayPanel.this.repaint();
            }
            time++;

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void actionMovableItems() {
        managerMyTank.destroyWithHeart(managerHearts.getHearts());

        managerEnemyTanks.moveAllEnemyTanks(time, managerImmovableItems.getImmovableItems(),
                managerMyTank.getMyTank(), managerBird.getBird(), managerHearts.getHearts());
        managerEnemyTanks.fireBulletsEnemyTank(time);

        managerMyBullets.moveAllBullets(time);
        managerMyBullets.destroyBulletWithImmovableItem(managerImmovableItems.getImmovableItems());
        managerMyBullets.destroyBulletWithBomb(managerBombs.getBombs());
        managerMyBullets.destroyBulletWithBullet(managerEnemyBullets.getBullets());

        managerEnemyBullets.moveAllBullets(time);
        managerEnemyBullets.destroyBulletWithImmovableItem(managerImmovableItems.getImmovableItems());

        managerEnemyBullets.destroyBulletWithBomb(managerBombs.getBombs());
        managerEnemyBullets.destroyBulletWithBullet(managerMyBullets.getBullets());

        managerBombs.moveAllBombs(time, managerImmovableItems.getImmovableItems());

        managerExplosions.occurExplosion(time);
    }

    private void actionMyTank() {
        managerMyTank.moveMyTank(press, time, managerImmovableItems.getImmovableItems(),
                managerEnemyTanks.getAssistantEnemyTankArray(), managerBird.getBird());
        managerMyTank.fireBulletsMyTank(press, time);
    }

    private void winGame() {
        int click = JOptionPane.showConfirmDialog(PlayPanel.this,
                "CONGRATULATION\nPRESS OK TO BACK MAIN MENU",
                "WIN", JOptionPane.DEFAULT_OPTION);
        if (JOptionPane.YES_OPTION == click) {
            iActionPlayGame.backMenuMainPanel();
        }
    }

    private void actionWinGame() {
        if (managerMyBullets.destroyBulletWithEnemyTank(managerEnemyTanks.getAssistantEnemyTankArray(),
                managerExplosions, managerEnemyBullets)) {
            winGame();
        }
        if (managerBombs.destroyBombWithEnemyTank(managerEnemyTanks.getAssistantEnemyTankArray(),
                managerExplosions, managerEnemyBullets)) {
            winGame();
        }
    }

    private void looseGame() {
        int click = JOptionPane.showConfirmDialog(PlayPanel.this,
                "CONSOLATORY\nPRESS OK TO BACK MAIN MENU",
                "LOOSE", JOptionPane.DEFAULT_OPTION);
        if (JOptionPane.YES_OPTION == click) {
            iActionPlayGame.backMenuMainPanel();
        }
    }

    private void actionLooseGame() {
        if (managerMyBullets.destroyBulletWithBird(managerBird.getBird(), managerExplosions, managerBird)) {
            looseGame();
        }
        if (managerEnemyBullets.destroyBulletWithMyTank(managerMyTank.getMyTank(), managerExplosions, managerMyBullets)) {
            looseGame();
        }
        if (managerBombs.destroyBombWithMyTank(managerMyTank.getMyTank(), managerExplosions, managerMyBullets)) {
            looseGame();
        }
        if (managerEnemyBullets.destroyBulletWithBird(managerBird.getBird(), managerExplosions, managerBird)) {
            looseGame();
        }
        if (managerBombs.destroyBombWithBird(managerBird.getBird(), managerExplosions, managerBird)) {
            looseGame();
        }
    }

    public void setIActionPlayGame(IActionPlayGame iActionPlayGame) {
        this.iActionPlayGame = iActionPlayGame;
    }

    @Override
    public void pauseGame() {
        isPause = true;
    }

    @Override
    public void playGame() {
        isPause = false;
        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public void stopGame() {
        isStop = true;
        for (int i = 0; i < press.length; i++) {
            press[i] = false;
        }
        managerImmovableItems.removeAllImmovableItems();
        managerBird.removeBird();
        managerHearts.removeAllHeart();
        managerMyTank.removeMyTank();
        managerEnemyTanks.removeAllEnemyTanks();
        managerEnemyTanks.removeAllAssistantEnemyTanks();
        managerBombs.removeAllBombs();
        managerMyBullets.removeAllBullets();
        managerEnemyBullets.removeAllBullets();
        managerExplosions.removeExplosion();
    }

    @Override
    public void startGame(Thread thread) {
        isStop = false;
        isPause = false;
        initManagersGameItems();
        managerMyTank.initNewMyTank(managerExplosions, managerMyBullets);
        managerEnemyTanks.initNewEnemyTank(managerExplosions, managerEnemyBullets);
        managerBird.initNewBird(managerExplosions, managerBird);
        thread.start();
    }

    @Override
    public void setIsImperishable(boolean isImperishable) {
        this.isImperishable = isImperishable;
    }


    @Override
    public void focusPlayPanel() {
        requestFocus();
    }

    @Override
    public void ignorePlayPanel() {
        for (int i = 0; i < press.length; i++) {
            press[i] = false;
        }
    }

    @Override
    public int getScore() {
        return managerMyBullets.getScore();
    }
    @Override
    public int getLifeEnemy() {
        return managerEnemyTanks.getNumberEnemyTank();
    }
    @Override
    public int getRealLifeMyTank() {
        return managerMyTank.getRealLifeMyTank();
    }

    @Override
    public int getRealLifeBird() {
        return managerBird.getRealLifeBird();
    }
}