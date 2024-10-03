import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;

public class HippodromeTest {

    List<Horse> horses = new ArrayList<>();

    @Test
    public void testConstructorWithNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void testConstructorWithNullMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void testConstructorWithEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @Test
    public void testConstructorWithEmptyMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void testGetHorses() {
        for (int i = 1; i <= 30; i++) {
            horses.add(new Horse("Horse" + i, i + 10));
        }
        assertEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    public void testMove() {
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome=new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse:horses) {
            Mockito.verify(horse).move();
        }
    }

    @Test
    public void testGetWinner() {
        for (int i = 1; i <= 10; i++) {
            horses.add(new Horse("Horse" + i, i + 10, i * 10));
        }
        assertSame(horses.get(9), new Hippodrome(horses).getWinner());
    }
}
