package com.uit.tanks.guis.containers.panels.gamepanels;

import com.uit.tanks.common.IAttributeConstants;
import com.uit.tanks.common.IIconConstants;
import com.uit.tanks.common.IImageConstants;
import com.uit.tanks.guis.containers.IActionEnterGame;
import com.uit.tanks.guis.containers.panels.IActionThread;
import com.uit.tanks.managers.ManagerExplosions;
import com.uit.tanks.managers.ManagerImmovableItems;
import com.uit.tanks.utils.MyButton;
import com.uit.tanks.utils.MyLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePanel extends JPanel implements IAttributeConstants, IIconConstants,
        ActionListener, IActionPlayGame, IImageConstants {
    private ManagerImmovableItems managerImmovableItems;

    private JLabel labelScore;
    private JLabel labelCourse;
    private JLabel labelLecturer;
    private JLabel labelAuthor;
    private JLabel labelAuthor1;
    private JLabel labelAuthor2;
    private JLabel labelPause;

    private JButton btPause;
    private JButton btMainMenu;

    private IActionEnterGame iActionEnterGame;
    private IActionThread iActionThread;
    private IActionShowGame iActionShowGame;

    private int isPause = 0;

    public ScorePanel() {
        initScorePanel();
        initComponents();
        addComponents();
        addEvents();
    }

    private void initScorePanel() {
        setBackground(Color.BLACK);
        setLayout(null);
        setSize(WIDTH_FRAME - WIDTH_PLAY_PANEL, HEIGHT_FRAME);
        setLocation(WIDTH_PLAY_PANEL + 1, 0);
    }

    private void initComponents() {
        initManagersItems();
        initLabelPlayer();
        initButtons();
    }

    private void initManagersItems() {
        managerImmovableItems = new ManagerImmovableItems(MAP_MENU, ROW_MENUMAP, COLUMN_MENUMAP, new ManagerExplosions());
    }

    private void initLabelPlayer() {
        labelScore = new JLabel();
        labelScore.setLocation(100, 220);
        labelScore.setText("<html><p align=\"center\">SCORE: ");
        labelScore.setForeground(Color.BLUE);

        Font fontLabelScore = new Font("Tahoma", Font.BOLD, 45);
        labelScore.setFont(fontLabelScore);

        FontMetrics mtLabelScore = getFontMetrics(fontLabelScore);
        int widthlabelScore = mtLabelScore.stringWidth("SCORE: ");
        labelScore.setSize(widthlabelScore, 45);

        labelCourse = new JLabel();
        labelCourse.setLocation(70, 30);
        labelCourse.setText("<html><p align=\"center\">Đồ án môn: Ngôn ngữ lập trình Java - SE330.K11\n");
        labelCourse.setForeground(Color.GREEN);

        Font fontLabelCourse = new Font("Tahoma", Font.BOLD, 15);
        labelCourse.setFont(fontLabelCourse);

        FontMetrics mtLabelCourse = getFontMetrics(fontLabelCourse);
        int widthlabelPlayer = mtLabelCourse.stringWidth(" Đồ án môn: Ngôn ngữ lập trình Java - SE330.K11    ");
        labelCourse.setSize(widthlabelPlayer, 45);

        labelLecturer = new JLabel();
        labelLecturer.setLocation(70, 60);
        labelLecturer.setText("<html><p align=\"center\">Giáo viên hướng dẫn: Th.S Lê Thanh Trọng\n");
        labelLecturer.setForeground(Color.GREEN);

        Font fontLabelLecturer = new Font("Tahoma", Font.BOLD, 15);
        labelLecturer.setFont(fontLabelLecturer);

        FontMetrics mtLabelLecturer = getFontMetrics(fontLabelLecturer);
        int widthlabelLecturer = mtLabelLecturer.stringWidth(" Giáo viên hướng dẫn: Th.S Lê Thanh Trọng      ");
        labelLecturer.setSize(widthlabelLecturer, 45);

        labelAuthor = new JLabel();
        labelAuthor.setLocation(70, 90);
        labelAuthor.setText("<html><p align=\"center\">Nhóm thực hiện: \n");
        labelAuthor.setForeground(Color.GREEN);

        Font fontLabelAuthor = new Font("Tahoma", Font.BOLD, 15);
        labelAuthor.setFont(fontLabelAuthor);

        FontMetrics mtLabelAuthor = getFontMetrics(fontLabelAuthor);
        int widthlabelAuthor = mtLabelAuthor.stringWidth(" Nhóm thực hiện:       ");
        labelAuthor.setSize(widthlabelAuthor, 45);

        labelAuthor1 = new JLabel();
        labelAuthor1.setLocation(90, 120);
        labelAuthor1.setText("<html><p align=\"center\">+ Nguyễn Phi Hùng - 17520068 \n");
        labelAuthor1.setForeground(Color.GREEN);

        Font fontLabelAuthor1 = new Font("Tahoma", Font.BOLD, 15);
        labelAuthor1.setFont(fontLabelAuthor1);

        FontMetrics mtLabelAuthor1 = getFontMetrics(fontLabelAuthor1);
        int widthlabelAuthor1 = mtLabelAuthor1.stringWidth(" + Nguyễn Phi Hùng - 17520068      ");
        labelAuthor1.setSize(widthlabelAuthor1, 45);

        labelAuthor2 = new JLabel();
        labelAuthor2.setLocation(90, 150);
        labelAuthor2.setText("<html><p align=\"center\">+ Nguyễn Duy Hoài Sơn - 17520148 \n");
        labelAuthor2.setForeground(Color.GREEN);

        Font fontLabelAuthor2 = new Font("Tahoma", Font.BOLD, 15);
        labelAuthor2.setFont(fontLabelAuthor2);

        FontMetrics mtLabelAuthor2 = getFontMetrics(fontLabelAuthor2);
        int widthlabelAuthor2 = mtLabelAuthor2.stringWidth(" + Nguyễn Duy Hoài Sơn - 17520148      ");
        labelAuthor2.setSize(widthlabelAuthor2, 45);
    }

    private void initButtons() {
        btPause = new MyButton(BUTTON_PAUSE, BUTTON_PAUSE1,
                300, 50, 95, 590);
        btPause.setLayout(null);
        labelPause = new MyLabel("OFF", 240, 5);
        btMainMenu = new MyButton(BUTTON_MENU, BUTTON_MENU1,
                300, 50, 95, 650);
    }

    private void addComponents() {
        add(labelScore);
        add(labelCourse);
        add(labelLecturer);
        add(labelAuthor);
        add(labelAuthor1);
        add(labelAuthor2);
        add(btPause);
        add(btMainMenu);
        btPause.add(labelPause);
    }

    private void addEvents() {
        btPause.addActionListener(this);
        btPause.setActionCommand(PAUSE_BUTTON);
        btMainMenu.addActionListener(this);
        btMainMenu.setActionCommand(MAIN_MENU_BUTTON);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        managerImmovableItems.drawAllImmovableItems(graphics2D);
        drawScore(graphics2D);
        drawLifeMyTank(graphics2D);
        drawLifeBird(graphics2D);
        drawLifeEnemy(graphics2D);
        repaint();
    }

    private void drawScore(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Tahoma", Font.BOLD, 45));
        graphics2D.drawString("" + iActionShowGame.getScore(), 300, 260);
    }
    private void drawLifeEnemy(Graphics2D graphics2D)
    {
        graphics2D.drawImage(ENEMYTANK_UP, 183, 500, 50, 50, null);
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Tahoma", Font.BOLD, 45));
        graphics2D.drawString("" + iActionShowGame.getLifeEnemy(), 270, 540);
    }
    private void drawLifeMyTank(Graphics2D graphics2D) {
        switch (iActionShowGame.getRealLifeMyTank()) {
            case 1: {
                graphics2D.drawImage(MYTANK_UP, 220, 320, 50, 50, null);
                break;
            }
            case 2: {
                graphics2D.drawImage(MYTANK_UP, 183, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 252, 320, 50, 50, null);
                break;
            }
            case 3: {
                graphics2D.drawImage(MYTANK_UP, 151, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 220, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 289, 320, 50, 50, null);
                break;
            }
            case 4: {
                graphics2D.drawImage(MYTANK_UP, 114, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 183, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 252, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 321, 320, 50, 50, null);
                break;
            }
            case 5: {
                graphics2D.drawImage(MYTANK_UP, 82, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 151, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 220, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 289, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 358, 320, 50, 50, null);
                break;
            }
            case 6: {
                graphics2D.drawImage(MYTANK_UP, 45, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 114, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 183, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 252, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 321, 320, 50, 50, null);
                graphics2D.drawImage(MYTANK_UP, 390, 320, 50, 50, null);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void drawLifeBird(Graphics2D graphics2D) {
        switch (iActionShowGame.getRealLifeBird()) {
            case 1: {
                graphics2D.drawImage(BIRD, 220, 420, 50, 50, null);
                break;
            }
            case 2: {
                graphics2D.drawImage(BIRD, 183, 420, 50, 50, null);
                graphics2D.drawImage(BIRD, 252, 420, 50, 50, null);
                break;
            }
            case 3: {
                graphics2D.drawImage(BIRD, 151, 420, 50, 50, null);
                graphics2D.drawImage(BIRD, 220, 420, 50, 50, null);
                graphics2D.drawImage(BIRD, 289, 420, 50, 50, null);
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String idButton = e.getActionCommand();
        switch (idButton) {
            case PAUSE_BUTTON: {
                isPause++;
                if (1 == isPause % 2) {
                    labelPause.setForeground(Color.GREEN);
                    labelPause.setText("<html><p align=\"center\">ON");

                    iActionThread.pauseGame();
                    iActionShowGame.ignorePlayPanel();
                } else {
                    labelPause.setForeground(Color.RED);
                    labelPause.setText("<html><p align=\"center\">OFF");

                    iActionThread.playGame();
                    iActionShowGame.focusPlayPanel();
                }
                break;
            }
            case MAIN_MENU_BUTTON: {
                backMenuMainPanel();
                break;
            }
            default: {
                break;
            }
        }
    }

    public void setIActionEnterGame(IActionEnterGame iActionEnterGame) {
        this.iActionEnterGame = iActionEnterGame;
    }

    public void setIActionThread(IActionThread iActionThread) {
        this.iActionThread = iActionThread;
    }

    public void setIActionShowGame(IActionShowGame iActionShowGame) {
        this.iActionShowGame = iActionShowGame;
    }

    @Override
    public void backMenuMainPanel() {
        iActionEnterGame.showMainMenuPanel();

        iActionThread.stopGame();

        if (1 == isPause % 2) {
            isPause = 0;
            labelPause.setForeground(Color.RED);
            labelPause.setText("<html><p align=\"center\">OFF");
        }
    }
}