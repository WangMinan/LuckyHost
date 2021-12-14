package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.Item;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Vector;

/**
 * @author  WuSigan
 * @description 对于bee物品的详细设计
 * @see CommonItem
 */
public class Bee extends CommonItem {

    public Bee(){
        this.setPriority(1);
        this.setName("bee");
        this.setIcon(new JButton(new ImageIcon("images/bee.png")));
        this.setDescription("价值1枚金币，如果周围有花，则价值2枚金币");
    }

    public int calculateMoney(ItemCategory category){
        //基础金币数1
        int price =1;
        for(int i=0;i<20 ;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("Flower") && this.isNear(i,this.getPosition())){
                price++;
            }
        }
        return price;
    }
}
