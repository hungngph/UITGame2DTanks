package com.uit.tanks.managers;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.models.Bird;
import com.uit.tanks.models.Heart;
import com.uit.tanks.models.ImmovableItem;
import com.uit.tanks.models.listenermanagers.IOnBullets;
import com.uit.tanks.models.listenermanagers.IOnEnemyTanks;
import com.uit.tanks.models.listenermanagers.IOnExplosions;
import com.uit.tanks.models.tankcomponents.EnemyTank;
import com.uit.tanks.models.tankcomponents.MyTank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AssistantEnemyTank implements IOnEnemyTanks, IAttributeConstants, IImageConstants {
    private EnemyTank enemyTank;
    private int lifeEnemyTank = LIFE_ENEMYTANK;
    private ManagerEnemyTanks managerEnemyTanks;

    public AssistantEnemyTank(ManagerEnemyTanks managerEnemyTanks) {
        this.managerEnemyTanks = managerEnemyTanks;
    }

    public void initNewEnemyTank(int x, int y, IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        enemyTank = new EnemyTank(x, y, TANK_SIZE, TANK_SIZE, ENEMYTANK_SPEED, DOWN,
                iOnExplosions, iOnBullets, this);
    }

    public void drawEnemyTank(Graphics2D graphics2D) {
        if (null != enemyTank) {
            enemyTank.draw(graphics2D);
        }
    }

    public void moveEnemyTank(int time, AssistantEnemyTank[] assistantEnemyTankArray, ImmovableItem[][] immovableItems,
                              MyTank myTank, Bird bird, Heart[] hearts) {
        if (null != enemyTank) {
            enemyTank.moveEnemyTank(time, assistantEnemyTankArray, immovableItems, myTank, bird, hearts);
        }
    }

    public void fireBulletsEnemyTank(int time) {
        if (null != enemyTank) {
            enemyTank.fireBullets(time, ENEMYBULLET_FIRE_DELTA);
        }
    }

    public void removeEnemyTank() {
        if (null != enemyTank) {
            enemyTank = null;
        }
    }

    public void destroyAssistantEnemyTank() {
        managerEnemyTanks.removeAssistantEnemyTank(this);
    }

    public int getNumberEnemyTanks() {
        return managerEnemyTanks.getNumberEnemyTanks();
    }

    @Override
    public EnemyTank getEnemyTank() {
        return enemyTank;
    }

    public boolean setxx(int x, int y) {
        if (x <= y && x + TANK_SIZE >= y)
            return false;
        if (x >= y && x - TANK_SIZE <= y)
            return false;

        return true;
    }

    @Override
    public void initRivivalEnemyTank(List<Integer> xList, IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        List<Integer> xEnemyTank;
        xEnemyTank = new ArrayList<>();
        xEnemyTank.add(30);
        xEnemyTank.add(175);
        xEnemyTank.add(540);
        xEnemyTank.add(675);
        int xEnemy = xEnemyTank.get(0);
        for (int j = 1; j <= 3; j++) {
            boolean k = true;
            for (int i = 0; i < xList.size(); i++) {
                if (!setxx(xEnemy, xList.get(i))) {
                    k = false;
                    break;
                }
            }
            if (k) break;
            xEnemy = xEnemyTank.get(j);
        }

        int yEnemy = 30;
        enemyTank = new EnemyTank(xEnemy, yEnemy, TANK_SIZE, TANK_SIZE, ENEMYTANK_SPEED, DOWN,
                iOnExplosions, iOnBullets, this);
    }

    @Override
    public void initNewEnemyTank(IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        List<Integer> xEnemyTank;
        xEnemyTank = new ArrayList<>();
        xEnemyTank.add(30);
        xEnemyTank.add(90);
        xEnemyTank.add(525);
        xEnemyTank.add(675);
        int xEnemy = xEnemyTank.get(1);
        int yEnemy = 30;
        enemyTank = new EnemyTank(xEnemy, yEnemy, TANK_SIZE, TANK_SIZE, ENEMYTANK_SPEED, DOWN,
                iOnExplosions, iOnBullets, this);

    }

    @Override
    public int getLifeEnemyTank() {
        lifeEnemyTank--;
        return lifeEnemyTank;
    }

    @Override
    public int getLifeEnemy() {
        return lifeEnemyTank;
    }

    @Override
    public void remove(EnemyTank enemyTank) {
        if (enemyTank == this.enemyTank) {
            this.enemyTank = null;
        }
    }

    @Override
    public AssistantEnemyTank getAssistantEnemyTank() {
        return this;
    }
}