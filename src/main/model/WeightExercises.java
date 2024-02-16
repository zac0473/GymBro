package model;

// this class declares a finite number of weight lifting exercises or equipments that users can choose from
public enum WeightExercises {
    SQUAT("Squat"),
    LEG_PRESS("Leg Press"),
    DEADLIFT("Dead lift"),
    LEG_EXTENSION("Leg Extension"),
    LEG_CURL("Leg Curl"),
    STANDING_CALF_RAISE("Standing Calf Raise"),
    SEATED_CALF_RAISE("Seated Calf Raise"),
    BENCH_PRESS("Bench Press"),
    CHEST_FLY("Chest Fly"),
    PULL_DOWN("Pull-down"),
    BENT_OVER_ROW("Bent-over Row"),
    UPRIGHT_ROW("Upright Row"),
    SHOULDER_PRESS("Shoulder Press"),
    LATERAL_RAISE("Lateral Raise"),
    SHOULDER_SHRUG("Shoulder Shrug"),
    PUSHDOWN("Push-down"),
    TRICEPS_EXTENSION("Triceps Extension"),
    BICEPS_CURL("Biceps Curl"),
    CRUNCH("Crunch"),
    RUSSIAN_TWIST("Russian Twist"),
    LEG_RAISE("Leg Raise"),
    BACK_EXTENSION("Back Extension");

    private final String exerciseName;

    /*
     * MODIFIES: this
     * EFFECTS: initializes the 'exerciseName' field of the enum instance when it's created
     */
    WeightExercises(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    // EFFECTS: return the name of the exercise
    public String getWeightExerciseName() {
        return exerciseName;
    }

    // EFFECTS: return the name of the exercise
    @Override
    public String toString() {
        return exerciseName;
    }
}
