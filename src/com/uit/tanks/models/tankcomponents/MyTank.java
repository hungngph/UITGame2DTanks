package com.uit.tanks.models.tankcomponents;

import com.uit.tanks.managers.AssistantEnemyTank;
import com.uit.tanks.models.Bird;
import com.uit.tanks.models.ImmovableItem;
import com.uit.tanks.models.ObjectAudio;
import com.uit.tanks.models.listenermanagers.IOnBullets;
import com.uit.tanks.models.listenermanagers.IOnExplosions;
import com.uit.tanks.models.listenermanagers.IOnMyTanks;

import java.awt.*;

public class MyTank extends Tank {
    private IOnMyTanks iOnMyTanks;
    private ObjectAudio objectAudio;

    public MyTank(int x, int y, int width, int height, int speed, int orient,
                  IOnExplosions iOnExplosions, IOnBullets iOnBullets,
                  IOnMyTanks iOnMyTanks) {
        super(x, y, width, height, speed, orient, iOnExplosions, iOnBullets);
        this.iOnMyTanks = iOnMyTanks;
        objectAudio = new ObjectAudio(EXPLOSION_TANK);
    }

    @Override
    public Image getImage(int orient) {
        switch (orient) {
            case LEFT: {
                return MYTANK_LEFT;
            }
            case RIGHT: {
                return MYTANK_RIGHT;
            }
            case UP: {
                return MYTANK_UP;
            }
            case DOWN: {
                return MYTANK_DOWN;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(getImage(orient), x, y, width, height, null);
    }

    public void destroyedMyTank() {
        if (destroyed()) {
            iOnMyTanks.remove(this);
        }
    }

    public void moveMyTank(boolean[] press, int time,
                           ImmovableItem[][] immovableItems, AssistantEnemyTank[] assistantEnemyTankArray, Bird bird) {
        if (press[LEFT]) {
            setOrient(LEFT);
            move(time);
            interSectWithImmovableItem(immovableItems);
            interSectWithEnemyTanks(assistantEnemyTankArray);
            interSectWithBird(bird);
        } else if (press[RIGHT]) {
            setOrient(RIGHT);
            move(time);
            interSectWithImmovableItem(immovableItems);
            interSectWithEnemyTanks(assistantEnemyTankArray);
            interSectWithBird(bird);
        } else if (press[UP]) {
            setOrient(UP);
            move(time);
            interSectWithImmovableItem(immovableItems);
            interSectWithEnemyTanks(assistantEnemyTankArray);
            interSectWithBird(bird);
        } else if (press[DOWN]) {
            setOrient(DOWN);
            move(time);
            interSectWithImmovableItem(immovableItems);
            interSectWithEnemyTanks(assistantEnemyTankArray);
            interSectWithBird(bird);
        }
    }

    public void fireBulletsMyTank(boolean[] press, int time) {
        if (press[FIRE]) {
            fireBullets(time, MYBULLET_FIRE_DELTA);
        }
    }

    public void initNewMyTank(IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        iOnMyTanks.initNewMyTank(iOnExplosions, iOnBullets);
    }

    public int getLifeMyTank() {
        return iOnMyTanks.getLifeMyTank();
    }

    @Override
    protected boolean checkIsDestroyed() {
        return isDestroy;
    }

    @Override
    protected int checkIsDestroyedWithImmovableItem(ImmovableItem[][] immovableItems) {
        return 0;
    }
}