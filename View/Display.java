package View;
import java.util.ArrayList;
import java.util.Scanner;
import Model.Piece;
public class Display {
    public Display(){
    }
    public void PrintBoard(Piece[] pieceList,String[] riverL,String[] trapL,String[]denL){
        String chessPlayer;
        boolean haveChessInside = false;
        boolean haveTrapInside = false;
        boolean haveDenInside = false;
        boolean haveRiverInside = false;
        int whichChess = 0;
        int NumberOfRiver = riverL.length;
        int NumberOfTrap = trapL.length;
        int NumberOfDen = denL.length;
        System.out.println("********************************************************************");
        for (int i = 9; i>= 1; i--){
            if(i==9){
                System.out.println("     A           B           C           D           E           F           G");
            }
            System.out.print(i);
            for (int p = 1; p<= 7; p++){
                String currentPos =  Integer.toString(p)+ Integer.toString(i); // save the current position
                for (int g = 0; g<NumberOfRiver ; g++){ // to check whether have river inside of this box
                    if(currentPos.equals(Posconversion(riverL[g]))){
                        haveRiverInside = true;
                    }
                }
                for (int t = 0; t<NumberOfTrap ; t++){ // to check whether have trap inside of this box
                    if(currentPos.equals(Posconversion(trapL[t]))){
                        haveTrapInside = true;
                    }
                }
                for (int k = 0; k<=15 ; k++){ // to check whether have chess inside of this box
                    if (currentPos.equals( pieceList[k].getPosString())){
                        haveChessInside = true;
                        whichChess = k;
                    }
                }
                for (int j = 0; j < NumberOfDen; j++){ //print out the den
                    if(currentPos.equals(Posconversion(denL[j]))){
                        System.out.print("  DEN        ");
                        haveDenInside = true;
                    }
                }
                if (haveChessInside == true){ //check the piece is owned by which player
                    Boolean playerColor = pieceList[whichChess].getPlayerColor();
                    if (playerColor == true){
                        chessPlayer = "red";
                    }else{
                        chessPlayer = "black";
                    }
                    System.out.print(( " "+ chessPlayer.charAt(0) + pieceList[whichChess].getPieceName()));

                }else{
                    if (haveRiverInside == true){
                        System.out.print(" ~~~~~~~~~~~");
                    }
                    if (haveTrapInside == true){
                        System.out.print("  Trap     ");
                    }
                    if((haveTrapInside != true) && (haveDenInside != true) && (haveRiverInside != true)){
                        System.out.print("           "); //print an empty space to shows no piece at this position
                    }
                }
                haveChessInside = false;
                haveTrapInside = false;   
                haveDenInside = false;
                haveRiverInside = false;         
            }
            System.out.print("\n");
        }
        System.out.print("\n");
         
    }
    public void PrintCapturedPiece(Piece[] pList){ // list out which piece have been capture, if not piece has been capture, then it will not show in command line 
        ArrayList<String> capturedPieceNameForPlayerRed = new ArrayList<String>();
        ArrayList<String> capturedPieceNameForPlayerBlack = new ArrayList<String>();
        for (int p = 0 ; p < pList.length ; p++){
            if (pList[p].getPos().equals("Z1")){
                if (pList[p].getPlayerColor() == true){
                    capturedPieceNameForPlayerRed.add(pList[p].getPieceName());
                }else{
                    capturedPieceNameForPlayerBlack.add(pList[p].getPieceName());
                }
            }
        }
        if (capturedPieceNameForPlayerRed.size() != 0){
            System.out.print("The piece that player red has been captured : ");
            for (int g = 0; g < capturedPieceNameForPlayerRed.size(); g++){
                System.out.print(capturedPieceNameForPlayerRed.get(g) + "   ");
            }
            System.out.print("\n");
        }
        if (capturedPieceNameForPlayerBlack.size() != 0){
            System.out.print("The piece that player black has been captured : ");
            for (int g = 0; g < capturedPieceNameForPlayerBlack.size(); g++){
                System.out.print(capturedPieceNameForPlayerBlack.get(g) );
            }
            System.out.print("\n");
        }
    }
    public void PrintWelcomeMessaage(){ //print the starting screen
        System.out.println("Welcome to Jungle Game!");
    }
    public int PrintMenu(){ //print menu
        System.out.println("********************************************************************");
        System.out.println("This is a game menu");
        System.out.println("1: Start the game");
        System.out.println("2: Print game rule");
        System.out.print("Please input the number to indicate the action:");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        while(true){
            if (userAnswer.equals("1")){
                return 0;
            }
            if (userAnswer.equals("2")){
                PrintGuide();
                return 0;
            }
        System.out.println("Unknow Input!");
        scanner = new Scanner(System.in);
        userAnswer = scanner.nextLine();
        }
    }
    public void PrintEnd(Boolean winPlayer,int turn){ //print end message
        String playerName;
        if (winPlayer == true){
            playerName = "red";
        }else{
            playerName = "black";
        }
        System.out.println("********************************************************************");
        System.out.println("Game is finish !");
        System.out.println("Total turn : " + turn);
        System.out.println(playerName+" Player win the game");
    }

    public String CollectUserAction(String playerName){ // method for user input in gaming
        String xPosL[] = {"A","B","C","D","E","F","G"};
        String yPosL[] = {"1","2","3","4","5","6","7","8","9"};
        System.out.println("********************************************************************");
        System.out.println("You can input QUIT to stop the game OR input Piece Position and Target position to play the game");
        System.out.println("Format: X coordinate + Y coordinate, A1 move to A2, e.g A1,A2");
        System.out.print(playerName + " , Please input your action : ");
        Scanner scanner = new Scanner(System.in);
        String userAction = scanner.nextLine();
        while (true){ //check the input is valid or not
            if (userAction.equals("QUIT")){
                return userAction;
            }      
            String[] userInputL = userAction.split(",");
            if (userInputL.length == 2){
                if (userInputL[0].length() == 2 && userInputL[1].length() == 2){
                    boolean xCurrentFormatCorrect = false;
                    boolean xTargetFormatCorrect = false;
                    boolean yCurrentFormatCorrect = false;
                    boolean yTargetFormatCorrect = false;
                    String userInputCurrentX = userInputL[0].charAt(0) + "";
                    String userInputCurrentY = userInputL[0].charAt(1) + "";
                    String userInputTargetX = userInputL[1].charAt(0) + "";
                    String userInputTargetY = userInputL[1].charAt(1) + "";
                    for (int p = 0; p < xPosL.length ; p++){ // to check the x format is correct or not
                        if(xPosL[p].equals(userInputCurrentX)){
                            xCurrentFormatCorrect = true;
                        }
                        if (xPosL[p].equals(userInputTargetX)){
                            xTargetFormatCorrect = true;
                        }
                    }
                    for (int k = 0; k < yPosL.length ; k++){ // to check the y format is correct or not
                        if(yPosL[k].equals(userInputCurrentY)){
                            yCurrentFormatCorrect = true;
                        }
                        if(yPosL[k].equals(userInputTargetY)){
                            yTargetFormatCorrect = true;
                        }
                    }
                    if ((xCurrentFormatCorrect == true) && (xTargetFormatCorrect == true) && (yCurrentFormatCorrect == true) && (yTargetFormatCorrect == true)){ // to check it is fulfill the input format
                        return userAction;
                    }
                              
                }
            }
            System.out.println("********************************************************************");
            System.out.print("Unknown input , " + playerName + " , Please input your action: ");
            scanner = new Scanner(System.in);
            userAction = scanner.nextLine();
        }     
    }

    public void Warning(int type){ //print Warning or error message
        System.out.println("");
        switch(type){
            case 0:
                System.out.println("FAIL : Player can only move one square in x axis or y axis");
                break;
            case 1:
                System.out.println("FAIL : player moves one square horizontally or vertically (not diagonally)");
                break;
            case 2:
                System.out.println("FAIL : Player cannot move to same square, user must move");
                break;
            case 3:
                System.out.println("FAIL : This piece is not your");
                break;
            case 4:
                System.out.println("FAIL : This piece is not allowed to enter water square (Only rat allowed)");
                break;
            case 5:
                System.out.println("FAIL : No piece can be selected");
                break;
            case 6:
                System.out.println("FAIL : This piece do not have enought rank to capture the target");
                break;            
            case 7:
                System.out.println("FAIL : You can not capture your own piece");
                break;
            case 8:
                System.out.println("FAIL : Rat can not capture elephant in river area");
                break;
            case 9:
                System.out.println("FAIL : Only rat can enter the river area");
                break;   
            case 10:
                System.out.println("FAIL : Rat from river area cannot capture enemy rat on land area"); 
                break;
            case 11:
                System.out.println("FAIL : Rat from land area cannot capture enemy rat on river area");
                break;
            case 12:
                System.out.println("FAIL : Have piece inside of Intervening river square");
                break;
            case 13:
                System.out.println("FAIL : Elephant can not capture rat");
                break;
            default:
                System.out.println("ERROR");
            }

    }
    public void PrintGuide(){ //print guide
        System.out.println("1. Players alternate moves. During their turn, a player must move. Each piece moves one square horizontally or vertically (not diagonally).");
        System.out.println("2. Higher ranking pieces can capture all pieces of identical or lower ranking, but rat may capture the elephant");
        System.out.println("3. A piece may not move to its own den.");
        System.out.println("4. The rat is the only type of animal that is allowed to go onto a water square");
        System.out.println("5. The rat may not capture the elephant or another rat on land directly from a water square. Similarly, a rat on land may not attack a rat in the water.");
        System.out.println("6. he rat may attack the opponent rat if both pieces are in the water or on the land.");
        System.out.println("7. The lion and tiger pieces may jump over a river by moving horizontally or vertically. They move from a square on one edge of the river to the next non-water square on the other side. Such a move is not allowed if there is a rat (whether friendly or enemy) on any of the intervening water squares. The lion and tiger are allowed to capture enemy pieces by such jumping moves");
        System.out.println("8. The goal of the game is to move a piece onto the den on the opponent's side of the board or capture all the opponent's pieces.");
        System.out.println("********************************************************************");
    }

    public String[] Registration(){ //let user can select which player (color) start first
        System.out.print("Please input the which color start first (red/black):");
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.nextLine();
        String[] gameStartPriority = new String[2];
        while (true){
            if (userAnswer.equals("red")){
                gameStartPriority[0] = "red";
                gameStartPriority[1] = "black";
                System.out.println("The red color will start first, then followed by black color");
                break;
            }
            if (userAnswer.equals("black")){
                gameStartPriority[0] = "black";
                gameStartPriority[1] = "red";
                System.out.println("The black color will start first, then followed by red color");
                break;
            }
            System.out.println("Unknow input, try again");
            scanner = new Scanner(System.in);
            userAnswer = scanner.nextLine();
        }
        return gameStartPriority;
    }

    public String Posconversion(String pos){ //position conversion for internal use
        String x = pos.charAt(0) + "";
		String y = pos.charAt(1) + "";
		switch(x){
			case "A":
				x = "1";
				break;
			case "B":
				x = "2";
				break;
			case "C":
				x = "3";
				break;
			case "D":
				x = "4";
				break;
			case "E":
				x = "5";
				break;
			case "F":
				x = "6";
				break;
			case "G":
				x = "7";
				break;
			default:
				return null;
		}
		String posString = x + y;
		return posString;
    }
}

