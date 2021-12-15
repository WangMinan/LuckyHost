package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 鱼类，价值1枚金币，消除周围的泡泡，每消除一个泡泡，获得4枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class Fish extends CommonItem {
    public Fish(){
        this.setPriority(3);
        this.setName("fish");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/fish.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值1枚金币，消除周围的泡泡，每消除一个泡泡，获得4枚金币");
    }

    public int calculateMoney(ItemCategory category){
        int price=1;
        for(int i = 0;i<20;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("bubble") && this.isNear(i,this.getPosition())){
                price+=4;
                category.getItemCategory().set(i,new Empty());
            }
        }

        return price;
    }
}
