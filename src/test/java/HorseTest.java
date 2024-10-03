import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class HorseTest {

    @Test
    public void testConstructorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 70.3, 30.2));
    }

    @Test
    public void testConstructorWithNullMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 70.3, 30.2));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n", "\r"," "})
    public void testConstructorWithBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 70.3, 30.2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\n", "\r"," "})
    public void testConstructorWithBlankMessage(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 70.3, 30.2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void testConstructWithNegativeSpeed() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Lobster", -2.6, 30.2));
    }

    @Test
    public void testConstructWithNegativeSpeedMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Lobster", -2.6, 30.2));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void testConstructWithNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Lobster", 70.3, -30.2));
    }

    @Test
    public void testConstructWithNegativeDistanceMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Lobster", 70.3, -30.2));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testGetName() {
        Horse horse = new Horse("Lobster", 80.3, 80.4);
        assertEquals("Lobster", horse.getName());
    }

    @Test
    public void testGetSpeed() {
        Horse horse = new Horse("Lobster", 80.3, 80.4);
        assertEquals(80.3, horse.getSpeed(), 0.00001);
    }

    @Test
    public void testGetDistance() {
        Horse horse = new Horse("Lobster", 80.3, 80.4);
        assertEquals(80.4, horse.getDistance(), 0.00001);
    }

    @Test
    public void testGetNullDistance() {
        Horse horse = new Horse("Lobster", 80.3);
        assertEquals(0, horse.getDistance(), 0.00001);
    }

    @Test
    public void testMoveUseGetRandomDouble() {
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Lobster", 80.3, 80.4);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.8, 0.4})
    public void testMove(double randomArg) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Lobster", 80.3, 30.9);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomArg);
            double initialDistance = horse.getDistance();

            horse.move();

            double expectedDistance = initialDistance + horse.getSpeed() * randomArg;
            assertEquals(expectedDistance, horse.getDistance(), 0.000001);
        }
    }
}
