/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.gui;

import java.awt.Graphics;
import java.awt.Image;
import mathgame.Btn;
import mathgame.Game;
import mathgame.Renderer;

/**
 *
 * @author Roman
 */
public class BtnContainer implements Renderer{

    private Btn[] btns;    
    private int width;    
    private int height;
    private Image bgImage;
    private int posX, posY;
    private int margin;
    
    public BtnContainer(Btn[] btns,int width, Image bgImage){
        this.btns = btns;              
        this.bgImage = bgImage;        
        margin = 10;
        posX = Game.WIDTH / 2;
        posY = 100;
        for(int i = 0; i < btns.length; i++){            
            height += btns[i].getHeight();
            height += margin;
            if(btns[i].getWidth() > this.width){
                this.width = btns[i].getWidth() + margin;
            }
        }
        height += margin;
    }
    
    @Override
    public void draw(Graphics g) {                
        int currentY, lastY = posY;
        g.drawRect(posX, posY, width, height);
        for(int i = 0; i < btns.length; i++){
            btns[i].setPosX(posX + width / 2 - btns[i].getWidth() / 2);            
            currentY = lastY + margin;
            btns[i].setPosY(currentY);
            lastY = currentY + btns[i].getHeight();
        }
    }

    @Override
    public void update() {        
    }
    
}
