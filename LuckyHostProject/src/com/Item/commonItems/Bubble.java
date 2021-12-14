package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.Item;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Vector;
/**
 * @author  WuSigan
 * @description 对于bubble物品的详细设计
 * @see CommonItem
 */
public class Bubble extends CommonItem {

    int age=4;

    public Bubble(){
        this.setPriority(1);
        this.setName("bubble");
        this.setIcon(new JButton(new ImageIcon("images/bubble.png")));
        this.setDescription("价值2枚金币");
    }

    public int calculateMoney(ItemCategory category){
        int price =2;
        age--;
        return price;
    }
}
