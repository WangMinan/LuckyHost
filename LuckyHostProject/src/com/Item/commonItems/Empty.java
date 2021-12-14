package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class Empty extends CommonItem {
    public Empty(){
        this.setPriority(3);
        this.setName("empty");
        this.setIcon(new JButton(new ImageIcon("images/empty.png")));
        this.setDescription("");
    }

    public int calculateMoney(ItemCategory category){
        int price =0;
        return price;
    }
}
