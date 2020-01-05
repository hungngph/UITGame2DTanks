package com.uit.tanks.models.tankcomponents;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IAudioConstants;
import com.uit.tanks.models.ImmovableItem;
import com.uit.tanks.models.ObjectAudio;
import com.uit.tanks.models.abstractmodels.MovableItem;
import com.uit.tanks.models.listenermanagers.IOnBullets;
import com.uit.tanks.models.listenermanagers.IOnExplosions;

public abstract class Tank extends MovableItem implements IAttributeConstants, IAudioConstants {
    private IOnBullets iOnBullets;
    private ObjectAudio objectAudio;

    public Tank(int x, int y, int width, int height, int speed, int orient,
                IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        super(x, y, width, height, speed, orient, iOnExplosions);
        this.iOnBullets = iOnBullets;
        objectAudio = new ObjectAudio(SHOOT);
    }

    public void fireBullets(int time, int fireDelta) {
        if (0 == time % fireDelta) {
            objectAudio.play();
            int bulletX = getX();
            int bulletY = getY();

            switch (orient) {
                case LEFT: {
                    bulletX -= BULLET_SIZE;
                    bulletY += (TANK_SIZE - BULLET_SIZE) / 2;
                    Bullet bullet = new Bullet(bulletX, bulletY, 2 * BULLET_SIZE, BULLET_SIZE,
                            BULLET_SPEED, orient, iOnExplosions, iOnBullets);
                    iOnBullets.addBullet(bullet);
                    break;
                }
                case RIGHT: {
                    bulletX += TANK_SIZE;
                    bulletY += (TANK_SIZE - BULLET_SIZE) / 2;
                    Bullet bullet = new Bullet(bulletX, bulletY, 2 * BULLET_SIZE, BULLET_SIZE,
                            BULLET_SPEED, orient, iOnExplosions, iOnBullets);
                    iOnBullets.addBullet(bullet);
                    break;
                }
                case UP: {
                    bulletX += (TANK_SIZE - BULLET_SIZE) / 2;
                    bulletY -= BULLET_SIZE;
                    Bullet bullet = new Bullet(bulletX, bulletY, BULLET_SIZE, 2 * BULLET_SIZE,
                            BULLET_SPEED, orient, iOnExplosions, iOnBullets);
                    iOnBullets.addBullet(bullet);
                    break;
                }
                case DOWN: {
                    bulletX += (TANK_SIZE - BULLET_SIZE) / 2;
                    bulletY += TANK_SIZE;
                    Bullet bullet = new Bullet(bulletX, bulletY, BULLET_SIZE, 2 * BULLET_SIZE,
                            BULLET_SPEED, orient, iOnExplosions, iOnBullets);
                    iOnBullets.addBullet(bullet);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    @Override
    protected boolean isInterSectWithImmovableItem(ImmovableItem immovableItem) {
        return immovableItem != null && immovableItem.getId() != TREE_ID;
    }
}