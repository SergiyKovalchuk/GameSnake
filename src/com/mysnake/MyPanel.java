package com.mysnake;


import javax.swing.*;
import java.awt.*;


/**
 * Created by Hi-Tech on 25.04.2017.
 */
public class MyPanel extends JPanel{


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,60*Snake.SCALE-5, 60* Snake.SCALE );
        g.setColor(Color.lightGray);
        g.setColor(Color.RED);
        for (Point point: Snake.snake.partsBySnake ){
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }

        g.setColor(Color.GREEN);
        g.fillRect(Snake.snake.eat.x * Snake.SCALE, Snake.snake.eat.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);

        g.setColor(Color.BLACK);
        for(Point point: Snake.snake.block){
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }

        g.setColor(Color.WHITE);
        Font f1 = new Font("DIALOG", Font.BOLD|Font.ITALIC, 50);
        Font f2 = new Font("DIALOG", Font.ITALIC, 10);
        String spause = "PAUSE";
        if(Snake.pause == true) {
            g.setFont(f1);
            g.drawString(spause, 210, 200);
            Font f4 = new Font ("DIALOG", Font.BOLD, 15);
            g.setFont(f4);
            g.drawString("Click ENTER to continue", 215, 220);
        }
        String sgame_over = "GAME OVER";
        if(Snake.game_over == true) {
            g.setFont(f1);
            g.drawString(sgame_over, 150, 200);
            Font f3 = new Font ("DIALOG", Font.BOLD, 15);
            g.setFont(f3);
            g.drawString("Click ENTER to new game", 220, 220);
        }

        g.setFont(f2);
        g.drawString("LENGHT: " + Snake.lenghtSnake + " SPEED: " + Snake.snake.speed,  230, 10);
    }
}
