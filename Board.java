public class Board {
	private int[][] grid;

	public Board() {
		grid = new int[4][4];
		newBoard();
	}

	private void newBoard() {
		// Resets the board, so all numbers are in order.
		int count = 1;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (count <= 15) {
					grid[r][c] = count;
					count++;
				}
			}
		}
	}

	public void scrambleBoard(boolean show) {
		// Picks 100 random directions/moves to make. Hopefully scrambles 
		// the board.
		// 0 -> up = 'w'
		// 1 -> left = 'a'
		// 2 -> down = 's'
		// 3 -> right = 'd'
		int count = 0;
		while (count < 100) {
			String move = "";
			int random = (int)(Math.random() * 4);
			if (random == 0) {
				move = "w";
			}
			else if (random == 1) {
				move = "a";
			}
			else if (random == 2) {
				move = "s";
			}
			else {
				move = "d";
			}
			if (validMove(move)) {
				makeMove(move);
				count++;
				if (show) {
					showBoard();
				}
			}
		}
	}

	public void makeMove(String dir) {
		int[] move = spaceFromDir(dir);
		switchEmpty(move);
	}

	public boolean validMove(String dir) {
		int[] space = spaceFromDir(dir);
		if (space[0] < 0 || space[0] > 3 || space[1] < 0 || space[1] > 3) {
			return false;
		}
		return true;
	}

	private int[] spaceFromDir(String dir) {
		// Assume dir is 'w', 'a', 's', or 'd'.
		// Assume dir is valid.
		// w -> move up -> target = space below empty
		// a -> move left -> target = space right of empty
		// s -> move down -> target = space above empty
		// d -> move right -> target = space left of empty

		int[] target = getEmpty();
		if (dir.equalsIgnoreCase("w")) {
			target[0]++;
		}
		else if (dir.equalsIgnoreCase("a")) {
			target[1]++;
		}
		else if (dir.equalsIgnoreCase("s")) {
			target[0]--;
		}
		else if (dir.equalsIgnoreCase("d")) {
			target[1]--;
		}
		return target;
	}

	private void switchEmpty(int[] space) {
		// Assume this is a valid move, i.e. the space is next to the empty
		// space.
		int row1 = space[0];
		int col1 = space[1];
		int[] e = getEmpty();
		int row2 = e[0];
		int col2 = e[1];

		grid[row2][col2] = grid[row1][col1];
		grid[row1][col1] = 0;
	}

	private int[] getEmpty() {
		// Returns the {row, col} of the empty space
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == 0) {
					return new int[]{r, c};
				}
			}
		}
		return new int[]{-1, -1};
	}

	private boolean isEmpty(int[] space) {
		// space must be length 2
		// Checks if a space is empty
		int r = space[0];
		int c = space[1];

		return (grid[r][c] == 0);
	}

	public boolean isSolved() {
		int count = 1;
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (count <= 15) {
					if (grid[r][c] != count) {
						return false;
					}
					count++;
				}
			}
		}
		return true;
	}

	public void showBoard() {
		for (int r = 0; r < grid.length; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] >= 10) {
					System.out.print(grid[r][c] + " ");
				}
				else {
					System.out.print(grid[r][c] + "  ");
				}
			}
			System.out.println();
		}
	}
}