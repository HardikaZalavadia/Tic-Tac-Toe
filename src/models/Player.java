package models;

import java.util.Scanner;

public class Player {
    private String name;
    private PlayerType playerType;
    private long id;
    private char symbol;
    private Scanner scanner;
    public Player(String name, PlayerType playerType, long id, char symbol) {
        this.name = name;
        this.playerType = playerType;
        this.id = id;
        this.symbol = symbol;
        scanner = new Scanner(System.in);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public long getId() {
        return id;
    }

    public char getSymbol() {
        return symbol;
    }
    public Cell makeMove(Board board) {
        System.out.println(this.name+"'s tern to make move. Enter row and colman: ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while(validateMove(row,col,board)==false){
            System.out.println(this.name +" Move is invalid please try again..");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }
        Cell cell = board.getCell().get(row).get(col);
        cell.setCellState(CellState.FILL);
        cell.setPlayer(this);
        return cell;
    }
    private boolean validateMove(int row, int col, Board board) {
        if(row>=board.getSize() || row<0){
            return false;
        }
        if(col>=board.getSize() || col<0){
            return false;
        }
        if(CellState.FILL.equals(board.getCell().get(row).get(col).getCellState())){
            return false;
        }
        return true;
    }
}
