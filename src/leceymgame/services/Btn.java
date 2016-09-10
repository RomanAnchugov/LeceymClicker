/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.services;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Roman
 */
public class Btn extends MouseAdapter implements Renderer{//класс для всех кнопок
private Image img;
    private int posX, posY;
    private int width, height;

    public Btn(int posX, int posY,int width, int height, Image img){
        this.img = img;
        this.width = width;
        this.height = height;
        this.posX = posX ;
        this.posY = posY;        
    }
    public Btn(Image img){
        this.img = img;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
        this.posX = posX ;
        this.posY = posY;        
    }
    
    public Btn(int posX, int posY,int width, int height){
        this.img = null;
        this.width = width;
        this.height = height;
        this.posX = posX ;
        this.posY = posY;        
    }

    @Override
    public void draw(Graphics g) {
        if(img != null){
            g.drawImage(img, posX, posY, width, height,null);
        }else{
            g.drawRect(posX, posY, width, height);
        }
    }

    @Override
    public void update() {
        
    }        

    @Override
    public void mouseClicked(MouseEvent e) {
        if(checkClick(e)){        
            click();
        }
    }
    
     
    public void click() {
        System.err.println("Click");
    }
    
    public boolean checkClick(MouseEvent e){
        if(e.getX() >= posX && e.getX() <= posX + width 
           &&  e.getY() >= posY && e.getY() < posY + height){
           return true; 
        }
        return false;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }              
}
