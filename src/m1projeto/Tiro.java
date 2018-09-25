/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

/**
 *
 * @author jrram
 */
/**
 *
 * @author jrram
 */
public class Tiro extends Base{
    
 
    
    public Tiro(String url){
        super(url);
    }

    @Override
    public void mover() {
        x = x + incX;
        y = y + incY;
        this.rect.x = x;
        this.rect.y = y;
    }
    
    
    
}
