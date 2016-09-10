/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import leceymgame.services.GameStats;
import leceymgame.services.Renderer;

/**
 *
 * @author Roman
 */
public class Capacity implements Renderer{//вместимость - верхний левый угол
    
    private int posX, posY;
    
    public Capacity(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
        
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));        
        g.drawString("Вместимость: " + GameStats.CAPACITY, posX, posY);
    }

    @Override
    public void update() {
   
    }
    
}
