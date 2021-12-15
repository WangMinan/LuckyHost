package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 藏宝箱类
 * @see CommonItem
 */
public class Sun extends CommonItem {

    public Sun(){
        this.setPriority(1);
        this.setName("sun");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/sun.png")));
        this.setDescription("价值4枚金币，如果周围有花，每朵花增加4枚金币");
    }

    public int calculateMoney(ItemCategory category){

        //基础金币数1
        int price =1;
        for(int i=0;i<20 ;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("flower") && this.isNear(i,this.getPosition())){
                price = price + 4;
            }
        }
        return price;
    }
}
