package util;

public class SudokuBoard {
    private static final int SIZE = 9;
    private int[][] board;
    private boolean[][] fixedNumbers;

    public SudokuBoard(String[] initialValues) {
        board = new int[SIZE][SIZE];
        fixedNumbers = new boolean[SIZE][SIZE];
        initializeBoard(initialValues);
    }

    private void initializeBoard(String[] initialValues) {
        if (initialValues != null) {
            for (String value : initialValues) {
                String[] parts = value.split(",");
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                int num = Integer.parseInt(parts[2]);
                board[row][col] = num;
                fixedNumbers[row][col] = true;
            }
        }
    }

    public void placeNumber(int row, int col, int number) {
        validatePosition(row, col);
        validateNumber(number);

        if (fixedNumbers[row][col]) {
            throw new IllegalArgumentException("Esta posição contém um número fixo e não pode ser alterada!");
        }

        if (board[row][col] != 0) {
            throw new IllegalArgumentException("Esta posição já contém um número!");
        }

        board[row][col] = number;
    }

    public void removeNumber(int row, int col) {
        validatePosition(row, col);

        if (fixedNumbers[row][col]) {
            throw new IllegalArgumentException("Não é possível remover números fixos!");
        }

        board[row][col] = 0;
    }

    public void clearUserNumbers() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (!fixedNumbers[i][j]) {
                    board[i][j] = 0;
                }
            }
        }
    }

    public boolean isComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasConflicts() {
        // Verifica linhas
        for (int i = 0; i < SIZE; i++) {
            if (hasDuplicateInRow(i)) {
                return true;
            }
        }

        // Verifica colunas
        for (int j = 0; j < SIZE; j++) {
            if (hasDuplicateInColumn(j)) {
                return true;
            }
        }

        // Verifica quadrantes 3x3
        for (int boxRow = 0; boxRow < SIZE; boxRow += 3) {
            for (int boxCol = 0; boxCol < SIZE; boxCol += 3) {
                if (hasDuplicateInBox(boxRow, boxCol)) {
                    return true;
                }
            }
        }

        return false;
    }

    public String getGameStatus() {
        if (isBoardEmpty()) {
            return "Não iniciado";
        } else if (isComplete()) {
            return "Completo";
        } else {
            return "Incompleto";
        }
    }

    public void printBoard() {
        System.out.println("\n  -----------------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
                if ((j + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if ((i + 1) % 3 == 0) {
                System.out.println("  -----------------------");
            }
        }
        System.out.println("    1 2 3   4 5 6   7 8 9");
    }

    private boolean isBoardEmpty() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0 && !fixedNumbers[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean hasDuplicateInRow(int row) {
        boolean[] seen = new boolean[SIZE + 1];
        for (int j = 0; j < SIZE; j++) {
            int num = board[row][j];
            if (num != 0) {
                if (seen[num]) {
                    return true;
                }
                seen[num] = true;
            }
        }
        return false;
    }

    private boolean hasDuplicateInColumn(int col) {
        boolean[] seen = new boolean[SIZE + 1];
        for (int i = 0; i < SIZE; i++) {
            int num = board[i][col];
            if (num != 0) {
                if (seen[num]) {
                    return true;
                }
                seen[num] = true;
            }
        }
        return false;
    }

    private boolean hasDuplicateInBox(int startRow, int startCol) {
        boolean[] seen = new boolean[SIZE + 1];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = board[startRow + i][startCol + j];
                if (num != 0) {
                    if (seen[num]) {
                        return true;
                    }
                    seen[num] = true;
                }
            }
        }
        return false;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Posição inválida! Use valores entre 1 e 9.");
        }
    }

    private void validateNumber(int number) {
        if (number < 1 || number > 9) {
            throw new IllegalArgumentException("Número inválido! Use valores entre 1 e 9.");
        }
    }
}