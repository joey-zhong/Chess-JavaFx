package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends Rectangle {

	private Piece piece;

	public Square(boolean isLight, int x, int y) {
		setWidth(Main.SQUARE_SIZE);
		setHeight(Main.SQUARE_SIZE);

		relocate(x * Main.SQUARE_SIZE, y * Main.SQUARE_SIZE);
		setFill(isLight ? Color.BEIGE : Color.BURLYWOOD);
	}

	public boolean hasPiece() {
		return piece != null;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece p) {
		this.piece = p;
	}
}
