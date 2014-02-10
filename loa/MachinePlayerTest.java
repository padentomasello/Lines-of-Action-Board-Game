package loa;

import static loa.Piece.BP;
import static loa.Piece.EMP;
import static loa.Piece.WP;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import org.junit.Test;

import ucb.util.Stopwatch;

public class MachinePlayerTest {

    @Test
    public void test() {
        Piece[][] pieces = {
            { EMP, WP, EMP, EMP, EMP, EMP, EMP,  EMP},
            { EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
            { EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
            { EMP,  BP,  WP, EMP,  BP, EMP, EMP, EMP},
            { EMP,  WP,  BP,  WP,  WP, EMP, EMP, EMP},
            { EMP, EMP,  BP,  BP,  WP,  WP,  WP, EMP},
            { EMP,  WP,  WP,  WP, EMP, EMP, EMP, EMP},
            { EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
        };
        MutableBoard board8 = new MutableBoard(pieces, Side.WHITE);
        Game game = new Game(1, Side.WHITE, 30, 500, false);
        MachinePlayer ai = new MachinePlayer(Side.WHITE, game, false, null);
        Move move = ai.aiMakeMove(board8);
        System.out.println("");
        Move lastmove = Move.create(2, 1, 5, 4);
        assertEquals("IsLegal Move not functioning correctly", lastmove, move);
        Piece[][] pieces2 = {
            { EMP, EMP, WP, EMP, EMP, EMP, EMP,  EMP},
            { EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
            { EMP, EMP, EMP, EMP, EMP, EMP, EMP, EMP},
            { EMP,  WP,  WP, WP, EMP, EMP, EMP, EMP},
            { EMP,  WP,  BP,  WP,  WP, WP, EMP, EMP},
            { EMP, WP,  EMP, BP,  WP,  WP,  WP, EMP},
            { EMP,  WP,  WP, WP, WP, EMP, EMP, EMP},
            { EMP, EMP, BP, WP, BP, WP, EMP, EMP},
        };
        board8 = new MutableBoard(pieces2, Side.WHITE);
        System.out.println(board8);
        ai = new MachinePlayer(Side.WHITE, game, false, null);
        move = ai.aiMakeMove(board8);
        System.out.println("");
        Move secondlastmove = Move.create(3, 4, 6, 7);
        assertEquals("IsLegal Move not functioning correctly",
                secondlastmove, move);
        board8.makeMove(move);
        move = ai.aiMakeMove(board8);
        lastmove = Move.create(3, 5, 3, 1);
        assertEquals("IsLegal Move not functioning correctly", lastmove, move);
        board8.makeMove(move);
        System.out.println(board8);
        MutableBoard board1 = new MutableBoard();
        Stopwatch watch = new Stopwatch();
        watch.start();
        move = ai.aiMakeMove(board1);
        watch.stop();
        System.out.println("time:" + watch.getAccum());
    }

    @Test
    /** Not really a unit test. Used for dubugging. */
    public void test4() throws IOException {
        MutableBoard board = new MutableBoard();
        FileReader reader = new FileReader("test4.inp");
        BufferedReader breader = new BufferedReader(reader);
        System.out.println(breader.readLine());
        System.out.println(breader.readLine());
        System.out.println(breader.readLine());
        System.out.println(breader.readLine());
        for (int i = 0; i < 48; i += 1) {
            Move move = Move.create(breader.readLine());
            System.out.println(move);
            board.makeMove(move);
        }
        System.out.println(board);
        board.makeMove(Move.create("a3-b3"));
        System.out.println(board);
        Game game = new Game(1, Side.BLACK, 30, 500, false);
        MachinePlayer ai = new MachinePlayer(Side.BLACK, game, false, null);
        Move move = ai.aiMakeMove(board);
        System.out.println(move);
        board.makeMove(move);
        move = ai.aiMakeMove(board);
        System.out.println(move);
        board.makeMove(move);
        move = ai.aiMakeMove(board);
        System.out.println(move);
        board.makeMove(move);
        move = ai.aiMakeMove(board);
        System.out.println(move);
        board.makeMove(move);
    }
    @Test
    public void winnable1test() throws IOException {
        MutableBoard board = new MutableBoard();
        FileReader reader = new FileReader("winnable1.inp");
        BufferedReader breader = new BufferedReader(reader);
        System.out.println(breader.readLine());
        System.out.println(breader.readLine());
        System.out.println(breader.readLine());
        System.out.println(breader.readLine());
        for (int i = 0; i < 57; i += 1) {
            Move move = Move.create(breader.readLine());
            System.out.println(move);
            board.makeMove(move);
        }
        System.out.println(board);
        Game game = new Game(1, Side.WHITE, 30, 500, false);
        MachinePlayer ai = new MachinePlayer(Side.WHITE, game, false, null);
        Move move = ai.aiMakeMove(board);
        System.out.println(move);
        board.makeMove(move);
    }

}
