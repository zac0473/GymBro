package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutRecordsTest {

    private WorkoutRecords workoutRecords;
    private WorkoutSession workoutSession1;
    private WorkoutSession workoutSession2;
    private WorkoutSession workoutSession3;
    private Exercise c1;
    private Exercise lw1;
    private Cardio c2;
    private LiftWeight lw2;

    @BeforeEach
    void runBefore() {
        workoutRecords = new WorkoutRecords();
        workoutSession1 = new WorkoutSession(20);
        workoutSession2 = new WorkoutSession(60);
        workoutSession3 = new WorkoutSession(15);
        c1 = new Cardio(CardioExercises.JUMP_ROPE, 30);
        lw1 = new LiftWeight(WeightExercises.SQUAT, 100.5, 10);
        c2 = new Cardio(CardioExercises.SPIN_BIKE, 20);
        lw2 = new LiftWeight(WeightExercises.DEADLIFT, 400, 3);
        workoutSession1.addExercise(c1);
        workoutSession1.addExercise(c2);
        workoutSession2.addExercise(lw1);
        workoutSession2.addExercise(lw2);
        workoutSession3.addExercise(c1);
        workoutSession3.addExercise(lw1);
    }

    @Test
    void addWorkoutSessionTest() {
        assertEquals(0, workoutRecords.getTotalWorkoutSessionNum());
        workoutRecords.addWorkoutSession(workoutSession1);
        assertEquals(1, workoutRecords.getTotalWorkoutSessionNum());
        workoutRecords.addWorkoutSession(workoutSession2);
        assertEquals(2, workoutRecords.getTotalWorkoutSessionNum());
        workoutRecords.addWorkoutSession(workoutSession3);
        assertEquals(3, workoutRecords.getTotalWorkoutSessionNum());
        workoutRecords.addWorkoutSession(workoutSession1);
        assertEquals(4, workoutRecords.getTotalWorkoutSessionNum());
    }

    @Test
    void getTotalWorkoutHoursTest() {
        assertEquals(0, workoutRecords.getTotalWorkoutSessionNum());
        workoutRecords.addWorkoutSession(workoutSession1);
        assertEquals(0.33, workoutRecords.getTotalWorkoutHours());
        workoutRecords.addWorkoutSession(workoutSession2);
        assertEquals(1.33, workoutRecords.getTotalWorkoutHours());
        workoutRecords.addWorkoutSession(workoutSession3);
        assertEquals(1.58, workoutRecords.getTotalWorkoutHours());
        workoutRecords.addWorkoutSession(workoutSession1);
        assertEquals(1.92, workoutRecords.getTotalWorkoutHours());
    }

    @Test
    void getThatWorkoutSessionTest() {
        assertEquals(0, workoutRecords.getTotalWorkoutSessionNum());
        workoutRecords.addWorkoutSession(workoutSession1);
        workoutRecords.addWorkoutSession(workoutSession2);
        workoutRecords.addWorkoutSession(workoutSession3);
        workoutRecords.addWorkoutSession(workoutSession1);
        assertEquals(workoutSession1, workoutRecords.getThatWorkoutSession(1));
        assertEquals(workoutSession2, workoutRecords.getThatWorkoutSession(2));
        assertEquals(workoutSession3, workoutRecords.getThatWorkoutSession(3));
        assertEquals(workoutSession1, workoutRecords.getThatWorkoutSession(4));
    }

}
