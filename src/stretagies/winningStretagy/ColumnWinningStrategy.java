package stretagies.winningStretagy;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategies{
    Map<Integer, Map<Character,Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        if(!colMap.containsKey(col)){
            colMap.put(col,new HashMap<>());
        }
        Map<Character,Integer> map = colMap.get(col);
        char symbol = move.getPlayer().getSymbol();
        if(!map.containsKey(symbol)){
            map.put(symbol,0);
        }
        map.put(symbol,map.get(symbol)+1);

        if(board.getCell().size() == map.get(symbol)){
            System.out.println("winner by col" + col);
            return true;
        }
        return false;
    }

    @Override
    public void undo(Move lastMove, Board board) {
        int col = lastMove.getCell().getCol();
        char symbol = lastMove.getPlayer().getSymbol();
        Map<Character,Integer> map = colMap.get(col);
        map.put(symbol,map.get(symbol) - 1);
    }
}
