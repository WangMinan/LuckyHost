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
import java.io.*;
import java.lang.module.ResolvedModule;
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
    private JFrame commonSelectFrame;
    private JTextArea removeArea;

    /**
     * 每周老虎机希望达到的目标金币
     */
    private int targetMoney = 25;

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
     * 游戏中通过选取得到的普通物品
     */
    private ItemCategory gameCommonItems;

    /**
     * 游戏中通过选取得到的特殊物品
     */
    private ItemCategory gameSpecialItems;

    /**
     * 剩余的移除次数
     */
    private int chancesToRemove = 0;

    /**
     * 旋转计数器
     */
    private int countDays = 0;

    /**
     * @Constructor
     */
    public GameBoard() {
    }

//    /**
//     * 注释见上
//     * @param gameFrame
//     * @param targetMoney
//     * @param totalMoney
//     * @param commonItems
//     * @param specialItems
//     */
//    public GameBoard(JFrame gameFrame, int targetMoney, int totalMoney, ItemCategory commonItems,
//                     ItemCategory specialItems, ItemCategory panelCommonItems,
//                     ItemCategory panelSpecialItems, ItemCategory inventory) {
//        this.gameFrame = gameFrame;
//        this.targetMoney = targetMoney;
//        this.totalMoney = totalMoney;
//        this.commonItems = commonItems;
//        this.specialItems = specialItems;
//        this.panelCommonItems = panelCommonItems;
//    }

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
        //this.panelSpecialItems = new ItemCategory();
        this.gameSpecialItems = new ItemCategory();

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

        initItemCategories();
        this.panelCommonItems = new ItemCategory();
        this.gameCommonItems = new ItemCategory();
        this.gameSpecialItems = new ItemCategory();

        this.gameFrame = new JFrame();
        //读取模块
        File file = new File("save/save.dat");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            this.targetMoney = Integer.parseInt(br.readLine());
            this.totalMoney = Integer.parseInt(br.readLine());
            this.countDays = Integer.parseInt(br.readLine());
            this.chancesToRemove = Integer.parseInt(br.readLine());
            //读取面板物品
            br.readLine();
            for(int i = 0; i < 20; i++){
                String itemName = br.readLine();
                for(int j = 0; j < commonItems.getItemCategory().size(); j++){
                    if(itemName.equals(commonItems.getItemCategory().elementAt(j).getName())){
                        CommonItem item = (CommonItem) commonItems.getItemCategory().elementAt(j).createNewItem();
                        item.setPosition(new ItemPosition(i/5,i%5));
                        panelCommonItems.addItem(item);
                        break;
                    }
                }
            }

            //读取其他普通物品
            br.readLine();
            int numOfGameCommonItems = Integer.parseInt(br.readLine());
            for(int i = 0; i < numOfGameCommonItems; i++){
                String itemName = br.readLine();
                for(int j = 0; j < commonItems.getItemCategory().size(); j++){
                    if(itemName.equals(commonItems.getItemCategory().elementAt(j).getName())){
                        CommonItem item = (CommonItem) commonItems.getItemCategory().elementAt(j).createNewItem();
                        gameCommonItems.addItem(item);
                    }
                }
            }

            //读取特殊物品
            br.readLine();
            int numOfGameSpecialItems = Integer.parseInt(br.readLine());
            for(int i = 0; i < numOfGameSpecialItems; i++){
                String itemName = br.readLine();
                for(int j = 0; j < specialItems.getItemCategory().size(); j++){
                    if(itemName.equals(specialItems.getItemCategory().elementAt(j).getName())){
                        SpecialItem item = (SpecialItem)specialItems.getItemCategory().elementAt(j).createNewItem();
                        gameSpecialItems.addItem(item);
                    }
                }
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        //specialItemPanel.setLayout(null);
        specialItemPanel.setBounds(10,10,200,350);
        specialItemPanel.setBackground(Color.ORANGE);

        for(int i = 0; i < gameSpecialItems.getItemCategory().size(); i++){
            specialItemPanel.add(gameSpecialItems.getItemCategory().elementAt(i).createNewItem().getIcon());
        }

        /**
         * 旋转按钮
         */
        JButton rotateButton = new JButton("旋转");
        rotateButton.setBounds(428,472,170,60);
        rotateButton.setFont(new Font("Syria",Font.BOLD,50));
        rotateButton.setBackground(new Color(17,201,99));
        rotateButton.setFocusPainted(false);
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate();
            }
        });

        /**
         * 剩余的删除次数
         */
        removeArea = new JTextArea();
        removeArea.setBounds(0,410,200,50);
        removeArea.setFont(new Font("Syria",Font.BOLD,20));
        removeArea.setBackground(Color.ORANGE);
        removeArea.setLineWrap(true);
        removeArea.setText("剩余的移除次数(按下面板按钮移除):" + chancesToRemove);
        removeArea.setForeground(Color.BLACK);
        removeArea.setEditable(false);

        /**
         * 金币数
         */
        goldArea = new JTextArea();
        goldArea.setBounds(0,480,200,60);
        goldArea.setFont(new Font("Syria",Font.BOLD,20));
        goldArea.setBackground(Color.orange);
        goldArea.setText("金币数：" + totalMoney);
        goldArea.setForeground(Color.BLACK);
        goldArea.setEditable(false);

        /**
         * 返回按钮
         */
        JButton returnButton = new JButton("返回");
        returnButton.setFocusPainted(false);
        returnButton.setBounds(850,472,170,60);
        returnButton.setFont(new Font("Syria",Font.BOLD,40));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revise();
                gameFrame.dispose();
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
            commonItemsButtons[i] =
                ((CommonItem)panelCommonItems.getItemCategory().elementAt(i)).createNewItem().getIcon();
            slotMachine.add(commonItemsButtons[i]);

            int pos = i;

            /**
             * 设置移除效果
             */
            commonItemsButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(chancesToRemove > 0){
                        chancesToRemove--;
                        panelCommonItems.getItemCategory().setElementAt(new Empty(),pos);
                        slotMachine.removeAll();
                        for(int j = 0; j < 20; j++){
                            JButton[] commonItemsButtons = new JButton[20];
                            for(int i=0; i<20; i++){
                                commonItemsButtons[i] = new Empty().getIcon();
                                if(!panelCommonItems.getItemCategory().elementAt(i).getName().equals("empty")){
                                    commonItemsButtons[i] =
                                            ((CommonItem)panelCommonItems.getItemCategory().elementAt(i)).createNewItem().getIcon();
                                }
                                slotMachine.add(commonItemsButtons[i]);
                            }
                        }
                    } else {
                        return;
                    }
                }
            });
        }

        this.gameFrame.add(slotMachine);
        this.gameFrame.add(specialItemPanel);
        this.gameFrame.add(rotateButton);
        this.gameFrame.add(removeArea);
        this.gameFrame.add(goldArea);
        this.gameFrame.add(returnButton);
        this.gameFrame.setLayout(null);
        this.gameFrame.setSize(1024,576);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e){
                //System.out.println("hi");
                super.windowClosed(e);
                revise();
            }

            @Override
            public void windowClosing(WindowEvent e){
                //System.out.println("hi");
                super.windowClosed(e);
                revise();
            }
        });
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
            //this.gameFrame.setVisible(false);
            this.gameFrame.dispose();
            showMessageDialog(MainEntrance.mainFrame,"你没能按时支付房租，游戏结束");
            MainEntrance.mainFrame.setVisible(true);
            return true;
        }
    }

    /**
     * 计算每一回合的金币
     * @return 0;
     */
    public int calculateTotalMoney(){

        int total = 0;

        for(int i=4;i>=0;i--){
            for(int j = 0; j < panelCommonItems.getItemCategory().size();j++){
                if(((CommonItem)(panelCommonItems.getItemCategory().elementAt(j))).getPriority() == i){
                    total = total + panelCommonItems.getItemCategory().elementAt(j).calculateMoney(panelCommonItems);
                }
            }
        }

        for(int i = 0; i<gameSpecialItems.getItemCategory().size();i++){
            total = total + gameSpecialItems.getItemCategory().elementAt(i).calculateMoney(panelCommonItems);
        }

        return total;
    }

    /**
     * 旋转
     */
    public void rotate(){
        //提示框
        if(countDays != 6){
            showMessageDialog(null,"离下次支付还有" + (6-countDays) + "天，下次需支付"
                    + targetMoney + "枚金币");
        }

        //算钱
        int tmpNum = calculateTotalMoney();
        totalMoney = totalMoney + tmpNum;
        goldArea.setText("金币数：" + totalMoney + "\n上一次旋转得到" + tmpNum);
        //如果一周结束，扣除房租，下周房租加50
        if(countDays == 6 && judgeLose()){
            return;
        } else if(countDays == 6 && !judgeLose()){
            showMessageDialog(this.gameFrame,"一周结束了,您获得了删除面板物品次数和选择特殊物品的机会。");
            this.chancesToRemove += 2;
            this.removeArea.setText("剩余的移除次数:" + chancesToRemove);
            this.totalMoney = this.totalMoney - targetMoney;
            this.targetMoney = this.targetMoney + 50;
            chooseSpecialItem();
            chooseCommonItem();
        } else {
            chooseCommonItem();
        }
    }

    /**
     * 更新老虎机界面
     */
    public void updateSlotMachine(){

        //将panelCommonItems中的物品全部变成空
        for(int i = 0;i<20;i++){
            panelCommonItems.getItemCategory().addElement(new Empty());
        }

        if(gameCommonItems.getItemCategory().size()<=20){
            //全部取
            for(int i = 0; i <gameCommonItems.getItemCategory().size(); i++){
                givePosition(((CommonItem) gameCommonItems.getItemCategory().elementAt(i)).createNewItem());
            }
            //从gameItem里面删去加入老虎机的部分
            gameCommonItems.getItemCategory().clear();
        } else {
            //取20个
            int[] a = new int[gameCommonItems.getItemCategory().size()];
            for(int i=0;i<a.length;i++){
                a[i] = 0;
            }

            int cnt = 0;
            while(cnt < 20){
                int totalNum = gameCommonItems.getItemCategory().size();
                Random rnd = new Random();
                int pos = rnd.nextInt(totalNum);
                int pos1 = rnd.nextInt(20);
                if(a[pos1] == 0){
                    a[pos1] = 1;
                    panelCommonItems.getItemCategory().setElementAt(
                            ((CommonItem)gameCommonItems.getItemCategory().elementAt(pos)).createNewItem(),
                            pos1);
                    //gameCommonItems.getItemCategory().removeElementAt(pos);
                    cnt++;
                }
            }

            /**
             * 加入老虎机之后设定位置
             */
            for(int i=0; i<20; i++){
                ItemPosition tmpPosition = new ItemPosition();
                tmpPosition.setRow(i/5);
                tmpPosition.setColum(i%5);
                panelCommonItems.getItemCategory().elementAt(i).setPosition(tmpPosition);
            }
        }

        /**
         * 重新绘图
         */
        slotMachine.removeAll();
        JButton[] commonItemsButtons = new JButton[20];
        for(int i=0; i<20; i++){
            commonItemsButtons[i] = new Empty().getIcon();
            if(!panelCommonItems.getItemCategory().elementAt(i).getName().equals("empty")){
                commonItemsButtons[i] =
                    ((CommonItem)panelCommonItems.getItemCategory().elementAt(i)).createNewItem().getIcon();
            }
            slotMachine.add(commonItemsButtons[i]);
        }

    }

    /**
     * 增加普通物品
     */
    public void chooseCommonItem(){
        //算完钱之后把panelCommonItems里的东西加入到gameCommonItems里面
        for(int i = 0; i < 20; i++){
            if(!panelCommonItems.getItemCategory().elementAt(i).getName().equals("empty")){
                gameCommonItems.getItemCategory().addElement(
                        ((CommonItem)panelCommonItems.getItemCategory().elementAt(i)).createNewItem());
            }
        }
        panelCommonItems.getItemCategory().clear();

        //显示选取界面（普通物品）

        commonSelectFrame = new JFrame();

        //跳过按钮
        JButton skipButton = new JButton("跳过");
        skipButton.setFocusPainted(false);
        skipButton.setBounds(430,0,100,40);
        skipButton.setFont(new Font("Syria",Font.BOLD,20));
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSlotMachine();
                //commonSelectFrame.setVisible(false);
                commonSelectFrame.dispose();
                gameFrame.setVisible(true);
            }
        });

        /**
         * 物品栏按钮
         */
        JButton materialButton = new JButton("物品栏");
        materialButton.setFocusPainted(false);
        materialButton.setBounds(850,0,100,40);
        materialButton.setFont(new Font("Syria",Font.BOLD,20));
        materialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMaterialFrame();
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
            //选择按钮
            options[i] = ((CommonItem)commonItems.getItemCategory().elementAt(itemPos[i])).createNewItem().getIcon();
            options[i].setBounds(100,0,50,50);
            CommonItem tmpItem =
                    ((CommonItem) commonItems.getItemCategory().elementAt(itemPos[i])).createNewItem();
            options[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //更新panelCommonItems
                    gameCommonItems.getItemCategory().addElement(tmpItem);
                    updateSlotMachine();

                    commonSelectFrame.dispose();
                    gameFrame.setVisible(true);
                }
            });

            //描述界面
            descriptions[i] = new JTextArea();
            descriptions[i].setFont(new Font("Syria",Font.BOLD,20));
            descriptions[i].setBounds(10,60,230,350);
            descriptions[i].setLineWrap(true);        //激活自动换行功能
            //descriptions[i].setWrapStyleWord(true);            // 激活断行不断字功能
            descriptions[i].setText(commonItems.getItemCategory().elementAt(itemPos[i]).getName() +
                    ": \n" + commonItems.getItemCategory().elementAt(itemPos[i]).getDescription());
            descriptions[i].setEditable(false);

            //添加
            selectPanels[i].add(options[i]);
            selectPanels[i].add(descriptions[i]);
        }

        if(countDays == 6){
            countDays = -1;
        }
        countDays ++;

        //添加部分
        commonSelectFrame.add(skipButton);
        for(int i = 0; i < 3; i++){
            commonSelectFrame.add(selectPanels[i]);
        }

        commonSelectFrame.add(materialButton);

        Container container = commonSelectFrame.getContentPane();
        container.setBackground(Color.orange);
        commonSelectFrame.setLayout(null);
        commonSelectFrame.setSize(976,576);
        commonSelectFrame.setLocationRelativeTo(null);
        commonSelectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        commonSelectFrame.setVisible(true);
    }

    /**
     * 增加特殊物品
     */
    public void chooseSpecialItem(){

        /**
         * 规定上限
         */
        if(gameSpecialItems.getItemCategory().size() > 2){
            return;
        }

        JFrame selectFrame = new JFrame("chooseSpecialItem");

        //跳过按钮
        JButton skipButton = new JButton("跳过");
        skipButton.setBounds(430,0,100,40);
        skipButton.setFont(new Font("Syria",Font.BOLD,20));
        skipButton.setFocusPainted(false);
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //selectFrame.setVisible(false);
                selectFrame.dispose();
                commonSelectFrame.setVisible(true);
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
        //随机从specialItems中找出三种物品
        while(cnt<3){
            Random random = new Random();
            int pos = random.nextInt(specialItems.getItemCategory().size());
            /**
             * 同一次内不重复
             */
            if(pos != itemPos[0] && pos != itemPos[1] && pos != itemPos[2]){
                itemPos[cnt] = pos;
                cnt++;
            }
        }

        JButton[] options = new JButton[3];
        JTextArea[] descriptions = new JTextArea[3];

        for(int i = 0; i < 3; i++){
            //选择按钮
            options[i] = ((SpecialItem)specialItems.getItemCategory().elementAt(itemPos[i])).createNewItem().getIcon();
            options[i].setBounds(100,0,50,50);
            SpecialItem tmpItem = (SpecialItem) specialItems.getItemCategory().elementAt(itemPos[i]);
            options[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameSpecialItems.addItem(tmpItem);

                    specialItemPanel.removeAll();
                    int tmpX = 0;
                    int tmpY = 0;
                    for(int j = 0;j<gameSpecialItems.getItemCategory().size();j++){
                        JButton button = ((SpecialItem)gameSpecialItems.getItemCategory().elementAt(j)).createNewItem().getIcon();
                        button.setBounds(tmpX,tmpY,50,50);
                        if(j%2 == 1){
                            tmpX = 0;
                            tmpY += 50;
                        }
                        tmpX+=100;
                        specialItemPanel.add(button);
                    }

                    selectFrame.dispose();
                }
            });

            //描述界面
            descriptions[i] = new JTextArea();
            descriptions[i].setFont(new Font("Syria",Font.BOLD,20));
            descriptions[i].setBounds(10,60,230,350);
            descriptions[i].setLineWrap(true);        //激活自动换行功能
            //descriptions[i].setWrapStyleWord(true);            // 激活断行不断字功能
            descriptions[i].setText(specialItems.getItemCategory().elementAt(itemPos[i]).getName() +
                    ": \n" + specialItems.getItemCategory().elementAt(itemPos[i]).getDescription());
            descriptions[i].setEditable(false);

            //添加
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
        selectFrame.setLocationRelativeTo(null);
        selectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //保证选取窗口前置
        selectFrame.setAlwaysOnTop(!selectFrame.isAlwaysOnTop());
        selectFrame.setVisible(true);
    }

    /**
     * 展示物品栏
     */
    public void showMaterialFrame(){
        JFrame materialFrame = new JFrame();
        JLabel label = new JLabel("   物品栏");
        label.setFont(new Font("Syria",Font.BOLD,20));
        label.setOpaque(true);
        label.setBackground(Color.YELLOW);
        label.setBounds(450,0,100,40);

        JButton returnButton = new JButton("返回");
        returnButton.setFont(new Font("Syria",Font.BOLD,20));
        returnButton.setBounds(850,0,100,40);
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materialFrame.removeAll();
                materialFrame.dispose();
                commonSelectFrame.setVisible(true);
            }
        });

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBackground(Color.WHITE);

        //调整大小
        if(gameSpecialItems.getItemCategory().size()+gameCommonItems.getItemCategory().size()<13){
            area.setPreferredSize(new Dimension(800,420));
        } else {
            area.setPreferredSize(new Dimension(800,
                    (gameSpecialItems.getItemCategory().size()+gameCommonItems.getItemCategory().size())*25));
        }

        area.setFont(new Font("Syria",Font.BOLD,16));
        area.setLineWrap(true);        //激活自动换行功能
        area.setWrapStyleWord(true);            // 激活断行不断字功能
        area.setText("");

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < gameSpecialItems.getItemCategory().size(); i++){
            sb.append(gameSpecialItems.getItemCategory().elementAt(i).getName());
            sb.append(":");
            sb.append(gameSpecialItems.getItemCategory().elementAt(i).getDescription());
            sb.append("\n");
        }
        for(int i = 0; i < gameCommonItems.getItemCategory().size(); i++){
            sb.append(gameCommonItems.getItemCategory().elementAt(i).getName());
            sb.append(": ");
            sb.append(gameCommonItems.getItemCategory().elementAt(i).getDescription());
            sb.append("\n");
        }

        area.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(100,60,700,400);

        materialFrame.add(label);
        materialFrame.add(returnButton);
        materialFrame.add(scrollPane);

        Container container = materialFrame.getContentPane();
        container.setBackground(Color.ORANGE);
        materialFrame.setLayout(null);
        materialFrame.setSize(1000,500);
        materialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        materialFrame.setLocationRelativeTo(null);
        materialFrame.setVisible(true);
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
     * 保存函数
     */
    public void revise(){
        //System.out.println("hi");
        try {
            //替换掉现有的所有文件并记录
            File file = new File("save/save.dat");
            FileWriter fw = new FileWriter(file);

            fw.write(this.targetMoney + "\n");
            fw.write(this.totalMoney + "\n");
            fw.write(this.countDays + "\n");
            fw.write(this.chancesToRemove + "\n");

            //先commonItems
            /*  规定存档格式
                把panelCommonItems里面的东西加入gameCommonItems中
             */
            fw.write("panelCommonItems:" + "20" + "\n");
            for(int i = 0; i < 20; i++){
                fw.write(panelCommonItems.getItemCategory().elementAt(i).getName()
                            + "\n");
            }

            fw.write("gameCommonItems:\n");
            fw.write(gameCommonItems.getItemCategory().size() + "\n");
            for(int i = 0; i < gameCommonItems.getItemCategory().size(); i++){
                fw.write(gameCommonItems.getItemCategory().elementAt(i).getName());
                fw.write("\n");
            }

            //再specialItems
            fw.write("gameSpecialItems:\n");
            fw.write(gameSpecialItems.getItemCategory().size() + "\n");
            for(int i = 0; i < gameSpecialItems.getItemCategory().size(); i++){
                fw.write(gameSpecialItems.getItemCategory().elementAt(i).getName());
                fw.write("\n");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 临时测试用主函数，不代表最后函数
     */
    public static void main(String args[]){
        GameBoard gameBoard = new GameBoard();
        gameBoard.initNewGame();
    }
}