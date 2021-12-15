package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;
import javax.swing.*;

/**
 * @author Wangminan
 * @description 猴子奥利凡德类
 * @see SpecialItem
 */
public class MonkeyOlivander extends SpecialItem {

    public MonkeyOlivander() {
        this.setName("monkeyOlivander");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/monkeyOlivander.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("猴子每次旋转+2金币");
    }

    @Override
    public int calculateMoney(ItemCategory category){
        int cnt = 0;
        for(int i=0; i<20; i++){
            if(category.getItemCategory().elementAt(i).getName().equals("monkey")){
                cnt++;
            }
        }
        return cnt*2;
    }
}
