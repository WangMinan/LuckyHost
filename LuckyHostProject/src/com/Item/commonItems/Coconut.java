package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author
 * @description
 * @see
 */
public class Coconut extends CommonItem {

    public Coconut(){
        this.setPriority(1);
        this.setName("coconut");
        this.setIcon(new JButton(new ImageIcon("images/coconut")));
        this.setDescription("价值4金币");
    }

    @Override
    public int calculateMoney(ItemCategory category) {
        return 4;
    }
}
