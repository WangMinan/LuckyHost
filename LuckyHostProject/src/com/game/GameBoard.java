package com.game;

import com.Item.*;
import com.Item.commonItems.*;
import com.Item.specialItems.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

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
    private int totalMoney = 0;

    /**
     * 基础物品所在的列表
     */
    private ItemCategory commonItems;

    /**
     * 特殊物品所在的列表
     */
    private ItemCategory specialItems;

    /**
     * 面板上的物品
     */
    private ItemCategory panelCommonItems;

    /**
     * 游戏中的加成物品
     */
    private ItemCategory panelSpecialItems;

    /**
     * 物品栏里的所有物品
     */
    private ItemCategory inventory;

    /**
     * 剩余的移除次数
     */
    private int chancesToRemove;

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
    public GameBoard(JFrame gameFrame, int targetMoney, int totalMoney, ItemCategory commonItems,
                     ItemCategory specialItems, ItemCategory panelCommonItems,
                     ItemCategory panelSpecialItems, ItemCategory inventory) {
        this.gameFrame = gameFrame;
        this.targetMoney = targetMoney;
        this.totalMoney = totalMoney;
        this.commonItems = commonItems;
        this.specialItems = specialItems;
        this.panelCommonItems = panelCommonItems;
        this.panelSpecialItems = panelSpecialItems;
        this.inventory = inventory;
    }

    /**
     * 开始新游戏
     */
    public void initNewGame(){
        initItemCategories();
        this.targetMoney = 25;
        this.gameFrame = new JFrame();
        showMessageDialog(gameFrame,"欢迎来到幸运房东，你有一周的时间来通过房间里的老虎机获取房租，房租每周上涨，祝你好运");

        Cat cat = new Cat();
        Flower flower = new Flower();
        Coin coin = new Coin();
        HalfCoconut halfCoconut = new HalfCoconut();
        Pearl pearl = new Pearl();

        this.panelCommonItems = new ItemCategory();
        for(int i = 0; i < 20; i++){
            this.panelCommonItems.addItem(new Empty());
        }

        givePosition(cat);
        givePosition(flower);
        givePosition(coin);
        givePosition(halfCoconut);
        givePosition(pearl);

        play();
    }

    /**
     * 开始储存在本地的游戏
     */
    public void initLoadedGame(){
        //读取模块
        play();
    }

    /**
     * 玩
     */
    public void play(){

        /**
         * 老虎机panel
         */
        JPanel slotMachine = new JPanel();
        slotMachine.setLayout(new GridLayout(4,5));
        slotMachine.setBorder(new LineBorder(Color.BLACK));
        slotMachine.setBounds(210,0,610,460);
        slotMachine.setBackground(Color.white);

        /**
         * 特殊物品panel
         */
        JPanel specialItemPanel = new JPanel();
        specialItemPanel.setLayout(new GridLayout(3,2));
        specialItemPanel.setBounds(0,0,200,345);
        specialItemPanel.setBackground(Color.ORANGE);

        /**
         * 旋转按钮
         */
        JButton rotateButton = new JButton("旋转");
        rotateButton.setBounds(428,472,170,60);
        rotateButton.setFont(new Font("Syria",Font.BOLD,50));
        rotateButton.setBackground(new Color(17,201,99));
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate();
            }
        });

        /**
         * 金币数
         */
        JTextArea goldArea = new JTextArea();
        goldArea.setBounds(0,480,170,60);
        goldArea.setFont(new Font("Syria",Font.BOLD,20));
        goldArea.setBackground(Color.orange);
        goldArea.setText("金币数：" + totalMoney);
        goldArea.setEditable(false);

        /**
         * 物品栏按钮
         */
        JButton materialButton = new JButton("物品栏");
        materialButton.setBounds(850,410,170,60);
        materialButton.setFont(new Font("Syria",Font.BOLD,40));

        /**
         * 返回按钮
         */
        JButton returnButton = new JButton("返回");
        returnButton.setBounds(850,472,170,60);
        returnButton.setFont(new Font("Syria",Font.BOLD,40));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revise();
                gameFrame.setVisible(false);
                MainEntrance.mainFrame.setVisible(true);
            }
        });

        /**
         * 更改JFrame颜色
         */
        Container container = gameFrame.getContentPane();
        container.setBackground(Color.orange);

        JButton[] commonItemsButtons = new JButton[20];
        for(int i=0; i<20; i++){
            commonItemsButtons[i] = new JButton();
            commonItemsButtons[i] = panelCommonItems.getItemCategory().elementAt(i).getIcon();
            slotMachine.add(commonItemsButtons[i]);
        }

        this.gameFrame.add(slotMachine);
        this.gameFrame.add(specialItemPanel);
        this.gameFrame.add(rotateButton);
        this.gameFrame.add(goldArea);
        this.gameFrame.add(materialButton);
        this.gameFrame.add(returnButton);
        this.gameFrame.setLayout(null);
        this.gameFrame.setSize(1024,576);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameFrame.setVisible(true);
    }

    /**
     * 基础化所有物品
     */
    public void initItemCategories(){

        this.commonItems = new ItemCategory();
        this.specialItems = new ItemCategory();

        commonItems.addItem(new Bee());
        commonItems.addItem(new Bubble());
        commonItems.addItem(new Cat());
        commonItems.addItem(new Coconut());
        commonItems.addItem(new Coin());
        commonItems.addItem(new Cow());
        commonItems.addItem(new Empty());
        commonItems.addItem(new Fish());
        commonItems.addItem(new Flower());
        commonItems.addItem(new GoldEgg());
        commonItems.addItem(new Goose());
        commonItems.addItem(new HalfCoconut());
        commonItems.addItem(new HollowFruit());
        commonItems.addItem(new Key());
        commonItems.addItem(new Milk());
        commonItems.addItem(new Monkey());
        commonItems.addItem(new Pearl());
        commonItems.addItem(new Rain());
        commonItems.addItem(new Strawberry());
        commonItems.addItem(new Sun());
        commonItems.addItem(new TreasureCase());

        specialItems.addItem(new BlackPepper());
        specialItems.addItem(new GreyPepper());
        specialItems.addItem(new LockRemover());
        specialItems.addItem(new MonkeyOlivander());
        specialItems.addItem(new RainCloud());

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
        //显示选取界面
        //用户3选1或跳过
        //更新panelCommonItems和commonItems
        //
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
     * 随机位置
     */
    public void givePosition(CommonItem item){
        while(true){
            Random random = new Random();
            int randRow = random.nextInt(4);//[0,3)
            int randCol = random.nextInt(5);//[0,4)
            if(panelCommonItems.getItemCategory().elementAt(randRow*5+randCol).getName().equals("empty")){
                ItemPosition position = new ItemPosition();
                position.setRow(randRow);
                position.setColum(randCol);
                item.setPosition(position);
                panelCommonItems.getItemCategory().setElementAt(item,randRow*5+randCol);
                break;
            }
        }

    }

    /**
     * 自动保存模块的监听方法
     */
    class MyWindowListener extends WindowAdapter{
        @Override
        public void windowClosed(WindowEvent e){
            super.windowClosed(e);
            revise();
        }
    }

    /**
     * 保存函数
     */
    public void revise(){

    }

    /**
     * 临时测试用主函数，不代表最后函数
     */
    public static void main(String args[]){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initNewGame();

    }
}
