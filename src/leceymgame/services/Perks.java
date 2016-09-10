/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.services;

import java.awt.Image;
import static leceymgame.services.GameStats.*;

/**
 *
 * @author Roman
 */
public class Perks extends Btn implements Renderer{
    
    private int type;//1 - click, 2 - time, 3 - вместимость    
    private int cost;//стартовая стоимость
    private int upperCount;//увелечение параметра
    private double upperCost;//увелечени стоимости        
    
    public Perks(Image img, int type, int startCost, int upperCount, double upperCost) {
        super(img);
        this.type = type;
        this.cost = startCost;
        this.upperCount = upperCount;
        this.upperCost = upperCost;
    }

    @Override
    public void click() {
        super.click();        
        if(QUANTITY >= this.cost){
            QUANTITY -= this.cost;
            if(type == 1){
                CLICK_UP += upperCount;                
            }
            if(type == 2){
                TIME_UP += upperCount;
            }
            if(type == 3){
                CAPACITY += upperCount;                
            }            
            cost *= upperCost;            
            System.out.println(cost);
        }        
    }
    
    
    
}
