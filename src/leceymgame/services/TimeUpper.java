/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.services;

import java.util.logging.Level;
import java.util.logging.Logger;
import leceymgame.main.Game;
import static leceymgame.services.Children.que;
import static leceymgame.services.GameStats.*;

/**
 *
 * @author Roman
 */
public class TimeUpper implements Runnable{

    Game game;
    
    public TimeUpper(Game game){
        this.game = game;
        start();        
    }
    
    private void start(){
        new Thread(this).start();        
    }
    
    @Override
    public void run() {         
        while(game.running){
            try {               
                Thread.sleep(1000);
                QUANTITY += TIME_UP;
                if(que + TIME_UP < 100){
                    que += TIME_UP;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeUpper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
