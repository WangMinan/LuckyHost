package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 珍珠类
 * @see CommonItem
 */
public class Pearl extends CommonItem {
    public Pearl(){
        this.setPriority(1);
        this.setName("pearl");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/pearl.png")));
        this.setDescription("价值1枚金币");
    }

    public int calculateMoney(ItemCategory category){
        return 1;
    }
}
