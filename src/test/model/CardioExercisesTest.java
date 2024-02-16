package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardioExercisesTest {

    @Test
    public void testGetExerciseName() {
        assertEquals("Treadmill", CardioExercises.TREADMILL.getExerciseName());
        assertEquals("Elliptical Machine", CardioExercises.ELLIPTICAL.getExerciseName());
        assertEquals("Stationary Bike", CardioExercises.STATIONARY_BIKE.getExerciseName());
        assertEquals("Rowing Machine", CardioExercises.ROWING_MACHINE.getExerciseName());
        assertEquals("Stair Stepper", CardioExercises.STAIR_STEPPER.getExerciseName());
        assertEquals("Recumbent Bike", CardioExercises.RECUMBENT_BIKE.getExerciseName());
        assertEquals("Spin Bike", CardioExercises.SPIN_BIKE.getExerciseName());
        assertEquals("Arc Trainer", CardioExercises.ARC_TRAINER.getExerciseName());
        assertEquals("Jump Rope", CardioExercises.JUMP_ROPE.getExerciseName());
    }

    @Test
    public void testToString() {
        assertEquals("Treadmill", CardioExercises.TREADMILL.toString());
        assertEquals("Elliptical Machine", CardioExercises.ELLIPTICAL.toString());
        assertEquals("Stationary Bike", CardioExercises.STATIONARY_BIKE.toString());
        assertEquals("Rowing Machine", CardioExercises.ROWING_MACHINE.toString());
        assertEquals("Stair Stepper", CardioExercises.STAIR_STEPPER.toString());
        assertEquals("Recumbent Bike", CardioExercises.RECUMBENT_BIKE.toString());
        assertEquals("Spin Bike", CardioExercises.SPIN_BIKE.toString());
        assertEquals("Arc Trainer", CardioExercises.ARC_TRAINER.toString());
        assertEquals("Jump Rope", CardioExercises.JUMP_ROPE.toString());
    }
}
