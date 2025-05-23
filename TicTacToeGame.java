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
     * maxLivesPerCell must be greater than zero (0),and better greater than 3 and less or equal than 9.
     */
    public TicTacToeGame(int maxLivesPerCell)
    {
        assert maxLivesPerCell > 0: "maxLivesPerCell must be greater than 0.";
        assert maxLivesPerCell <= 9: "maxLivesPerCell must be less or equal to 9.";
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
        System.out.print(toString());
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
         assert board.getCell(row,col).toString().equals("  "):"The cell is not empty now";
         
         
         if(currentPlayer == 1)
         {
             board.x(row,col);
             currentPlayer = 2;
         }else
         {
             board.o(row,col);
             currentPlayer = 1;
            }
         System.out.print(toString());
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
        if(gameEnded()){
            if(playerOneXWon()){
                return "Game finished, player one won!\n" + board.toString();
            }else{
                return "Game finished, player two won!\n" + board.toString();
            }
        }else{
            if(currentPlayer == 1){
                return "Player one (x) is playing.\n" + board.toString();
            }else{
                return "Player two (circle) is playing.\n" + board.toString();
            }
        }
    }
}

