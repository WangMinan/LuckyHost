package com.game;

import com.sun.tools.javac.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
    static public AudioInputStream audioInputStream;
    static public Clip clip;

    public MainEntrance(JFrame mainFrame) {
        MainEntrance.mainFrame = mainFrame;
    }



    public static void main(String[] args){

        File file = new File("music/mainEntranceBGM.wav");
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }


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
        startButton.addActionListener(e -> {
            //更换背景音乐
            File file1 = new File("music/gameBoardBGM.wav");
            try {
                clip.stop();
                audioInputStream = AudioSystem.getAudioInputStream(file1);
                clip = AudioSystem.getClip();
                clip.open(MainEntrance.audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                e1.printStackTrace();
            }

            MainEntrance.mainFrame.setVisible(false);
            GameBoard gameBoard = new GameBoard();
            gameBoard.initNewGame();
        });
        startButton.setFocusPainted(false);

        //主界面的继续游戏按钮
        JButton continueButton = new JButton("继续游戏");
        continueButton.setBounds(430,350,150,40);
        continueButton.setFont(new Font("Syria",Font.BOLD,20));
        continueButton.addActionListener(e->{
            File file2 = new File("music/gameBoardBGM.wav");
            try {
                clip.stop();
                audioInputStream = AudioSystem.getAudioInputStream(file2);
                clip = AudioSystem.getClip();
                clip.open(MainEntrance.audioInputStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                e1.printStackTrace();
            }

            MainEntrance.mainFrame.setVisible(false);
            GameBoard gameBoard = new GameBoard();
            gameBoard.initLoadedGame();
        });
        continueButton.setFocusPainted(false);

        //主界面的退出游戏按钮
        JButton exitButton = new JButton("退出游戏");
        exitButton.setBounds(430,400,150,40);
        exitButton.setFont(new Font("Syria",Font.BOLD,20));
        exitButton.addActionListener(e->{
            System.exit(0);
        });
        exitButton.setFocusPainted(false);

        //开启声音的按钮
        JButton playButton = new JButton("播放声音");
        playButton.setBounds(790,10,100,40);
        playButton.setFont(new Font("Syria",Font.BOLD,15));
        playButton.addActionListener(e->{
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        });
        playButton.setFocusPainted(false);

        //关闭声音的按钮
        JButton silenceButton = new JButton("静音");
        silenceButton.setBounds(900,10,100,40);
        silenceButton.setFont(new Font("Syria",Font.BOLD,15));
        silenceButton.addActionListener(e->{
            clip.stop();
        });
        silenceButton.setFocusPainted(false);

        mainFrame.add(startText);
        mainFrame.add(startButton);
        mainFrame.add(continueButton);
        mainFrame.add(exitButton);
        mainFrame.add(playButton);
        mainFrame.add(silenceButton);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}


