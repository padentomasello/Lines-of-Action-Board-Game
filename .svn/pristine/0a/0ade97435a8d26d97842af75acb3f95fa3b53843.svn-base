package loa;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.MatchResult;

import ucb.util.Stopwatch;

/** An automated Player.
 *  @author Daniel Paden Tomasello cs61b-bz */
class MachinePlayer extends Player {
    /** Constant for ten. */
    private static final int TWO = 2;
    /** Constant for 15. */
    private static final int FIVE = 5;
    /** Constnat for twenty. */
    private static final int TEN = 10;
    /** A MachinePlayer that plays the SIDE pieces in GAME. BOX0 is msg
     * DISPLAYON0 for if Gui.*/
    MachinePlayer(Side side, Game game,
            boolean displayOn0, QueuedMailBox box0) {
        super(side, game);
        randomSource = getGame().getRandomSource();
        box = box0;
        aiStarted = false;
        displayOn = displayOn0;
    }

    @Override
    Move makeMove() throws InterruptedException {
        if (aiStarted) {
            return aiMakeMove(getGame().getBoard());
        }
        if (!displayOn) {
            Scanner inp = new Scanner(System.in);
            String comm;
            int col0, col1, row0, row1;
            while (true) {
                if (aiStarted) {
                    return aiMakeMove(getGame().getBoard());
                }
                System.out.print("Player " + side()
                        + " please make a move >  ");
                while (!inp.hasNext()) {
                    continue;
                }
                inp.findWithinHorizon("\\s*([pqs#]|\\z)?([a-h][1-8]"
                        + "-[a-h][1-8])?", 0);
                MatchResult match = inp.match();
                String first = match.group(1);
                if (first != null) {
                    if (match.group(1).equals("s")) {
                        System.out.println(getGame().getBoard().toString());
                    } else if (match.group(1).equals("p")) {
                        startAi();
                    } else if (match.group(1).equals("q")
                            | match.group(1).equals("\\z")) {
                        System.exit(0);
                    }
                    inp.nextLine();
                    continue;
                } else if (match.group(2) != null) {
                    comm = match.group(2);
                    col0 = Board.col(comm.substring(0, 2));
                    row0 = Board.row(comm.substring(0, 2));
                    col1 = Board.col(comm.substring(3, 5));
                    row1 = Board.row(comm.substring(3, 5));
                    Move move = Move.create(col0, row0, col1, row1);
                    if (getGame().getBoard().isLegal(move)) {
                        return move;
                    } else {
                        System.out.println("Illegal Move");
                        continue;
                    }
                } else {
                    System.out.println("Incorrect input, please try again.");
                }
                inp.nextLine();
            }
        } else {
            Move move = box.receive();
            if (move == null) {
                return makeMove();
            } else {
                return move;
            }
        }
    }
    /** Returns a move based on BOARD. */
    Move aiMakeMove(MutableBoard board) {
        Iterator<Move> iter = board.legalMoves();
        LinkedList<MoveTree> moves = new LinkedList<MoveTree>();
        while (iter.hasNext()) {
            moves.push(new MoveTree(iter.next()));
        }
        int randint = Math.round((randomSource.nextFloat()
                * (moves.size() - 1)));
        Move move = moves.get(randint).root();
        int depth = 3;
        if (getGame().timeRemaining(side()) < TEN) {
            depth = 2;
        }
        if (getGame().timeRemaining(side()) < FIVE) {
            depth = 1;
        }
        if (getGame().timeRemaining(side()) < TWO) {
            depth = 0;
        }
        while (!moves.isEmpty()) {
            MoveTree tree = moves.pop();
            for (int k = 0; k < depth; k += 1) {
                if (forcedWin(tree, k, board)) {
                    String t = side().toString().substring(0, 1).toUpperCase();
                    System.out.println(t
                            + "::" + tree.root());
                    return tree.root();
                }
            }
        }
        System.out.println(side().toString().substring(0, 1).toUpperCase()
                + "::" + move);
        return move;
    }
    /** Visits TREE and if not contigouos adds legal moves from
     * BOARD and returns false.
     */
    Boolean visitNode(MoveTree tree, MutableBoard board) {
        board.makeMove(tree.root());
        if (board.piecesContiguous(side())) {
            board.retract();
            return true;
        } else if (!board.piecesContiguous(side().opponent())) {
            Iterator<Move> iter = board.legalMoves();
            while (iter.hasNext()) {
                tree.add(iter.next());
            }
        }
        board.retract();
        return false;
    }
    /** Deptch traversal of TREE at LEVEL with BOARD. Returns true
     * if tree represents forced win. */
    Boolean forcedWin(MoveTree tree, int level, MutableBoard board) {
        boolean forcedwin = true;
        if (level == 0) {
            return visitNode(tree, board);
        } else {
            board.makeMove(tree.root());
            for (MoveTree child: tree.getChildren()) {
                forcedwin = forcedwin & forcedWin(child, level - 1, board);
            }
            if (tree.getChildren().isEmpty()) {
                board.retract();
                return false;
            }
            board.retract();
        }
        return forcedwin;
    }

    /** Sets stopwatch to WATCH0. */
    public void setWatch(Stopwatch watch0) {
        watch = watch0;
    }

    /** Random seed. */
    private Random randomSource;
    /** Msg box. */
    private QueuedMailBox box;
    /** True if AI has started. */
    private static boolean aiStarted = false;
    /**
     * @return the aiStarted
     */
    public static boolean isAiStarted() {
        return aiStarted;
    }
    /** Keeps track of time. */
    private  Stopwatch watch;

    /** if display is on. */
    private boolean displayOn;

    /**
     * Sets aiStarted to true.
     */
    public static void startAi() {
        MachinePlayer.aiStarted = true;
    }
}
