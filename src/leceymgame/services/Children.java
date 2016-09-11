/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.services;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import static leceymgame.main.Game.HEIGHT;
import static leceymgame.main.Game.WIDTH;

/**
 *
 * @author Roman
 */
public class Children{

    public static int que = 0;
    
    private Image img;
    private int direction;//0 - top, 1- right, 2 - bot, 3 - left
    private int speedY, speedX, speed;
    private int posX, posY;
    private int width, height;
    private int dist;
    
    public Children(){
        img = new ImageIcon("assets/child.png").getImage();
        direction = 1;
        speed = 2;
        width = 32;
        height = 64;
        dist = 0;
        switch(direction){
       
            case 1:
                speedY = 0;
                speedX = -speed;
                posX = WIDTH + 30;
                posY = HEIGHT / 2;
                break;
            
        }     
    }     
    
    public void draw(Graphics g){
        g.drawImage(img, posX, posY, width, height, null);
    }
    
    public void update(){
        posX += speedX;
        posY += speedY;
        dist += Math.abs(speedX) + Math.abs(speedY);
        if(dist >= 150){
            replace();
        }
    } 
    
    public void replace(){
        que--;
        dist = 0;
        direction = 1;
        speed = new Random().nextInt(3) + 1;
        switch(direction){
            
            case 1:
                speedY = 0;
                speedX = -speed;
                posX = WIDTH + new Random().nextInt(100);
                posY = HEIGHT / 2;
                break;
            
        }     
    }
}
