package com.game;

import com.Item.ItemCatagory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author WangMinan
 * @description To define a gameBoard
 * @see MainEntrance
 */
public class GameBoard {
    /**
     * 游戏窗口，实现游戏的基本功能
     */
    private JFrame gameFrame;

    /**
     * 每周老虎机希望达到的目标金币
     */
    private int targetMoney;

    /**
     * 玩家当前拥有的金币
     */
    private int totalMoney;

    /**
     * 基础物品所在的列表
     */
    private ItemCatagory commonItems;

    /**
     * 剩余的移除次数
     */
    private int chancesToRemove;

    /**
     * 特殊物品所在的列表
     */
    private ItemCatagory specialItems;

    /**
     * @Constructor
     */
    public GameBoard() {
    }

    /**
     * 注释见上
     * @param gameFrame
     * @param targetMoney
     * @param totalMoney
     * @param commonItems
     * @param specialItems
     */
    public GameBoard(JFrame gameFrame, int targetMoney, int totalMoney, ItemCatagory commonItems, ItemCatagory specialItems) {
        this.gameFrame = gameFrame;
        this.targetMoney = targetMoney;
        this.totalMoney = totalMoney;
        this.commonItems = commonItems;
        this.specialItems = specialItems;
    }

    /**
     * 开始新游戏
     */
    public void initNewGame(){

    }

    /**
     * 开始储存在本地的游戏
     */
    public void initLoadedGame(){
        //读取模块
    }

    /**
     * 判断是否输掉游戏
     * @return 判断的结果
     */
    public boolean judgeLose(){
        return false;
    }

    /**
     * 计算每一回合的金币
     * @return 0;
     */
    public int calculateTotalMoney(){
        return 0;
    }

    /**
     * 旋转
     */
    public void rotate(){

    }

    /**
     * 更新老虎机界面
     */
    public void updateItemFrame(){

    }

    /**
     * 更新物品栏
     */
    public void updateMaterialList(){

    }

    /**
     * 展示物品栏
     */
    public void showMaterialFrame(){

    }

    /**
     * 自动保存模块的监听方法
     */
    class MyWindowListener extends WindowAdapter{
        @Override
        public void windowClosed(WindowEvent e){
            super.windowClosed(e);
        }
    }
}
