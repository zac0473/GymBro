package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a weight lifting exercise record having a exercise name, weight (in lbs), and sets
public class LiftWeight implements Exercise {
    private WeightExercises weightExercise;
    private double weight;
    private int sets;

    //REQUIRES: 0 < weight <= 700 && 1 <= sets <= 50
    //EFFECTS: constructs a weight lifting exercise with given weightExercise, sets and duration
    public LiftWeight(WeightExercises weightExercise, double weight, int sets) {
        this.weightExercise = weightExercise;
        this.weight = weight;
        this.sets = sets;
    }

    // EFFECTS: get weight lifting exercise name
    @Override
    public String getExerciseName() {
        return this.weightExercise.getWeightExerciseName();
    }

    // EFFECTS: get weight lifted
    public double getWeight() {
        return this.weight;
    }

    // EFFECTS: get weight lifting exercise sets number
    public int getSetsNum() {
        return this.sets;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("exerciseType", getClass().getName());
        json.put("liftType", weightExercise);
        json.put("weight", weight);
        json.put("sets", sets);
        return json;
    }
}
