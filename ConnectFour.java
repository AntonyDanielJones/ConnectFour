package connect.four;

/**
 * A simple class for playing Connect Four. Allows the use of a default 6x7
 * grid or a custom-sized grid.
 * @author Daniel Jones
 * @version 0.9
 * Last revision: 14/04/2018
 */
public class ConnectFour {
    // final variables for both colours
    public final int YELLOW_COIN = 1;
    public final int RED_COIN = 2;    
    
    // 2D array which holds a mixture of coins (1's and 2's) and empty spaces (0's)
    private int[][] grid;
    // row and column sizes (6x7 grid as standard)
    private int rows = 6;
    private int columns = 7;

    /**
     * Default constructor with standard grid size
     */
    public ConnectFour() {
        // set up empty 6x7 grid
        grid = new int[rows][columns];
    }
    
    /**
     * Custom grid size constructor
     * @param rows the number of rows in the grid. Minimum allowed is 4.
     * @param columns the number of columns in the grid. Minimum allowed is 4.
     */
    public ConnectFour(int rows, int columns) {
        // Ensure size is at least 4x4
        if (rows < 4)
            rows = 4;
        if (columns < 4)
            columns = 4;
        
        // change class values
        this.rows = rows;
        this.columns = columns;

        // set up empty custom-size grid
        grid = new int[rows][columns];
    }
    
    /**
     * Sets all values in the grid to 0.
     */
    public void clearBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = 0;
            }
        }
    }
    
    /**
     * Place a given coin at the last available slot of a given column.
     * @param colour The colour of the coin. 1 is yellow, 2 is red. Defaults to
     *               1 if outside range.
     * @param column The index of the column. Defaults to 0 if outside range.
     */
    public void placeCoin(int colour, int column) {
        // VALIDATION -- adopt default behaviours if params are invalid
        // colour should be either 1 or 2
        if (colour != 1 && colour != 2) {
            colour = 1; // set to 1 by default
        }
        // column must exist
        if (column < 0 || column >= columns) {
            column = 0; // set to first column by default
        }
        
        // determine where the coin should be placed
        // start at top of grid
        int row = rows - 1;
        // keep moving down the grid while there's free space
        while (grid[row][column] != 0) {
            row--;
        }
        
        // place the coin 
        grid[row][column] = colour;
    }
    
    /**
     * Prints a text representation of the board with current coin positions.
     */
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // print the contents of each position of the grid
                System.out.print("| " + grid[i][j] + " ");
            }
            
            // print row seperators
            String rowSeperators = "|\n  -   ";
            for (int k = 0; k < columns - 1; k++)
                rowSeperators += "-   ";
            rowSeperators += "\n";
            System.out.print(rowSeperators);
        }
    }
    
    /**
     * Get method for grid array.
     * @return the grid array which holds coin positions
     */
    public int[][] getGrid() {
        return grid;
    }
    
    /**
     * Checks grid for any instances of four consecutive 1's or 2's.
     * @return 1 if there are four consecutive 1's, 2 if there are four 
     * consecutive 2's, 0 if neither.
     */
    public int checkWin() {
        // check each direction for four consecutive 1's
        if (checkNE(1) || checkE(1) || checkSE(1) || checkS(1))
            return 1; // yellow wins
        
        // check each direction for four consecutive 2's
        if (checkNE(2) || checkE(2) || checkSE(2) || checkS(2))
            return 2; // red wins
        
        // no win
        return 0;
    }
    
    // Helper methods for checkWin()
    /**
     * Checks the grid for four consecutive instances of a given colour in a 
     * north-easterly direction.
     * @param colour the colour to check for. 1 is yellow, 2 is red.
     * @return true if there are four consecutive instances 
     */
    public boolean checkNE(int colour) {
        for (int i = 3; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (grid[i][j] == colour && grid[i-1][j+1] == colour &&
                        grid[i-2][j+2] == colour && grid[i-3][j+3] == colour) {
                    // north-easterly win
                    return true;
                }
            }
        }
        // no north-easterly win
        return false;
    }

    /**
     * Checks the grid for four consecutive instances of a given colour in an 
     * easterly direction.
     * @param colour the colour to check for. 1 is yellow, 2 is red.
     * @return true if there are four consecutive instances 
     */
    public boolean checkE(int colour) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (grid[i][j] == colour && grid[i][j+1] == colour &&
                        grid[i][j+2] == colour && grid[i][j+3] == colour) {
                    // easterly win
                    return true;
                }
            }
        }
        // no easterly win
        return false;
    }

    /**
     * Checks the grid for four consecutive instances of a given colour in a 
     * south-easterly direction.
     * @param colour the colour to check for. 1 is yellow, 2 is red.
     * @return true if there are four consecutive instances 
     */
    public boolean checkSE(int colour) {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns - 3; j++) {
                if (grid[i][j] == colour && grid[i+1][j+1] == colour &&
                        grid[i+2][j+2] == colour && grid[i+3][j+3] == colour) {
                    // south-easterly win
                    return true;
                }
            }
        }
        // no south-easterly win
        return false;
    }

    /**
     * Checks the grid for four consecutive instances of a given colour in a 
     * southerly direction.
     * @param colour the colour to check for. 1 is yellow, 2 is red.
     * @return true if there are four consecutive instances 
     */
    public boolean checkS(int colour) {
        for (int i = 0; i < rows - 3; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == colour && grid[i+1][j] == colour &&
                        grid[i+2][j] == colour && grid[i+3][j] == colour) {
                    return true;
                }
            }
        }
        return false;
    }
}
