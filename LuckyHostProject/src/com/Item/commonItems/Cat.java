package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
import java.awt.*;

/**
 * @author  WuSigan
 * @version 0.0.1
 * @see CommonItem
 */
public class Cat extends CommonItem {
    public Cat(){
        this.setPriority(3);
        this.setName("cat");
        JButton button = new JButton(new ImageIcon("images/commonItems/cat.png")){
            public JToolTip createToolTip() {
                JToolTip tip = super.createToolTip();
                tip.setTipText("价值1枚金币，消除周围的牛奶，每消除一个牛奶，获得9枚金币");
                // MultiLineToolTip tip=new MultiLineToolTip();
                tip.setBackground(new Color(0xfffacd));
                tip.setForeground(Color.red);
                return tip;
            }
        };
        this.setIcon(button);
        this.getIcon().setFocusPainted(false);
        this.setDescription("价值1枚金币，消除周围的牛奶，每消除一个牛奶，获得9枚金币");
    }

    public int calculateMoney(ItemCategory category){
        int price=1;
        for(int i = 0;i < 20; i++){
            if(category.getItemCategory().elementAt(i).getName().equals("milk") && this.isNear(i,this.getPosition())){
                price+=9;
                category.getItemCategory().set(i,new Empty());
            }
        }

        return price;
    }

    @Override
    public Cat createNewCommonItem(){
        return new Cat();
    }


}
