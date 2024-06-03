package stretagies.winningStretagy;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategies{
    Map<Character, Integer> leftDiagonal = new HashMap<>();
    Map<Character,Integer> rightDiagonal = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {

        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();
        if(col==row) {
            if (!leftDiagonal.containsKey(symbol)) {
                leftDiagonal.put(symbol, 0);
            }
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);
            if (board.getSize() == leftDiagonal.get(symbol)) {
                System.out.println("winner by left Diagonal");
                return true;
            }

        }
        if(row+col == board.getSize()-1){
            if (!rightDiagonal.containsKey(symbol)) {
                rightDiagonal.put(symbol,0);
            }
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);
            if (board.getSize() == rightDiagonal.get(symbol)) {
                System.out.println("winner by right Diagonal");
                return true;
            }
        }
        return false;
    }

    @Override
    public void undo(Move move, Board board) {
        int col = move.getCell().getCol();
        int row = move.getCell().getRow();
        char symbol = move.getPlayer().getSymbol();
        if(row==col){
            leftDiagonal.put(symbol,leftDiagonal.get(symbol)-1);
        }
        if(row+col == board.getSize()-1){
            rightDiagonal.put(symbol,rightDiagonal.get(symbol)-1);
        }
    }
}
