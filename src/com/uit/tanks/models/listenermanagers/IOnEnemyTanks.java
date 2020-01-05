package com.uit.tanks.models.listenermanagers;

import com.uit.tanks.managers.AssistantEnemyTank;
import com.uit.tanks.models.tankcomponents.EnemyTank;

import java.util.List;

public interface IOnEnemyTanks {
    EnemyTank getEnemyTank();

    void initNewEnemyTank(IOnExplosions iOnExplosions, IOnBullets iOnBullets);

    void initRivivalEnemyTank(List<Integer> xList, IOnExplosions iOnExplosions, IOnBullets iOnBullets);

    int getLifeEnemy();

    int getLifeEnemyTank();

    void remove(EnemyTank enemyTank);

    AssistantEnemyTank getAssistantEnemyTank();
}