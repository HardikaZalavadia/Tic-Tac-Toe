package AppRunner;

import controller.GameController;
import exception.DuplicateSymbolException;
import exception.MorePlayersException;
import exception.MoreThanOneBotException;
import models.*;
import stretagies.winningStretagy.ColumnWinningStrategy;
import stretagies.winningStretagy.DiagonalWinningStrategy;
import stretagies.winningStretagy.RowWinningStrategy;
import stretagies.winningStretagy.WinningStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws DuplicateSymbolException, MorePlayersException, MoreThanOneBotException {

        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimension = 3;
        List<Player> players = new ArrayList<>();
        List<WinningStrategies> winningStrategies = new ArrayList<>();

        players.add(new Player("Niv", PlayerType.HUMAN, 1L, 'X'));
        players.add(new Bot("Bot", PlayerType.BOT,2L,'0', DifficultyLevel.EASY));

        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(dimension,players,winningStrategies);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            game.printBoard();
            System.out.println("Dose any one wants to undo ? y/n");
            String undo = scanner.next();
            if(undo.equalsIgnoreCase("y")){
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }

        // If I'm here, it means game is not in progress anymore
        if(game.getGameState().equals(GameState.SUCCESS)){
            System.out.println(game.getWinner().getName()+", Congrats! You won the Game :)");
        }
        if(game.getGameState().equals(GameState.DRAW)){
            System.out.println("Opps!! Match is tied :|");
        }

    }
}
