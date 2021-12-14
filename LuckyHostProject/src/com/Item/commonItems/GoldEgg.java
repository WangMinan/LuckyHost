package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class GoldEgg extends CommonItem {
    public GoldEgg(){
        this.setPriority(1);
        this.setName("goldEgg");
        this.setIcon(new JButton(new ImageIcon("images/goldEgg.png")));
        this.setDescription("价值4枚金币");
    }

    public int calculateMoney(ItemCategory category){
        return 4;
    }
}
