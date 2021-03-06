/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.gui.btns;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import leceymgame.services.Btn;
import leceymgame.main.Game;
import static leceymgame.services.Children.que;
import leceymgame.services.GameStats;
import static leceymgame.services.GameStats.CLICK_UP;
import static leceymgame.services.GameStats.TIME_UP;
import leceymgame.services.Renderer;

/**
 *
 * @author Roman
 */
public class ClickBtn extends Btn{

    public ClickBtn(int posX, int posY, int width, int height, Image img) {
        super(posX, posY, width, height, img);
    }

    @Override
    public void click() {
        super.click(); 
        if(GameStats.CAPACITY >= GameStats.CLICK_UP + GameStats.QUANTITY){
            GameStats.QUANTITY += GameStats.CLICK_UP; 
            if(que + GameStats.CLICK_UP < 100){
                    que += CLICK_UP;
            }
        }else{
            GameStats.QUANTITY = GameStats.CAPACITY;
        }
    }                                        
}
