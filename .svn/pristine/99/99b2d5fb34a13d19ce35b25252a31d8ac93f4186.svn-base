package loa;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
/** Gui for commands.
 *  @author Daniel Paden Tomasello cs61b-bz
 */
public class GuiCommand extends JFrame {
    /** Constant for 600. */
    private static final int SIXHUNDRED = 600;
    /** Constant for 100. */
    private static final int ONEHUNDRED = 100;
    /** Constant for 400. */
    private static final int FOURHUNDRED = 400;
    /** Output label. */
    private Output label;

    /** Creates new Gui based on BOARD and BOX. */
    public GuiCommand(Board board, QueuedMailBox box) {
        super.setSize(FOURHUNDRED, ONEHUNDRED);
        JPanel p1 = new JPanel();
        BoardButtonListener listener = new BoardButtonListener(box);
        p1.setLayout(new GridLayout(1, 3));
        p1.setSize(SIXHUNDRED, SIXHUNDRED);
        NonMoveButton q = new NonMoveButton("q");
        q.addActionListener(listener);
        p1.add(q);
        NonMoveButton p = new NonMoveButton("p");
        p.addActionListener(listener);
        p1.add(p);
        label = new Output(board, box);
        p1.add(label);
        super.add(p1);
        super.show();
    }
    /** Returns Label. */
    public Output getLabel() {
        return label;
    }
}
