import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class StackTests {
    @Test
    public void count_WhenStackIsEmpty_ShouldReturnZero() {
        //Arrange
        NiceStack sut = new NiceStack();
        //Act
        int result = sut.count();

        //Assert
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void push_WhenAddingNewItem_ShouldIncrementCount() {
        //Arrange
        NiceStack sut = new NiceStack();
        //Act
        sut.push("Meltem");
        int result = sut.count();
        //Assert
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void push_WhenAddingNullItem_ShouldThrowIllegalArgumentsException() {
        //Arrange
        NiceStack sut = new NiceStack();
        //Act
        Throwable throwable = catchThrowable(() -> sut.push(null));
        //Assert
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class).hasMessage("You can not push null item.");
    }

    @Test
    public void pop_WhenStackIsEmpty_ShouldThrowNullPointerException() {
        //Arrange
        NiceStack sut = new NiceStack();
        //Act
        Throwable throwable = catchThrowable(() -> sut.pop());
        //Assert
        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("You can not pop from empty stack.");
    }

    @Test
    public void pop_WhenStackIsNotEmpty_ShouldPopItem() {
        //Arrange
        NiceStack sut = new NiceStack();
        sut.push("fırst");
        sut.push("second");
        sut.push("last");
        //Act
        Object result = sut.pop();
        //Assert
        assertThat(result).isEqualTo("last");
    }

    @Test
    public void pop_WhenStackIsNotEmpty_ShouldDecreaseCount() {
        //Arrange
        NiceStack sut = new NiceStack();
        sut.push("fırst");
        sut.push("second");
        sut.push("last");
        //Act
        sut.pop();
        int result = sut.count();
        //Assert
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void peek_WhenStackIsNotEmpty_ShouldPeekItem() {
        //Arrange
        NiceStack sut = new NiceStack();
        sut.push("first");
        sut.push("second");
        sut.push("last");
        //Act
        Object result = sut.peek();
        //Assert
        assertThat(result).isEqualTo("last");
    }

    @Test
    public void peek_WhenStackIsNotEmpty_ShouldDecreaseCount() {
        //Arrange
        NiceStack sut = new NiceStack();
        sut.push("fırst");
        sut.push("second");
        sut.push("last");
        //Act
        sut.peek();
        int result = sut.count();
        //Assert
        assertThat(result).isEqualTo(3);
    }

}
