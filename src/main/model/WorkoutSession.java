package model;

// Represents a workout session having a list of exercise and total duration
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSession implements Writable {

    private List<Exercise> exercises;
    private int totalDuration;

    // REQUIRES: totalDuration > 0
    // EFFECTS: constructs a Workout Session with a empty list of Exercise and total duration (in minutes)
    public WorkoutSession(int totalDuration) {
        this.exercises = new ArrayList<Exercise>();
        this.totalDuration = totalDuration;
    }

    // REQUIRES: !exercises.contains(exercise)
    // && if (exercise instanceof Cardio) {exercise.getDuration() + getTotalCardioDuration() <= getTotalDuration()}
    // MODIFIES: this
    // EFFECTS: add the exercise into the exercises (list)
    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    // EFFECTS: get total number of exercises did in the session
    public int getExerciseNum() {
        return this.exercises.size();
    }

    // EFFECTS: count the number of Cardio type exercise in the list
    public int getCardioExerciseNum() {
        int cardioCount = 0;

        for (Exercise exercise : this.exercises) {
            if (exercise instanceof Cardio) {
                cardioCount++;
            }
        }

        return cardioCount;
    }

    // EFFECTS: count the number of liftWeight type exercise in the list
    public int getWeightExerciseNum() {
        int liftWeightCount = 0;

        for (Exercise exercise : this.exercises) {
            if (exercise instanceof LiftWeight) {
                liftWeightCount++;
            }
        }

        return liftWeightCount;
    }

    // EFFECTS: get the total duration
    public int getTotalDuration() {
        return totalDuration;
    }

    // EFFECTS: get the list of exercises
    public List<Exercise> getExerciseList() {
        return exercises;
    }

    // EFFECTS: get the total time spend on Cardio exercise (in minutes)
    public int getTotalCardioDuration() {
        int spendOnCardio = 0;

        for (Exercise exercise : this.exercises) {
            if (exercise instanceof Cardio) {
                spendOnCardio += ((Cardio) exercise).getDuration();
            }
        }

        return spendOnCardio;
    }

    // EFFECTS: returns Workout session as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("totalDuration", totalDuration);
        json.put("listOfExercise", exerciseToJson());
        return json;
    }

    //EFFECTS: returns exercises in this workout session as a JSON array
    private JSONArray exerciseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : exercises) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
