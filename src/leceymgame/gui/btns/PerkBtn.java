/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.gui.btns;

import java.awt.Image;
import leceymgame.services.Btn;
import static leceymgame.services.GameStats.GAME_STATE;

/**
 *
 * @author Roman
 */
public class PerkBtn extends Btn{
    
    public PerkBtn(int posX, int posY, int width, int height, Image img) {
        super(posX, posY, width, height, img);
    }

    @Override
    public void click() {
        super.click();
        if(GAME_STATE == 3){
            GAME_STATE = 2;
        }else{
            GAME_STATE = 3;
        }
    }
    
    
    
}
