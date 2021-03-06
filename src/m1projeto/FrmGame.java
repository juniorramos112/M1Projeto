/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m1projeto;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author jrram
 */
public class FrmGame extends javax.swing.JFrame implements Runnable{

    
    private boolean left;
    private boolean right;
    private boolean fimJogo;
    private boolean keyRestart;
    private boolean tiro;
    private long ultimoTiro;
    
    
    public FrmGame() {
        initComponents();
        createBufferStrategy(2);
        Thread t = new Thread(this);
        t.start();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1000, 1000));
        setMinimumSize(new java.awt.Dimension(1000, 1000));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (evt.getKeyCode() == KeyEvent.VK_R) {
            keyRestart = true;
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tiro = true;
        }
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (evt.getKeyCode() == KeyEvent.VK_R) {
            keyRestart = false;
        } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tiro = false;
        }
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGame().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
        Graphics g;
        Game game = new Game(getWidth(),getHeight());
        ArrayList<Base> lixo = new ArrayList();
        ArrayList<Base> lista = new ArrayList();
        ArrayList<Base> tiros = new ArrayList();
        ArrayList<Base> inimigos = new ArrayList();
        Player player = new Player();
        player = (Player) game.player();
        lista.add(game.player());
        

        int pos = 0;

        for (int i = 0; i < 7; i++) {

            Inimigo inimigo = new Inimigo("img/inimigo.png");
            inimigo.setAltura(85);
            inimigo.setLargura(85);
            inimigo.setX(100 + pos);
            inimigo.setY(100);
            pos += 120;
            lista.add(inimigo);
            inimigos.add(inimigo);
        }

        while (true) {

            g = getBufferStrategy().getDrawGraphics();
            game.inicio(g, getWidth(), getHeight());
            

            for (Base b : lista) {
                b.desenhar(g);
                b.mover();
            }

            for(Base b : inimigos) // colisão
            {
                for(Base t : tiros)
                {
                    if(b.colide(t) == true)
                    {
                        //Inimigo inimigo = new Inimigo("img/explosion1.png");
                        //inimigo.setX(b.getX());
                        //inimigo.setY(b.getY());
                        //lista.add(inimigo);  
                        lixo.add(b);
                                              
                    }
                                 
                }
            }
            
            
            
            long tempo = System.currentTimeMillis();

            if (tiro && tempo > ultimoTiro + 400) {
                ultimoTiro = tempo;
                Tiro t = new Tiro("img/tiroplayer.png");
                t.setIncX(0);
                t.setIncY(-4);
                t.setX(player.getX() + player.getLargura() / 2);
                t.setY(player.getY() - player.getAltura() / 2 + 10);
                t.setAltura(40);
                t.setLargura(17);
                tiros.add(t);
                lista.add(t);
            }

            lista.removeAll(lixo);
            inimigos.removeAll(lixo);
            lixo.clear();

            if (left) {
                player.setIncX(-2);
            } else {
                if (right) {
                    player.setIncX(2);
                } else {
                    player.setIncX(0);
                }
            }

            g.dispose();
            getBufferStrategy().show();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
            }

        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
