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
}
