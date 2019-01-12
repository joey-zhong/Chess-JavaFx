package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Piece {

	private Image image;
	private int delta;

	public Pawn(boolean isWhite, int x, int y) {
		super(new ImageView(), isWhite, x, y, 'P');

		if (isWhite) {
			image = new Image("/Chess Pieces/White Pawn.png");
			delta = -1;
		} else {
			image = new Image("/Chess Pieces/Black Pawn.png");
			delta = 1;
		}

		setView(image);
	}

	public void updateMoveList() {

		int newX = getX();
		int newY = getY() + 1 * delta;

		if (isOccupied(newX, newY) == 'N') {
			getMoveList().add(newX, newY, "NORMAL");
		}

		newX = getX();
		newY = getY() + 2 * delta;
		if (!getHasMoved() && isOccupied(newX, newY) == 'N') {
			getMoveList().add(newX, newY, "NORMAL");
		}

		newX = getX() - 1;
		newY = getY() + 1 * delta;
		if (!isOut(newX, newY) && isOccupied(newX, newY) == getOpposite(getIsWhite())) {
			getMoveList().add(newX, newY, "CAPTURE");
		}

		newX = getX() + 1;
		newY = getY() + 1 * delta;
		if (!isOut(newX, newY) && isOccupied(newX, newY) == getOpposite(getIsWhite())) {
			getMoveList().add(newX, newY, "CAPTURE");
		}
	}
}
