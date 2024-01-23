import game.GameLoop;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        GameLoop gameLoop = new GameLoop();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Chess");
        window.add(gameLoop);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameLoop.init();

        gameLoop.run();


    }

}