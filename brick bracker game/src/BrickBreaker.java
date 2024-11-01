import javax.swing.*;

public class BrickBreaker {
    BrickBreaker() {
        Game game = new Game();
        JFrame jFrame = new JFrame();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(700, 600);
        jFrame.setTitle("Brick Breaker");
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        jFrame.add(game);
        jFrame.setVisible(true);
    }
}