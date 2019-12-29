package com.uit.tanks.managers;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.models.Heart;
import com.uit.tanks.models.listenermanagers.IOnExplosions;
import com.uit.tanks.models.listenermanagers.IOnHearts;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagerHearts implements IOnHearts, IAttributeConstants {
    private Heart[] hearts;
    private List<Integer> xHearts;
    private List<Integer> yHearts;

    public ManagerHearts(IOnExplosions iOnExplosions) {
        hearts = new Heart[HEART_MAX];
        initXHearts();
        initYHearts();
        Random random = new Random();

        for (int i = 0; i < HEART_MAX; i++) {
            int indexX = random.nextInt(xHearts.size());
            int x = xHearts.get(indexX);
            xHearts.remove(indexX);

            int indexY = random.nextInt(yHearts.size());
            int y = yHearts.get(indexY);

            hearts[i] = new Heart(x, y, HEART_SIZE, HEART_SIZE, iOnExplosions, this);
        }
    }

    private void initXHearts() {
        xHearts = new ArrayList<>();
        xHearts.add(162);
        xHearts.add(216);
        xHearts.add(270);
        xHearts.add(432);
        xHearts.add(486);
    }

    private void initYHearts() {
        yHearts = new ArrayList<>();
        yHearts.add(378);
        yHearts.add(432);
    }

    public void drawAllHearts(Graphics2D graphics2D) {
        for (int i = 0; i < hearts.length; i++) {
            if (null != hearts[i]) {
                hearts[i].draw(graphics2D);
            }
        }
    }

    public void removeAllHeart() {
        if (0 < hearts.length) {
            for (int i = 0; i < hearts.length; i++) {
                if (null != hearts[i]) {
                    hearts[i] = null;
                }
            }
        }
    }

    @Override
    public void remove(Heart heart) {
        for (int i = 0; i < hearts.length; i++) {
            if (heart == hearts[i]) {
                hearts[i] = null;
            }
        }
    }

    public Heart[] getHearts() {
        return hearts;
    }
}