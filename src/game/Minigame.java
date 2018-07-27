package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import javazoom.jl.player.Player;

/**
 * 	This project is used to improve and enhance 
 *	your math instinct. Training you to have the
 *	quick response with the basic addition.
 * @author ThuVo
 */
public class Minigame extends javax.swing.JFrame implements ActionListener {
    MP3 mp3 = new MP3(this.getClass().getClass().getResourceAsStream("/music/ThePinkPanther.mp3"));
    MP3 mp3t = new MP3(this.getClass().getClass().getResourceAsStream("/music/true.mp3"));
    MP3 mp3f = new MP3(this.getClass().getClass().getResourceAsStream("/music/false.mp3"));
    MP3 mp3e = new MP3(this.getClass().getClass().getResourceAsStream("/music/warning.mp3"));

    // The score
    int s = 0;

    // The range of the numbers
    int max = 9;
    int min = 1;
    int range = (max - min) + 1;

    int max2 = 4;
    int min2 = 1;
    int range2 = (max2 - min2) + 1;

    // result
    int i;

    int choose = JOptionPane.YES_NO_OPTION;
    // Time
    Timer time;
    int customizedTime;

    // Sound
    public void game() {
    	// Time limit equals 10 seconds
        customizedTime = 100;
        
        // Applied random method in Math class
        int random1 = (int) ((Math.random() * range) + min);
        int random2 = (int) ((Math.random() * range) + min);
        int rr = (int) ((Math.random() * range2) + min2);

        int result = random1 + random2;
        int result2 = random1 + random2;
        switch (rr) {
        case 1:
            result += 1;
            break;
        case 2:
            result -= 1;
            break;
        case 3:
            result /= 1;
            break;
        case 4:
            result *= 1;
            break;
        }
        text.setText(" " + random1 + "+" + random2 + "=" + result);
        
        // If the answer is right, i equals 1
        if (result == result2) {
            i = 1;
        // If the answer is wrong, i equals 2
        } else if (result != result2) {
            i = 2;
        }

    }

    // Time limit
    public void loadtime() {
        customizedTime = 100;
        time.restart();
    }

    /**
     * The method indicates when the time has reached
     * its limit.
     */
    public void actionPerformed(ActionEvent e) {

        time = new Timer(50, this);
        if (customizedTime > 0) {
            customizedTime--;
            pb.setValue(customizedTime);
        }
        if (customizedTime == 0) {
            text.setSize(500, 100);
            text.setText("Time's up !");
            btagain.setVisible(true);
            bttrue.setEnabled(false);
            btfalse.setEnabled(false);
        }
    }

    /**
     * The introduction of the game
     */
    public Minigame() {
        initComponents();
        mp3.play();
        setLocation(100, 100);
        setSize(500, 500);
        setTitle("Magical plus sign");
//        setTitle("@2018 Thu Vo");
        bttrue.setVisible(false);
        btfalse.setVisible(false);
        text.setVisible(true);
        score.setVisible(false);
        btagain.setVisible(false);
        pb.setValue(customizedTime);
        pb.setVisible(false);
        exit.setToolTipText("Exit ?");
        play.setToolTipText("Play");

    }

    class MP3 {
        private Player player;
        private InputStream filename;

        public MP3(InputStream filename) {
            this.filename = filename;
        }

        public void stop() {
            if (player != null)
                player.close();
        }

        public void play() {
            try {
                BufferedInputStream bis = new BufferedInputStream(filename);
                player = new Player(bis);

            } catch (Exception ex) {
                System.out.println(ex);
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }).start();
        }
    }

    /**
     * The sounds every time we replay the game
     */
    public void reset() {
        game();
        mp3 = new MP3(this.getClass().getClass().getResourceAsStream("/music/ThePinkPanther.mp3"));
        mp3t = new MP3(this.getClass().getClass().getResourceAsStream("/music/true.mp3"));
        mp3f = new MP3(this.getClass().getClass().getResourceAsStream("/music/false.mp3"));
        mp3e = new MP3(this.getClass().getClass().getResourceAsStream("/music/warning.mp3"));
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel() {
            ImageIcon icon = new ImageIcon("blue.jpg");

            public void paintComponent(Graphics g) {
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        
        //Buttons initializations.
        play = new javax.swing.JButton();
        bttrue = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btfalse = new javax.swing.JButton();
        text = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btagain = new javax.swing.JButton();
        pb = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 470, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 480, Short.MAX_VALUE));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(480, 480));
        getContentPane().setLayout(null);

        play.setText("Play");
        play.setToolTipText("Play");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        getContentPane().add(play);
        play.setBounds(130, 316, 233, 45);

        bttrue.setText("Correct");
        bttrue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bttrueMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bttrueMouseReleased(evt);
            }
        });
        bttrue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttrueActionPerformed(evt);
            }
        });

        // Set the bounds of true, false button and the title
        getContentPane().add(bttrue);
        bttrue.setBounds(40, 374, 130, 70);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Magical plus sign");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 56, 296, 74);

        btfalse.setText("False");
        btfalse.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mousePressed(java.awt.event.MouseEvent evt) {
                btfalseMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btfalseMouseReleased(evt);
            }
        });
        btfalse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btfalseActionPerformed(evt);
            }
        });
        getContentPane().add(btfalse);
        btfalse.setBounds(330, 376, 130, 70);

        text.setFont(new java.awt.Font("Dialog", 1, 60));
        text.setForeground(new java.awt.Color(255, 255, 0));
        text.setEnabled(false);
        getContentPane().add(text);
        text.setBounds(120, 219, 271, 70);

        score.setFont(new java.awt.Font("Dialog", 1, 14));
        score.setForeground(new java.awt.Color(255, 255, 0));
        score.setText("Score : 0");
        getContentPane().add(score);
        score.setBounds(310, 120, 88, 30);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/exit.png")));
        exit.setToolTipText("Exit");
        exit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                exitMouseMoved(evt);
            }
        });
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitMouseReleased(evt);
            }
        });
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit);
        exit.setBounds(430, 14, 30, 30);

        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Code By : Thu Vo");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 476, 150, 16);

        btagain.setText("Again");
        btagain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btagainActionPerformed(evt);
            }
        });
        getContentPane().add(btagain);
        btagain.setBounds(210, 386, 80, 30);
        getContentPane().add(pb);
        pb.setBounds(105, 170, 271, 12);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/blue.jpg")));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 500, 476);

        pack();
    }

    /**
     * The method indicates the performance of the 'play' button
     * @param evt
     */
    private void playActionPerformed(java.awt.event.ActionEvent evt) {
        mp3.stop();
        pb.setVisible(true);
        play.setVisible(false);
        bttrue.setVisible(true);
        btfalse.setVisible(true);
        text.setVisible(true);
        score.setVisible(true);

        game();

        time = new Timer(50, this);
        if (customizedTime > 0) {
            customizedTime--;
            pb.setValue(customizedTime);
        }
        if (customizedTime == 0) {

            text.setText("Time's up !");
            btagain.setVisible(true);

        }
        time.start();
    }

    /**
     * This method indicates the action of the correct/false answers
     * @param evt
     */
    private void bttrueActionPerformed(java.awt.event.ActionEvent evt) {
        // if correct
        if (i == 1) {

            customizedTime = 100;
            s += 1;
            reset();
            mp3t.play();
        // if false
        } else if (i == 2) {
            mp3f.play();
            text.setText("You lost!");
            bttrue.setEnabled(false);
            btfalse.setEnabled(false);
            btagain.setVisible(true);
            customizedTime = -1;
        }

        score.setText("Score: " + s);
        time = new Timer(50, this);
        if (customizedTime > 0) {
            customizedTime--;
            pb.setValue(customizedTime);
        }
        if (customizedTime == 0) {

            text.setText("Time's up!");
            btagain.setVisible(true);
            btfalse.setEnabled(false);
            bttrue.setEnabled(false);
        }

    }

    //The performance of the false button
    private void btfalseActionPerformed(java.awt.event.ActionEvent evt) {
        if (i == 2) {
            customizedTime = 100;
            s += 1;
            reset();
            mp3t.play();
        } else if (i == 1) {
            mp3f.play();
            text.setText("You lost!");
            bttrue.setEnabled(false);
            btfalse.setEnabled(false);
            btagain.setVisible(true);

            customizedTime = -1;
        }
        score.setText("Score: " + s);
        time = new Timer(50, this);
        if (customizedTime > 0) {
            customizedTime--;
            pb.setValue(customizedTime);
        }
        if (customizedTime == 0) {

            text.setText("Time's up!");
            btagain.setVisible(false);
            btfalse.setEnabled(false);
            bttrue.setEnabled(false);
        }
    }

    //The performance of the exit button
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
        mp3e.play();
        choose = JOptionPane.showConfirmDialog(null, "Do you want to exit this game ?", "", JOptionPane.YES_NO_OPTION);

        if (choose == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else if (choose == JOptionPane.NO_OPTION) {

        }
    }

    // The performance of the 'again' button
    private void btagainActionPerformed(java.awt.event.ActionEvent evt) {
        s = 0;
        score.setText("Score: " + s);
        btfalse.setEnabled(true);
        bttrue.setEnabled(true);
        reset();
        btagain.setVisible(false);
        time = new Timer(50, this);
        if (customizedTime > 0) {
            customizedTime--;
            pb.setValue(customizedTime);
        }
        if (customizedTime == 0) {

            text.setText("Time's up!");
            btagain.setVisible(true);

        }
    }

    // When 'true' button is pressed
    private void bttrueMousePressed(java.awt.event.MouseEvent evt) {
        bttrue.setText("Correct!!");
    }

    // When 'true' button is released
    private void bttrueMouseReleased(java.awt.event.MouseEvent evt) {
        bttrue.setText("Correct!!");
    }

    // When 'false' button is pressed
    private void btfalseMousePressed(java.awt.event.MouseEvent evt) {
        btfalse.setText("False");
    }

    // When 'false' button is released
    private void btfalseMouseReleased(java.awt.event.MouseEvent evt) {
        btfalse.setText("False");
    }

    // When 'exit' button is pressed
    private void exitMouseMoved(java.awt.event.MouseEvent evt) {
        exit.setIcon(new ImageIcon(getClass().getResource("/pictures/exit2.png")));
    }

    // When 'exit' button is realeased
    private void exitMouseReleased(java.awt.event.MouseEvent evt) {
        exit.setIcon(new ImageIcon(getClass().getResource("/pictures/exit.png")));
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Minigame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Minigame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Minigame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Minigame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Minigame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btagain;
    private javax.swing.JButton btfalse;
    private javax.swing.JButton bttrue;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar pb;
    private javax.swing.JButton play;
    private javax.swing.JLabel score;
    private javax.swing.JLabel text;
    // End of variables declaration

}
