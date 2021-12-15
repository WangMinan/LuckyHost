package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 钥匙类
 * @see CommonItem
 */
public class Key extends CommonItem {
    public Key(){
        this.setPriority(3);
        this.setName("key");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/key.png")));
        this.setDescription("价值1枚金币,可以消除相邻的财宝箱");
    }

    public int calculateMoney(ItemCategory category){
        int price = 1;
        for(int i=0;i<20;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("treasureCase") &&
                this.isNear(i,this.getPosition())){
                price = price + 20;
                category.getItemCategory().set(i,new Empty());
                break;
            }
        }
        return price;
    }
}