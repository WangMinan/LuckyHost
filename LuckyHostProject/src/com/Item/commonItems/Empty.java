package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class Empty extends CommonItem {
    public Empty(){
        this.setPriority(1);
        this.setName("empty");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/empty.png")));
        this.setDescription("");
    }

    public int calculateMoney(ItemCategory category){
        return 0;
    }
}
