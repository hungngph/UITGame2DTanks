package com.uit.tanks.models.listenermanagers;

import com.uit.tanks.models.tankcomponents.Bullet;

import java.util.List;

public interface IOnBullets {
    void addBullet(Bullet bullet);

    List<Bullet> getBullets();
}