package jupi;

//import BilliardsConstants;
//import World;

import javax.swing.*;
import java.awt.event.*;


public class gameContainer extends JFrame implements ActionListener {

    private JButton exit;
    private JPanel game_panel;
    private JButton pause;
    private JButton play;
    private World w = new World();
    private Thread t = new Thread(new Live());

    public gameContainer() {

        play = new JButton("Play");
        pause = new JButton("Pause");
        exit = new JButton("Exit");
        
        game_panel = new JPanel();
        
        
        play.setBackground(new java.awt.Color(255, 255, 153));
        play.setFont(new java.awt.Font("Tahoma", 1, 16));
        play.setForeground(new java.awt.Color(204, 0, 0));
        play.setAlignmentX(1.0F);
        play.setAlignmentY(1.0F);
        play.setFocusable(false);
        play.addActionListener(this);
        play.setVisible(false);//not needed for now

        pause.setBackground(new java.awt.Color(255, 255, 153));
        pause.setFont(new java.awt.Font("Tahoma", 1, 16));
        pause.setForeground(new java.awt.Color(204, 0, 0));
        pause.setAlignmentX(1.0F);
        pause.setAlignmentY(1.0F);
        pause.setFocusable(false);
        pause.setEnabled(false);
        pause.addActionListener(this);
        pause.setVisible(false);//not needed for now
        
        exit.setBackground(new java.awt.Color(255, 255, 153));
        exit.setFont(new java.awt.Font("Tahoma", 1, 16));
        exit.setForeground(new java.awt.Color(204, 0, 0));
        exit.setAlignmentX(1.0F);
        exit.setAlignmentY(1.0F);
        exit.setFocusable(false);
        exit.addActionListener(this);

        javax.swing.GroupLayout game_panelLayout = new javax.swing.GroupLayout(game_panel);
        game_panel.setLayout(game_panelLayout);
        game_panelLayout.setHorizontalGroup(
                game_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 0, Short.MAX_VALUE));
        game_panelLayout.setVerticalGroup(
                game_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 541, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(game_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                .addComponent(play, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221)
                .addComponent(pause, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(play, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addComponent(pause, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 24, Short.MAX_VALUE)
                .addComponent(game_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));

        game_panel.setLayout(new java.awt.BorderLayout());
        game_panel.setBackground(BilliardsConstants.FLOOR);
        game_panel.add(w);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        	if (e.getSource() == exit) {
        		new StartJupiGame().setVisible(true);
        		this.setVisible(false);
        }
    }

    private class Live implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    w.table.update();
                    t.sleep(BilliardsConstants.TIME_SLICE);
                }
            } catch (InterruptedException ex) {
                System.err.println("Problem while putting thread to sleep.");
            }
        }
    }    
}