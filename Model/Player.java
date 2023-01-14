package Model;
public class Player{
	private boolean pColor; //True represent red, False represent black
	private int pTurn;

	public Player(boolean playerColor, int playerTurn){
		pColor = playerColor;
		pTurn = playerTurn;
	}
	
	public boolean getPlayerColor(){ // return player color
		return pColor;
	}
	
	public int getPlayerTurn(){ // return the number of turn
		return pTurn;
	}

	public void UpdataPlyerTurn(int newTurn){ //update the player turn
		pTurn = newTurn;
	}
}