package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Random;

/**
 * @author Wangminan
 * @description 鹅类
 * @see CommonItem
 */
public class Goose extends CommonItem {

    public Goose(){
        this.setName("goose");
        this.setIcon(new JButton(new ImageIcon("images/commonItems/goose.png")));
        this.setDescription("价值3金币,如果老虎机面板上有空格,有1%概率增加金蛋");
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
            if(random == 2){
                if(cntEmpty == 0){
                    category.getItemCategory().setElementAt(new GoldEgg(),pos[0]);
                } else {
                    int rnd = rand.nextInt(cntEmpty+1);
                    category.getItemCategory().setElementAt(new GoldEgg(),pos[rnd]);
                }
            }

        }
        return 3;
    }
}
