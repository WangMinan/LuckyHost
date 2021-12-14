package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;
import javax.swing.*;
import java.util.Random;

/**
 * @author Wangminan
 * @description 开锁器类
 * @see SpecialItem
 */
public class MonkeyOlivander extends SpecialItem {

    public MonkeyOlivander() {
        this.setName("monkeyOlivander");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/monkeyOlivander.png")));
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
        return cnt*3;
    }
}
