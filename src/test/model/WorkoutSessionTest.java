package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutSessionTest {
    private WorkoutSession mixedWorkoutSession;
    private Exercise c1;
    private Exercise lw1;
    private Cardio c2;
    private LiftWeight lw2;

    @BeforeEach
    void runBefore() {
        mixedWorkoutSession = new WorkoutSession(79);
        c1 = new Cardio(CardioExercises.JUMP_ROPE, 30);
        lw1 = new LiftWeight(WeightExercises.SQUAT, 100.5, 10);
        c2 = new Cardio(CardioExercises.SPIN_BIKE, 20);
        lw2 = new LiftWeight(WeightExercises.DEADLIFT, 400, 3);
    }

    @Test
    void addExerciseTest() {
        assertEquals(0, mixedWorkoutSession.getExerciseNum());
        mixedWorkoutSession.addExercise(c1);
        assertEquals(1, mixedWorkoutSession.getExerciseNum());
        mixedWorkoutSession.addExercise(lw1);
        assertEquals(2, mixedWorkoutSession.getExerciseNum());
        mixedWorkoutSession.addExercise(c2);
        assertEquals(3, mixedWorkoutSession.getExerciseNum());
        mixedWorkoutSession.addExercise(lw2);
        assertEquals(4, mixedWorkoutSession.getExerciseNum());
    }

    @Test
    void getCardioExerciseNumTest() {
        assertEquals(0, mixedWorkoutSession.getExerciseNum());
        assertEquals(0, mixedWorkoutSession.getCardioExerciseNum());
        mixedWorkoutSession.addExercise(c1);
        assertEquals(1, mixedWorkoutSession.getCardioExerciseNum());
        mixedWorkoutSession.addExercise(lw1);
        assertEquals(1, mixedWorkoutSession.getCardioExerciseNum());
        mixedWorkoutSession.addExercise(c2);
        assertEquals(2, mixedWorkoutSession.getCardioExerciseNum());
        mixedWorkoutSession.addExercise(lw2);
        assertEquals(2, mixedWorkoutSession.getCardioExerciseNum());
    }

    @Test
    void getWeightExerciseNumTest() {
        assertEquals(0, mixedWorkoutSession.getExerciseNum());
        assertEquals(0, mixedWorkoutSession.getWeightExerciseNum());
        mixedWorkoutSession.addExercise(c1);
        assertEquals(0, mixedWorkoutSession.getWeightExerciseNum());
        mixedWorkoutSession.addExercise(lw1);
        assertEquals(1, mixedWorkoutSession.getWeightExerciseNum());
        mixedWorkoutSession.addExercise(c2);
        assertEquals(1, mixedWorkoutSession.getWeightExerciseNum());
        mixedWorkoutSession.addExercise(lw2);
        assertEquals(2, mixedWorkoutSession.getWeightExerciseNum());
    }

    @Test
    void getTotalCardioDurationTest() {
        assertEquals(0, mixedWorkoutSession.getExerciseNum());
        assertEquals(79, mixedWorkoutSession.getTotalDuration());
        mixedWorkoutSession.addExercise(c1);
        assertEquals(79, mixedWorkoutSession.getTotalDuration());
        assertEquals(30, mixedWorkoutSession.getTotalCardioDuration());
        mixedWorkoutSession.addExercise(lw1);
        assertEquals(79, mixedWorkoutSession.getTotalDuration());
        assertEquals(30, mixedWorkoutSession.getTotalCardioDuration());
        mixedWorkoutSession.addExercise(c2);
        assertEquals(79, mixedWorkoutSession.getTotalDuration());
        assertEquals(50, mixedWorkoutSession.getTotalCardioDuration());
        mixedWorkoutSession.addExercise(lw2);
        assertEquals(79, mixedWorkoutSession.getTotalDuration());
        assertEquals(50, mixedWorkoutSession.getTotalCardioDuration());
    }

    @Test
    void getExerciseListTest() {
        List<Exercise> expectedList = new ArrayList<Exercise>();
        assertEquals(0, mixedWorkoutSession.getExerciseNum());
        mixedWorkoutSession.addExercise(c1);
        expectedList.add(c1);
        assertEquals(1, mixedWorkoutSession.getExerciseNum());
        assertEquals(expectedList, mixedWorkoutSession.getExerciseList());
        mixedWorkoutSession.addExercise(c2);
        expectedList.add(c2);
        assertEquals(2, mixedWorkoutSession.getExerciseNum());
        assertEquals(expectedList, mixedWorkoutSession.getExerciseList());
    }

}