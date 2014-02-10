package loa;

import java.util.LinkedList;
import java.util.List;

/** Message passing object.
 * @author Daniel Paden Tomasello cs61b-bz
 */
public class QueuedMailBox {
    /** Queue for moves. */
    private List<Move> queue = new LinkedList<Move>();
    /** Deposites MOVE0. */
    public synchronized void deposit(Move move0) {
        queue.add(move0);
        this.notifyAll();
    }
    /** Returns next move if available. */
    public synchronized Move receive() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Move temp = queue.remove(0);
        return temp;
    }

}
