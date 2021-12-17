package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;
import com.Item.commonItems.Sun;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 黑胡椒类
 * @see SpecialItem
 */
public class BlackPepper extends SpecialItem {

    public BlackPepper() {
        this.setName("blackPepper");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/BlackPepper.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("如果面板上有三个以上的空格，给与每次旋转三个金币");
    }

    @Override
    public int calculateMoney(ItemCategory category){
        int cnt = 0;
        for(int i=0; i<20; i++){
            if(category.getItemCategory().elementAt(i).getName().equals("empty")){
                cnt++;
            }
        }
        if(cnt>=3){
            return 3;
        } else {
            return 0;
        }
    }

    @Override
    public BlackPepper createNewItem(){
        return new BlackPepper();
    }
}
