package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.Item;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Vector;

/**
 * 蜜蜂类，价值1枚金币，如果周围有花，每朵花增加2枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class Bee extends CommonItem {

    public Bee(){
        this.setPriority(2);
        this.setName("bee");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/bee.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值1枚金币，如果周围有花，每朵花增加1枚金币");
    }

    public int calculateMoney(ItemCategory category){
        //基础金币数1
        int price =1;
        for(int i=0;i<20 ;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("flower") && this.isNear(i,this.getPosition())){
                price+=2;
            }
        }
        return price;
    }

    @Override
    public Bee createNewItem(){
        return new Bee();
    }
}
