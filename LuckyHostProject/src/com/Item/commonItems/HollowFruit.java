package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

public class HollowFruit extends CommonItem {

    public HollowFruit(){
        this.setPriority(1);
        this.setName("hollowfruit");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/hollowFruit.png")));
        this.setDescription("价值1枚金币，如果周围有空，每个空增加1枚金币");
    }

    public int calculateMoney(ItemCategory category){
        //基础金币数1
        int price =1;
        for(int i=0;i<20 ;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("empty") && this.isNear(i,this.getPosition())){
                price++;
            }
        }
        return price;
    }
}
