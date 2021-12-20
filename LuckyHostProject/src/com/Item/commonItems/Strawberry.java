package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
/**
 * 草莓类，价值3枚金币，如果周围有其他草莓，每多1个草莓增加3枚金币
 * @author  WuSigan
 * @see CommonItem
 */
public class Strawberry extends CommonItem {
    public Strawberry(){
        this.setPriority(2);
        this.setName("strawberry");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/strawberry.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值3枚金币，如果周围有其他草莓，每多1个草莓增加3枚金币");
    }

    public int calculateMoney(ItemCategory category){
        //基础金币数3
        int price =3;
        for(int i=0;i<20 ;i++){
            if(category.getItemCategory().elementAt(i).getName().equals("strawberry") &&
                    this.isNear(i,this.getPosition())&&
                    !category.getItemCategory().elementAt(i).getPosition().equals(this.getPosition())){
                price+=3;
            }
        }
        return price;
    }

    @Override
    public Strawberry createNewItem(){
        return new Strawberry();
    }
}
