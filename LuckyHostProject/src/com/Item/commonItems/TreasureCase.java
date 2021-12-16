package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 硬币类，价值1枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class TreasureCase extends CommonItem {
    public TreasureCase(){
        this.setPriority(1);
        this.setName("treasureCase");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/treasureCase.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值1枚金币,可以被钥匙消除");
    }

    public int calculateMoney(ItemCategory category){
        return 1;
    }

    @Override
    public TreasureCase createNewCommonItem(){
        return new TreasureCase();
    }
}
