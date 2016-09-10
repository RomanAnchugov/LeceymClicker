/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leceymgame.main;

import leceymgame.gui.btns.ClickBtn;
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
import leceymgame.gui.BtnContainer;
import leceymgame.gui.Capacity;
import leceymgame.gui.btns.MenuBtn;
import leceymgame.gui.btns.PerkBtn;
import leceymgame.gui.Quantity;
import leceymgame.services.Btn;
import static leceymgame.services.GameStats.GAME_STATE;

/**
 *
 * @author Roman
 */
public class Game extends Canvas implements Runnable{
    
    private boolean running = false; 
    
    public static int WIDTH = 700;
    public static int HEIGHT = 500;
    public static String TITLE = "It WORKS!";         
    
    //gui
    private ClickBtn clickBtn;//главна клик кнопка
    private Capacity capacity;//вместимость
    private Quantity quantity;//текущее кол-во учеников
    private MenuBtn menuBtn;//кнопка для меню
    private PerkBtn perkBtn;//кнопка для перков
    private BtnContainer menuContainer;//контейнер для кнопок меню
    private BtnContainer perkContainerB1;//контейнер для кнопок перков, блок 1
    private BtnContainer perkContainerB2;//контейнер для кнопок перков, блок 1
       
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
        capacity = new Capacity(5, 15);//posX, posY
        quantity = new Quantity(32, 64);//width, height   
        
        clickBtn = new ClickBtn(Game.WIDTH / 2 - 100, Game.HEIGHT - 105, 200, 100, new ImageIcon("assets/clickBtn.png").getImage());//posX, posY, width, height, img             
        menuBtn = new MenuBtn(Game.WIDTH - 69, Game.HEIGHT - 69, 64, 64, new ImageIcon("assets/menuBtn.png").getImage());//posX, posY, width, height, img
        perkBtn = new PerkBtn(5, Game.HEIGHT - 69, 64, 64, new ImageIcon("assets/perkBtn.png").getImage());//posX, posY, width, height, img        
        
        Btn test1 = new Btn(5, Game.HEIGHT - 64, 64, 64);//помощ
        Btn test2 = new Btn(5, Game.HEIGHT - 64, 64, 64);//сохранить
        Btn test3 = new Btn(5, Game.HEIGHT - 64, 64, 64);//выход
        
        
        Btn[] btns = {test1, test2, test3};        
        menuContainer = new BtnContainer(btns, WIDTH / 2 - 50, 100, null);//Btn btns[], posX, posY, width, bgimage        
        
        Btn test4 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк1б1
        Btn test5 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк2б1
        Btn test6 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк3б1
        Btn test7 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк4б1        
        Btn[] btnsPerkB1 = {test4, test5, test6, test7};
        
        Btn test8 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк1б2
        Btn test9 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк2б2
        Btn test10 = new Btn(5, Game.HEIGHT - 64, 64, 64);//перк3б2
        Btn[] btnsPerkB2 = {test8, test9, test10};
        
        perkContainerB1 = new BtnContainer(btnsPerkB1, WIDTH / 2 - 84, 68, null);
        perkContainerB2 = new BtnContainer(btnsPerkB2, WIDTH / 2 - 10, 110, null);
        
        addMouseListener(clickBtn);
        addMouseListener(menuBtn);
        addMouseListener(perkBtn);
        for(Btn btn : btns){
            addMouseListener(btn);
        }
        for(Btn btn : btnsPerkB1){
            addMouseListener(btn);
        }
        for(Btn btn : btnsPerkB2){
            addMouseListener(btn);
        }
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

        if(GAME_STATE == 1){
            menuContainer.draw(g);
        }
        if(GAME_STATE == 3){
            perkContainerB1.draw(g);
            perkContainerB2.draw(g);
        }
        
        //
	g.dispose();
	bs.show(); //показать 
   }
    
    public void update(){                
        capacity.update();
        quantity.update();
        
        clickBtn.update();
        menuBtn.update();
        perkBtn.update();  
        
        menuContainer.update();
        perkContainerB1.update();
        perkContainerB2.update();
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
