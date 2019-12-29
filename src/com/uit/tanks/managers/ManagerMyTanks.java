package com.uit.tanks.managers;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.models.Bird;
import com.uit.tanks.models.Heart;
import com.uit.tanks.models.ImmovableItem;
import com.uit.tanks.models.listenermanagers.IOnBullets;
import com.uit.tanks.models.listenermanagers.IOnExplosions;
import com.uit.tanks.models.listenermanagers.IOnMyTanks;
import com.uit.tanks.models.tankcomponents.MyTank;

import java.awt.*;

public class ManagerMyTanks implements IOnMyTanks, IAttributeConstants, IImageConstants {
    private MyTank myTank;
    private int lifeMyTank = LIFE_MYTANK;

    public ManagerMyTanks() {
    }

    public void drawMyTank(Graphics2D graphics2D) {
        if (null != myTank) {
            myTank.draw(graphics2D);
        }
    }

    public void moveMyTank(boolean[] press, int time, ImmovableItem[][] immovableItems, AssistantEnemyTank[] assistantEnemyTankArray, Bird bird) {
        if (null != myTank) {
            myTank.moveMyTank(press, time, immovableItems, assistantEnemyTankArray, bird);
        }
    }

    public void fireBulletsMyTank(boolean[] press, int time) {
        if (null != myTank) {
            myTank.fireBulletsMyTank(press, time);
        }
    }

    public void destroyWithHeart(Heart[] hearts) {
        if (null != myTank) {
            if (myTank.destroyedWithHeart(hearts)) {
                lifeMyTank++;
            }
        }
    }

    public void removeMyTank() {
        if (null != myTank) {
            myTank = null;
        }
    }

    public int getRealLifeMyTank() {
        return lifeMyTank;
    }

    @Override
    public MyTank getMyTank() {
        if (null != myTank) {
            return myTank;
        } else {
            return null;
        }
    }

    @Override
    public void initNewMyTank(IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        myTank = new MyTank(X_MYTANK_DEFAULT, Y_MYTANK_DEFAULT, TANK_SIZE, TANK_SIZE, MYTANK_SPEED, UP,
                iOnExplosions, iOnBullets, this);
    }

    @Override
    public int getLifeMyTank() {
        lifeMyTank--;
        return lifeMyTank;
    }

    @Override
    public void remove(MyTank myTank) {
        if (myTank == this.myTank) {
            this.myTank = null;
        }
    }
}