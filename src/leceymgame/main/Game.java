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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
import leceymgame.gui.btns.ExitBtn;
import leceymgame.gui.btns.HelpBtn;
import leceymgame.gui.btns.SaveBtn;
import leceymgame.services.Btn;
import leceymgame.services.Children;
import static leceymgame.services.Children.que;
import leceymgame.services.GameStats;
import static leceymgame.services.GameStats.GAME_STATE;
import leceymgame.services.Perks;
import leceymgame.services.TimeUpper;

/**
 *
 * @author Roman
 */
public class Game extends Canvas implements Runnable{
    
    public boolean running = false; 
    
    public static int WIDTH = 700;
    public static int HEIGHT = 500;
    public static String TITLE = "It WORKS!";         
    
    //gui
    private Image leceymImg;//куартинка лицея
    private Image helpImg;//картинка помощи
    private ClickBtn clickBtn;//главна клик кнопка
    private Capacity capacity;//вместимость
    private Quantity quantity;//текущее кол-во учеников
    private MenuBtn menuBtn;//кнопка для меню
    private PerkBtn perkBtn;//кнопка для перков
    private BtnContainer menuContainer;//контейнер для кнопок меню
    private BtnContainer perkContainerB1;//контейнер для кнопок перков, блок 1
    private BtnContainer perkContainerB2;//контейнер для кнопок перков, блок 1
    private ArrayList<Children> childs;
       
    public void start(){        
        running = true;
        readSave();
        new Thread(this).start();
        new TimeUpper(this);
    }

    public void readSave(){
        Scanner sc;
        try {
            sc = new Scanner(new File("assets/save.txt"));
            GameStats.CLICK_UP = Integer.parseInt(sc.nextLine());
            GameStats.TIME_UP = Integer.parseInt(sc.nextLine());
            GameStats.CAPACITY = Integer.parseInt(sc.nextLine());
            GameStats.QUANTITY = Integer.parseInt(sc.nextLine());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        leceymImg = new ImageIcon("assets/leceym.png").getImage();
        helpImg = new ImageIcon("assets/help.png").getImage();
        
        capacity = new Capacity(5, 15);//posX, posY
        quantity = new Quantity(32, 64);//width, height   
        
        clickBtn = new ClickBtn(Game.WIDTH / 2 - 100, Game.HEIGHT - 105, 200, 100, new ImageIcon("assets/clickBtn.png").getImage());//posX, posY, width, height, img             
        menuBtn = new MenuBtn(Game.WIDTH - 69, Game.HEIGHT - 69, 64, 64, new ImageIcon("assets/menuBtn.png").getImage());//posX, posY, width, height, img
        perkBtn = new PerkBtn(5, Game.HEIGHT - 69, 64, 64, new ImageIcon("assets/perkBtn.png").getImage());//posX, posY, width, height, img        
        
        HelpBtn helpBtn = new HelpBtn(new ImageIcon("assets/helpBtn.png").getImage());//помощ
        SaveBtn saveBtn = new SaveBtn(new ImageIcon("assets/saveBtn.png").getImage());//сохранить
        ExitBtn exitBtn = new ExitBtn(new ImageIcon("assets/exitBtn.png").getImage());//выход        
        
        Btn[] btns = {helpBtn, saveBtn, exitBtn};        
        menuContainer = new BtnContainer(btns, WIDTH / 2 - helpBtn.getWidth() / 2, 150, new ImageIcon("assets/containerBg.png").getImage());//Btn btns[], posX, posY, bgimage        
        
        //img, ширина, высота,тип, стоимоть, увеличение, увелечение стоимости(раз)
        Perks perk1B1 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 128, 60, 1, 20, 2, 2);//перк1б1
        Perks perk2B1 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 128, 60, 1, 55, 3, 4);//перк2б1
        Perks perk3B1 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 128, 60, 2, 200, 1, 3);//перк3б1
        Btn test7 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 128, 60, 2, 450, 3, 5);//перк4б1        
        Btn[] btnsPerkB1 = {perk1B1, perk2B1, perk3B1, test7};
        
        //img, тип, стоимоть, увеличение, увелечение стоимости(раз)
        Perks perk1B2 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 180, 60, 3, 185, 300, 3);//перк1б2
        Perks perk2B2 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 180, 60, 3, 500, 400, 2);//перк2б2
        Perks perk3B2 = new Perks(new ImageIcon("assets/perksBgBtn.png").getImage(), 180, 60, 3, 320, 205, 1);//перк3б2
        Btn[] btnsPerkB2 = {perk1B2, perk2B2, perk3B2};
        
        perkContainerB1 = new BtnContainer(btnsPerkB1, WIDTH / 2 - perk1B1.getWidth() / 2 - 100, 68, null);
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
        
        childs = new ArrayList<Children>();
        for(int i = 0; i < 110; i++){
            childs.add(new Children());
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
        g.drawImage(leceymImg, 0, 0, null);
        
        //после этого происходит рендер всего 
        for(int i = 0; i < que; i++){
            childs.get(i).draw(g);
        }
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
        if(GAME_STATE == 4){
            g.drawImage(helpImg, 10, 10, 550, 450, null);
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
        
        for(int i = 0; i < que; i++){
            childs.get(i).update();            
        }
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
