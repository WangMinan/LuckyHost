package com.Item.specialItems;

import com.Item.ItemCategory;
import com.Item.SpecialItem;
import com.Item.commonItems.Empty;

import javax.swing.*;
import java.util.Random;

/**
 * @author Wangminan
 * @description 开锁器类
 * @see SpecialItem
 */
public class LockRemover extends SpecialItem {

    public LockRemover() {
        this.setName("lockRemover");
        this.setIcon(new JButton(new ImageIcon("images/specialItems/lockRemover.png")));
        this.getIcon().setFocusPainted(false);
        this.setDescription("面板中的宝箱有30%的几率被消除");
    }

    @Override
    public int calculateMoney(ItemCategory category){
        for(int i=0; i<20; i++){
            if(category.getItemCategory().elementAt(i).getName().equals("treasureCase")){
                Random rand =new Random();
                int random = rand.nextInt(100);
                if(random < 30){
                    Empty empty = new Empty();
                    category.getItemCategory().setElementAt(empty,i);
                    return 50;
                }
            }
        }
        return 0;
    }
}
