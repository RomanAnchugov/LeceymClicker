/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame;

import mathgame.gui.Sprite;
import mathgame.gui.ClickBtn;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mathgame.gui.BtnContainer;
import mathgame.gui.Capacity;
import mathgame.gui.MenuBtn;
import mathgame.gui.PerkBtn;
import mathgame.gui.Quantity;

/**
 *
 * @author Roman
 */
public class Game extends Canvas implements Runnable{
    
    private boolean running = false; 
    
    public static int WIDTH = 700;
    public static int HEIGHT = 500;
    public static String TITLE = "It WORKS!"; 
    
    private Sprite spr;
    
    //gui
    private ClickBtn clickBtn;//главна клик кнопка
    private Capacity capacity;//вместимость
    private Quantity quantity;//текущее кол-во учеников
    private MenuBtn menuBtn;//кнопка для меню
    private PerkBtn perkBtn;//кнопка для перков
    private BtnContainer btnContainer;
       
    public void start(){        
        running = true;
        new Thread(this).start();        
    }

    @Override
    public void run() {
        init();
        while(running){
            update();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void init(){        
        spr = new Sprite(new ImageIcon("assets/1.png").getImage());        
        clickBtn = new ClickBtn(Game.WIDTH / 2 - 100, Game.HEIGHT - 100, 200, 100, new ImageIcon("assets/clickBtn.png").getImage());//posX, posY, width, height, img
        capacity = new Capacity(5, 15);//posX, posY
        quantity = new Quantity(32, 64);//width, height        
        menuBtn = new MenuBtn(Game.WIDTH - 69, Game.HEIGHT - 64, 64, 64, new ImageIcon("assets/menuBtn.png").getImage());//posX, posY, width, height, img
        perkBtn = new PerkBtn(5, Game.HEIGHT - 64, 64, 64, new ImageIcon("assets/perkBtn.png").getImage());//posX, posY, width, height, img        
        
        
        Btn[] btns = {menuBtn, clickBtn, perkBtn};        
        btnContainer = new BtnContainer(btns, 100, new ImageIcon("assets/perkBtn.png").getImage());//Btn btns[], width, bgimage
        
        addMouseListener(clickBtn);
        addMouseListener(menuBtn);
        addMouseListener(perkBtn);
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy(); 
	if (bs == null) {
            createBufferStrategy(2); //создаем BufferStrategy для нашего холста
            requestFocus();
            return;
	}
		
	Graphics g = bs.getDrawGraphics(); //получаем Graphics из созданной нами BufferStrategy
	g.setColor(Color.white); //выбрать цвет
	g.fillRect(0, 0, WIDTH, HEIGHT); //заполнить прямоугольник 
        
        //после этого происходит рендер всего        
        clickBtn.draw(g);
        capacity.draw(g);
        quantity.draw(g);
        menuBtn.draw(g);
        perkBtn.draw(g);
        
        btnContainer.draw(g);
        
        //
	g.dispose();
	bs.show(); //показать 
   }
    
    public void update(){        
        clickBtn.update();
        capacity.update();
        quantity.update();
        menuBtn.update();
        perkBtn.update();       
    }
    
    public static void main(String[] args) { 
        Game game = new Game();
	game.setPreferredSize(new Dimension(WIDTH - 10, HEIGHT - 10));
        //game.setSize(WIDTH - 10, HEIGHT - 10);

	JFrame frame = new JFrame(TITLE);        
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //выход из приложения по нажатию клавиши ESC
	frame.setLayout(new BorderLayout());
	frame.add(game, BorderLayout.CENTER); //добавляем холст на наш фрейм
	frame.pack();
	frame.setResizable(false);
	frame.setVisible(true);
        frame.setLocationRelativeTo(null);

	game.start();
    }
    
    
}
