package com.Item;

import javax.swing.*;

/**
 * 这个类表示Item中需要在转盘中出现，可以获取金币的对象（水果）
 * @author WangMinan
 * @version 0.0.1
 * @see Item
 */
public class CommonItem extends Item{
    /**
     * @param priority
     * 1：基础物品，可被消除，可被加成
     * 2：加成物品，可为其他物品提供加成
     * 3：消除物品，可消除其他物品
     * 4：增加物品，可增加基础物品的物品
     */
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

    @Override
    public CommonItem createNewItem(){
        return new CommonItem();
    }
}
