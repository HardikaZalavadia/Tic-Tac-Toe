package stretagies.BotPlayingStrategy;

import models.DifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getStrategyByDifficultyLevel(DifficultyLevel difficultyLevel){
        if(DifficultyLevel.EASY.equals(difficultyLevel)){
            return new EasyBotPlayingStrategy();
        }
        else{
            return new MediumBotPlayingStrategy();
        }

    }
}
