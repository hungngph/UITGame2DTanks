package com.uit.tanks.models.listenermanagers;

import com.uit.tanks.managers.AssistantEnemyTank;
import com.uit.tanks.models.tankcomponents.EnemyTank;

public interface IOnEnemyTanks {
    EnemyTank getEnemyTank();

    void initNewEnemyTank(IOnExplosions iOnExplosions, IOnBullets iOnBullets);

    int getLifeEnemyTank();

    void remove(EnemyTank enemyTank);

    AssistantEnemyTank getAssistantEnemyTank();
}