package Controller;
import java.lang.reflect.Array;
import java.util.Arrays;

import Model.ChessBoard;
import Model.Piece;
import Model.Player;
import View.Display;
public class GameController {
    private Display display;
    private static Player playerR;
    private static Player playerB;
    private ChessBoard chessboard;
    private static Piece[] pList = new Piece[16];
    private static boolean currentPlayer;

    public GameController(){
        display = new Display();
        chessboard = new ChessBoard(9, 7);
        StartGame();
    }
    public Piece[] Initialization(){
        String[] gameStartPlayer = display.Registration();
        if (gameStartPlayer[0].equals("red")){
            currentPlayer = true; 
        }else{
            currentPlayer = false;
        }

        playerR = new Player(true,0);
        playerB = new Player(false,0);

        Piece ratR = new Piece(playerR, 1, "G3");
        Piece catR = new Piece(playerR, 2, "B2");
        Piece dogR = new Piece(playerR, 3, "F2");
        Piece wolfR = new Piece(playerR, 4, "C3");
        Piece leopardR = new Piece(playerR, 5, "E3");
        Piece tigerR = new Piece(playerR, 6, "A1");
        Piece lionR = new Piece(playerR, 7, "G1");
        Piece elephantR = new Piece(playerR, 8, "A3");

        Piece ratB = new Piece(playerB, 1, "A7");
        Piece catB = new Piece(playerB, 2, "F8");
        Piece dogB = new Piece(playerB, 3, "B8");
        Piece wolfB = new Piece(playerB, 4, "E7");
        Piece leopardB = new Piece(playerB, 5, "C7");
        Piece tigerB = new Piece(playerB, 6, "G9");
        Piece lionB = new Piece(playerB, 7, "A9");
        Piece elephantB = new Piece(playerB, 8, "G7");

        pList[0] = ratR;
        pList[1] = catR;
        pList[2] = dogR;
        pList[3] = wolfR;
        pList[4] = leopardR;
        pList[5] = tigerR;
        pList[6] = lionR;
        pList[7] = elephantR;
        pList[8] = ratB;
        pList[9] = catB;
        pList[10] = dogB;
        pList[11] = wolfB;
        pList[12] = leopardB;
        pList[13] = tigerB;
        pList[14] = lionB;
        pList[15] = elephantB;


  

        chessboard.setPiece(ratR);
        chessboard.setPiece(catR);
        chessboard.setPiece(dogR);
        chessboard.setPiece(wolfR);
        chessboard.setPiece(leopardR);
        chessboard.setPiece(tigerR);
        chessboard.setPiece(lionR);
        chessboard.setPiece(elephantR);

        chessboard.setPiece(ratB);
        chessboard.setPiece(catB);
        chessboard.setPiece(dogB);
        chessboard.setPiece(wolfB);
        chessboard.setPiece(leopardB);
        chessboard.setPiece(tigerB);
        chessboard.setPiece(lionB);
        chessboard.setPiece(elephantB);

        return pList;
    }
    public boolean CheckCapture(String currentPos, String targetPos){  // check the capture
        int IndexOfTargetPiece = -1;
        int IndexOfCurrentPiece = -1;
        for (int p = 0 ; p < pList.length ; p++){
            if (pList[p].getPos().equals(targetPos)){
                IndexOfTargetPiece = p;
            }
            if (pList[p].getPos().equals(currentPos)){
                IndexOfCurrentPiece = p;
            }
        }
        //case of handle the target square do not have piece
        if (IndexOfTargetPiece == -1){
            return true;
        }
        //case of handle the target piece is belong current player
        if (pList[IndexOfTargetPiece].getPlayerColor() == pList[IndexOfCurrentPiece].getPlayerColor()){
            display.Warning(7);
            return false;
        }

        //case of handle special case that rat can capture elephant
        if((pList[IndexOfCurrentPiece].getRank() == 1) && (pList[IndexOfTargetPiece].getRank() == 8)){
            String[] riverList = chessboard.River();
            for (int f = 0; f < riverList.length; f++){
                // case of handle case that rat cannot capture elephant on land from river area
                if (pList[IndexOfCurrentPiece].getPos().equals(riverList[f])){
                    display.Warning(8);
                    return false;
                }
            }
            pList[IndexOfTargetPiece].setCaptured();
            return true;
        }
        //case of handle special case that rat can not capture enemy rat that on land from river area
        // and rat on land cannot capture enemy rat that on river
        if((pList[IndexOfCurrentPiece].getRank() == 1) && (pList[IndexOfTargetPiece].getRank() == 1)){
            Boolean currentPieceIsOnRiver = false; //default
            Boolean targetPieceIsOnRiver = false;
            String[] riverList = chessboard.River();
            //  checking the current and target piece is on river or not
            for (int f = 0; f < riverList.length; f++){
                if (pList[IndexOfCurrentPiece].getPos().equals(riverList[f])){
                    currentPieceIsOnRiver = true;
                }
                if (pList[IndexOfTargetPiece].getPos().equals(riverList[f])){
                    targetPieceIsOnRiver = true;
                }
            }
            // checking rat on river cannot capture enemy rat on land
            if ((currentPieceIsOnRiver == true) && (targetPieceIsOnRiver == false)){
                display.Warning(10);
                return false;
            }
            // checking rat on land cannot capture enemy rat on river
            if ((currentPieceIsOnRiver == false) && (targetPieceIsOnRiver == true)){
                display.Warning(11);
                return false;
            }
            return true;
        }
        //case of handle the target square have piece and the piece is enemy
        if(pList[IndexOfCurrentPiece].getRank() >= pList[IndexOfTargetPiece].getRank()){
            if((pList[IndexOfCurrentPiece].getRank() == 8) && (pList[IndexOfTargetPiece].getRank() == 1)) {
                display.Warning(13); //case of handle special case that elephant cannot capture rat
                return false;
            }
            else {
                pList[IndexOfTargetPiece].setCaptured();
                return true;
            }
        }else{
            display.Warning(6);
            return false;
        }
    }
    public void CheckTrap(){
        String[] trapList = chessboard.Trap(); //get all trap position
        for (int p = 0 ; p < pList.length; p++){ //untraped all the chess if the chess is traped (trap it back in later part if the chess still in trap square)
            if (pList[p].getRank() < 0){
                pList[p].setUntraped();
            }
        }
        for (int p = 0 ; p < pList.length; p++){ //loop all the chess
            boolean pieceIsTrap = false;
            for (int k = 0; k < trapList.length; k++){
                if (pList[p].getPos().equals(trapList[k])){ 
                    pieceIsTrap = true; //if chess the trap area, set it to true
                }
            }
            if (pieceIsTrap == true){ //if true then set corresponding piece to traped
                pList[p].setTraped();
            }
        }
    }
    public boolean CheckWin(){
        String[] denL = chessboard.Den(); //get all den position
        String redDenPos = denL[0];
        String blackDenPos = denL[1];
        for(int i = 0; i < pList.length; i++){
            if (pList[i].getPos().equals(redDenPos)){ // if any piece inside of the red den then black color player win the game
                if (pList[i].getPlayerColor() == false){
                    return true;
                }
            }
            if (pList[i].getPos().equals(blackDenPos)){// if any piece inside of the black den then red color player win the game
                if (pList[i].getPlayerColor() == true){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean checkRiver(String location){ //check any piece inside of the river
        for (int p = 0; p < pList.length; p++){
            if (pList[p].getPos().equals(location)){
                return true; //piece found
            }
        }
        return false; // piece not found
    }
    public boolean checkJumpOverTheRiver(String currentPos,String targetPos,int indexOfSelectedPiece){
        String[] riverBorder = chessboard.RiverBorderCanBeJumpOver();
        String[] riverInterveningSquareList = chessboard.RiverInterveningSquare();
        int numberOfChar = 0;
        boolean pieceInRiver = false;
        boolean canPieceJump = false;
        if ((pList[indexOfSelectedPiece].getRank() == 7) || (pList[indexOfSelectedPiece].getRank() == 6)){
            for (int a = 0 ; a < riverBorder.length ; a++){
                String jumpStartingSquare = (riverBorder[a].charAt(0)+"") + (riverBorder [a].charAt(1) + "");
                String jumpEndingSquare = (riverBorder[a].charAt(2)+"") + (riverBorder [a].charAt(3) + "");
                if ((pList[indexOfSelectedPiece].getPos().equals(jumpStartingSquare)) && (targetPos.equals(jumpEndingSquare))){
                    canPieceJump = true;
                    for (int p = 0; p < riverInterveningSquareList.length ; p++){
                        numberOfChar = riverInterveningSquareList[p].length();
                        String riverInterveningSquareStartingPos = (riverInterveningSquareList[p].charAt(0)+"") + (riverInterveningSquareList[p].charAt(1)+"");
                        String riverInterveningSquareEndingPos = (riverInterveningSquareList[p].charAt(numberOfChar-2)+"") + (riverInterveningSquareList[p].charAt(numberOfChar-1)+"");
                        if ((riverInterveningSquareStartingPos.equals(currentPos)) && (riverInterveningSquareEndingPos.equals(targetPos))){
                            String riverInterveningSquarePosList[] = riverInterveningSquareList[p].split(",");
                            riverInterveningSquarePosList[0] = null;
                            riverInterveningSquarePosList[riverInterveningSquarePosList.length-1] = null;
                            for (int k = 0; k < riverInterveningSquarePosList.length; k++){
                                if (riverInterveningSquarePosList[k] != null){
                                    if (checkRiver(riverInterveningSquarePosList[k]) == true){
                                        pieceInRiver = true;
                                    }
                                }
                            }
            
                        }
                    }
                }
            }
        }
        if ((canPieceJump == true) && (pieceInRiver == false)){
            return true;
        }else{
            return false;
        }
    }

    public void StartGame()  {
        display.PrintWelcomeMessaage();
        display.PrintMenu();
        Piece[] pieceList = Initialization();
        RunGame(pieceList);
    }

    public void RunGame(Piece[] pieceList)  {
        String userInput;
        String currentPlayerName;
        boolean previousPlayer = false;
        while(true){
            CheckTrap();
            display.PrintBoard(pieceList,chessboard.River(),chessboard.Trap(),chessboard.Den());
            if (currentPlayer == true){ //find which is the current player
                currentPlayerName = "red"; //current player is red
            }else{
                currentPlayerName = "black"; //current player is black
            }                
            userInput = display.CollectUserAction(currentPlayerName);
            if (userInput.equals("QUIT")){
                Exit();
            }
            String[] userInputL = userInput.split(",");
            String currentPos = userInputL[0];
            String targetPos = userInputL[1];
            display.PrintCapturedPiece(pieceList);
            if((GameRuleCheck(currentPos,targetPos) == true)) {
                Move(pieceList,currentPos,targetPos);
            }


            if (CheckWin() == true){ // check win
                if (currentPlayer == true){// found the previous player not current player because the current player is updataed
                    previousPlayer = false;
                }else{
                    previousPlayer = true;
                }

                if (currentPlayer == true){ // update the turn after player have win the game
                    playerR.UpdataPlyerTurn(playerR.getPlayerTurn()+1);
                }else{
                    playerB.UpdataPlyerTurn(playerB.getPlayerTurn()+1);
                }
                
                if (playerB.getPlayerTurn() < playerR.getPlayerTurn()){ // get and pass the higher player turn
                    display.PrintEnd(previousPlayer,playerB.getPlayerTurn());
                }else{
                    display.PrintEnd(previousPlayer,playerR.getPlayerTurn());
                }
                break;
            }  
        }
    }

    public void Move(Piece[] pieceList,String currentPos,String targetPos){
        int NumberOfPiece = pieceList.length;
        for (int k = 0; k<NumberOfPiece ; k++){ // to check whether have chess inside of this box
            if(pieceList[k].getPos().equals(currentPos)){
                pieceList[k].setPos(targetPos);
            }
        }
    }
    public boolean GameRuleCheck(String currentPos,String targetPos){
        int indexOfCurrentXinArray = -1;
        int indexOfCurrentYinArray = -1;
        int indexOfSelectedPiece = -1;
        boolean[] WarningType = new boolean[5];
        boolean haveViolateX = true; //default
        boolean haveViolateY = true; //default
        boolean violateDiagonallyLeft = false; //default
        boolean violateDiagonallyRight = false; //default
        boolean violateNoPiece = true;// default
        String xPosL[] = {"border","A","B","C","D","E","F","G","border"};
        String yPosL[] = {"border","1","2","3","4","5","6","7","8","9","border"};
        String riverL[] = chessboard.River();
        String riverBorder[] = chessboard.RiverBorderCanBeJumpOver();
        String currentPosX = currentPos.charAt(0)+"";  //split the current x position from the String and convert char to Sting
        String currentPosY = currentPos.charAt(1)+""; //split the current y position from the String and convert char to Sting
        String targetPosX = targetPos.charAt(0)+""; //split the target x position from the String and convert char to Sting
        String targetPosY = targetPos.charAt(1)+""; //split the target y position from the String and convert char to Sting
        for (int k=0; k<5 ;k++){ //initialize the Warning list
            WarningType[k] = false;
        }

        for (int t = 0; t < pList.length ; t++){ // loop for taking out information from the piece array
            if (pList[t].getPos().equals(currentPos)){
                violateNoPiece = false;// check any piece inside of the selected square
                indexOfSelectedPiece = t; // save the index of current piece in the piece array
            }
        }
        for (int q = 0; q < riverL.length ;q++){ //warning if the piece not allowed to pass the river
            if ((riverL[q].equals(targetPos) ) && (indexOfSelectedPiece != -1)){
                if (pList[indexOfSelectedPiece].getRank() != 1){
                    display.Warning(9);
                    return false;
                }
            }
        }

        if (violateNoPiece == true){ //check the selected square have piece or not
            display.Warning(5);
            return false;
        }
        if (pList[indexOfSelectedPiece].getPlayerColor() != currentPlayer){ //Warning if the selected piece is not own by current player
            display.Warning(3);
            return false;
        }
       
        if (currentPos.equals(targetPos)){ //Warning if the target position is the current position
            display.Warning(2);
            return false;
        }

        for (int g = 0; g < riverBorder.length ; g++){ // check the piece is at the river border and check can it jump over the river
            String riverBorderPos = (riverBorder[g].charAt(0)+"") + (riverBorder[g].charAt(1)+"");
            if (currentPos.equals(riverBorderPos)){
                if (checkJumpOverTheRiver(currentPos,targetPos,indexOfSelectedPiece) == true){
                    if (CheckCapture(currentPos, targetPos) == false){ //check the target square can be captured or not
                        return false;
                    }
                    UpdatePlayerTurn();
                    return true;
                }
            }
        }
       
        for (int i = 0; i < xPosL.length; i++){ // locate the index of currentPosX in the xPosL
            if (currentPosX.equals(xPosL[i])){
                indexOfCurrentXinArray = i;
            }
        }
        for (int p = 0; p < yPosL.length; p++){ // locate the index of currentPosY in the YPosL
            if (currentPosY.equals(yPosL[p])){
                indexOfCurrentYinArray = p;
            }
        }

        if ((currentPosX.equals(targetPosX)) || (targetPosX.equals(xPosL[indexOfCurrentXinArray+1])) || (targetPosX.equals(xPosL[indexOfCurrentXinArray-1]))){ // user can only Move one box in x axis
            haveViolateX = false;
            WarningType [0] = true; 
        }

        if ((currentPosY.equals(targetPosY)) || (targetPosY.equals(yPosL[indexOfCurrentYinArray+1]))|| (targetPosY.equals(yPosL[indexOfCurrentYinArray-1]))){ // user can only Move one box in y axis
            haveViolateY = false;
            WarningType [0] = true;
        }

        if((targetPosX.equals(xPosL[indexOfCurrentXinArray-1]) && ((targetPosY.equals(yPosL[indexOfCurrentYinArray+1])) || (targetPosY.equals(yPosL[indexOfCurrentYinArray-1]))))){
            violateDiagonallyLeft = true;
            WarningType [1] = true; 
        }

        if((targetPosX.equals(xPosL[indexOfCurrentXinArray+1]) && ((targetPosY.equals(yPosL[indexOfCurrentYinArray+1])) || (targetPosY.equals(yPosL[indexOfCurrentYinArray-1]))))){
            violateDiagonallyRight = true;
            WarningType [1] = true; 
        }

        if ((haveViolateX == true) && (haveViolateX == true)){
            WarningType [0] = true;
            WarningType [1] = true;
        }

        if ((haveViolateX == false) && (haveViolateY == false) && (violateDiagonallyLeft == false) && (violateDiagonallyRight == false)){
            if (CheckCapture(currentPos, targetPos) == false){ //check the target square can be captured or not
                return false;
            }
            UpdatePlayerTurn();// update the player turn
            return true;
        }else{
            // pass Warning type 
            for (int d = 0; d < WarningType.length ; d++){
                if (WarningType[d] == true){
                    display.Warning(d);
                }
            }
            return false;
        }
    
    }
    public void UpdatePlayerTurn(){ //update the current player
        if (currentPlayer == true){
            playerR.UpdataPlyerTurn(playerR.getPlayerTurn()+1);
        }else{
            playerB.UpdataPlyerTurn(playerB.getPlayerTurn()+1);
        }
        if (currentPlayer == true){
            currentPlayer = false;
        }else{
            currentPlayer = true;
        }
    } 
    public void Exit(){
        System.out.println("Sucess, The game is closed");
        System.exit(0);
    }
}