package loa;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;
/** Gui for spot.
 *  @author Daniel Paden Tomasello cs61b-bz
 */
public class GuiSpot extends JButton {
    /** Constant for 30. */
    private static final int THIRTY = 30;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** Creates new GUI from BOARD at C col and R row. */
    GuiSpot(Board board, int c, int r) {
        super("");
        spot = board.getSpot(c, 9 - r);
    }
    /** Paints component with Graphics G. */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        if (getSpot().getPiece() != Piece.EMP) {
            if (getSpot().getPiece() == Piece.BP) {
                g.fillOval(8, 8, THIRTY, THIRTY);
            } else {
                g.drawOval(8, 8, THIRTY, THIRTY);
            }
        }
    }
    /** Returns spot. */
    BoardSpot getSpot() {
        return spot;
    }
    /** returns spot. */
    BoardSpot getBoardSpot() {
        return spot;
    }
    /** Spot. */
    private BoardSpot spot;
}
