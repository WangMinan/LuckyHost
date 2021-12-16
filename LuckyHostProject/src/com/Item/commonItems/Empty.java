package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 空类，价值0枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class Empty extends CommonItem {
    public Empty(){
        this.setPriority(1);
        this.setName("empty");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/empty.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("");
    }

    public int calculateMoney(ItemCategory category){
        return 0;
    }

    @Override
    public Empty createNewCommonItem(){
        return new Empty();
    }
}
