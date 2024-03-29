package model;

import persistence.Writable;

// Represents an interface Exercise with a method getExerciseName
public interface Exercise extends Writable {

    // EFFECTS: return the name of the exercise
    public String getExerciseName();
}
