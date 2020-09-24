import lombok.Getter;

@Getter
public class Game {
    Player playerOne;
    Player playerTwo;
    String[][] board = new String[3][3];
    String lastPlayedSign = "";
    String winner;
    int moveCount;


    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void playerOneMove(int horizontal, int vertical) {
        checkIsValid(vertical, horizontal);
        move(horizontal, vertical, "X");
    }

    public void playerTwoMove(int horizontal, int vertical) {
        checkIsValid(horizontal, vertical);
        move(horizontal, vertical, "O");

    }

    public void checkIsValid(int horizontal, int vertical) {
        if (horizontal > 2 || horizontal < 0 || vertical < 0 || vertical > 2) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }

    public void move(int horizontal, int vertical, String sign) {
        if (this.lastPlayedSign.equals(sign)) {
            throw new UnsupportedOperationException("Opponent turn, please wait yours.");
        }
        if (!String.valueOf(this.board[horizontal][vertical]).equals("null")) {
            throw new UnsupportedOperationException("Please pick empty area.");
        }
        this.lastPlayedSign = sign;

        this.board[horizontal][vertical] = sign;
        this.moveCount += 1;
        this.checkBoard();
    }

    public void checkBoard() {
        try {
            for (int i = 0; i < 3; i++) {
                if (this.board[i][0].charAt(0) == this.board[i][1].charAt(0) &&
                        this.board[i][0].charAt(0) == this.board[i][2].charAt(0)) {
                    this.getWinnerNameAndSign(this.board[i][0]);
                }
                if (this.board[0][i].charAt(0) == this.board[1][i].charAt(0) &&
                        this.board[0][i].charAt(0) == this.board[2][i].charAt(0)) {
                    this.getWinnerNameAndSign(this.board[0][i]);
                }
            }
            if (this.board[0][0].charAt(0) == this.board[1][1].charAt(0) &&
                    this.board[0][1].charAt(0) == this.board[2][2].charAt(0)) {
                this.getWinnerNameAndSign(this.board[0][0]);
            }
            if (this.board[0][2].charAt(0) == this.board[1][1].charAt(0) &&
                    this.board[0][2].charAt(0) == this.board[2][0].charAt(0)) {
                this.getWinnerNameAndSign(this.board[0][2]);
            }
            if (this.moveCount == 9) {
                this.getWinnerNameAndSign("XOX");
            }
        } catch (Exception e) {

        }
    }

    public void getWinnerNameAndSign(String sign) {
        switch (sign) {
            case "X":
                System.out.println("The player as named " + this.playerOne.name + " with that sign => " + sign);
                this.winner = this.playerOne.name;
                this.clear();
                break;
            case "O":
                System.out.println("The player as named " + this.playerTwo.name + " with that sign => " + sign);
                this.winner = this.playerTwo.name;
                this.clear();
                break;
            case "XOX":
                System.out.println("The game is draw");
                this.winner = "draw";
                this.clear();
                break;
        }
    }

    public void clear() {
        this.board = new String[3][3];
    }
}
