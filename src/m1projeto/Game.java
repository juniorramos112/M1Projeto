/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author jrram
 */
public class Game {
    
    private int altura, largura;
    public Game(int al, int la)
    {
        this.altura = al;
        this.largura = la;
    }
    
    
    public void inicio(Graphics g, int lar, int alt)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, lar, alt);
    }

    public Base player()
    {
        Player player = new Player("img/player.png");
        player.setLargura(79);
        player.setAltura(70);
        player.setIncX(0);
        player.setIncY(0);
        player.setX((largura - player.getLargura())/2);
        player.setY(altura - player.getAltura() - 50);
        return player;
    }
    
    public void movimento()
    {
        
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }
    
    
    
}
