import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame jFrame = new JFrame();
    JPanel jPanelTitle = new JPanel();
    JPanel jPanelButton = new JPanel();
    JLabel jLabel = new JLabel();
    JButton[] jButton = new JButton[9];
    boolean xTurn;

    TicTacToe() {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 600);
        jFrame.getContentPane().setBackground(new Color(19, 62, 135));
        jFrame.setLayout(new BorderLayout());
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        jLabel.setBackground(new Color(96, 139, 193));
        jLabel.setForeground(new Color(243, 243, 224));
        jLabel.setFont(new Font("Sherif", Font.BOLD, 50));
        jLabel.setHorizontalAlignment(JTextField.CENTER);
        jLabel.setText("Tic Tac Toe");
        jLabel.setOpaque(true);

        jPanelTitle.setLayout(new BorderLayout());
        jPanelTitle.setBounds(0, 0, 500, 100);
        jPanelTitle.add(jLabel);

        jPanelButton.setLayout(new GridLayout(3, 3));
        jPanelButton.setBackground(new Color(203, 220, 235));

        for (int i = 0; i < 9; i++) {
            jButton[i] = new JButton();
            jPanelButton.add(jButton[i]);

            jButton[i].setFont(new Font("Sherif", Font.BOLD, 75));
            jButton[i].setFocusable(false);
            jButton[i].addActionListener(this);
        }

        jFrame.add(jPanelTitle);
        jFrame.add(jPanelTitle, BorderLayout.NORTH);
        jFrame.add(jPanelButton);

        turn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == jButton[i]) {
                if (xTurn) {
                    if (jButton[i].getText() == "") {
                        jButton[i].setForeground(new Color(252, 65, 0));
                        jButton[i].setText("X");
                        xTurn = false;
                        jLabel.setText("O turn");
                        winCheck();
                    }
                }
                else {
                    if (jButton[i].getText() == "") {
                        jButton[i].setForeground(new Color(22, 199, 154));
                        jButton[i].setText("O");
                        xTurn = true;
                        jLabel.setText("X turn");
                        winCheck();
                    }
                }
            }
        }
    }

    public void turn() {
        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (random.nextInt(2) == 0) {
            xTurn = true;
            jLabel.setText("X turn");
        }
        else {
            xTurn = false;
            jLabel.setText("O turn");
        }
    }

    public void winCheck() {
        // win condition for X
        if ((jButton[0].getText().equals("X")) && (jButton[1].getText().equals("X")) && (jButton[2].getText().equals("X"))) {
            xWin(0, 1, 2);
        }

        if ((jButton[3].getText().equals("X")) && (jButton[4].getText().equals("X")) && (jButton[5].getText().equals("X"))) {
            xWin(3, 4, 5);
        }

        if ((jButton[6].getText().equals("X")) && (jButton[7].getText().equals("X")) && (jButton[8].getText().equals("X"))) {
            xWin(6, 7, 8);
        }

        if ((jButton[0].getText().equals("X")) && (jButton[3].getText().equals("X")) && (jButton[6].getText().equals("X"))) {
            xWin(0, 3, 6);
        }

        if ((jButton[1].getText().equals("X")) && (jButton[4].getText().equals("X")) && (jButton[7].getText().equals("X"))) {
            xWin(1, 4, 7);
        }

        if ((jButton[2].getText().equals("X")) && (jButton[5].getText().equals("X")) && (jButton[8].getText().equals("X"))) {
            xWin(2, 5, 8);
        }

        if ((jButton[0].getText().equals("X")) && (jButton[4].getText().equals("X")) && (jButton[8].getText().equals("X"))) {
            xWin(0, 4, 8);
        }

        if ((jButton[2].getText().equals("X")) && (jButton[4].getText().equals("X")) && (jButton[6].getText().equals("X"))) {
            xWin(2, 4, 6);
        }

        // win condition for O
        if ((jButton[0].getText().equals("O")) && (jButton[1].getText().equals("O")) && (jButton[2].getText().equals("O"))) {
            oWin(0, 1, 2);
        }

        if ((jButton[3].getText().equals("O")) && (jButton[4].getText().equals("O")) && (jButton[5].getText().equals("O"))) {
            oWin(3, 4, 5);
        }

        if ((jButton[6].getText().equals("O")) && (jButton[7].getText().equals("O")) && (jButton[8].getText().equals("O"))) {
            oWin(6, 7, 8);
        }

        if ((jButton[0].getText().equals("O")) && (jButton[3].getText().equals("O")) && (jButton[6].getText().equals("O"))) {
            oWin(0, 3, 6);
        }

        if ((jButton[1].getText().equals("O")) && (jButton[4].getText().equals("O")) && (jButton[7].getText().equals("O"))) {
            oWin(1, 4, 7);
        }

        if ((jButton[2].getText().equals("O")) && (jButton[5].getText().equals("O")) && (jButton[8].getText().equals("O"))) {
            oWin(2, 5, 8);
        }

        if ((jButton[0].getText().equals("O")) && (jButton[4].getText().equals("O")) && (jButton[8].getText().equals("O"))) {
            oWin(0, 4, 8);
        }

        if ((jButton[2].getText().equals("O")) && (jButton[4].getText().equals("O")) && (jButton[6].getText().equals("O"))) {
            oWin(2, 4, 6);
        }
    }

    public void xWin(int a, int b, int c) {
        jButton[a].setBackground(new Color(79, 177, 255));
        jButton[b].setBackground(new Color(79, 177, 255));
        jButton[c].setBackground(new Color(79, 177, 255));

        for (int i = 0; i < 9; i++){
            jButton[i].setEnabled(false);
        }

        jLabel.setText("X Wins");
    }

    public void oWin(int a, int b, int c) {
        jButton[a].setBackground(new Color(79, 177, 255));
        jButton[b].setBackground(new Color(79, 177, 255));
        jButton[c].setBackground(new Color(79, 177, 255));

        for (int i = 0; i < 9; i++){
            jButton[i].setEnabled(false);
        }

        jLabel.setText("O Wins");
    }
}