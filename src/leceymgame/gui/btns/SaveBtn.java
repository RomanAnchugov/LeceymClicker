/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.gui.btns;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import leceymgame.services.Btn;
import leceymgame.services.GameStats;
import static leceymgame.services.GameStats.*;
import leceymgame.services.Renderer;

/**
 *
 * @author Roman
 */
public class SaveBtn extends Btn implements Renderer{
    
    public SaveBtn(Image img) {
        super(img);
    }

    @Override
    public void click() {
        super.click();
        save();
    }
    
    public void save(){        
        try {            
            BufferedWriter wr = new BufferedWriter(new FileWriter("assets/save.txt"));
            //clikcup, timeup, capacity(вместимость), quantity(количество)
            wr.write(CLICK_UP + "\n" + TIME_UP + "\n" + CAPACITY + "\n" + QUANTITY);
            wr.close();
        } catch (IOException ex) {
            Logger.getLogger(SaveBtn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
