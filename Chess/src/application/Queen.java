package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Piece {

	private Image image;

	public Queen(boolean isWhite, int x, int y) {
		super(new ImageView(), isWhite, x, y, 'Q');

		if (isWhite) {
			image = new Image("/Chess Pieces/White Queen.png");
		} else {
			image = new Image("/Chess Pieces/Black Queen.png");

		}

		setView(image);
	}

	public void updateMoveList() {

		int[] dx = { -1, -1, 1, 1, -1, 1, 0, 0 };
		int[] dy = { 1, -1, 1, -1, 0, 0, -1, 1 };
		boolean[] b = new boolean[8];

		for (int i = 1; i <= 7; i++) {
			for (int j = 0; j < 8; j++) {

				int newX = getX() + i * dx[j];
				int newY = getY() + i * dy[j];

				if (!b[j] && !isOut(newX, newY)) {
					if (isOccupied(newX, newY) == 'N') {
						getMoveList().add(newX, newY, "NORMAL");
					} else if (isOccupied(newX, newY) == getOpposite(getIsWhite())) {
						getMoveList().add(newX, newY, "Capture");
						b[j] = true;
					} else {
						b[j] = true;
					}
				}

			}
		}
	}
}
