package models;

import exception.DuplicateSymbolException;
import exception.MorePlayersException;
import exception.MoreThanOneBotException;
import stretagies.winningStretagy.WinningStrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategies> winningStrategy;
    private Player winner;

    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    //Here I am going to use Builder design pattern for some validations, so make constructor private
    private Game(int dimension, List<Player> players, List<WinningStrategies> winningStrategy) {
        this.board = new Board(dimension);
        this.winningStrategy = winningStrategy;
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
    }
    public static Builder getBuilder(){
        return new Builder();
    }


    public void printBoard(){
        board.printBoard();
    }
    public void makeMove(){
        Player nextplayer = players.get(nextPlayerIndex);
        Cell cell = nextplayer.makeMove(board);

        Move move = new Move(nextplayer, cell);
        moves.add(move);

        if(checkFinalWinner(board,move)){
            gameState = GameState.SUCCESS;
            winner = nextplayer;
            return;
        }
        if(moves.size() == board.getSize() * board.getSize()){
            gameState = GameState.DRAW;
            return;
        }
        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();
    }
    private boolean checkFinalWinner(Board board, Move move) {
        for(WinningStrategies winningStrategy : winningStrategy){
            if(winningStrategy.checkWinner(board,move)){
                return true;
            }
        }
        return false;
    }

    public void undo(){
        Move lastMove = removeLastMove();
        if(lastMove==null){
            return;
        }
        updateCellAndStrategy(lastMove);
        updateNextPlayer();
    }
    private Move removeLastMove() {
        if(moves.size()==0){
            System.out.println("no move to undo....");
            return null;
        }
        Move lastMove = moves.get(moves.size()-1);
        moves.remove(lastMove);
        return lastMove;
    }
    private void updateNextPlayer() {
        if(nextPlayerIndex != 0){
            nextPlayerIndex--;
        }
        else{
            nextPlayerIndex = players.size()-1;
        }
    }

    private void updateCellAndStrategy(Move lastMove) {
        Cell cell = lastMove.getCell();
        cell.setCellState(CellState.EMPTY);
        cell.setPlayer(null);

        for(WinningStrategies winningStrategy : winningStrategy){
            winningStrategy.undo(lastMove,board);
        }
    }



    //Builder design pattern
    public static class Builder {
        private List<WinningStrategies> winningStrategy;
        private List<Player> player;
        private int dimension;

        private Builder() {
            this.dimension = 0;
            this.player = new ArrayList<>();
            this.winningStrategy = new ArrayList<>();
        }

        public Game build() throws MoreThanOneBotException, MorePlayersException, DuplicateSymbolException {
            // validate bot count, dimension and number of players, unique symbol
            validateBotCount();
            validateDimensionNumberOfPlayers();
            validateSymbol();
            return new Game(dimension, player, winningStrategy);
        }

        private void validateSymbol() throws DuplicateSymbolException {
            Set<Character> set = new HashSet<>();
            for (Player players : player) {
                set.add(players.getSymbol());
            }
            if (set.size() < player.size()) {
                throw new DuplicateSymbolException("Every Players should have different symbol");
            }
        }

        private void validateDimensionNumberOfPlayers() throws MorePlayersException {
            if (player.size() != dimension - 1) {
                throw new MorePlayersException("Enter " + (dimension - 1) + " players");
            }
        }

        private void validateBotCount() throws MoreThanOneBotException {
            int botCount = 0;
            for (Player players : player) {
                if (players.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if (botCount > 1) {
                throw new MoreThanOneBotException("There should be only one Bot allowed");
            }
        }

        public Builder setWinningStrategy(List<WinningStrategies> winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;
        }

        public Builder setPlayer(List<Player> player) {
            this.player = player;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
    public GameState getGameState() {
        return gameState;
    }
}
