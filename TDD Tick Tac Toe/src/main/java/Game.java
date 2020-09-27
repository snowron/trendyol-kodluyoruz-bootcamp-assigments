
public class Game {
    private final Player playerOne;
    private final Player playerTwo;
    private char[][] board = new char[3][3];
    private char lastPlayedSign;
    private String winner;
    private int moveCount;


    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public void playerOneMove(int horizontal, int vertical) {
        checkIsValid(vertical, horizontal);
        move(horizontal, vertical, 'X');
    }

    public void playerTwoMove(int horizontal, int vertical) {
        checkIsValid(horizontal, vertical);
        move(horizontal, vertical, 'O');
    }

    public void checkIsValid(int horizontal, int vertical) {
        if (horizontal > 2 || horizontal < 0 || vertical < 0 || vertical > 2) {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }

    public void move(int horizontal, int vertical, char sign) {
        if (this.lastPlayedSign == sign) {
            throw new UnsupportedOperationException("Opponent turn, please wait yours.");
        }
        if (this.board[horizontal][vertical] != '\u0000') {
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
                if (this.board[i][0] == this.board[i][1] &&
                        this.board[i][0] == this.board[i][2]) {
                    this.getWinnerNameAndSign(this.board[i][0]);
                }
                if (this.board[0][i] == this.board[1][i] &&
                        this.board[0][i] == this.board[2][i]) {
                    this.getWinnerNameAndSign(this.board[0][i]);
                }
            }
            if (this.board[0][0] == this.board[1][1] &&
                    this.board[0][1] == this.board[2][2]) {
                this.getWinnerNameAndSign(this.board[0][0]);
            }
            if (this.board[0][2] == this.board[1][1] &&
                    this.board[0][2] == this.board[2][0]) {
                this.getWinnerNameAndSign(this.board[0][2]);
            }
            if (this.moveCount == 9) {
                this.getWinnerNameAndSign('=');
            }
        } catch (Exception e) {

        }
    }

    public void getWinnerNameAndSign(char sign) {
        switch (sign) {
            case 'X':
                System.out.println("The player as named " + this.playerOne.name + " with that sign => " + sign);
                this.winner = this.playerOne.name;
                this.clear();
                break;
            case 'O':
                System.out.println("The player as named " + this.playerTwo.name + " with that sign => " + sign);
                this.winner = this.playerTwo.name;
                this.clear();
                break;
            case '=':
                System.out.println("The game is draw");
                this.winner = "draw";
                this.clear();
                break;
        }
    }

    public void clear() {
        this.board = new char[3][3];
    }

    public char getBoardCellWithCoordinates(int horizontal, int vertical) {
        return this.board[horizontal][vertical];
    }
    public String getWinner() {
        return this.winner;
    }
}
