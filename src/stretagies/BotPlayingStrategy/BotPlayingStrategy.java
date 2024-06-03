package stretagies.BotPlayingStrategy;

import models.Board;
import models.Cell;

public interface BotPlayingStrategy {
    public Cell makeMove(Board board);
}
