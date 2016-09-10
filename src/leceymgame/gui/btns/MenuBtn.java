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
import leceymgame.services.GameStats;
import static leceymgame.services.GameStats.GAME_STATE;
import leceymgame.services.Renderer;

/**
 *
 * @author Roman
 */
public class MenuBtn extends Btn {//кнопка меню, внизу справа

    public MenuBtn(int posX, int posY, int width, int height, Image img) {
        super(posX, posY, width, height, img);
    }

    @Override
    public void click() {
        super.click();
        if(GAME_STATE == 1){
            GAME_STATE = 2;
        }else{
            GAME_STATE = 1;
        }
    }
    
    
}
