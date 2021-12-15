package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 半个椰子类
 * @see CommonItem
 */
public class HalfCoconut extends CommonItem {
    public HalfCoconut(){
        this.setPriority(1);
        this.setName("halfCoconut");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/halfCoconut.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值1枚金币,被消除后给予4金币");
    }

    public int calculateMoney(ItemCategory category){
        return 1;
    }
}
