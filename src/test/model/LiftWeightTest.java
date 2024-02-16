package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LiftWeightTest {
    private LiftWeight lw1;
    private LiftWeight lw2;
    private LiftWeight lw3;
    private LiftWeight lw4;
    private LiftWeight lw5;
    private WeightExercises we;

    @BeforeEach
    void runBefore() {
        lw1 = new LiftWeight(we.SQUAT, 40, 10);
        lw2 = new LiftWeight(we.SQUAT, 65.4, 12);
        lw3 = new LiftWeight(we.LEG_EXTENSION, 77.5, 15);
        lw4 = new LiftWeight(we.BENCH_PRESS, 225, 3);
        lw5 = new LiftWeight(we.SEATED_CALF_RAISE, 0.5, 10);
    }

    @Test
    void getWeightNameTest() {
        assertEquals("Squat", lw1.getExerciseName());
        assertEquals("Squat", lw2.getExerciseName());
        assertEquals("Leg Extension", lw3.getExerciseName());
        assertEquals("Bench Press", lw4.getExerciseName());
        assertEquals("Seated Calf Raise", lw5.getExerciseName());
    }

    @Test
    void getWeightTest() {
        assertEquals(40, lw1.getWeight());
        assertEquals(65.4, lw2.getWeight());
        assertEquals(77.5, lw3.getWeight());
        assertEquals(225, lw4.getWeight());
        assertEquals(0.5, lw5.getWeight());
    }

    @Test
    void getSetsNumTest() {
        assertEquals(10, lw1.getSetsNum());
        assertEquals(12, lw2.getSetsNum());
        assertEquals(15, lw3.getSetsNum());
        assertEquals(3, lw4.getSetsNum());
        assertEquals(10, lw5.getSetsNum());
    }

}
