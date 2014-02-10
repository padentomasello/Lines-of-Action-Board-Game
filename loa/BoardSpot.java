package loa;

/** Represents a Spot on the Board.
 *
 * @author padentomasello
 *
 */
public class BoardSpot {
    /** Creates a spot at column COL and row ROW. */
    BoardSpot(int col, int row) {
        _row = row;
        _col = col;
        traverseNum = 0;
        spotArray = new BoardSpot[8];
    }

    /**
     * @return the _row
     */
    public int getRow() {
        return _row;
    }

    /**
     * @return the _col
     */
    public int getCol() {
        return _col;
    }
    /** Adds piece PIECE0 to spot. */
    public void addPiece(Piece piece0) {
        piece = piece0;
    }
    /** Represents all connecting BoardSpots. */
    private BoardSpot tl, t, tr, r, l, br, bl, b;
    /** Array for spots. */
    private BoardSpot[] spotArray;

    /**
     * @return the spotArray
     */
    public BoardSpot[] getSpotArray() {
        return spotArray;
    }

    /**
     * @return the TopLeft Boardspot. */
    public BoardSpot getTL() {
        return tl;
    }

    /**
     * @return the traverseNum
     */
    public int getTraverseNum() {
        return traverseNum;
    }

    /**
     * @param traverseNum0 the traverseNum to set
     */
    public void setTraverseNum(int traverseNum0) {
        traverseNum = traverseNum0;
    }

    /**
     * Sets tl to the TL0. */
    public void setTL(BoardSpot tL0) {
        tl = tL0;
        spotArray[0] = tL0;
    }

    /**
     * @return the t
     */
    public BoardSpot getT() {
        return t;
    }

    /**
     * @param t0 the t to set
     */
    public void setT(BoardSpot t0) {
        t = t0;
        spotArray[1] = t;
    }

    /**
     * @return the tR
     */
    public BoardSpot getTR() {
        return tr;
    }

    /**
     * @param tR the tR to set
     */
    public void setTR(BoardSpot tR) {
        tr = tR;
        spotArray[2] = tr;
    }

    /**
     * @return the r
     */
    public BoardSpot getR() {
        return r;
    }

    /**
     * @param r0 the r to set
     */
    public void setR(BoardSpot r0) {
        r = r0;
        spotArray[3] = r;
    }

    /**
     * @return the l
     */
    public BoardSpot getL() {
        return l;
    }

    /**
     * @param l0 the l to set
     */
    public void setL(BoardSpot l0) {
        l = l0;
        spotArray[7] = l;
    }

    /**
     * @return the bR
     */
    public BoardSpot getBR() {
        return br;
    }

    /**
     * @param bR the bR to set
     */
    public void setBR(BoardSpot bR) {
        br = bR;
        spotArray[4] = br;
    }

    /**
     * @return the rL
     */
    public BoardSpot getBL() {
        return bl;
    }

    /**
     * @param bl0 the rL to set
     */
    public void setBL(BoardSpot bl0) {
        bl = bl0;
        spotArray[6] = bl;
    }

    /**
     * @return the b
     */
    public BoardSpot getB() {
        return b;
    }

    /**
     * @param b0 the b to set
     */
    public void setB(BoardSpot b0) {
        b = b0;
        spotArray[5] = b;
    }

    /**
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BoardSpot [_row=" + _row + ", _col=" + _col + "]" + piece;
    }

    /**
     * @param piece0 the piece to set
     */
    public void setPiece(Piece piece0) {
        this.piece = piece0;
    }
    /** Number of times I have been traversed. */
    private int traverseNum;
    /** Piece on spot. */
    private Piece piece;
    /** Row and Coloumn of spot. */
    private  int _row, _col;
}
