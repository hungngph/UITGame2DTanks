package com.uit.tanks.models;

import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.models.abstractmodels.GameItem;
import com.uit.tanks.models.listenermanagers.IOnBirds;
import com.uit.tanks.models.listenermanagers.IOnExplosions;

import java.awt.*;

public class Bird extends GameItem implements IImageConstants {
    private IOnBirds iOnBirds;

    public Bird(int x, int y, int width, int height, IOnExplosions iOnExplosions, IOnBirds iOnBirds) {
        super(x, y, width, height, iOnExplosions);
        this.iOnBirds = iOnBirds;
    }

    @Override
    public Image getImage(int idGameItem) {
        return null;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(BIRD, x, y, width, height, null);
    }

    public void destroyedBird() {
        if (destroyed()) {
            iOnBirds.remove(this);
        }
    }

    public void initNewBird(IOnExplosions iOnExplosions, IOnBirds iOnBirds) {
        iOnBirds.initNewBird(iOnExplosions, iOnBirds);
    }

    public int getLifeBird() {
        return iOnBirds.getLifeBird();
    }


    @Override
    protected boolean checkIsDestroyed() {
        return isDestroy;
    }
}