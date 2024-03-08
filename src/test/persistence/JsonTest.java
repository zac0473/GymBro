package persistence;

import model.Cardio;
import model.CardioExercises;
import model.LiftWeight;
import model.WeightExercises;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    // EFFECTS: test if the given CardioExercises and duration are same as given cardio exercise
    protected void checkCardio(CardioExercises cardioExercises, int duration, Cardio cardio) {
        assertEquals(cardioExercises.toString(), cardio.getExerciseName());
        assertEquals(duration, cardio.getDuration());
    }

    // EFFECTS: test if the given WeightExercises, weight and sets are same as given liftWeight exercise
    protected void checkLiftWeight(WeightExercises weightExercises, double weight, int sets, LiftWeight liftWeight) {
        assertEquals(weightExercises.toString(), liftWeight.getExerciseName());
        assertEquals(weight, liftWeight.getWeight());
        assertEquals(sets, liftWeight.getSetsNum());
    }
}
