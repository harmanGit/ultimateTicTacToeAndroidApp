package com.cs345.harmanjeetdhillon.ultimatetictactoe;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.StringTokenizer;

/**
 * The Class is connect to the XML and plays the game using the logic from the
 * UltimateTicTacToeLogicModel class.
 * Created by harmanjeetdhillon on 10/30/17.
 */

public class PlayGame extends AppCompatActivity {
    private MediaPlayer mp;
    private MediaPlayer end;

    private String openBoard = " "; //the name of the board that is open
    private String buttonPressedName = "medMed5"; //default button name of the button pressed
    private String player = "X"; //player
    private int xClicks = 0;
    private int oClicks = 0;
    private TextView textViewX;
    private TextView textViewO;
    private Button button; // represents the button pressed out of the 81 totally buttons.
    private UltimateTicTacToeLogicModel gameLogic; //brains of the game

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);
        textViewX = findViewById(R.id.countingClicksX);
        textViewO = findViewById(R.id.countingClicksO);
        startGame(); //starting the game
    }
    /**
     * Method is an onClick for all 81 buttons. The method runs the play game method, and helps
     * open and close available and unavailable inner boards
     * @param view
     */
    public void setButtonPressed(View view) {
        mp = MediaPlayer.create(this, R.raw.btn);
        mp.start();
        unSelectAllButton();
        button = findViewById(view.getId()); // getting the id of the button pressed
        System.out.println("PLAY: button ID Found");

        selectButton(openBoard);// placings a focus on selected board.
        String nameLocation = button.getResources().getResourceName(button.getId());
        System.out.println("PLAY: " + nameLocation);
        String stopper = "/";
        StringTokenizer token = new StringTokenizer(nameLocation, stopper);
        System.out.println(token.nextToken());
        buttonPressedName = token.nextToken(); //finding the name of the button pressed
        System.out.println("PLAY: button name: " + buttonPressedName);
        playGame();

    }
    /**
     * Method runs the game logic.
     */
    private void playGame() {
        //mp.stop();
        closingAllBoards();
        unSelectButton(openBoard);
        if (!gameLogic.isMainBoardWin()) // checking to see of the game is won
        {
            //checking to see if the users move is valid
            if (gameLogic.placeMaker(buttonPressedName, player.charAt(0))) {
                //checking to see if the entire board should be opened
                if (gameLogic.openEntireBoard(buttonPressedName)) {
                    openingAllBoards();
                    wonBoard();
                    button.setText(player);//placing the marker
                    if(player.equals("X")){
                        xClicks++;
                        textViewX.setText(Integer.toString(xClicks));
                    }else {
                        oClicks++;
                        textViewO.setText(Integer.toString(oClicks));
                    }
                    //changing the open board based of the button clicked
                    openBoard = gameLogic.openBoard(buttonPressedName);
                    activatingButtons(openBoard); // opening the new board
                    switchPlayer();
                } else {
                    wonBoard();
                    openBoard = gameLogic.openBoard(buttonPressedName);
                    activatingButtons(openBoard);
                    button.setText(player);
                    if(player.equals("X")){
                        xClicks++;
                        textViewX.setText(Integer.toString(xClicks));
                    }else {
                        oClicks++;
                        textViewO.setText(Integer.toString(oClicks));
                    }
                    switchPlayer();
                }
            } else {
                activatingButtons(openBoard);
                System.out.println("Spot Taken");
            }
        } else {
            gameOver();
            System.out.println("Psych! The Game Was Won");
        }

       // if(mp.isPlaying()){
       //     mp.stop();
       // }
    }
    /**
     * Method switchs the player marker value, if the player is X it flips to O, if player is O
     * it flips to X.
     */
    private void switchPlayer(){
        if (player.equals("X")) {
        player = "O";
    } else {
        player = "X";
    }}
    /**
     * Method deactivates all buttons of all the inner boards.
     */
    private void closingAllBoards() {
        deactivatingButtons("topLeft");
        deactivatingButtons("topMed");
        deactivatingButtons("topRight");
        deactivatingButtons("medLeft");
        deactivatingButtons("medMed");
        deactivatingButtons("medRight");
        deactivatingButtons("bottomLeft");
        deactivatingButtons("bottomMed");
        deactivatingButtons("bottomRight");
    }
    /**
     * Method activates all buttons of all the inner boards.
     */
    private void openingAllBoards(){
        activatingButtons("topLeft");
        selectButton("topLeft");
        activatingButtons("topMed");
        selectButton("topMed");
        activatingButtons("topRight");
        selectButton("topRight");
        activatingButtons("medLeft");
        selectButton("medLeft");
        activatingButtons("medMed");
        selectButton("medMed");
        activatingButtons("medRight");
        selectButton("medRight");
        activatingButtons("bottomLeft");
        selectButton("bottomLeft");
        activatingButtons("bottomMed");
        selectButton("bottomMed");
        activatingButtons("bottomRight");
        selectButton("bottomRight");
    }
    /**
     * Method creates an object of the UltimateTicTacToeLogicModel class.
     */
    private void startGame() {
        gameLogic = new UltimateTicTacToeLogicModel();
        openBoard = gameLogic.openBoard(buttonPressedName);
        openingAllBoards();
        //closingAllBoards();
        //activatingButtons(openBoard);
    }
    /**
     * Method displays a toast and locks the entire board. When the main board is won.
     */
    private void gameOver(){

        Context ct = getApplicationContext();
        switchPlayer();
        if(player.equals("X")){
            end = MediaPlayer.create(this, R.raw.xwin);
            end.start();
        }else if(player.equals("O")){
            end = MediaPlayer.create(this, R.raw.owon);
            end.start();
        }

        Toast.makeText(ct,"Psych! The Game Was Won By: " + player,Toast.LENGTH_LONG).show();
        mp = MediaPlayer.create(this, R.raw.win);
        mp.start();
        deactivatingButtons("topLeft");
        deactivatingButtons("topMed");
        deactivatingButtons("topRight");
        deactivatingButtons("medLeft");
        deactivatingButtons("medMed");
        deactivatingButtons("medRight");
        deactivatingButtons("bottomLeft");
        deactivatingButtons("bottomMed");
        deactivatingButtons("bottomRight");

    }
    /**
     * Method changes the color of the won boards.
     */
    private void wonBoard() {
        if(!gameLogic.topLeftWinner().equals("null")){
            winColor(gameLogic.topLeftWinner());
        }
        if(!gameLogic.topRightWinner().equals("null")){
            winColor(gameLogic.topRightWinner());
        }
        if(!gameLogic.topMedWinner().equals("null")){
            winColor(gameLogic.topMedWinner());
        }
        if(!gameLogic.medLeftWinner().equals("null")){
            winColor(gameLogic.medLeftWinner());
        }
        if(!gameLogic.medMedWinner().equals("null")){
            winColor(gameLogic.medMedWinner());
        }
        if(!gameLogic.medRightWinner().equals("null")){
            winColor(gameLogic.medRightWinner());
        }
        if(!gameLogic.bottomLeftWinner().equals("null")){
            winColor(gameLogic.bottomLeftWinner());
        }
        if(!gameLogic.bottomMedWinner().equals("null")){
            winColor(gameLogic.bottomMedWinner());
        }
        if(!gameLogic.bottomRightWinner().equals("null")){
            winColor(gameLogic.bottomRightWinner());
        }
    }
    /**
     * Method takes the board name and colors it based off it it is an X or a O. This is used to
     * change the color of the won boards.
     * @param openBoardName
     */
    private void winColor(String openBoardName){
        LinearLayout board = findViewById(getResources().getIdentifier(openBoardName, "id",
                this.getPackageName()));
        System.out.println("COLOR");
        if (player.equals("X")) {
            System.out.println("COLOR: X");
            //board.setBackgroundColor(getResources().getColor(R.color.xBoardWin));
            board.setBackgroundColor(getResources().getColor(R.color.oBoardWin));
        } else {
            System.out.println("COLOR: O");
            board.setBackgroundColor(getResources().getColor(R.color.oBoardWin));
        }
    }
    /**
     * Method turns off the focus from all buttons
     */
    private void unSelectAllButton(){
        unSelectButton("topLeft");
        unSelectButton("topMed");
        unSelectButton("topRight");
        unSelectButton("medLeft");
        unSelectButton("medMed");
        unSelectButton("medRight");
        unSelectButton("bottomLeft");
        unSelectButton("bottomMed");
        unSelectButton("bottomRight");
    }
    /**
     * Method turns off the focus of a single button
     * @param openBoardName the name of what board is open for the user to click in
     */
    private void unSelectButton(String openBoardName){
        LinearLayout board = findViewById(getResources().getIdentifier(openBoardName, "id",
                this.getPackageName()));
        board.setBackgroundColor(getResources().getColor(R.color.deselectBoard));
    }
    /**
     * Method places focus on the button selected, based of what board is open.
     * @param openBoardName the name of what board is open for the user to click in
     */
    private void selectButton(String openBoardName){
        LinearLayout board = findViewById(getResources().getIdentifier(openBoardName, "id",
                this.getPackageName()));
        board.setBackgroundColor(getResources().getColor(R.color.selectedBoard));
    }
    /**
     * Method activates all buttons of a inner board
     * @param openBoardName the name of what board the user is in
     */
    private void activatingButtons(String openBoardName) {
        selectButton(openBoard);
        for (int i = 1; i < 10; i++) {
            String buttonName = openBoardName + i;
            Button button = findViewById(getResources().getIdentifier(buttonName, "id",
                    this.getPackageName()));
            button.setClickable(true);
        }
    }
    /**
     * Method deactivates all buttons of a inner board
     * @param openBoardName the name of what board the user is in
     */
    private void deactivatingButtons(String openBoardName) {
        for (int i = 1; i < 10; i++) {
            String buttonName = openBoardName + i;
            Button button = findViewById(getResources().getIdentifier(buttonName, "id",
                    this.getPackageName()));
            button.setClickable(false);
        }
    }
}
