package com.uit.tanks.managers;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.models.Bird;
import com.uit.tanks.models.Bomb;
import com.uit.tanks.models.ImmovableItem;
import com.uit.tanks.models.listenermanagers.IOnBirds;
import com.uit.tanks.models.listenermanagers.IOnBullets;
import com.uit.tanks.models.listenermanagers.IOnExplosions;
import com.uit.tanks.models.tankcomponents.Bullet;
import com.uit.tanks.models.tankcomponents.MyTank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerBullets implements IOnBullets, IImageConstants, IAttributeConstants {
    protected List<Bullet> bullets;
    private int score = 0;

    public ManagerBullets() {
        bullets = new ArrayList<>(BULLET_MIN);
    }

    public void drawAllBullets(Graphics2D graphics2D) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(graphics2D);
        }
    }

    public void moveAllBullets(int time) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move(time);
        }
    }

    public void destroyBulletWithImmovableItem(ImmovableItem[][] immovableItems) {
        if (0 < bullets.size()) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                int result = bullets.get(i).destroyedWithImmovableItem(immovableItems);
                if (0 != result) {
                    bullets.remove(i);
                }
                if (1 == result || 4 == result) {
                    score++;
                }
            }
        }
    }

    public boolean destroyBulletWithEnemyTank(AssistantEnemyTank[] assistantEnemyTankArray,
                                              IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        if (0 < bullets.size()) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                int result = bullets.get(i).destroyedWithEnemyTank(assistantEnemyTankArray, iOnExplosions, iOnBullets);
                if (0 != result) {
                    bullets.remove(i);
                    score++;
                }
                if (2 == result) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean destroyBulletWithMyTank(MyTank myTank, IOnExplosions iOnExplosions, IOnBullets iOnBullets) {
        if (0 < bullets.size()) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                int result = bullets.get(i).destroyedWithMyTank(myTank, iOnExplosions, iOnBullets);
                if (0 != result) {
                    bullets.remove(i);
                }
                if (2 == result) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean destroyBulletWithBird(Bird bird, IOnExplosions iOnExplosions, IOnBirds iOnBirds) {
        if (0 < bullets.size()) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                int result = bullets.get(i).destroyedWithBird(bird, iOnExplosions, iOnBirds);
                if (0 != result) {
                    bullets.remove(i);
                }
                if (2 == result) {
                    return true;
                }
            }
        }
        return false;
    }

    public void destroyBulletWithBomb(Bomb[] bombs) {
        if (0 < bullets.size()) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                if (bullets.get(i).destroyedWithBomb(bombs)) {
                    bullets.remove(i);
                    score++;
                }
            }
        }
    }

    public void destroyBulletWithBullet(List<Bullet> bulletList) {
        if (0 < bullets.size()) {
            for (int i = bullets.size() - 1; i >= 0; i--) {
                if (bullets.get(i).destroyedWithBullet(bulletList)) {
                    bullets.remove(i);
                }
            }
        }
    }

    public void removeAllBullets() {
        bullets.clear();
    }

    public int getScore() {
        return score;
    }

    @Override
    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    @Override
    public List<Bullet> getBullets() {
        return bullets;
    }
}