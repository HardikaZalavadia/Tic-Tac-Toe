package controller;

import exception.DuplicateSymbolException;
import exception.MorePlayersException;
import exception.MoreThanOneBotException;
import models.Game;
import models.Player;
import stretagies.winningStretagy.WinningStrategies;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players, List<WinningStrategies> winningStrategy)
            throws DuplicateSymbolException, MorePlayersException, MoreThanOneBotException {
                return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayer(players)
                    .setWinningStrategy(winningStrategy)
                    .build();
    }
    public void printBoard(Game game){
        game.printBoard();
    }
    public void makeMove(Game game){
        game.makeMove();
    }
    public void undo(Game game){
        game.undo();
    }
}
