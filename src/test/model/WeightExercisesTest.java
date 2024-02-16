package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightExercisesTest {
    @Test
    public void testGetWeightExerciseName() {
        assertEquals("Squat", WeightExercises.SQUAT.getWeightExerciseName());
        assertEquals("Leg Press", WeightExercises.LEG_PRESS.getWeightExerciseName());
        assertEquals("Dead lift", WeightExercises.DEADLIFT.getWeightExerciseName());
        assertEquals("Leg Extension", WeightExercises.LEG_EXTENSION.getWeightExerciseName());
        assertEquals("Leg Curl", WeightExercises.LEG_CURL.getWeightExerciseName());
        assertEquals("Standing Calf Raise", WeightExercises.STANDING_CALF_RAISE.getWeightExerciseName());
        assertEquals("Seated Calf Raise", WeightExercises.SEATED_CALF_RAISE.getWeightExerciseName());
        assertEquals("Bench Press", WeightExercises.BENCH_PRESS.getWeightExerciseName());
        assertEquals("Chest Fly", WeightExercises.CHEST_FLY.getWeightExerciseName());
        assertEquals("Pull-down", WeightExercises.PULL_DOWN.getWeightExerciseName());
        assertEquals("Bent-over Row", WeightExercises.BENT_OVER_ROW.getWeightExerciseName());
        assertEquals("Upright Row", WeightExercises.UPRIGHT_ROW.getWeightExerciseName());
        assertEquals("Shoulder Press", WeightExercises.SHOULDER_PRESS.getWeightExerciseName());
        assertEquals("Lateral Raise", WeightExercises.LATERAL_RAISE.getWeightExerciseName());
        assertEquals("Shoulder Shrug", WeightExercises.SHOULDER_SHRUG.getWeightExerciseName());
        assertEquals("Push-down", WeightExercises.PUSHDOWN.getWeightExerciseName());
        assertEquals("Triceps Extension", WeightExercises.TRICEPS_EXTENSION.getWeightExerciseName());
        assertEquals("Biceps Curl", WeightExercises.BICEPS_CURL.getWeightExerciseName());
        assertEquals("Crunch", WeightExercises.CRUNCH.getWeightExerciseName());
        assertEquals("Russian Twist", WeightExercises.RUSSIAN_TWIST.getWeightExerciseName());
        assertEquals("Leg Raise", WeightExercises.LEG_RAISE.getWeightExerciseName());
        assertEquals("Back Extension", WeightExercises.BACK_EXTENSION.getWeightExerciseName());
    }

    @Test
    public void testToString() {
        assertEquals("Squat", WeightExercises.SQUAT.toString());
        assertEquals("Leg Press", WeightExercises.LEG_PRESS.toString());
        assertEquals("Dead lift", WeightExercises.DEADLIFT.toString());
        assertEquals("Leg Extension", WeightExercises.LEG_EXTENSION.toString());
        assertEquals("Leg Curl", WeightExercises.LEG_CURL.toString());
        assertEquals("Standing Calf Raise", WeightExercises.STANDING_CALF_RAISE.toString());
        assertEquals("Seated Calf Raise", WeightExercises.SEATED_CALF_RAISE.toString());
        assertEquals("Bench Press", WeightExercises.BENCH_PRESS.toString());
        assertEquals("Chest Fly", WeightExercises.CHEST_FLY.toString());
        assertEquals("Pull-down", WeightExercises.PULL_DOWN.toString());
        assertEquals("Bent-over Row", WeightExercises.BENT_OVER_ROW.toString());
        assertEquals("Upright Row", WeightExercises.UPRIGHT_ROW.toString());
        assertEquals("Shoulder Press", WeightExercises.SHOULDER_PRESS.toString());
        assertEquals("Lateral Raise", WeightExercises.LATERAL_RAISE.toString());
        assertEquals("Shoulder Shrug", WeightExercises.SHOULDER_SHRUG.toString());
        assertEquals("Push-down", WeightExercises.PUSHDOWN.toString());
        assertEquals("Triceps Extension", WeightExercises.TRICEPS_EXTENSION.toString());
        assertEquals("Biceps Curl", WeightExercises.BICEPS_CURL.toString());
        assertEquals("Crunch", WeightExercises.CRUNCH.toString());
        assertEquals("Russian Twist", WeightExercises.RUSSIAN_TWIST.toString());
        assertEquals("Leg Raise", WeightExercises.LEG_RAISE.toString());
        assertEquals("Back Extension", WeightExercises.BACK_EXTENSION.toString());
    }
}
