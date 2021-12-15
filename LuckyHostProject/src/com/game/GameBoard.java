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
     * gameFrame 游戏窗口，实现游戏的基本功能
     */
    private JFrame gameFrame;
    private JPanel slotMachine;
    private JPanel specialItemPanel;
    private JTextArea goldArea;

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
     * 游戏中通过选取得到的普通物品
     */
    private ItemCategory gameCommonItems;

    /**
     * 游戏中通过选取得到的特殊物品
     */
    private ItemCategory gameSpecialItems;

    /**
     * 物品栏里的所有物品
     */
    private ItemCategory inventory;

    /**
     * 剩余的移除次数
     */
    private int chancesToRemove;

    /**
     * 旋转计数器
     */
    private int countDays = 0;

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
        this.gameCommonItems = new ItemCategory();

        for(int i = 0; i < 20; i++){
            this.panelCommonItems.addItem(new Empty());
        }

        gameCommonItems.addItem(cat);
        gameCommonItems.addItem(flower);
        gameCommonItems.addItem(coin);
        gameCommonItems.addItem(halfCoconut);
        gameCommonItems.addItem(pearl);

        givePosition(cat);
        givePosition(flower);
        givePosition(coin);
        givePosition(halfCoconut);
        givePosition(pearl);

        this.panelSpecialItems = new ItemCategory();
        this.gameSpecialItems = new ItemCategory();
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
        slotMachine = new JPanel();
        slotMachine.setLayout(new GridLayout(4,5));
        slotMachine.setBorder(new LineBorder(Color.BLACK));
        slotMachine.setBounds(210,0,610,460);
        slotMachine.setBackground(Color.white);

        /**
         * 特殊物品panel
         */
        specialItemPanel = new JPanel();
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
        goldArea = new JTextArea();
        goldArea.setBounds(0,480,170,60);
        goldArea.setFont(new Font("Syria",Font.BOLD,20));
        goldArea.setBackground(Color.orange);
        goldArea.setText("金币数：" + totalMoney);
        goldArea.setForeground(Color.YELLOW);
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
     * 存在一个退出的出口
     */
    public boolean judgeLose(){
        if(this.totalMoney >= this.targetMoney){
            return false;
        } else {
            this.gameFrame.setVisible(false);
            showMessageDialog(MainEntrance.mainFrame,"你没能按时支付房租，游戏结束");
            return true;
        }
    }

    /**
     * 计算每一回合的金币
     * @return 0;
     */
    public int calculateTotalMoney(){

        int total = 0;

        for(int i = 0; i<20; i++){
            total = total + panelCommonItems.getItemCategory().elementAt(i).calculateMoney(panelCommonItems);
        }

        for(int i = 0; i<panelSpecialItems.getItemCategory().size();i++){
            total = total + panelSpecialItems.getItemCategory().elementAt(i).calculateMoney(panelCommonItems);
        }

        return total;
    }

    /**
     * 旋转
     */
    public void rotate(){

        //算钱
        totalMoney = totalMoney + calculateTotalMoney();
        //如果一周结束，扣除房租，下周房租加50
        if(countDays == 6 && !judgeLose()){
            this.totalMoney = this.totalMoney - targetMoney;
            this.targetMoney += 50;
            countDays = 0;
        }
        //显示选取界面（普通物品）

        JFrame selectFrame = new JFrame();

        showMessageDialog(selectFrame,"离下次支付还有" + (7-countDays) + "天，下次需支付"
                            + targetMoney + "枚金币");

        //跳过按钮
        JButton skipButton = new JButton("跳过");
        skipButton.setBounds(430,0,100,40);
        skipButton.setFont(new Font("Syria",Font.BOLD,20));
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFrame.setVisible(false);
                gameFrame.setVisible(true);
            }
        });

        //左中右三个panel
        JPanel[] selectPanels = new JPanel[3];
        int posX = 38;
        int posY = 76;

        for(int i = 0; i < 3; i++){
            selectPanels[i] = new JPanel();
            selectPanels[i].setBorder(new LineBorder(Color.BLACK));
            selectPanels[i].setBackground(Color.WHITE);
            selectPanels[i].setBounds(posX,posY,250,450);
            posX+=300;
        }

        int[] itemPos = {-1,-1,-1};
        int cnt = 0;
        //随即从commonItems中找出三种物品
        while(cnt<3){
            Random random = new Random();
            int pos = random.nextInt(commonItems.getItemCategory().size());
            //empty存在6的位置上
            if(pos != itemPos[0] && pos != itemPos[1] && pos != itemPos[2] && pos != 6){
                itemPos[cnt] = pos;
                cnt++;
            }
        }

        JButton[] options = new JButton[3];
        JTextArea[] descriptions = new JTextArea[3];

        for(int i = 0; i < 3; i++){
            options[i] = commonItems.getItemCategory().elementAt(itemPos[i]).getIcon();
            options[i].setBounds(100,0,50,50);
            descriptions[i] = new JTextArea();
            descriptions[i].setFont(new Font("Syria",Font.BOLD,20));
            descriptions[i].setBounds(10,60,230,350);
            descriptions[i].setLineWrap(true);        //激活自动换行功能
            //descriptions[i].setWrapStyleWord(true);            // 激活断行不断字功能
            descriptions[i].setText(commonItems.getItemCategory().elementAt(itemPos[i]).getName() +
                    ": \n" + commonItems.getItemCategory().elementAt(itemPos[i]).getDescription());
            descriptions[i].setEditable(false);
            selectPanels[i].add(options[i]);
            selectPanels[i].add(descriptions[i]);
        }

        //添加部分
        selectFrame.add(skipButton);
        for(int i = 0; i < 3; i++){
            selectFrame.add(selectPanels[i]);
        }

        Container container = selectFrame.getContentPane();
        container.setBackground(Color.orange);
        selectFrame.setLayout(null);
        selectFrame.setSize(976,576);
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selectFrame.setVisible(true);

        //用户3选1或跳过

        //显示选取界面（特殊物品（如果一周结束了））

        //用户3选1或跳过

        countDays ++;
        //更新panelCommonItems
        //updateMaterialList();
        //显示新界面
        //updateItemFrame();
    }

    /**
     * 更新物品栏
     */
    public void updateGameCommonItem(CommonItem item){

    }

    /**
     * 更新老虎机界面
     */
    public void updateSlotMachine(){

    }

//    /**
//     * 展示物品栏
//     */
//    public void showMaterialFrame(){
//
//    }

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
