package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            WorkoutRecords wr = new WorkoutRecords();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("NO Exception was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkoutRecords() {
        try {
            WorkoutRecords wr = new WorkoutRecords();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutRecords.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutRecords.json");
            wr = reader.read();
            assertEquals(0, wr.getTotalWorkoutSessionNum());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkoutRecords() {
        try {
            Cardio cardio = new Cardio(CardioExercises.ELLIPTICAL, 100);
            LiftWeight liftWeight = new LiftWeight(WeightExercises.BENCH_PRESS, 300, 4);
            WorkoutRecords wr = new WorkoutRecords();
            WorkoutSession ws = new WorkoutSession(300);
            ws.addExercise(cardio);
            ws.addExercise(liftWeight);
            wr.addWorkoutSession(ws);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkoutRecords.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutRecords.json");
            wr = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
