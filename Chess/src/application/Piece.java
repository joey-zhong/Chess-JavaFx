package application;

import application.MoveList.Move;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends StackPane {

	private ImageView view;
	private boolean isWhite;
	private int x, y;
	private double pixelX, pixelY;
	private double mouseX, mouseY;
	private Character type;
	private boolean hasMoved = false;
	private MoveList moveList = new MoveList();

	public Piece(ImageView view, boolean isWhite, int x, int y, char type) {
		this.view = view;
		this.isWhite = isWhite;
		this.x = x;
		this.y = y;
		this.type = type;
		this.moveList = new MoveList();
		setOnMouse();
	}

	public void setOnMouse() {

		setOnMousePressed(e -> {
			setMouseX(e.getSceneX());
			setMouseY(e.getSceneY());

			for (Move m : moveList.getMoveList()) {
				Circle c = new Circle(m.getX() * Main.SQUARE_SIZE + 40, m.getY() * Main.SQUARE_SIZE + 40, 14,
						m.getType().equals("NORMAL") ? Color.GREEN : Color.RED);
				Main.availableSquares.getChildren().add(c);
			}
		});

		setOnMouseDragged(e -> {
			relocate(e.getSceneX() - getMouseX() + getPixelX(), e.getSceneY() - getMouseY() + getPixelY());
		});

		setOnMouseReleased(e -> {
			Main.availableSquares.getChildren().clear();
			int newX = toBoard(getLayoutX());
			int newY = toBoard(getLayoutY());

			if (!moveList.isInMoveList(newX, newY)) {
				relocate(pixelX, pixelY);
				// resets the location of the piece
			} else {

				if (newX != x || newY != y && !hasMoved) {
					hasMoved = true;
				}

				Main.board[(int) getX()][(int) getY()].setPiece(null);

				if (Main.board[newX][newY].hasPiece()) {
					Main.pieces.getChildren().remove(Main.board[newX][newY].getPiece());
				}

				Main.board[newX][newY].setPiece(this);
				move(newX, newY);
				moveList.clear();
				Main.updateAllMoveList();
			}
		});
	}

	public static int toBoard(double pixel) {
		return (int) (pixel + Main.SQUARE_SIZE / 2) / Main.SQUARE_SIZE;
	}

	public boolean isOut(int x, int y) {
		return x < 0 || x >= 8 || y < 0 || y >= 8;
	}

	public char isOccupied(int x, int y) {
		if (!Main.board[x][y].hasPiece()) {
			return 'N';
		} else if (Main.board[x][y].getPiece().getIsWhite()) {
			return 'W';
		} else {
			return 'B';
		}
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		move();
	}

	public void move() {
		pixelX = x * Main.SQUARE_SIZE + 10;
		pixelY = y * Main.SQUARE_SIZE + 10;
		relocate(pixelX, pixelY);
	}

	public ImageView getView() {
		return view;
	}

	public void setView(Image i) {
		view.setImage(i);
		view.setFitHeight(Main.SQUARE_SIZE - 20);
		view.setFitWidth(Main.SQUARE_SIZE - 20);
		view.preserveRatioProperty();
		move();
		getChildren().add(view);
	}

	public boolean getIsWhite() {
		return isWhite;
	}

	public void setIsWhite(boolean f) {
		this.isWhite = f;
	}

	public int getX() {
		return x;
	}

	public void setX(int n) {
		this.x = n;
	}

	public int getY() {
		return y;
	}

	public void setY(int n) {
		this.y = n;
	}

	public char getType() {
		return type;
	}

	public void setType(char c) {
		type = c;
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double n) {
		this.mouseX = n;
	}

	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(double n) {
		this.mouseY = n;
	}

	public double getPixelX() {
		return pixelX;
	}

	public double getPixelY() {
		return pixelY;
	}

	public boolean getHasMoved() {
		return hasMoved;
	}

	public void setHasMoved(boolean f) {
		hasMoved = f;
	}

	public MoveList getMoveList() {
		return moveList;
	}

	public void updateMoveList() {
		// overriden method
	}

	public Character getOpposite(boolean isW) {
		return isW ? 'B' : 'W';
	}
}
