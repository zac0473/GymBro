package model;

import persistence.Writable;

// Represents an interface Exercise with a method getExerciseName
public interface Exercise extends Writable {
    public String getExerciseName();
}
