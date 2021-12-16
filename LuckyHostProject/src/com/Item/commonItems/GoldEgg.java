package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 金蛋类，价值4枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class GoldEgg extends CommonItem {
    public GoldEgg(){
        this.setPriority(1);
        this.setName("goldEgg");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/goldEgg.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值4枚金币");
    }

    public int calculateMoney(ItemCategory category){
        return 4;
    }

    @Override
    public GoldEgg createNewCommonItem(){
        return new GoldEgg();
    }
}
