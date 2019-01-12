package application;

import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {

	public static final int SQUARE_SIZE = 80;
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;

	public static Group squares = new Group();
	public static Group pieces = new Group();
	public static Group availableSquares = new Group();
	public static Square[][] board = new Square[HEIGHT][WIDTH];

	public Parent createContent() {
		Pane root = new Pane();
		root.setPrefSize(WIDTH * SQUARE_SIZE, HEIGHT * SQUARE_SIZE);
		root.getChildren().addAll(availableSquares, squares, pieces);
		availableSquares.toFront();
		// pieces.toFront();

		for (int j = 0; j < HEIGHT; j++) {
			for (int i = 0; i < WIDTH; i++) {
				Square square = new Square((i + j) % 2 == 0, i, j);
				squares.getChildren().add(square);

				Piece piece = makePiece(i, j);

				if (piece != null) {
					square.setPiece(piece);
					pieces.getChildren().add(piece);
				}
				board[i][j] = square;
			}
		}

		updateAllMoveList();
		return root;
	}

	private Piece makePiece(int i, int j) {
		Piece piece = null;

		if (j == 1 || j == 6) {
			piece = new Pawn(j == 6 ? true : false, i, j);
		}
		if ((i == 0 && j == 0) || (i == 7 && j == 0) || (i == 0 && j == 7) || (i == 7 && j == 7)) {
			piece = new Rook(j == 7 ? true : false, i, j);
		}
		if ((i == 1 && j == 0) || (i == 6 && j == 0) || (i == 1 && j == 7) || (i == 6 && j == 7)) {
			piece = new Knight(j == 7 ? true : false, i, j);
		}
		if ((i == 2 && j == 0) || (i == 5 && j == 0) || (i == 2 && j == 7) || (i == 5 && j == 7)) {
			piece = new Bishop(j == 7 ? true : false, i, j);
		}
		if ((i == 3 && j == 0) || (i == 3 && j == 7)) {
			piece = new Queen(j == 7 ? true : false, i, j);
		}
		if ((i == 4 && j == 0) || (i == 4 && j == 7)) {
			piece = new King(j == 7 ? true : false, i, j);
		}

		return piece;
	}

	public static void updateAllMoveList() {
		for (int j = 0; j < HEIGHT; j++) {
			for (int i = 0; i < WIDTH; i++) {
				if (board[i][j].hasPiece()) {
					board[i][j].getPiece().updateMoveList();
				}
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Chess");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
