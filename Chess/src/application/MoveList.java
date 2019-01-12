package application;

import java.util.ArrayList;

public class MoveList {

	private ArrayList<Move> list;
	public MoveList() {
		this.list = new ArrayList<>();
	}
	
	public boolean isInMoveList(int x, int y) {
		for(Move m : list) {
			if(m.getX() == x && m.getY() == y){
				return true;
			}
		}
		return false;
	}
	
	public void add(int x, int y, String s) {
		list.add(new Move(x, y, s));
	}
	
	public void clear() {
		list.clear();
	}
	
	public ArrayList<Move> getMoveList(){
		return list;
	}
	
	public void setMoveList(ArrayList<Move> x) {
		list = x;
	}
	
	public class Move{
		int x, y;
		String type;
		
		public Move(int x, int y, String s) {
			this.x = x;
			this.y = y;
			this.type = s;
		}

		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public String getType() {
			return type;
		}
	}
}
