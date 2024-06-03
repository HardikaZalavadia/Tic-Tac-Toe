package stretagies.winningStretagy;

import models.Board;
import models.Move;

public interface WinningStrategies {
    public boolean checkWinner(Board board, Move move);
    void undo(Move move,Board board);
}
