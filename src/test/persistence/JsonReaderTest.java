package persistence;

import model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutRecords wr = reader.read();
            fail("NO Exception expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutRecords.json");
        try {
            WorkoutRecords wr = reader.read();
            assertEquals(0, wr.getTotalWorkoutHours());
            assertEquals(0, wr.getTotalWorkoutSessionNum());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutRecords.json");
        try {
            WorkoutRecords wr = reader.read();
            assertEquals(1, wr.getTotalWorkoutSessionNum());
            assertEquals(5, wr.getTotalWorkoutHours());
            WorkoutSession workoutSessions = wr.getThatWorkoutSession(1);
            assertEquals(2, workoutSessions.getExerciseNum());
            assertEquals(300, workoutSessions.getTotalDuration());
            assertEquals(100, workoutSessions.getTotalCardioDuration());
            assertEquals(1, workoutSessions.getCardioExerciseNum());
            assertEquals(1, workoutSessions.getWeightExerciseNum());
            List<Exercise> listWorkoutSession = workoutSessions.getExerciseList();
            checkCardio(CardioExercises.ELLIPTICAL, 100, (Cardio) listWorkoutSession.get(0));
            checkLiftWeight(WeightExercises.BENCH_PRESS, 300, 4, (LiftWeight) listWorkoutSession.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testAddExerciseUnknownType() {
        JsonReader reader = new JsonReader("./data/testAddExerciseWeightLiftType.json");

        try {
            WorkoutRecords wr = reader.read();
            assertEquals(1, wr.getTotalWorkoutSessionNum());
            assertEquals(1, wr.getTotalWorkoutHours());
            WorkoutSession workoutSession = wr.getThatWorkoutSession(1);
            assertEquals(2, workoutSession.getExerciseNum());
            assertEquals(2, workoutSession.getWeightExerciseNum());
            assertEquals(0, workoutSession.getCardioExerciseNum());
            assertEquals(0, workoutSession.getTotalCardioDuration());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}