package jupi;

//import gameContainer;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartJupiGame extends JFrame implements ActionListener {

    private JButton credit, how_to_play, jupi_game_rule, play_now;
    private JPanel info_panel;
    private JLabel team3;
    private JScrollPane message;
    private JTextArea jmessage;

    public StartJupiGame() {
        
        team3 = new JLabel("JUPI GAME");
        info_panel = new JPanel();
        message = new JScrollPane(new JLabel(new ImageIcon("JupiLogo3.jpg")));
        message.setPreferredSize(new Dimension(400,400));
        jmessage = new JTextArea();
        play_now = new JButton("Play Now");
        jupi_game_rule = new JButton("Jupi Game Rules");
        credit = new JButton("Credit");
        how_to_play = new JButton("How to Play");

        team3.setBackground(new java.awt.Color(0, 204, 255));
        team3.setFont(new java.awt.Font("Stencil Std", 1, 37));
        team3.setForeground(new java.awt.Color(255, 11, 46));

        play_now.setBackground(new java.awt.Color(255, 255, 0));
        play_now.setFont(new java.awt.Font("Stencil Std", 1, 50));
        play_now.setForeground(new java.awt.Color(0, 255, 0));
        play_now.setFocusable(false);
        play_now.addActionListener(this);

        jupi_game_rule.setBackground(new java.awt.Color(255, 255, 153));
        jupi_game_rule.setFont(new java.awt.Font("Tahoma", 1, 16));
        jupi_game_rule.setForeground(new java.awt.Color(204, 0, 0));
        jupi_game_rule.setAlignmentX(1.0F);
        jupi_game_rule.setAlignmentY(1.0F);
        jupi_game_rule.setFocusable(false);
        jupi_game_rule.addActionListener(this);

        credit.setBackground(new java.awt.Color(255, 255, 153));
        credit.setFont(new java.awt.Font("Tahoma", 1, 16));
        credit.setForeground(new java.awt.Color(204, 0, 0));
        credit.setAlignmentX(1.0F);
        credit.setAlignmentY(1.0F);
        credit.setFocusable(false);
        credit.addActionListener(this);

        how_to_play.setBackground(new java.awt.Color(255, 255, 153));
        how_to_play.setFont(new java.awt.Font("Tahoma", 1, 16));
        how_to_play.setForeground(new java.awt.Color(204, 0, 0));
        how_to_play.setAlignmentX(1.0F);
        how_to_play.setAlignmentY(1.0F);
        how_to_play.setFocusable(false);
        how_to_play.addActionListener(this);

        GroupLayout info_panelLayout = new GroupLayout(info_panel);
        info_panel.setLayout(info_panelLayout);
        info_panelLayout.setHorizontalGroup(
                info_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(info_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(message)
                .addContainerGap()));
        info_panelLayout.setVerticalGroup(
                info_panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, info_panelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(message, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(team3, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(play_now, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(info_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(jupi_game_rule, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(credit, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(how_to_play, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(team3, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(info_panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                .addComponent(how_to_play, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addComponent(jupi_game_rule, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(credit, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(play_now, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));

        pack();
        //setResizable(false);
        setLocationRelativeTo(null);
        setAlwaysOnTop (true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play_now) {
            new gameContainer().setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == jupi_game_rule) {
            jmessage.setColumns(20);
            jmessage.setRows(5);
            jmessage.setText("  Jupi Rules\n\n  "
                    + "Rules: Each player is assigned "
                    + "one unique ball, i.e. both can’t "
                    + "have same one. Each player can\n  "
                    + "only hit their own ball with the cue. "
                    + "The goal is to use your "
                    + "balland try to hit both of the other balls\n  with it. "
                    + "As long as the goal is met, you "
                    + "keep on playing and accumulating points"
                    + "(1 point scored\n  every time your ball hits the other 2 balls), "
                    + "your turn ends when you miss.\n\n  Game End: The game ends when "
                    + "a certain number of points is reached(user definable or fixed). \n\n  "
                    + "Only 2D will be supported, i.e. the balls can’t jump over each other. "
                    + "There will be no spins\n  allowed, i.e. the player’s ball can only be hit "
                    + "in the center by the cue.");
            jmessage.setEditable(false);
            message.setViewportView(jmessage);
        } else if (e.getSource() == how_to_play) {
            jmessage.setColumns(20);
            jmessage.setRows(5);
            jmessage.setText("  Click the mouse and drug back to hit the ball.");
            jmessage.setEditable(false);
            message.setViewportView(jmessage);
        } else if (e.getSource() == credit) {
            jmessage.setColumns(20);
            jmessage.setRows(5);
            jmessage.setText("  someone@yahoo.com");
            jmessage.setEditable(false);
            message.setViewportView(jmessage);
        }
    }

    public static void main(String args[]) {
        
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartJupiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartJupiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartJupiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartJupiGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartJupiGame().setVisible(true);
            }
        });
    }
}