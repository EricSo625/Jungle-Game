package Model;
public class ChessBoard{
	private int cRow;
	private int cCol;
	private Piece cPiece;
	public ChessBoard(int row, int col){
		cRow = row;
		cCol = col;
	}
	
	public Piece getPiece(int row, int col){
		return cPiece;
	}

	public void setPiece(Piece piece){
		cRow = piece.getX();
		cCol = piece.getY();
		cPiece = piece;
	}
	
	public String[] Trap(){
		int numberOfTrap =  6; 
		String TrapList[] = new String[numberOfTrap];
		TrapList[0] = "C1";
		TrapList[1] = "D2"; 
		TrapList[2] = "E1"; 
		TrapList[3] = "C9"; 
		TrapList[4] = "D8"; 
		TrapList[5] = "E9"; 
		return TrapList;
	}
	
	public String[] River(){
		int numberOfRiver = 12;
		String[] riverL = new String[numberOfRiver];
		riverL[0] = "B4";
		riverL[1] = "B5";
		riverL[2] = "B6";
		riverL[3] = "C4";
		riverL[4] = "C5";
		riverL[5] = "C6";
		riverL[6] = "E4";
		riverL[7] = "E5";
		riverL[8] = "E6";
		riverL[9] = "F4";
		riverL[10] = "F5";
		riverL[11] = "F6";
		return riverL;
	}
	public String[] RiverBorderCanBeJumpOver(){ //the square that can be jump over
		int numberOfRiverBorder = 20;
		String[] riverBorderList = new String[numberOfRiverBorder];
		// first two char is the starting square and last two square is the end square
		riverBorderList[0] = "A6D6"; 
		riverBorderList[1] = "A5D5";
		riverBorderList[2] = "A4D4";
		riverBorderList[3] = "D6A6";
		riverBorderList[4] = "D5A5";
		riverBorderList[5] = "D4A4";
		riverBorderList[6] = "D6G6";
		riverBorderList[7] = "D5G5";
		riverBorderList[8] = "D4G4";
		riverBorderList[9] = "G6D6";
		riverBorderList[10] = "G5D5";
		riverBorderList[11] = "G4D4";
		riverBorderList[12] = "B7B3";
		riverBorderList[13] = "C7C3";
		riverBorderList[14] = "B3B7";
		riverBorderList[15] = "C3C7";
		riverBorderList[16] = "E7E3";
		riverBorderList[17] = "F7F3";
		riverBorderList[18] = "E3E7";
		riverBorderList[19] = "F3F7";
		return riverBorderList;
	}
	public String[] RiverInterveningSquare(){
		int numberOfRiverIntervening = 20;
		String[] riverInterveningSquareList = new String[numberOfRiverIntervening];
		// the first two char is represent the position of starting point
		// the last two char is represent the position of ending point
		// the rest of the char is the intervening square position
        riverInterveningSquareList[0] = "A4,B4,C4,D4";
		riverInterveningSquareList[1] = "A5,B5,C5,D5";
		riverInterveningSquareList[2] = "A6,B6,C6,D6";
		riverInterveningSquareList[3] = "B7,B6,B5,B4,B3";
		riverInterveningSquareList[4] = "C7,C6,C5,C4,C3";
		riverInterveningSquareList[5] = "B3,B4,B5,B6,B7";
		riverInterveningSquareList[6] = "C3,C4,C5,C6,C7";
		riverInterveningSquareList[7] = "G6,F6,E6,D6";
		riverInterveningSquareList[8] = "G5,F5,E5,D5";
		riverInterveningSquareList[9] = "G4,F4,E4,D4";
		riverInterveningSquareList[10] = "E7,E6,E5,E4,E3";
		riverInterveningSquareList[11] = "F7,F6,F5,F4,F3";
		riverInterveningSquareList[12] = "E3,E4,E5,E6,E7";
		riverInterveningSquareList[13] = "F3,F4,F5,F6,F7";
		riverInterveningSquareList[14] = "D6,C6,B6,A6";
		riverInterveningSquareList[15] = "D6,E6,F6,G6";
		riverInterveningSquareList[16] = "D5,C5,B5,A5";
		riverInterveningSquareList[17] = "D5,E5,F5,G5";
		riverInterveningSquareList[18] = "D4,C4,B4,A4";
		riverInterveningSquareList[19] = "D4,E4,F4,G4";
		return riverInterveningSquareList;
	}
	public String[] Den(){
		int numberOfDen = 2;
		String[] denL = new String[numberOfDen];
		denL[0] = "D1";
		denL[1] = "D9";
		return denL;
	}
}