import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemEventListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GamePlay Example");
        GamePlay gamePlay = new GamePlay();
        frame.add(gamePlay);
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}