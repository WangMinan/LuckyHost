package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Random;

/**
 * @author Wangminan
 * @description 奶牛类
 * @see CommonItem
 */
public class Cow extends CommonItem {

    public Cow(){
        this.setName("cow");
        this.setIcon(new JButton(new ImageIcon("images/commonItem/cow")));
        this.setDescription("价值3金币,如果老虎机面板上有空格,有15%概率增加牛奶");
        this.setPriority(4);
    }

    @Override
    public int calculateMoney(ItemCategory category) {
        int cntEmpty = -1;
        int[] pos = new int[20];
        for(int i = 0; i<20; i++){
            if (category.getItemCategory().elementAt(i).getName().equals("empty")){
                cntEmpty ++;
                pos[cntEmpty] = i;
            }
        }
        if(cntEmpty!=-1){
            //概率判定
            Random rand =new Random();
            int random = rand.nextInt(100);
            if(random < 15){
                int rnd = rand.nextInt(cntEmpty);
                category.getItemCategory().setElementAt(new Milk(),pos[rnd]);
            }

        }
        return 3;
    }
}