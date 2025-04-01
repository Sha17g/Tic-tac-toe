import java.util.ArrayList;

/**
 * This class represents a game board of tic-tac-toe.
 *
 * @author Simon
 * @version 0.1
 */
public class Board
{ 
    private ArrayList<ArrayList<Cell>> board;

    /**
     * Construct a new empty board.
     * All cells will be empty and with the default number of lives.
     */
    public Board()
    {
        board = new ArrayList<ArrayList<Cell>>(); //3 by 3 board
        for (int row = 0; row < 3; row++) {
            board.add(new ArrayList<Cell>()); //adds new row of cells
            for (int col = 0; col < 3; col++) {
                board.get(row).add(new Cell()); //adds a new cell to a column
            }
        }
    }
    
    /**
     * Construct a new empty board.
     * All cells will be empty and with a specific number of lives.
     * @param maxCellLives the number of lives for each cell.
     * preconditions:
     * <ul>
     * <li>maxCellLives must be greater than zero (0).</li>
     * </ul>
     */
    public Board(int maxCellLives)
    {
        assert maxCellLives > 0 : "maxCellLives must greater than 0";
        assert maxCellLives <= 9 : "maxCellLives must less or equal to 9";
        board = new ArrayList<ArrayList<Cell>>(); //3 by 3 board
        for (int row = 0; row < 3; row++) {
            board.add(new ArrayList<Cell>()); //adds new row of cells
            for (int col = 0; col < 3; col++) {
                board.get(row).add(new Cell(maxCellLives));
            }
        }
    }
    
    /**
     * Puts an X into a specific cell.
     * Every other cell must have their lives decreased by one.
     * @param row the row where to put the X.
     * @param col the column where to put the X.
     * preconditions:
     * <ul>
     * <li>The game must not be finished</li>
     * <li>The cell must be empty.</li>
     * <li>The row must be greater or equal than zero (0).</li>
     * <li>The row must be less than three (3).</li>
     * <li>The column must be greater or equal than zero (0).</li>
     * <li>The column must be less than three (3).</li>
     * </ul>
     */
    public void x(int row, int col)
    {
        assert isGameFinished() == false : "The game is finished.";
        assert board.get(row).get(col).toString() == "  " : "The cell is not empty now.";
        assert row >= 0 & row <3 & col >=0 & row <3 : "The row and column must greater or equal to 0 and less than 3.";
        
        skipTurn();
        board.get(row).get(col).x();
    }
    
    /**
     * Puts a circle into a specific cell.
     * Every other cell must have their lives decreased by one.
     * @param row the row where to put the circle.
     * @param col the column where to put the circle.
     * preconditions:
     * <ul>
     * <li>The game must not be finished</li>
     * <li>The cell must be empty.</li>
     * <li>The row must be greater or equal than zero (0).</li>
     * <li>The row must be less than three (3).</li>
     * <li>The column must be greater or equal than zero (0).</li>
     * <li>The column must be less than three (3).</li>
     * </ul>
     */
    public void o(int row, int col)
    {
        assert isGameFinished() == false : "The game is finished.";
        assert board.get(row).get(col).toString().equals("  ") : "The cell is not empty now.";
        assert row >= 0 & row <3 & col >=0 & row <3 : "The row and column must greater or equal to 0 and less than 3.";
        
        skipTurn();
        
        board.get(row).get(col).o();
        
    }
    
    /**
     * Decreases the lives of every cell.
     */
    public void skipTurn()
    {
        for (ArrayList<Cell> row : board) {
            for (Cell cell : row) {
               if(cell.toString() != "  "){
                    cell.decreaseLives();            
               }
        }
    }
    }
    /**
     * @return true iff the game was won by x or circle.
     */
    
    public boolean isGameFinished()
    {
        return xWon() || oWon();
    }
    
    /**
     * @return true iff x won the game.
     */
    public boolean xWon()
    {
        boolean xLineFound = false;
        for (ArrayList<Cell> line : getAllLines()) {
            if (allTheSameWithX(line)) {
                xLineFound = true;
            }
        }
        return xLineFound;
    }
    
    /*
     * Returns true iff all cells in a line have Xs.
     * preconditions:
     * - line is not null
     * - line has size 3
     */
    private boolean allTheSameWithX(ArrayList<Cell> line) {
        assert line != null: "line can't be null";
        assert line.size() == 3: "line must have exactly 3 cells";
        return line.get(0).isX() && line.get(1).isX() && line.get(2).isX();
    }
    
    /**
     * @return true iff circle won the game.
     */
    public boolean oWon()
    {
        boolean circleLineFound = false;
        for (ArrayList<Cell> line : getAllLines()) {
            if (allTheSameWithCircle(line)) {
                circleLineFound = true;
            }
        }
        return circleLineFound;
    }
    
    /*
     * Returns true iff all cells in a line have Circles.
     * preconditions:
     * - line is not null
     * - line has size 3
     */
    private boolean allTheSameWithCircle(ArrayList<Cell> line) {
        assert line != null: "line can't be null";
        assert line.size() == 3: "line must have exactly 3 cells";
        
        return  line.get(0).isCircle()
                && line.get(1).isCircle()
                && line.get(2).isCircle();
    }
    
    /*
     * Returns all posible lines of the game.
     * There are 3 vertical lines, 3 horizontal lines, and 
     * 2 diagonal lines.
     */
    private ArrayList<ArrayList<Cell>> getAllLines()
    {
        ArrayList<ArrayList<Cell>> lines = new ArrayList<ArrayList<Cell>>();
        for (int col = 0; col < 3; col++) {
            lines.add(getVerticalLine(col));
        }
        for (int row = 0; row < 3; row++) {
            lines.add(getHorizontalLine(row));
        }
        lines.addAll(getDiagonalLines());
        return lines;
    }
    
    /*
     * Returns a vertical line given a specific column.
     * preconditions:
     * <ul>
     * <li>col must be greater or equal than zero (0).</li>
     * <li>col must be less than three (3).</li>
     * </ul>
     */
    private ArrayList<Cell> getVerticalLine(int col)
    {
        assert col >=0 & col < 3 : "col must be greater or equal than zero or less than 3.";
        
        ArrayList<Cell> verticalLine = new ArrayList<Cell>();
        for (ArrayList<Cell> row : board) {
            verticalLine.add(row.get(col));
        }
        return verticalLine;
    }
    
    /*
     * Returns an horizontal line given a specific row.
     * preconditions:
     * <ul>
     * <li>row must be greater or equal than zero (0).</li>
     * <li>row must be less than three (3).</li>
     * </ul>
     */
    private ArrayList<Cell> getHorizontalLine(int row)
    {
        assert row >=0 & row < 3 : "row must be greater or equal than zero or less than 3.";
        
        ArrayList<Cell> horizontalLine = new ArrayList<Cell>();
        for (Cell cell : board.get(row)) {
            horizontalLine.add(cell);
        }
        return horizontalLine;
    }
    
    /*
     * Returns the two horizontal lines.
     */
    private ArrayList<ArrayList<Cell>> getDiagonalLines()
    {
        ArrayList<ArrayList<Cell>> diagonalLines = new ArrayList<ArrayList<Cell>>();
        ArrayList<Cell> upperLeftToBottomRight = new ArrayList<Cell>();
        upperLeftToBottomRight.add(board.get(0).get(0));
        upperLeftToBottomRight.add(board.get(1).get(1));
        upperLeftToBottomRight.add(board.get(2).get(2));
        ArrayList<Cell> bottomLeftToUpperRight = new ArrayList<Cell>();
        bottomLeftToUpperRight.add(board.get(2).get(0));
        bottomLeftToUpperRight.add(board.get(1).get(1));
        bottomLeftToUpperRight.add(board.get(0).get(2));
        diagonalLines.add(upperLeftToBottomRight);
        diagonalLines.add(bottomLeftToUpperRight);
        return diagonalLines;
    }
    
    /**
     * @return a representation of this board.
     */
    @Override
    public String toString()
    {
        String rep = "|";
        for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
            rep += " " + columnIndex + "|";
        }
        rep += "\n";
        int rowIndex = 0;
        for (ArrayList<Cell> row : board) {
            rep += "|";
            for (Cell cell : row) {
                if(cell.toString() != ""){
                rep += cell.toString() + "|";
            }else{
                rep += "  " + "|";
            }
            }
            rep += rowIndex + "|\n";
            rowIndex++;
        }
        return rep;
    }
    
    /*
     * Get cell from the determined rows and columns.
     * to avoid some unknown problem when we use .get method in the TicTactoegame class.
     */
    public Cell getCell(int row, int col) {
        return board.get(row).get(col);
    }
}
