package com.Item;

import javax.swing.*;

/**
 * 这个类表示Item中需要在转盘中出现，可以获取金币的对象（水果）
 * @author WangMinan
 * @version 0.0.1
 * @see Item
 */
public class CommonItem extends Item{
    private int priority = 0;

    public CommonItem() {
    }

    public CommonItem(String name, JButton icon,String description, int priority){
        super(name,icon,description);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int calculateMoney(ItemCategory category) {
        return 0;
    }

}
