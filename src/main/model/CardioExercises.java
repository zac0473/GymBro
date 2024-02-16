package model;

// this class declares a finite number of cardio exercise or equipments that users can choose from
public enum CardioExercises {
    TREADMILL("Treadmill"),
    ELLIPTICAL("Elliptical Machine"),
    STATIONARY_BIKE("Stationary Bike"),
    ROWING_MACHINE("Rowing Machine"),
    STAIR_STEPPER("Stair Stepper"),
    RECUMBENT_BIKE("Recumbent Bike"),
    SPIN_BIKE("Spin Bike"),
    ARC_TRAINER("Arc Trainer"),
    JUMP_ROPE("Jump Rope");

    private final String exerciseName;

    // MODIFIES: this
    // EFFECTS: initializes the 'exerciseName' field of the enum instance when it's created
    CardioExercises(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    // EFFECTS: return the name of the exercise
    public String getExerciseName() {
        return exerciseName;
    }

    // EFFECTS: return the name of the exercise
    @Override
    public String toString() {
        return exerciseName;
    }
}
