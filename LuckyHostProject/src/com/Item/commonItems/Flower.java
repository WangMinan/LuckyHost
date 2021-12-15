package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class Flower extends CommonItem {
    public Flower(){
        this.setPriority(1);
        this.setName("flower");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/flower.png")));
        this.setDescription("价值1枚金币");
    }

    public int calculateMoney(ItemCategory category){
        return 1;
    }
}
