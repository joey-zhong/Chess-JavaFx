package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Piece {

	private Image image;

	public Knight(boolean isWhite, int x, int y) {
		super(new ImageView(), isWhite, x, y, 'N');

		if (isWhite) {
			image = new Image("/Chess Pieces/White Knight.png");
		} else {
			image = new Image("/Chess Pieces/Black Knight.png");
		}

		setView(image);
	}

	public void updateMoveList() {
		int[] dx = { -1, -1, 1, 1, -2, -2, 2, 2 };
		int[] dy = { -2, 2, -2, 2, -1, 1, -1, 1 };

		for (int i = 0; i < 8; i++) {
			
			int newX = getX() + dx[i];
			int newY = getY() + dy[i];
			
			if (!isOut(newX, newY)) {
				if (isOccupied(newX, newY) == 'N') {
					getMoveList().add(newX, newY, "NORMAL");
				} else if (isOccupied(newX, newY) == getOpposite(getIsWhite())) {
					getMoveList().add(newX, newY, "Capture");
				}
			}
		}
	}
}
