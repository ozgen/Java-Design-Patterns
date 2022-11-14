package templatemethod;


abstract class Game {
    protected int currentPlayer;
    protected final int numOfPlayer;

    public Game(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public void run() {

        start();
        while (!havingWinner()) {
            takeTurn();
        }
        System.out.println("Player " + getWinningPlayer() + " wins!");
    }

    protected abstract int getWinningPlayer();

    protected abstract void takeTurn();

    protected abstract boolean havingWinner();

    protected abstract void start();
}

class Chess extends Game {

    private int maxTurns = 10;
    private int turn = 1;

    public Chess() {
        super(2);
    }


    @Override
    protected int getWinningPlayer() {
        return 0;
    }

    @Override
    protected void takeTurn() {
        System.out.println("Turn " + turn++ + " taken by player " + currentPlayer);
        currentPlayer = (currentPlayer + 1) % numOfPlayer;
    }

    @Override
    protected boolean havingWinner() {
        return turn == maxTurns;
    }

    @Override
    protected void start() {
        System.out.println("Starting the game of Chess.");
    }
}

public class TemplateMethodDemo {

    public static void main(String[] args) {
        new Chess().run();
    }
}
