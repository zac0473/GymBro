package model;

// Represents a Cardio exercise record having a name and duration (in minutes)
public class Cardio implements Exercise {
    private CardioExercises cardioExercise;
    private int duration;


     //REQUIRES: duration > 0
     //EFFECTS: constructs a cardio exercise with given cardioExercise, and duration
    public Cardio(CardioExercises cardioExercise, int duration) {
        this.cardioExercise = cardioExercise;
        this.duration = duration;
    }

    // EFFECTS: get cardio exercise name
    @Override
    public String getExerciseName() {
        return this.cardioExercise.getExerciseName();
    }

    // EFFECTS: get cardio exercise duration
    public int getDuration() {
        return this.duration;
    }
}
