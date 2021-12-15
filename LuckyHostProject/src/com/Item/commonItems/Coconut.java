package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 椰子类
 * @see CommonItem
 */
public class Coconut extends CommonItem {

    public Coconut(){
        this.setPriority(1);
        this.setName("coconut");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/coconut.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值2金币,被消除后给予10金币");
    }

    @Override
    public int calculateMoney(ItemCategory category) {
        return 2;
    }
}
