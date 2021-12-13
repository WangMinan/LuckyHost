package com.Item;

import javax.swing.*;

/**
 * This Class
 * @author WangMinan
 * @version 0.0.1
 * @see Item
 */
public class CommonItem extends Item{
    private int priority;

    public CommonItem() {
    }

    public CommonItem(String id, String name, JButton icon,String decription, int priority){
        super(id,name,icon,decription);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int calculateMoney() {
        return 0;
    }
}
