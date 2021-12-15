package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;
import javax.swing.*;

/**
 * @author Wangminan
 * @description 雨云类
 * @see SpecialItem
 */
public class RainCloud extends SpecialItem {

    public RainCloud() {
        this.setName("rainCloud");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/rainCloud.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("雨每次+1金币");
    }

    @Override
    public int calculateMoney(ItemCategory category){
        int cnt = 0;
        for(int i=0; i<20; i++){
            if(category.getItemCategory().elementAt(i).getName().equals("rain")){
                cnt++;
            }
        }
        return cnt;
    }
}