package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class Cat extends CommonItem {
    public Cat(){
        this.setPriority(2);
        this.setName("cat");
        this.setIcon(new JButton(new ImageIcon("images/cat.png")));
        this.setDescription("价值1枚金币，消除周围的牛奶，每消除一个牛奶，获得9枚金币");
    }

    public int calculateMoney(ItemCategory category){
        int price=1;
        for(int i = 0;i<20;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("milk") && this.isNear(i,this.getPosition())){
                price+=9;
                category.getItemCategory().set(i,new Empty());
            }
        }

        return price;
    }
}
