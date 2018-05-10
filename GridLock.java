import javax.swing.*;

public class GridLock extends JFrame{


    public static void main(String[] args) {
        new GridLock();
    }

    //a JFrame called GridLock

    public GridLock(){
        //title for the game
        super("GridLock");
        //Size of the window of the game
        setSize(600,700);
        //Not allowed to resize the window
        setResizable(false);

        Menu menu = new Menu();
        add(menu);

        //when closing the window, finish process of program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //make the game being able to view
        setVisible(true);
    }
}
