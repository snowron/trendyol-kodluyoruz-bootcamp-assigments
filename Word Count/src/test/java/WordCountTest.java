import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class WordCountTest {
    @Test
    public void count_WhenStringIsOneWord_ShouldReturn1() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count("Wow ");
        //Assert
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void count_WhenStringIsTwoWord_ShouldReturn2() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count("Wow wow");
        //Assert
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void count_WhenStringHasComma_ShouldReturn1() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count("Wow, wow");
        //Assert
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void count_WhenStringHasQuestionMark_ShouldReturn1() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count("Wow wow?");
        //Assert
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void count_WhenStringHasDot_ShouldReturn1() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count("Wow wow.");
        //Assert
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void count_WhenStringHasToMuchSpace_ShouldReturn0() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count("            ");
        //Assert
        assertThat(count).isEqualTo(0);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testCases.csv")
    public void count_WhenStringIsSentencesList_ShouldReturnCalculatedFine(String sentence, int expectedResult) {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        int count = sut.count(sentence);
        //Assert
        assertThat(count).isEqualTo(expectedResult);
    }

    @Test
    public void count_WhenStringIsNull_ShouldThrowNullPointerException() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        Throwable throwable = catchThrowable(() -> sut.count(null));
        //Assert
        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("Sentence is null");
    }

    @Test
    public void count_WhenStringIsZeroLength_ShouldThrowNullPointerException() {
        //Arrange
        WordCount sut = new WordCount();
        //Act
        Throwable throwable = catchThrowable(() -> sut.count(""));
        //Assert

        assertThat(throwable).isInstanceOf(NullPointerException.class).hasMessage("Sentence is null");

    }
}
