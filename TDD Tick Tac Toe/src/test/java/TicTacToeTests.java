import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.lang.IllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TicTacToeTests {
    Player one = new Player("murat");
    Player two = new Player("turan");

    Game sut = new Game(one, two);

    @AfterEach
    public void cleanUp() {
        sut.clear();
    }

    @Test
    public void it_should_successfully_add_move_to_board_player_one() {
        sut.playerOneMove(2, 0);
        assertThat(sut.getBoard()[2][0]).isEqualTo("X");
    }

    @Test
    public void it_should_successfully_add_move_to_board_player_two() {
        sut.playerTwoMove(1, 1);
        assertThat(sut.getBoard()[1][1]).isEqualTo("O");
    }

    @Test
    public void it_should_successfully_win_the_game_player_two() {
        sut.playerOneMove(2, 2);
        sut.playerTwoMove(0, 1);
        sut.playerOneMove(2, 1);
        sut.playerTwoMove(0, 2);
        sut.playerOneMove(1, 1);
        sut.playerTwoMove(0, 0);
        assertThat(sut.getWinner()).isEqualTo("turan");
    }

    @Test
    public void it_should_successfully_must_draw_game() {
        sut.playerOneMove(0, 0);
        sut.playerTwoMove(1, 0);
        sut.playerOneMove(2, 0);
        sut.playerTwoMove(1, 1);
        sut.playerOneMove(2, 2);
        sut.playerTwoMove(2, 1);
        sut.playerOneMove(1, 2);
        sut.playerTwoMove(0, 2);
        sut.playerOneMove(0, 1);
        assertThat(sut.getWinner()).isEqualTo("draw");
    }

    @Test
    public void it_should_throw_exception_invalid_parameter_exception_when_wrong_coordinate() {
        Throwable throwable = catchThrowable(() -> sut.playerOneMove(0, 4));
        assertThat(throwable).hasMessage("Invalid coordinate").isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void it_should_throw_exception_unsupported_operation_exception_when_player_plays_twice() {
        sut.playerOneMove(0, 1);
        Throwable throwable = catchThrowable(() -> sut.playerOneMove(0, 2));
        assertThat(throwable).hasMessage("Opponent turn, please wait yours.").isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void it_should_throw_exception_unsupported_operation_exception_when_player_plays_not_empty_place() {
        sut.playerOneMove(0, 1);
        Throwable throwable = catchThrowable(() -> sut.playerTwoMove(0, 1));
        assertThat(throwable).hasMessage("Please pick empty area.").isInstanceOf(UnsupportedOperationException.class);
    }


}
