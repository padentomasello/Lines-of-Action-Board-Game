package loa;

import java.util.LinkedList;
/** Tree for a Moves sequence.
 *
 * @author Paden Tomasello cs61b-bz
 *
 */
public class MoveTree {
    /** Creates new MoveTree with MOVE as root. */
    MoveTree(Move move) {
        _root = move;
    }

    /** Returns root of tree. */
    public Move root() {
        return _root;
    }
    /** Adds MOVE to children. */
    public void add(Move move) {
        children.push(new MoveTree(move));
    }

    /**
     * @return the children
     */
    public LinkedList<MoveTree> getChildren() {
        return children;
    }

    /** Return child of MOVE of MoveTree. */
    public MoveTree child(Move move) {
        for (MoveTree tree: children) {
            if (tree.root().equals(move)) {
                return tree;
            }
        }
        return null;
    }
    /** Children of MoveTree. */
    private LinkedList<MoveTree> children = new LinkedList<MoveTree>();
    /** Root of MoveTree. */
    private Move _root;
}
