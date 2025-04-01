
/**
 * A tic-tac-toe cell, it can be either an empty cell, contain an x or a circle.
 * A non-empty cell will have a number lives that when they reach zero, the cell will
 * become empty again.
 *
 * @author Simon
 * @version 0.1
 */
public class Cell
{
    /*
     * The following are the valid contents a cell can have
     * Use this as values.
     */
    private static final String EMPTY = "";
    private static final String X = "X";
    private static final String CIRCLE = "O";
    
    /*
     * This is the default value for max lives, you can
     * use this value if a value for max lives has not been
     * provided.
     */
    private static final int DEFAULT_MAX_LIVES = 3;
    
    private String content;
    private int lives;
    private int maxLives;

    /**
     * Constructs a new empty cell.
     */
    public Cell()
    {
        content = EMPTY;
        lives = DEFAULT_MAX_LIVES;
        maxLives = DEFAULT_MAX_LIVES;
    }
    
    /**
     * Constructs a new empty cell with a given number of lives.
     * 
     * @param maximumLives the maximum number of lives this cell can have.
     * preconditions:
     * <ul>
     * <li>maximumLives must be greater or equal than one (1).</li>
     * </ul>
     */
    public Cell(int maximumLives)
    {
        assert maximumLives >= 1 : "Please enter the number greater or equal to 1";
        content = EMPTY;
        lives = maximumLives;
        maxLives = maximumLives;
    }

    /**
     * @return true iff this cell is empty.
     */
    public boolean isEmpty()
    {
        return content.equals(EMPTY);
    }
    
    /**
     * @return true iff this cell contains a circle.
     */
    public boolean isCircle()
    {
        return content.equals(CIRCLE);
    }
    
    /**
     * @return true iff this cell contains an x.
     */
    public boolean isX()
    {
        return content.equals(X);
    }
    
    /**
     * Clears the cell, i.e.: makes this cell empty.
     * This also restores the lives of the cell to the max lives value.
     */
    public void clearCell()
    {
        content = EMPTY;
        lives = maxLives;
    }
    
    /**
     * Puts an X into this cell.
     */
    public void x()
    {
        assert content == EMPTY: "This cell is not empty.";
        content = X;
    }
    
    /**
     * Puts an O into this cell.
     */
    public void o()
    {
        assert content == EMPTY: "This cell is not empty.";
        content = CIRCLE;
    }
    
    /**
     * Decreases the lives of this cell, if the cell is out of lives
     * i.e., lives become zero (0), the cell should be cleared.
     * preconditions:
     * <ul>
     * <li>The cell must not be out of lives or empty.</li>
     * </ul>
     */
    public void decreaseLives()
    {
        assert !isEmpty() : "The cells must be empty.";
        assert lives > 0 : "The cell is out of lives";
        if(!isEmpty())
        {
            lives = lives - 1;
            if(lives == 0){
                clearCell();
            }
        }
    }
    
    /**
     * @return the representation of this cell as either " ", "X", "O".
     */
    @Override
    public String toString()
    {
        if (isEmpty()) {
            return "  ";
        } else if (isX()) {
            return "X" + lives;
        } else {
            return "O" + lives;
        }
    }
    
}
