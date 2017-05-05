package com.mysnake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Hi-Tech on 24.04.2017.
 */
public class Snake implements ActionListener, KeyListener {

    public static Snake snake;

    public Dimension dim;

    public Timer timer = new Timer(10,this);

    public JFrame window;

    public MyPanel panel;

    public ArrayList<Point> partsBySnake = new ArrayList<>();

    public ArrayList<Point> block = new ArrayList<>();

    public Point head, eat;

    public static int lenghtSnake;

    public static final int SCALE = 10;

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

    public int score, direction, r_speed, speed;

    public  static boolean game_over = false, pause = false;

    public Random random;

    public Snake(){
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        window = new JFrame("MySnake");
        window.setVisible(true);
        window.setSize(60*SCALE - 5, 45*SCALE  );
        window.setLocation(dim.width/2 - window.getWidth()/2, dim.height/2 - window.getHeight()/2);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(panel = new MyPanel());
        window.addKeyListener(this);
        startGame();
    }

    public void startGame(){
        lenghtSnake = 3;
        score = 0;
        speed = 1;
        r_speed = 0;
        partsBySnake.clear();
        direction = DOWN;
        game_over = false;
        pause = false;
        random = new Random();
        for(int i = 0, x = 5, x1 = 5, x2 = 50, x3 = 49, x4 = 23, y = 5, y1 = 30, y2 = 5, y3 = 30, y4 =20; i<12; i++){
            block.add(new Point(x, y));
            block.add(new Point(x1, y1));
            block.add(new Point(x2, y2));
            block.add(new Point(x3, y3));
            block.add(new Point(x4, y4));
            x4++;
            if (i<6) {
                x++; y1++; y2++; x3++;
            }
            else {
                y++; x1++; x2++; y3++;
            }
        }
        head = new Point(0,-1);
        eat = new Point(random.nextInt(57), random.nextInt(41));
        if (block.contains(eat))
            while(block.contains(eat))
                eat = new Point(random.nextInt(57), random.nextInt(41));

        for(int i=0; i<=lenghtSnake; i++ ){
            partsBySnake.add(new Point(head.x,head.y));
        }

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        panel.repaint();
        score++;

        if(lenghtSnake < 5)
            r_speed = 8;
        else if (lenghtSnake >= 5 && lenghtSnake < 10){
            r_speed = 7;
            speed = 2;
        }
        else if (lenghtSnake >= 10 && lenghtSnake < 15){
            r_speed = 5;
            speed = 3;
        }
        else {
            r_speed = 3;
            speed = 4;
        }

        if (score % r_speed == 0 && head != null && !pause && !game_over ) {
            partsBySnake.add(new Point(head.x, head.y));

            if (direction == DOWN ) {
                if (head.y + 1 <= 41 && crash(head.x, head.y + 1) && crashBlock(head.x, head.y + 1))
                    head = new Point(head.x, head.y + 1);
                else
                    game_over = true;
            }

            if (direction == UP) {
                if (head.y - 1 >= 0 && crash(head.x, head.y - 1) && crashBlock(head.x, head.y - 1))
                    head = new Point(head.x, head.y - 1);
                else
                    game_over = true;
            }

            if (direction == LEFT) {
                if (head.x - 1 >= 0 && crash(head.x -1, head.y) && crashBlock(head.x -1, head.y))
                    head = new Point(head.x - 1, head.y);
                else
                    game_over = true;
            }

            if (direction == RIGHT) {
                if (head.x + 1 <= 58 && crash(head.x + 1, head.y) && crashBlock(head.x + 1, head.y))
                    head = new Point(head.x + 1, head.y);
                else
                    game_over = true;
            }

            if (partsBySnake.size() > lenghtSnake) {
                partsBySnake.remove(0);
            }

        }
        if (eat != null) {
            if (head.equals(eat)) {
                lenghtSnake++;
                eat.setLocation(random.nextInt(57), random.nextInt(41));
                if(block.contains(eat))
                    while (block.contains(eat))
                        eat.setLocation(random.nextInt(57), random.nextInt(41));
            }
        }
    }

    public boolean crash(int x, int y){
        for(Point point: partsBySnake) {
            if (point.equals(new Point(x, y)))
                return false;
        }
        return true;
    }

    public boolean crashBlock(int x, int y){
        for(Point point: block) {
            if (point.equals(new Point(x, y)))
                return false;
        }
        return true;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();

        if(i == KeyEvent.VK_S && direction != UP)
            direction = DOWN;
        if(i == KeyEvent.VK_W && direction != DOWN)
            direction = UP;
        if(i == KeyEvent.VK_D && direction != LEFT)
            direction = RIGHT;
        if(i == KeyEvent.VK_A && direction != RIGHT)
            direction = LEFT;

        if (i == KeyEvent.VK_ENTER) {
            if (game_over)
                startGame();
            else
                pause = !pause;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {

        snake = new Snake();
    }
}
