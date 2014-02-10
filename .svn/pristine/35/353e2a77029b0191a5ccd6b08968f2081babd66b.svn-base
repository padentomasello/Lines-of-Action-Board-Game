package loa;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** GUI for board.
 *  @author Daniel Paden Tomasello cs61b-bz
 */
public class GuiBoard extends JFrame {
    /** Constant for 400. */
    private static final int FOURHUNDRED = 400;
    /** Constant for 600. */
    private static final int SIXHUNDRED = 600;

    /** Creates a new Frame based on BOARD and BOX0 for messaging. */
    public GuiBoard(Board board, QueuedMailBox box0) {
        JButton button = new JButton("B");
        button.setBackground(Color.WHITE);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(8, 8));
        p1.setSize(SIXHUNDRED, SIXHUNDRED);
        BoardButtonListener listener = new BoardButtonListener(box0);
        for (int i = 1; i < 9; i += 1) {
            for (int k = 1; k < 9; k += 1) {
                GuiSpot spot = new GuiSpot(board, k, i);
                spot.addActionListener(listener);
                p1.add(spot);
            }
        }
        super.add(p1);
        setSize(FOURHUNDRED, FOURHUNDRED);
        super.show();
    }
    /** Performs actions based on E. */
    public void actionPerformed(ActionEvent e) {
        GuiSpot source = (GuiSpot) e.getSource();
        System.out.println(source.getBoardSpot().getPiece());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        QueuedMailBox box = new QueuedMailBox();
        GuiBoard frame = new GuiBoard(board, box);
        frame.setTitle("Checkers");
        frame.setSize(SIXHUNDRED, SIXHUNDRED);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
