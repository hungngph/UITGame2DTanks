package com.uit.tanks.guis.containers.panels;

public interface IActionThread {
    void pauseGame();

    void playGame();

    void stopGame();

    void startGame(Thread thread);

    void setIsImperishable(boolean isImperishable);
}