/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.services;

import java.awt.Color;
import java.awt.Graphics;
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
    
    private int marginTop = 20;
    private int marginLeft = 10;
    
    public Perks(Image img, int width, int height,int type, int startCost, int upperCount, double upperCost) {
        super(width, height);
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
        }        
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(38, 50, 56));
        g.fillRect(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
        g.setColor(new Color(251, 192, 45));        
        g.drawString("стоит : " + cost, this.getPosX() + marginLeft, this.getPosY() + marginTop);
        if(type == 1){            
            g.setColor(new Color(67, 160, 71));        
            g.drawString("клик + " + upperCount, this.getPosX() + marginLeft, this.getPosY() + marginTop * 2 + 3);
        }
        if(type == 2){
            g.setColor(new Color(3, 155, 229));        
            g.drawString("время + " + upperCount, this.getPosX() + marginLeft, this.getPosY() + marginTop * 2 + 3);
        }
        if(type == 3){
            g.setColor(new Color(233, 30, 99));        
            g.drawString("вместимость + " + upperCount, this.getPosX() + marginLeft, this.getPosY() + marginTop * 2 + 3);
        }   
    }
    
    
    
}
