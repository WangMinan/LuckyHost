package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 牛奶类，价值1枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class Milk extends CommonItem {
    public Milk(){
        this.setPriority(1);
        this.setName("milk");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/milk.png")));
        this.setDescription("价值1枚金币");
    }

    public int calculateMoney(ItemCategory category){
        return 1;
    }
}
