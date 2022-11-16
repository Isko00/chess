package front;

import backs.*;

public class ConsoleOutput {
    Tile[][] board;

    public ConsoleOutput(Board board) {
        this.board = board.getBoard();
    }

    public ConsoleOutput() {}

    private String letterLine() {
        String answer = "   ";

        for (char i = 'A'; i < 'I'; i++) {
            answer += "|-" + i + "--";
        }

        answer += "|";

        return answer;
    }

    public void print() {
        String div = "-".repeat(47);
        String letters = letterLine();

        System.out.println(letters + "\n" + div);

        for (int i = 1; i <= 8; i++) {
            System.out.print("-" + i + "-");
        
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i - 1][j]);
            }
        
            System.out.println("|-" + i + "-\n" + div);
        }
        
        System.out.println(letters);
    }
}
