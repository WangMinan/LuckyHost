package com.game;

import javax.swing.*;

/**
 * @author WangMinan
 * @description To define the main entrance of the game
 * @see GameBoard
 */
public class MainEntrance {
    /**
     * 游戏欢迎界面，有新游戏、继续、退出三个按钮
     */
    private JFrame mainFrame;
    private GameBoard gameBoard;

    public MainEntrance() {
    }

    public MainEntrance(JFrame mainFrame, GameBoard gameBoard) {
        this.mainFrame = mainFrame;
        this.gameBoard = gameBoard;
    }

    public void playNewGame(){

    }

    public void playSavedGame(){

    }

    public void exit(){

    }

    public static void main(String args[]){

    }
}
