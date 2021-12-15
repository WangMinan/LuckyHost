package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 猴子类
 * @see CommonItem
 */
public class Monkey extends CommonItem {
    public Monkey(){
        this.setPriority(3);
        this.setName("monkey");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/monkey.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值2枚金币,如果老虎机面板上有空格,可以消除相邻的椰子（生成半个椰子）和半个椰子");
    }

    public int calculateMoney(ItemCategory category){

        int price = 2;
        for(int i=0;i<20;i++){
            if(this.isNear(i,getPosition())){
                if(category.getItemCategory().elementAt(i).getName().equals("coconut")){
                    price = price + 10;
                    category.getItemCategory().setElementAt(new HalfCoconut(),i);
                } else if(category.getItemCategory().elementAt(i).getName().equals("halfCoconut")){
                    price = price + 4;
                    category.getItemCategory().setElementAt(new Empty(),i);
                }
            }
        }

        return price;
    }
}
