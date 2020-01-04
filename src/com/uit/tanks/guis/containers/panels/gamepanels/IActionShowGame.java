package com.uit.tanks.guis.containers.panels.gamepanels;

public interface IActionShowGame {
    void focusPlayPanel();

    void ignorePlayPanel();

    int getLifeEnemy();

    int getScore();

    int getRealLifeMyTank();

    int getRealLifeBird();
}