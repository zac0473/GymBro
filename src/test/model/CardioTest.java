package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardioTest {
    private Cardio c1;
    private Cardio c2;
    private Cardio c3;
    private Cardio c4;
    private CardioExercises ce;


    @BeforeEach
    void runBefore() {
        c1 = new Cardio(ce.TREADMILL, 10);
        c2 = new Cardio(ce.TREADMILL, 30);
        c3 = new Cardio(ce.SPIN_BIKE, 25);
        c4 = new Cardio(ce.JUMP_ROPE, 40);
    }

    @Test
    void getCardioNameTest() {
        assertEquals("Treadmill", c1.getExerciseName());
        assertEquals("Treadmill", c2.getExerciseName());
        assertEquals("Spin Bike", c3.getExerciseName());
        assertEquals("Jump Rope", c4.getExerciseName());
    }

    @Test
    void getDurationTest() {
        assertEquals(10, c1.getDuration());
        assertEquals(30, c2.getDuration());
        assertEquals(25, c3.getDuration());
        assertEquals(40, c4.getDuration());
    }

}
