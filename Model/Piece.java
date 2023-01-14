package Model;
import java.lang.*;
public class Piece{
	private Player pPlayer;
	private int pRank;
	private String pPos;

	public Piece(Player player, int rank, String pos){
		pPlayer = player;
		pRank = rank;
		pPos = pos;
	}
	
	public String getPos(){
		return pPos;
	}
	
	// X coordinate
	public int getX(){
		String y = pPos.charAt(0) + "";
		switch(y){
			case "A":
				return 1;
			case "B":
				return 2;
			case "C":
				return 3;
			case "D":
				return 4;
			case "E":
				return 5;
			case "F":
				return 6;
			case "G":
				return 7;
			default:
				return -1;
		}
		
	}

	// Y coordinate
	public int getY(){
		return pPos.charAt(1);
	}
	
	//return player color
	public boolean getPlayerColor(){
		return pPlayer.getPlayerColor();
	}

	//return the position in A1 format
	public String getPosString(){
		String x = pPos.charAt(0) + "";
		String y = pPos.charAt(1) + "";
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
	// combination of XY coordinates
	public void setPos(String pos){
		pPos = pos;
	}
	
	// return piece rank
	public int getRank(){
		return pRank;
	}
	
	//set piece capture
	public void setCaptured(){
		pPos = "Z1"; // unexist position to represent the piece is removed
	}
	//set piece traped
	public void setTraped(){
		pRank = pRank * -1;
	}
	//set piece not traped
	public void setUntraped(){
		pRank = Math.abs(pRank);
	}
	// piece can swim
	public boolean swim(){
		return false;
	}
	
	// piece can jump
	public boolean jump(){
		return false;
	}
	
	// name of the piece
	public String getPieceName(){
		switch (pRank){
			case -1:
				return "Rat(T)     ";
			case -2:
				return "Cat(T)     ";
			case -3:
				return "Dog(T)     ";
			case -4:
				return "Wolf(T)    ";
			case -5:
				return "Leopad(T)  ";
			case -6:
				return "Tiger(T)   ";
			case -7:
				return "Lion(T)    ";
			case -8:
				return "Elephant(T)";
			case 1:
				return "Rat        ";
			case 2:
				return "Cat        ";
			case 3:
				return "Dog        ";
			case 4:
				return "Wolf       ";
			case 5:
				return "Leopad     ";
			case 6:
				return "Tiger      ";
			case 7:
				return "Lion       ";
			case 8:
				return "Elephant   ";
			default:
				return "~";
		}
	}

}