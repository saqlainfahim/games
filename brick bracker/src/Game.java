import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements ActionListener, KeyListener {
    private boolean isPlaying = false;
    private int score = 0;
    private int bricksCount = 50;
    private Timer timer;
    private int delay = 8;

    private int sliderPosotionXAxis = 310;
    private int ballPositionXAxis = 120;
    private int ballPositionYAxis = 350;
    private int ballDirectionXAxis = -1;
    private int ballDirectionYAxis = -2;

    private BrickGenerator brickGenerator;

    Game() {
        brickGenerator = new BrickGenerator(5, 10);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.darkGray);
        graphics.fillRect(0, 1, 692, 592);
//        graphics.setColor(Color.yellow);
//        graphics.fillRect(0, 0, 3, 592);
//        graphics.fillRect(0, 0, 692, 3);
//        graphics.fillRect(691, 0, 3, 592);

        brickGenerator.draw((Graphics2D) graphics);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sherif", Font.BOLD, 20));
        graphics.drawString("Score: " + score, 590, 30);

        // slider
        graphics.setColor(Color.green);
        graphics.fillRect(sliderPosotionXAxis, 550, 100, 8);

        //ball
        graphics.setColor(Color.yellow);
        graphics.fillOval(ballPositionXAxis, ballPositionYAxis, 20, 20);

        if (bricksCount <= 0) {
            isPlaying = false;
            ballDirectionXAxis = 0;
            ballDirectionYAxis = 0;

            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Sherif", Font.BOLD, 40));
            graphics.drawString("You Won", 190, 300);
            graphics.setFont(new Font("Sherif", Font.BOLD, 30));
            graphics.drawString("'ENTER' to Restart", 230, 350);
        }

        if (ballPositionYAxis > 570) {
            isPlaying = false;
            ballDirectionXAxis = 0;
            ballDirectionYAxis = 0;

            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Sherif", Font.BOLD, 40));
            graphics.drawString("Game Over (Score: " + score + ")", 160, 300);
            graphics.setFont(new Font("Sherif", Font.BOLD, 30));
            graphics.drawString("'ENTER' to Restart", 225, 350);
        }

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if (isPlaying) {
            if (new Rectangle(ballPositionXAxis, ballPositionYAxis, 20, 20).intersects(new Rectangle(sliderPosotionXAxis, 550, 100, 8))) {
                ballDirectionYAxis = -ballDirectionYAxis;
            }

            A: for (int i = 0; i < brickGenerator.brick.length; i++) {
                for (int j = 0; j < brickGenerator.brick[0].length; j++) {
                    if (brickGenerator.brick[i][j] > 0) {
                        int brickXAxis = j * brickGenerator.brickWidth + 80;
                        int brickYAxis = i * brickGenerator.brickHeight + 50;

                        int brickWidth = brickGenerator.brickWidth;
                        int brickHeight = brickGenerator.brickHeight;

                        Rectangle rectangle = new Rectangle(brickXAxis, brickYAxis, brickWidth, brickHeight);
                        Rectangle ballRectangle = new Rectangle(ballPositionXAxis, ballPositionYAxis, 20, 20);
                        Rectangle brickRectangle = rectangle;

                        if (ballRectangle.intersects(brickRectangle)) {
                            brickGenerator.setBrickValue(0, i, j);
                            bricksCount--;
                            score += 1;

                            if (ballPositionXAxis + 19 <= brickRectangle.x || ballPositionYAxis + 1 >= brickRectangle.x + brickRectangle.width) {
                                ballDirectionXAxis = -ballDirectionXAxis;
                            }
                            else {
                                ballDirectionYAxis = -ballDirectionYAxis;
                            }
                            break A;
                        }
                    }
                }
            }

            ballPositionXAxis += ballDirectionXAxis;
            ballPositionYAxis += ballDirectionYAxis;
            if (ballPositionXAxis < 0) {
                ballDirectionXAxis = -ballDirectionXAxis;
            }
            if (ballPositionYAxis < 0) {
                ballDirectionYAxis = -ballDirectionYAxis;
            }
            if (ballPositionXAxis > 670) {
                ballDirectionXAxis = -ballDirectionXAxis;
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (sliderPosotionXAxis > 600) {
                sliderPosotionXAxis = 600;
            }
            else {
                modeRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (sliderPosotionXAxis < 10) {
                sliderPosotionXAxis = 10;
            }
            else {
                moveLeft();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!isPlaying) {
                isPlaying = true;
                ballPositionXAxis = 120;
                ballPositionYAxis = 350;
                ballDirectionXAxis = -1;
                ballDirectionYAxis = -2;
                sliderPosotionXAxis = 310;
                score = 0;
                bricksCount = 50;
                brickGenerator = new BrickGenerator(5, 10);
                repaint();
            }
        }
    }

    public void modeRight() {
        isPlaying = true;
        sliderPosotionXAxis += 20;
    }

    public void moveLeft() {
        isPlaying = true;
        sliderPosotionXAxis -= 20;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
