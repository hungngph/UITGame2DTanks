package com.uit.tanks.models.listenermanagers;

import com.uit.tanks.models.tankcomponents.MyTank;

public interface IOnMyTanks {
    MyTank getMyTank();

    void initNewMyTank(IOnExplosions iOnExplosions, IOnBullets iOnBullets);

    int getLifeMyTank();

    void remove(MyTank myTank);
}