package com.Item;

import javax.swing.*;

/**
 * 这个类用于描述存储对象(主要是转盘的水果和特殊道具)
 * @author WuSigan
 * @version 0.0.1
 */
public class Item {
    protected final int PANEL_SIZE = 20;

    private String name = "empty";
    private JButton icon = new JButton();
    private String description = "null";
    private ItemPosition position = null;
    private ImageIcon imageIcon;

    public Item(){

    }

    public Item(String name, JButton icon,String description) {
        this.name = name;
        this.icon = icon;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JButton getIcon() {
        return icon;
    }

    public void setIcon(JButton icon) {
        this.icon = icon;
    }

    public int calculateMoney(ItemCategory category){
        return 0;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public ItemPosition getPosition() {
        return position;
    }

    public void setPosition(ItemPosition position) {
        this.position = position;
    }

    /**
     * 判断是否在周边
     * @param tmpPos 被判断者的当前下标
     * @param currentItemPosition 加成给予者的当前下标
     * @return 一个布尔型变量包含是否相邻的信息
     */
    public boolean isNear(int tmpPos, ItemPosition currentItemPosition){
        boolean flag = false;
        int currentItemPos = currentItemPosition.getRow() * 5 + currentItemPosition.getColum();
        if(currentItemPos- 1 == tmpPos || currentItemPos + 1 == tmpPos || currentItemPos - 6 == tmpPos ||
            currentItemPos - 5 == tmpPos || currentItemPos - 4 == tmpPos || currentItemPos + 4 == tmpPos ||
            currentItemPos + 5 == tmpPos || currentItemPos + 6 == tmpPos){
            flag = true;
        }
        return flag;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

}
