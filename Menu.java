import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Menu extends JPanel{

    private boolean started;

    //Generating a JPanel menu with 4 buttons
    public Menu() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Button for NEW GAME(not fully functional yet)
        add(Box.createVerticalStrut(360));

        MenuButton button1 = new MenuButton("NEW GAME");

        button1.setAlignmentX(CENTER_ALIGNMENT);

        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });

        add(button1);

        //Button for LOAD GAME(not fully functional yet)
        MenuButton button2 = new MenuButton("LOAD GAME");

        button2.setAlignmentX(CENTER_ALIGNMENT);

        add(button2);

        //Button for LEVEL(not fully functional yet)
        MenuButton button3 = new MenuButton("LEVELS");

        button3.setAlignmentX(CENTER_ALIGNMENT);

        add(button3);

        //Button for EXIT to close the game
        MenuButton button4 = new MenuButton("EXIT");

        button4.setAlignmentX(CENTER_ALIGNMENT);

        button4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(button4);

        add(Box.createVerticalGlue());
    }

        private void start(){
            removeAll();
            started = true;
            repaint();
        }

        @Override
        public void paintComponent(Graphics graph) {
            super.paintComponent(graph);
            if (!started) {
                graph.drawImage(new ImageIcon(Menu.class.getResource("background.png")).getImage(), 0, 0, 600, 700, this);
            }
            else{
                //Currently just set background to black and load a picture of GridLockExample
                setBackground(Color.BLACK);
                graph.drawImage(new ImageIcon(Menu.class.getResource("GridLockExample.png")).getImage(), 0, 0, 600, 600, this);
            }
        }



    }
