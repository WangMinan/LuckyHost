package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class Coin extends CommonItem {
    public Coin(){
        this.setPriority(1);
        this.setName("coin");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/coin.png")));
        this.setDescription("价值1枚金币");
    }

    public int calculateMoney(ItemCategory category){
        return 1;
    }
}
