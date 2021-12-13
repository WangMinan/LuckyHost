package com.Item;

import javax.swing.*;

/**
 * 这个类用于描述存储对象(主要是转盘的水果和特殊道具)
 * @author WuSigan
 * @version 0.0.1
 */
public class Item {
    private String id = "000";
    private String name = "empty";
    private JButton icon = new JButton();
    private String description = "null";

    public Item(){

    }

    public Item(String id, String name, JButton icon,String description) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int calculateMoney(){
        return 0;
    }

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
