package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.Item;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Vector;
/**
 * 泡泡类，价值2
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class Bubble extends CommonItem {

    public Bubble(){
        this.setPriority(1);
        this.setName("bubble");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/bubble.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值2枚金币");
    }

    public int calculateMoney(ItemCategory category){
        int price =2;
        return price;
    }

    @Override
    public Bubble createNewItem(){
        return new Bubble();
    }
}
