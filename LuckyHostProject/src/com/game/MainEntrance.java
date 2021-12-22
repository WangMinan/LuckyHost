package com.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * To define the main entrance of the game
 * @author WuSigan
 * @see GameBoard
 */
public class MainEntrance extends JFrame{
    /**
     * 游戏欢迎界面，有新游戏、继续、退出三个按钮
     */
    static public JFrame mainFrame;

    public MainEntrance(JFrame mainFrame) {
        MainEntrance.mainFrame = mainFrame;

    }

    public static void main(String[] args){
        //默认的字体样式
        //游戏主界面，设置大小为1024X576，尺寸不可改，颜色为橙色
        mainFrame = new JFrame("幸运房东");
        mainFrame.setSize(1024,576);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);
        mainFrame.setFont(new Font("Syria",Font.BOLD,20));
        mainFrame.setLocationRelativeTo(null);

        Container container = mainFrame.getContentPane();
        container.setBackground(Color.orange);

        //主界面的标题，即游戏大标题
        JLabel startText = new JLabel("幸运房东");
        startText.setBounds(300,50,500,150);
        startText.setFont(new Font("Syria",Font.BOLD,100));

        //主界面的开始新游戏按钮
        JButton startButton = new JButton("开始新游戏");
        startButton.setBounds(430,300,150,40);
        startButton.setFont(new Font("Syria",Font.BOLD,20));
        startButton.addActionListener(new StartActionListener());
        startButton.setFocusPainted(false);

        //主界面的继续游戏按钮
        JButton continueButton = new JButton("继续游戏");
        continueButton.setBounds(430,350,150,40);
        continueButton.setFont(new Font("Syria",Font.BOLD,20));
        continueButton.addActionListener(new ContinueActionListener());
        continueButton.setFocusPainted(false);

        //主界面的退出游戏按钮
        JButton exitButton = new JButton("退出游戏");
        exitButton.setBounds(430,400,150,40);
        exitButton.setFont(new Font("Syria",Font.BOLD,20));
        exitButton.addActionListener(new ExitListener());
        exitButton.setFocusPainted(false);

        mainFrame.add(startText);
        mainFrame.add(startButton);
        mainFrame.add(continueButton);
        mainFrame.add(exitButton);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

 class StartActionListener implements ActionListener {
     @Override
     public void actionPerformed(ActionEvent e) {
         MainEntrance.mainFrame.setVisible(false);
         GameBoard gameBoard = new GameBoard();
         gameBoard.initNewGame();
     }
 }

 class ContinueActionListener implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
        MainEntrance.mainFrame.setVisible(false);
        GameBoard gameBoard = new GameBoard();
        gameBoard.initLoadedGame();
     }
 }

 class ExitListener implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
         System.exit(0);
     }
 }