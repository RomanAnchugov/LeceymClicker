/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.gui;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.Graphics;
import java.awt.Image;
import leceymgame.services.Btn;
import leceymgame.main.Game;
import leceymgame.services.Renderer;

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
    
    public BtnContainer(Btn[] btns,int posX, int posY, Image bgImage){
        this.btns = btns;              
        this.bgImage = bgImage;        
        margin = 10;
        this.posX = posX;
        this.posY = posY;
        int currentY, lastY = posY;
        for (Btn btn : btns) {            
            height += btn.getHeight();
            height += margin;
            if (btn.getWidth() > this.width) {
                this.width = btn.getWidth() + margin;
            }            
        }
        for (Btn btn : btns) {
            btn.setPosX(this.posX + this.width / 2 - btn.getWidth() / 2 + margin);            
            currentY = lastY + margin;
            btn.setPosY(currentY);
            lastY = currentY + btn.getHeight();
        }        
        height += margin;
    }
    
    @Override
    public void draw(Graphics g) {                        
        if(bgImage != null){
            g.drawImage(bgImage, posX, posY, width, height, null);
        }else{
            g.drawRect(posX + margin, posY, width, height);  
        }
        for(Btn btn : btns){
            btn.draw(g);
        }
    }

    @Override
    public void update() {        
        for (Btn btn : btns){
            btn.update();
        }
    }
    
}
