package com.Item;

import javax.swing.*;

/**
 * @author Wangminan
 * @description 对特殊Item的扩展
 * @see
 */
public class SpecialItem extends Item{

    public SpecialItem() {
    }

    public SpecialItem(String name, JButton icon, String description) {
        super(name, icon, description);
    }

    @Override
    public int calculateMoney(ItemCategory category){
        return 0;
    }

    @Override
    public SpecialItem createNewItem(){
        return new SpecialItem();
    }
}
