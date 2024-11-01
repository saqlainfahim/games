import java.awt.*;

public class BrickGenerator {
    public int brick[][];
    public int brickWidth;
    public int brickHeight;

    BrickGenerator(int row, int col) {
        brick = new int[row][col];
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[0].length; j++) {
                brick[i][j] = 1;
            }
        }

        brickWidth = (540 / col);
        brickHeight = (150 / row);
    }
    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < brick.length; i++) {
            for (int j = 0; j < brick[0].length; j++) {
                if (brick[i][j] > 0) {
                    graphics2D.setColor(Color.white);
                    graphics2D.fillRect((j * brickWidth + 80), (i * brickHeight + 50), brickWidth, brickHeight);

                    graphics2D.setStroke(new BasicStroke(3));
                    graphics2D.setColor(Color.darkGray);
                    graphics2D.drawRect((j * brickWidth + 80), (i * brickHeight + 50), brickWidth, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col) {
        brick[row][col] = value;
    }
}
