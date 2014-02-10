package loa;

import java.util.Random;

import ucb.util.Stopwatch;

/** Represents one game of Lines of Action.
 *  @author Daniel Paden Tomasello cs61b-bz */
class Game {
    /** Number of seconds in a minute. */
    private static final int SECONDS = 60;

    /** A new Game between NUMHUMAN0 humans and 2-NUMHUMAN AIs.  SIDE0
     *  indicates which side the first player (known as ``you'') is
     *  playing.  SEED0 is a random seed for random-number generation.
     *  TIME0 is the time limit each side has to make its moves (in seconds).
     *  and DISPLAY boolean value for if display is on.
     *  A TIME value of <=0 means there is no time limit.  A SEED value <= 0
     *  means to use a randomly seeded generator.
     */
    Game(int numHuman0, Side side0, long seed0, double time0, boolean display) {
        seed = seed0;
        blackWatch = new Stopwatch();
        whiteWatch = new Stopwatch();
        if (seed <= 0) {
            _randomSource = new Random();
        } else {
            _randomSource = new Random(seed);
        }
        side = side0;
        numHuman = numHuman0;
        Player player1, player2;
        box = new QueuedMailBox();
        if (numHuman0 == 2) {
            player1 = new HumanPlayer(side, this, display, box);
            player2 = new HumanPlayer(side.opponent(), this, display, box);
        } else if (numHuman0 == 1) {
            player1 = new HumanPlayer(side, this, display, box);
            player2 = new MachinePlayer(side.opponent(), this, display, box);
        } else {
            player1 = new MachinePlayer(side, this, display, box);
            player2 = new MachinePlayer(side.opponent(), this, display, box);
        }
        if (side0 == Side.WHITE) {
            whitePlayer = player1;
            blackPlayer = player2;
        } else {
            whitePlayer = player2;
            blackPlayer = player1;
        }
        displayOn = display;
        _board = new MutableBoard(Board.INITIAL_PIECES, Side.BLACK);
        time = time0 * SECONDS;
    }

    /** Return the current board. */
    MutableBoard getBoard() {
        return _board;
    }

    /** Return a move from the terminal.  Processes any intervening commands
     *  as well. */
    String getMove() {
        return "";
    }

    /** Play this game, printing any transcript and other results.
     * @throws InterruptedException */
    public void play() throws InterruptedException {
        Move move;
        if (displayOn) {
            GuiBoard gui = new GuiBoard(getBoard(), box);
            GuiCommand guicommand = new GuiCommand(_board, box);
            setOutput(guicommand.getLabel());
            while (!_board.gameOver()) {
                if (MachinePlayer.isAiStarted() | numHuman == 0) {
                    guiPlay(gui, guicommand);
                } else {
                    if (_board.turn() == Side.BLACK) {
                        move = blackPlayer.makeMove();
                        _board.makeMove(move);
                        setOutput(guicommand.getLabel());
                        guicommand.repaint();
                        gui.repaint();
                    } else {
                        move = whitePlayer.makeMove();
                        _board.makeMove(move);
                        setOutput(guicommand.getLabel());
                        gui.repaint();
                    }
                }
            }
        } else {
            while (!_board.gameOver()) {
                if (MachinePlayer.isAiStarted() | numHuman == 2) {
                    if (_board.turn() == Side.BLACK) {
                        blackWatch.start();
                        move = blackPlayer.makeMove();
                        blackWatch.stop();
                        if (blackWatch.getAccum() / THOUSAND > time) {
                            System.out.println("White wins.");
                            System.exit(0);
                        }
                        _board.makeMove(move);
                    } else if (MachinePlayer.isAiStarted() | numHuman == 2) {
                        whiteWatch.start();
                        move = whitePlayer.makeMove();
                        if (whiteWatch.getAccum() / THOUSAND > time) {
                            System.out.println("Black wins.");
                            System.exit(0);
                        }
                        _board.makeMove(move);
                        whiteWatch.stop();
                    }
                } else {
                    if (_board.turn() == Side.BLACK) {
                        move = blackPlayer.makeMove();
                    } else {
                        move = whitePlayer.makeMove();
                    }
                    _board.makeMove(move);
                }
            }
            winner();
        }
    }
    /** Sets OUTPUT text of Gui. */
    public void setOutput(Output output) {
        if (_board.turn() == Side.WHITE) {
            output.setText("<HTML>Turn: White<BR>"
                    + "Moves made: " + _board.movesMade() + "</HTML>");
        } else {
            output.setText("<HTML>Turn: Black<BR>"
                    + "Moves made: " + _board.movesMade() + "</HTML>");
        }
    }


    /** Declares winner. */
    private void winner() {
        if (_board.gameOver()) {
            Side side0 = null;
            if (_board.piecesContiguous(Side.WHITE)) {
                if (_board.piecesContiguous(Side.BLACK)) {
                    side0 = _board.turn();
                } else {
                    side0 = Side.WHITE;
                }
            } else if (_board.piecesContiguous(Side.BLACK)) {
                if (_board.piecesContiguous(Side.WHITE)) {
                    side0 = _board.turn();
                } else {
                    side0 = Side.BLACK;
                }
            }
            if (side0 == Side.WHITE) {
                System.out.println("White wins.");
                System.exit(0);
            } else {
                System.out.println("Black wins.");
                System.exit(0);
            }
        }
    }
    /**
     * @param gui Gui for game.
     * @param guicommand for guicommand.
     * @throws InterruptedException
     */
    private void guiPlay(GuiBoard gui,
            GuiCommand guicommand) throws InterruptedException {
        Move move;
        if (_board.turn() == Side.BLACK) {
            blackWatch.start();
            move = blackPlayer.makeMove();
            _board.makeMove(move);
            blackWatch.stop();
            gui.repaint();
            setOutput(guicommand.getLabel());
            guicommand.repaint();
            if (blackWatch.getAccum() / THOUSAND > time) {
                System.out.println(_board);
                System.out.println("White wins.");
                System.exit(0);
            }
        } else {
            whiteWatch.start();
            move = whitePlayer.makeMove();
            whiteWatch.stop();
            if (whiteWatch.getAccum() / THOUSAND > time) {
                System.out.println(_board);
                System.out.println("Black wins.");
                System.exit(0);
            }
            _board.makeMove(move);
            gui.repaint();
            setOutput(guicommand.getLabel());
            guicommand.repaint();
            gui.repaint();
        }
    }


    /** Return time remaining for SIDE0 (in seconds). */
    double timeRemaining(Side side0) {
        if (side0 == Side.WHITE) {
            return time - (whiteWatch.getAccum() / THOUSAND);
        } else {
            return time - (blackWatch.getAccum() / THOUSAND);
        }
    }

    /** Return the random number generator for this game. */
    Random getRandomSource() {
        return _randomSource;
    }

    /** Prints board. */
    public void printBoard() {
        _board.toString();
    }

    /** The official game board. */
    private MutableBoard _board;

    /** A source of random numbers, primed to deliver the same sequence in
     *  any Game with the same seed value. */
    private Random _randomSource;
    /** Message passer. */
    private QueuedMailBox box;


    /** numHuman is number of human players. Time is time limit. */
    private int numHuman;
    /** time is Time limit. */
    private double time;
    /** Random generator seed. */
    private long seed;
    /** Side of first player. */
    private Side side;

    /**Players of the game. */
    private Player blackPlayer, whitePlayer;
    /** True if display is on. */
    private boolean displayOn;

    /** Stopwatch for player1 and 2 respectively. */
    private Stopwatch blackWatch, whiteWatch;

    /** MAGIC number for 1000. */
    static final double THOUSAND = 1000;

}
