import java.util.*;

public class Fifteen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            System.out.print("p: play\nq: quit\nChooose: ");
            String option = sc.next();
            switch (option) {
                case "p":
                    boolean test = play(sc);
                    if (!test) {
                        option = "q";
                    }
                    else {
                        break;
                    }
                case "q":
                    cont = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
    }

    public static boolean play(Scanner sc) {
        Board game = new Board();
        game.scrambleBoard(false);
        game.showBoard();

        while (!game.isSolved()) {
            System.out.print("Enter a move (wasd to move, n for new game, q to quit): ");
            String dir = sc.next();
            System.out.print('\f');
            if (dir.equalsIgnoreCase("q")) {
                return false;
            }
            if (dir.equalsIgnoreCase("n")) {
                return play(sc);
            }
            if (game.validMove(dir)) {
                game.makeMove(dir);
            }
            else {
                System.out.println("Not a valid move.");
            }
            game.showBoard();
        }
        System.out.println("Yay you win");
        return true;
    }
}