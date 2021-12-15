package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 灰胡椒类
 * @see SpecialItem
 */
public class GreyPepper extends SpecialItem {

    public GreyPepper() {
        this.setName("greyPepper");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/greyPepper.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("如果面板上有三个以上的相同，给与每次旋转三个金币");
    }

    @Override
    public int calculateMoney(ItemCategory category){

        for(int i=0; i<19; i++){
            int cnt = 0;
            for(int j = i+1; j<20; j++){
                if(category.getItemCategory().elementAt(i).getName().equals(
                        category.getItemCategory().elementAt(j).getName()
                )){
                    cnt++;
                }
            }
            if(cnt >= 3){
                return 3;
            }
        }

        return 0;
    }
}