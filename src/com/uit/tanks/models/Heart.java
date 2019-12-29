package com.uit.tanks.models;

import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.models.abstractmodels.GameItem;
import com.uit.tanks.models.listenermanagers.IOnExplosions;
import com.uit.tanks.models.listenermanagers.IOnHearts;

import java.awt.*;

public class Heart extends GameItem implements IImageConstants {
    private IOnHearts iOnHearts;

    public Heart(int x, int y, int width, int height, IOnExplosions iOnExplosions, IOnHearts iOnHearts) {
        super(x, y, width, height, iOnExplosions);
        this.iOnHearts = iOnHearts;

    }

    @Override
    public Image getImage(int idGameItem) {
        return null;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(HEART, x, y, width, height, null);
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setStroke(new BasicStroke(3));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawOval(x, y, width, height);
    }

    public void destroyedHeart() {
        if (destroyed()) {
            iOnHearts.remove(this);
        }
    }

    @Override
    protected boolean checkIsDestroyed() {
        return isDestroy;
    }
}