/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import mathgame.Btn;
import mathgame.Game;
import mathgame.GameStats;
import mathgame.Renderer;

/**
 *
 * @author Roman
 */
public class ClickBtn extends Btn{

    public ClickBtn(int posX, int posY, int width, int height, Image img) {
        super(posX, posY, width, height, img);
    }
        

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(checkClick(e)){
            System.out.println("mathgame.gui.ClickBtn.mouseClicked()");
        }
    }
    
    
    
}
