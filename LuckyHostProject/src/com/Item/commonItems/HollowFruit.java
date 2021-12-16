package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 虚空水果类，价值1枚金币，如果周围有空，每个空增加1枚金币
 * @author  WuSigan
 * @see CommonItem
 *
 */
public class HollowFruit extends CommonItem {

    public HollowFruit(){
        this.setPriority(1);
        this.setName("hollowFruit");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/hollowFruit.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值1枚金币，如果周围有空，每个空增加1枚金币,否则消除自身，给予8金币");
    }

    public int calculateMoney(ItemCategory category){
        //基础金币数1
        int price =1;
        for(int i=0;i<20 ;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("empty") && this.isNear(i,this.getPosition())){
                price++;
            }
        }
        if(price == 1){
            category.getItemCategory().setElementAt(new Empty(),
                    this.getPosition().getRow()*5+this.getPosition().getColum());
            return 8;
        } else {
            return price;
        }
    }

    @Override
    public HollowFruit createNewCommonItem(){
        return new HollowFruit();
    }
}
