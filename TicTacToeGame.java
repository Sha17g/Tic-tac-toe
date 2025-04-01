import java.util.ArrayList;

/**
 * This class represents a Tic-Tac-Toe Game.
 *
 * @author Simon
 * @version 0.1
 */
public class TicTacToeGame
{
    private static final int PLAYER_ONE_X = 1;
    private static final int PLAYER_TWO_CIRCLE = 2;
    
    private Board board;
    //The current player, should change after each turn.
    private int currentPlayer;
    
    /**
     * Constructs a new tic-tac-toe game with the default lives per cell.
     */
    public TicTacToeGame()
    {
        board = new Board();
        currentPlayer = PLAYER_ONE_X;
    }
    
    /**
     * Constructs a new tic-tac-toe game with a specific value as lives per cell.
     * @param maxLivesPerCell the max lives per cell.
     * preconditions:
     * <ul>
     * <li>maxLivesPerCell must be greater than zero (0).</li>
     * </ul>
     */
    public TicTacToeGame(int maxLivesPerCell)
    {
        board = new Board(maxLivesPerCell);
        currentPlayer = PLAYER_ONE_X;    
    }
    
    /**
     * The current player skips the turn and the turn changes to the other player.
     * preconditions:
     * <ul>
     * <li>The game must not have finished.</li>
     * </ul>
     */
    public void skipTurn()
    {
        assert !gameEnded():"The game is ended.";
        if(currentPlayer == 1)
        {
            currentPlayer = 2;
        }else{
            currentPlayer = 1;
        }
        board.skipTurn();
        System.out.print(board.toString());
    }
    
    /**
     * Puts an x or a circle, depending on which player is playing, on 
     * a given position. The turn changes to the other player.
     * preconditions:
     * <ul>
     * <li>The game must not have finished.</li>
     * <li>row must be greater or equal than zero (0).</li>
     * <li>row must be less than three (3).</li>
     * <li>col must be greater or equal than zero (0).</li>
     * <li>col must be less than three (3).</li>
     * <li>The cell at the given position must be empty.</li>
     * </ul>
     */
    public void playAtPosition(int row, int col)
    {
         assert !gameEnded():"The game is ended.";
         assert row >= 0 & row <=3 & col >=0 & col <=3:"The row and col must be greater or equal than 0 and less or equal to 3.";
         /*Test = board.getCell(row,col).toString(); */
         assert board.getCell(row,col).toString() == "  ":"The cell is not empty now";
         if(currentPlayer == 1)
         {
             board.x(row,col);
             currentPlayer = 2;
         }else
         {
             board.o(row,col);
             currentPlayer = 1;
            }
         System.out.print(board.toString());
    }
    
    /**
     * @return true iff the game has ended.
     */
    public boolean gameEnded()
    {
        if(board.isGameFinished()){
            return true;
        }
        return false;
    }
    /**
     * @return true iff player one, with x, won the game.
     */
    public boolean playerOneXWon()
    {
        if(board.xWon()){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * @return true iff player two, with circle, won the game.
     */
    public boolean playerTwoCircleWon()
    {
        if(board.oWon()){
            return true;
        }else{
            return false;
        }

    }


    /**
     * Returns a representation of the game.
     * The representation will be as follows:
     * <p>
     * The board representation followed by one of these lines:
     * <ul>
     * <li>"Player one (x) is playing", if the x player is playing.</li>
     * <li>"Player two (circle) is playing", if the circle player is playing.</li>
     * <li>"Game finished, player <player who won> won!", if the game ended with a win.</li>
     * </ul>
     */

    @Override 
    public String toString() {
        String thefinal = "";
        thefinal += board ;
        thefinal += "---------------";
        //return the board

        if (gameEnded()) {
            if (playerOneXWon()) {
                thefinal += "Player 1 win!";
                return thefinal;
            } else {
                thefinal += "Player 2 win!";
                return thefinal;
            }
        }
        //justyfy who won

        if (currentPlayer == PLAYER_ONE_X)   {
            thefinal += "Player 1 is Thinking";
            return thefinal;
        }
        else {
            thefinal += "Player 2 is Thinking";
            return thefinal;
        }
        //justyfy who is going to drop a piece
    }
}

