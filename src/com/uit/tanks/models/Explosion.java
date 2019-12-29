package com.uit.tanks.models;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.models.abstractmodels.Object2D;

import java.awt.*;

public class Explosion extends Object2D implements IAttributeConstants, IImageConstants {
    private int index;

    public Explosion(int x, int y, int width, int height) {
        super(x, y, width, height);
        index = 0;
    }

    @Override
    public Image getImage(int index) {
        switch (index) {
            case 0: {
                return EXP0;
            }
            case 1: {
                return EXP1;
            }
            case 2: {
                return EXP2;
            }
            case 3: {
                return EXP3;
            }
            case 4: {
                return EXP4;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (0 <= index && 4 >= index) {
            graphics2D.drawImage(getImage(index), x - (EXPLOSION_SIZE - width) / 2, y - (EXPLOSION_SIZE - height) / 2,
                    EXPLOSION_SIZE, EXPLOSION_SIZE, null);
        }
    }

    public boolean explosion(int time) {
        if (0 == time % EXPLOSION_SPEED) {
            if (4 < index) {
                return true;
            } else {
                index++;
            }
        }
        return false;
    }
}