package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 黑胡椒类
 * @see SpecialItem
 */
public class LockRemover extends SpecialItem {

    public LockRemover() {
        this.setName("blackPepper");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/lockRemover.png")));
        this.setDescription("面板上的藏宝箱有30%的几率消除");
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
}
