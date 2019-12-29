package com.uit.tanks.guis.containers;

public interface IActionEnterGame {
    void showMainMenuPanel();

    void showControlsPanel();

    void showGamePanel();

    Runnable addRunnable();
}