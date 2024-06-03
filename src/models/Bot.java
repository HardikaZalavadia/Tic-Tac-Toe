package models;

import stretagies.BotPlayingStrategy.BotPlayingStrategy;
import stretagies.BotPlayingStrategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategies;

    public Bot(String name,PlayerType playerType,Long id, char symbol,DifficultyLevel difficultyLevel) {
        super(name, playerType, id, symbol);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategies = BotPlayingStrategyFactory.getStrategyByDifficultyLevel(difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}
