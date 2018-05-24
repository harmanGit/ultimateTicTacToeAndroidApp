package com.cs345.harmanjeetdhillon.ultimatetictactoe;

/**
 * This class is the logic model for ultimate tic tac toe. There are 10 3 by 3 arrays. The bigger
 * main board is a 3 by 3 array of boolean. Then there the 9 other inner boards are 3 by 3 char
 * arrays. The bigger main board is filled with true values by default. If the main boards
 * value is true that means that the inner board is not won or a draw. If the value is false of
 * any of the main board slots, that mean the inner board it represents is a draw or a win. The 9
 * other inner boards are filled it '0' which means that the slot is available. These change as the
 * user fills them in with a 'X' or a 'O'.
 * Created by harmanjeetdhillon on 10/30/17.
 */

public class UltimateTicTacToeLogicModel {

    private static char[][] topLeft; //inner board of tic tac toe
    private static boolean topLeftDraw;//represents draw in topLeft board
    private static boolean topLeftWin;//represents a win in the topLeft board
    private static char[][] topMed;//inner board of tic tac toe
    private static boolean topMedDraw;//represents draw in topMed board
    private static boolean topMedWin;//represents a win in the topMed board
    private static char[][] topRight;//inner board of tic tac toe
    private static boolean topRightDraw;//represents draw in topRight board
    private static boolean topRightwin;//represents a win in the topRight board

    private static char[][] medLeft;//inner board of tic tac toe
    private static boolean medLeftDraw;//represents draw in medLeft board
    private static boolean medLeftWin;//represents a win in the medLeft board
    private static char[][] medMed;//inner board of tic tac toe
    private static boolean medMedDraw;//represents draw in medMed board
    private static boolean medMedWin;//represents a win in the medMed board
    private static char[][] medRight;//inner board of tic tac toe
    private static boolean medRightDraw;//represents draw in medRight board
    private static boolean medRightWin;//represents a win in the medRight board

    private static char[][] bottomLeft;//inner board of tic tac toe
    private static boolean bottomLeftDraw;//represents draw in bottomLeft board
    private static boolean bottomLeftWin;//represents a win in the bottomLeft board
    private static char[][] bottomMed;//inner board of tic tac toe
    private static boolean bottomMedDraw;//represents draw in bottomMed board
    private static boolean bottomMedWin;//represents a win in the bottomMed board
    private static char[][] bottomRight;//inner board of tic tac toe
    private static boolean bottomRightDraw;//represents draw in bottomRight board
    private static boolean bottomRightWin;//represents a win in the bottomRight board

    private static boolean[][] mainBoard;//main larger board of tic tac toe
    private static boolean mainBoardWinner;//represents if the game is win
    private static char winner; //represents the player that has won

    UltimateTicTacToeLogicModel() {
        mainBoard = new boolean[3][3];
        mainBoard();

        topLeft = createBoard();
        topLeftDraw = false;
        topMed = createBoard();
        topMedDraw = false;
        topRight = createBoard();
        topRightDraw = false;

        medLeft = createBoard();
        medLeftDraw = false;
        medMed = createBoard();
        medMedDraw = false;
        medRight = createBoard();
        medRightDraw = false;

        bottomLeft = createBoard();
        bottomLeftDraw = false;
        bottomMed = createBoard();
        bottomMedDraw = false;
        bottomRight = createBoard();
        bottomRightDraw = false;

        mainBoardWinner = false;

        topLeftWin = false;
        topMedWin = false;
        topRightwin = false;

        medLeftWin = false;
        medMedWin = false;
        medRightWin = false;

        bottomLeftWin = false;
        bottomMedWin = false;
        bottomRightWin = false;
    }
    /**
     * Method populates the main tic tac toe (boolean) array with all true values.
     * Allowing all 9 inner boards to be open.
     */
    private void mainBoard() {
        for (int row = 0; row < 3; row++) {
            for (int cols = 0; cols < 3; cols++) {
                mainBoard[row][cols] = true;
            }
        }
    }
    /**
     * Method populates all inner boards with '0' representing each slot to be empty.
     * @return a 2D char array
     */
    private char[][] createBoard() {
        char[][] board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int cols = 0; cols < 3; cols++) {
                board[row][cols] = '0';
            }
        }
        return board;
    }
    /**
     * Method takes a button name and char player. Then using the slotAvailable method to check
     * to see the move the user wants to make is valid. If the move is valid then the move is made
     * and a true is returned. If the move can't be made than a false is returned.
     * @param buttonName full button name of the button that has been selected by the player
     * @param player char representing the player who has pressed the button
     * @return true if the move is valid, else it returns false
     */
    public boolean placeMaker(String buttonName, char player) {
        //getting the name of the board that the button was pressed in, from the buttonName
        String boardName = buttonName.substring(0, (buttonName.length() - 1));
        //getting which button of 9 was pressed (in the inner boards)
        int boardLocation = Integer.parseInt(buttonName.substring(
                (buttonName.length() - 1), buttonName.length()));

        if (slotAvailable(buttonName)) {//checking to see if the move is valid
            //using the boardFinder method to find a inner board and setting the placing the player
            boardFinder(boardName)[positionRow(boardLocation)][positionCols(boardLocation)] = player;

            // checking to see if the current move causes a win, in the current inner board
            innerBoardWin(boardName, player);
            mainBoardWin(player); //checking to see if the player has won with its current move
            return true;
        }
        return false;
    }
    /**
     * Method takes a button name of whatever button pressed and the returns what other
     * inner board it should move to
     * @param buttonName full button name of the button that has been selected by the player
     * @return a string representing what inner board should open next.
     */
    public String openBoard(String buttonName) {
        String returnString = "null";
        //getting which button of 9 was pressed (in the inner boards)
        int boardLocation = Integer.parseInt(buttonName.substring(
                (buttonName.length() - 1), buttonName.length()));

        int row = positionRow(boardLocation); // get what row the inner board is located in
        int cols = positionCols(boardLocation); // get what column the inner board is located in

        //finding and returning the next board to move too
        if (row == 0 && cols == 0) {
            return "topLeft";
        }
        if (row == 0 && cols == 1) {
            return "topMed";
        }
        if (row == 0 && cols == 2) {
            return "topRight";
        }
        if (row == 1 && cols == 0) {
            return "medLeft";
        }
        if (row == 1 && cols == 1) {
            return "medMed";
        }
        if (row == 1 && cols == 2) {
            return "medRight";
        }
        if (row == 2 && cols == 0) {
            return "bottomLeft";
        }
        if (row == 2 && cols == 1) {
            return "bottomMed";
        }
        if (row == 2 && cols == 2) {
            return "bottomRight";
        }
        return returnString;

    }
    /**
     * Method checks to see if the user has selected a won inner board and or a inner board that is
     * a draw. Method returns true if the entire board should be opened, else it returns false.
     * The button pressed is required to know if the next move should open the entire board.
     * @param buttonName full button name of the button that has been selected by the player
     * @return boolean true if the entire board should be open or false it should not
     */
    public boolean openEntireBoard(String buttonName) {
        //getting the name of the board the user has selected moved too based of the button pressed
        String movingToBoard = openBoard(buttonName);

        //checking to see if the board moving to is a winning inner board or a draw
        switch (movingToBoard) {
            case "topLeft":
                if (!mainBoard[0][0]) {
                  return true;
                }
                break;
            case "topMed":
                if (!mainBoard[0][1]) {
                   return true;
                }
                break;
            case "topRight":
                if (!mainBoard[0][2]) {
                    return true;
                }
                break;
            case "medLeft":
                if (!mainBoard[1][0]) {
                    return true;
                }
                break;
            case "medMed":
                if (!mainBoard[1][1]) {
                    return true;
                }
                break;
            case "medRight":
                if (!mainBoard[1][2]) {
                    return true;
                }
                break;
            case "bottomLeft":
                if (!mainBoard[2][0]) {
                    return true;
                }
                break;
            case "bottomMed":
                if (!mainBoard[2][1]) {
                    return true;
                }
                break;
            case "bottomRight":
                if (!mainBoard[2][2]) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
    /**
     * Method checks the topLeft inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String topLeftWinner(){
        if(topLeftWin){
            return "topLeft";
        }
        return "null";
    }
    /**
     * Method checks the topMed inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String topMedWinner(){
        if(topMedWin){
            return "topMed";
        }
        return "null";
    }
    /**
     * Method checks the topRight inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String topRightWinner(){
        if(topRightwin){
            return "topRight";
        }
        return "null";
    }
    /**
     * Method checks the medLeft inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String medLeftWinner(){
        if(medLeftWin){
            return "medLeft";
        }
        return "null";
    }
    /**
     * Method checks the medMed inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String medMedWinner(){
        if(medMedWin){
            return "medMed";
        }
        return "null";
    }
    /**
     * Method checks the medRight inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String medRightWinner(){
        if(medRightWin){
            return "medRight";
        }
        return "null";
    }
    /**
     * Method checks the bottomLeft inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String bottomLeftWinner(){
        if(bottomLeftWin){
            return "bottomLeft";
        }
        return "null";
    }
    /**
     * Method checks the bottomMed inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String bottomMedWinner(){
        if(bottomMedWin){
            return "bottomMed";
        }
        return "null";
    }
    /**
     * Method checks the bottomRight inner board to see if it was won and if so it returns the name of
     * the board thats won. Else it returns a null.
     * @return the name of the board if it was won, else returns null
     */
    public String bottomRightWinner(){
        if(bottomRightWin){
            return "bottomRight";
        }
        return "null";
    }
    /**
     * Method returns a true if mainBoard was won, else it returns a false
     * @return true if mainBoard was won, else it returns a false
     */
    public boolean isMainBoardWin(){ return mainBoardWinner;}
    /**
     * Method checks to see the inner board was won, using the board name and the player that
     * selected the button.
     * @param boardName the board the user selected
     * @param player  char representing the player who has pressed the button
     */
    private static void innerBoardWin(String boardName, char player) {
        //counter variable for each possible way to win a tic tac toe board
        int winningCountTopRow = 0;
        int winningCountMedRow = 0;
        int winningCountBottomRow = 0;
        int winningCountLeftCol = 0;
        int winningCountMedCol = 0;
        int winningCountRightCol = 0;
        int winningCountSlash = 0; // meaning /
        int winningCountBackSlash = 0; // meaning \
        // loops through all the boards counting all rows, cols, and slashes to check for a win
        // in any
        for (int i = 0; i < 3; i++) {
            if (boardFinder(boardName)[0][i] == player) {
                winningCountTopRow++;
            }
            if (boardFinder(boardName)[1][i] == player) {
                winningCountMedRow++;
            }
            if (boardFinder(boardName)[2][i] == player) {
                winningCountBottomRow++;
            }
            if (boardFinder(boardName)[i][0] == player) {
                winningCountLeftCol++;
            }
            if (boardFinder(boardName)[i][1] == player) {
                winningCountMedCol++;
            }
            if (boardFinder(boardName)[i][2] == player) {
                winningCountRightCol++;
            }
            if (boardFinder(boardName)[i][i] == player) {
                winningCountBackSlash++;
            }
        }

        // checking to see if in a slash it has a total of 3 markers were placed
        if (boardFinder(boardName)[0][2] == player
                && boardFinder(boardName)[1][1] == player
                && boardFinder(boardName)[2][0] == player) {
            winningCountSlash = 3;
        }

        //checking to see if any of the possible ways to win are won
        if (winningCountTopRow == 3 || winningCountMedRow == 3
                || winningCountBottomRow == 3 || winningCountLeftCol == 3
                || winningCountMedCol == 3 || winningCountRightCol == 3
                || winningCountSlash == 3 || winningCountBackSlash == 3) {
            //going through the switch and finding the board that has been won
            switch (boardName) {
                case "topLeft":
                    topLeftWin = true; // setting win var to true
                    mainBoard[0][0] = false; // setting slot in main board to false.
                    break;
                case "topMed":
                    topMedWin = true;
                    mainBoard[0][1] = false;
                    break;
                case "topRight":
                    topRightwin = true;
                    mainBoard[0][2] = false;
                    break;
                case "medLeft":
                    medLeftWin = true;
                    mainBoard[1][0] = false;
                    break;
                case "medMed":
                    medMedWin = true;
                    mainBoard[1][1] = false;
                    break;
                case "medRight":
                    medRightWin = true;
                    mainBoard[1][2] = false;
                    break;
                case "bottomLeft":
                    bottomLeftWin = true;
                    mainBoard[2][0] = false;
                    break;
                case "bottomMed":
                    bottomMedWin = true;
                    mainBoard[2][1] = false;
                    break;
                case "bottomRight":
                    bottomRightWin = true;
                    mainBoard[2][2] = false;
                    break;
                default:
                    break;
            }
        }
        draw(boardName); //calling the draw checking to see if a draw has occurred
    }
    /**
     * Method checks to see if the main tic tac toe has been won.
     * @param player char representing the player who has pressed the button
     */
    private static void mainBoardWin(char player) {
        // counter variable for each possible way to win a tic tac toe board.
        int winningCountTopRow = 0;
        int winningCountMedRow = 0;
        int winningCountBottomRow = 0;
        int winningCountLeftCol = 0;
        int winningCountMedCol = 0;
        int winningCountRightCol = 0;
        int winningCountSlash = 0; // meaning /
        int winningCountBackSlash = 0; // meaning \
        // loops through all the boards counting all rows, cols, and slashes to check for a win
        // in any
        for (int i = 0; i < 3; i++) {
            if (mainBoard[0][i] == false) {
                if(topLeftDraw != true || topMedDraw != true || topRightDraw != true){
                    winningCountTopRow++;
                }
            }
            if (mainBoard[1][i] == false) {
                if(medLeftDraw != true || medMedDraw != true || medRightDraw != true){
                    winningCountMedRow++;
                }
            }
            if (mainBoard[2][i] == false) {
                if(bottomLeftDraw != true || bottomMedDraw != true || bottomRightDraw != true){
                    winningCountBottomRow++;
                }
            }
            if (mainBoard[i][0] == false) {
                if(topLeftDraw != true || medLeftDraw != true || bottomLeftDraw != true ){
                    winningCountLeftCol++;
                }
            }
            if (mainBoard[i][1] == false) {
                if(topMedDraw != true  || medMedDraw != true ||bottomMedDraw != true){
                    winningCountMedCol++;
                }
            }
            if (mainBoard[i][2] == false) {
                if(topRightDraw != true ||  medRightDraw != true ||bottomRightDraw != true){
                    winningCountRightCol++;
                }
            }
            if (mainBoard[i][i] == false) {
                if(topLeftDraw != true || medMedDraw != true ||bottomRightDraw != true){
                    winningCountBackSlash++;
                }
            }
        }
        // checking to see if in a slash it has a total of 3 markers were placed
        if (mainBoard[0][2] == false
                && mainBoard[1][1] == false
                && mainBoard[2][0] == false) {
            winningCountSlash = 3;
            mainBoardWinner = true;
        }
        //checking to see if any of the possible ways to win are won
        if (winningCountTopRow == 3 || winningCountMedRow == 3
                || winningCountBottomRow == 3 || winningCountLeftCol == 3
                || winningCountMedCol == 3 || winningCountRightCol == 3
                || winningCountSlash == 3 || winningCountBackSlash == 3) {
            winner = player;
            mainBoardWinner = true;
        }
    }
    /**
     * Method checks to see if a draw has occured in any given inner board.
     * @param boardName inner board to check a draw for
     */
    private static void draw(String boardName) {
        boolean isDraw = false;
        int drawCounter = 0;

        //loop goes through the inner board and checks to see if the inner board has all
        //9 moves made. AKA all slots are filled
        for (int row = 0; row < 3; row++) {
            for (int cols = 0; cols < 3; cols++) {
                if (boardFinder(boardName)[row][cols] != '0')
                    drawCounter++;
            }
        }
        if (drawCounter == 9) { // isDraw = true if all 9 slots are filled
            isDraw = true;
        }

        if (isDraw)//Check for a draw
        {
            //switch will got to the board, and locks the inner board from further use
            switch (boardName) {
                case "topLeft":
                    topLeftDraw = true;
                    mainBoard[0][0] = false;
                    break;
                case "topMed":
                    topMedDraw = true;
                    mainBoard[0][1] = false;
                    break;
                case "topRight":
                    topRightDraw = true;
                    mainBoard[0][2] = false;
                    break;
                case "medLeft":
                    medLeftDraw = true;
                    mainBoard[1][0] = false;
                    break;
                case "medMed":
                    medMedDraw = true;
                    mainBoard[1][1] = false;
                    break;
                case "medRight":
                    medRightDraw = true;
                    mainBoard[1][2] = false;
                    break;
                case "bottomLeft":
                    bottomLeftDraw = true;
                    mainBoard[2][0] = false;
                    break;
                case "bottomMed":
                    bottomMedDraw = true;
                    mainBoard[2][1] = false;
                    break;
                case "bottomRight":
                    bottomRightDraw = true;
                    mainBoard[2][2] = false;
                    break;
                default:
                    break;
            }
        }


    }
    /**
     * Method checks to see if the slot the user wants to move to is available or not.
     * @param buttonName full button name of the button that has been selected by the player
     * @return true if the move can be made, else false
     */
    private static boolean slotAvailable(String buttonName) {
        //getting the name of the board that the button was pressed in, from the buttonName
        String boardName = buttonName.substring(0, (buttonName.length() - 1));
        //getting which button of 9 was pressed (in the inner boards)
        int boardLocation = Integer.parseInt(buttonName.substring(
                (buttonName.length() - 1), buttonName.length()));
        //using the valueAtPosition method to see what value is currently set to the slot clicked
        char postionValue = valueAtPosition(boardName, boardLocation);
        //if the value = '0' then the slot is available, else the slot is taken
        if (postionValue == '0') {
            return true;
        }
        return false;
    }
    /**
     * Method takes the boardName and returns the correct inner board for that name.
     * @param boardName name of the inner boards
     * @return the 2D array that represents one of the inner boards
     */
    private static char[][] boardFinder(String boardName) {
        char[][] returnBoard = new char[3][3];
        //Switch finds the board via the name
        switch (boardName) {
            case "topLeft":
                return topLeft;
            case "topMed":
                return topMed;
            case "topRight":
                return topRight;
            case "medLeft":
                return medLeft;
            case "medMed":
                return medMed;
            case "medRight":
                return medRight;
            case "bottomLeft":
                return bottomLeft;
            case "bottomMed":
                return bottomMed;
            case "bottomRight":
                return bottomRight;
            default:
                return returnBoard;
        }
    }
    /**
     * Method returns the row position based of the 1-9 placed at the end of a button name.
     * 0 being top row, 1 middle row, 2 bottom row.
     * @param position 1-9 placed at the end of a button name
     * @return int value representing 0 being top row, 1 middle row, 2 bottom row.
     */
    private static int positionRow(int position) {
        int returnPosition = 0;
        //checking to see what row the pressed button is located in
        if (position == 1 || position == 2 || position == 3) {
            returnPosition = 0;
        }
        if (position == 4 || position == 5 || position == 6) {
            returnPosition = 1;
        }
        if (position == 7 || position == 8 || position == 9) {
            returnPosition = 2;
        }
        return returnPosition;
    }
    /**
     * Method returns the cols position based of the 1-9 placed at the end of a button name.
     * 0 being top cols, 1 middle cols, 2 bottom cols.
     * @param position 1-9 placed at the end of a button name
     * @returnint value representing 0 being top cols, 1 middle cols, 2 bottom cols.
     */
    private static int positionCols(int position) {
        int returnPosition = 0;
        //checking to see what row the pressed button is located in
        if (position == 1 || position == 4 || position == 7) {
            returnPosition = 0;
        }
        if (position == 2 || position == 5 || position == 8) {
            returnPosition = 1;
        }
        if (position == 3 || position == 6 || position == 9) {
            returnPosition = 2;
        }
        return returnPosition;
    }
    /**
     * Method returns the char value of the array based of the board name and the position of the
     * button that was clicked
     * @param boardName the board the user selected
     * @param boardLocation the location of the inner board
     * @return the value of the slot in the inner array the user selected.
     */
    private static char valueAtPosition(String boardName, int boardLocation) {
        return boardFinder(boardName)[positionRow(boardLocation)][positionCols(boardLocation)];
    }
}

