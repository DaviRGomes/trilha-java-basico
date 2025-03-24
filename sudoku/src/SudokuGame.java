import java.util.Scanner;
import util.SudokuBoard;

public class Main {
    private static SudokuBoard board;
    private static boolean gameRunning = true;

    public static void main(String[] args) {
        board = new SudokuBoard(args);
        Scanner scanner = new Scanner(System.in);

        while (gameRunning) {
            printMenu();
            int choice = scanner.nextInt();
            handleMenuChoice(choice, scanner);
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== MENU SUDOKU ===");
        System.out.println("1. Iniciar novo jogo");
        System.out.println("2. Colocar número");
        System.out.println("3. Remover número");
        System.out.println("4. Verificar jogo atual");
        System.out.println("5. Verificar status do jogo");
        System.out.println("6. Limpar números do usuário");
        System.out.println("7. Finalizar jogo");
        System.out.print("Escolha uma opção: ");
    }

    private static void handleMenuChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("\nNovo jogo iniciado:");
                board.printBoard();
                break;
            case 2:
                placeNumber(scanner);
                break;
            case 3:
                removeNumber(scanner);
                break;
            case 4:
                board.printBoard();
                break;
            case 5:
                checkGameStatus();
                break;
            case 6:
                board.clearUserNumbers();
                System.out.println("Números do usuário removidos!");
                board.printBoard();
                break;
            case 7:
                finishGame();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void placeNumber(Scanner scanner) {
        System.out.print("Digite o número (1-9): ");
        int number = scanner.nextInt();
        System.out.print("Digite a linha (1-9): ");
        int row = scanner.nextInt() - 1;
        System.out.print("Digite a coluna (1-9): ");
        int col = scanner.nextInt() - 1;

        try {
            board.placeNumber(row, col, number);
            System.out.println("Número colocado com sucesso!");
            board.printBoard();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removeNumber(Scanner scanner) {
        System.out.print("Digite a linha (1-9): ");
        int row = scanner.nextInt() - 1;
        System.out.print("Digite a coluna (1-9): ");
        int col = scanner.nextInt() - 1;

        try {
            board.removeNumber(row, col);
            System.out.println("Número removido com sucesso!");
            board.printBoard();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void checkGameStatus() {
        String status = board.getGameStatus();
        boolean hasErrors = board.hasConflicts();

        System.out.println("\nStatus do jogo: " + status);
        System.out.println("Contém erros: " + (hasErrors ? "Sim" : "Não"));

        if (status.equals("Completo")) {
            if (!hasErrors) {
                System.out.println("Parabéns! Você resolveu o Sudoku corretamente!");
            } else {
                System.out.println("O jogo está completo, mas com erros.");
            }
        }
    }

    private static void finishGame() {
        if (board.isComplete() && !board.hasConflicts()) {
            System.out.println("Parabéns! Você completou o Sudoku corretamente!");
            gameRunning = false;
        } else {
            System.out.println("O jogo não está completo ou contém erros. Continue tentando!");
        }
    }
}