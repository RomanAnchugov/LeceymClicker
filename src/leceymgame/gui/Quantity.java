/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import leceymgame.main.Game;
import leceymgame.services.GameStats;
import leceymgame.services.Renderer;

/**
 *
 * @author Roman
 */
public class Quantity implements Renderer{//текущее количество учеников,центр сверху

    private int width,height;
    private Image studentImg;
    
    public Quantity(int width, int height){
        this.width = width;
        this.height = height;
        studentImg = new ImageIcon("assets/student.png").getImage();        
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(studentImg, Game.WIDTH / 2 - width / 2 - 30, 0, width, height, null);
        g.setColor(Color.BLACK);
        g.drawString(GameStats.QUANTITY + "", Game.WIDTH / 2, height / 2);
    }

    @Override
    public void update() {
        
    }
    
}
