package com.Item.commonItems;

import com.Item.CommonItem;
import com.Item.Item;
import com.Item.ItemCategory;

import javax.swing.*;
import java.util.Vector;

/**
 * @author  WuSigan
 * @description 对于bee物品的详细设计
 * @see CommonItem
 */
public class Bee extends CommonItem {

    public Bee(){
        this.setPriority(1);
        this.setName("Bee");
        this.setIcon(new JButton(new ImageIcon("images/bee.png")));
        this.setDescription("价值1枚金币，如果周围有花，则价值2枚金币");
    }

    public int calculateMoney(ItemCategory category){
        int price =1;
        Vector<Item> V = new Vector<>();
        for(int i=0;i<20;i++){

            int row = category.getItemCategory().elementAt(i).getPosition().getRow();
            int colum = category.getItemCategory().elementAt(i).getPosition().getColum();
            if(row==this.getPosition().getRow()-1&&colum==this.getPosition().getColum()-1){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()-1&&colum==this.getPosition().getColum()){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()-1&&colum==this.getPosition().getColum()+1){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()&&colum==this.getPosition().getColum()-1){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()&&colum==this.getPosition().getColum()+1){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()+1&&colum==this.getPosition().getColum()-1){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()+1&&colum==this.getPosition().getColum()){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
            if(row==this.getPosition().getRow()+1&&colum==this.getPosition().getColum()+1){
                V.add(category.getItemCategory().elementAt(i));
                continue;
            }
        }

        for(int i=0;i<8;i++){
            if(V.get(i).getName().equals("flower")){
                price++;
            }
        }
        return price;
    }
}
