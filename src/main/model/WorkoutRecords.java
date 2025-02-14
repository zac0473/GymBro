package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a workout records having a list of WorkoutSession
public class WorkoutRecords implements Writable {

    private List<WorkoutSession> workoutSessions;

    // EFFECTS: constructs a Workout Records with a empty list of WorkoutSession
    public WorkoutRecords() {
        this.workoutSessions = new ArrayList<WorkoutSession>();
    }

    // MODIFIES: this
    // EFFECTS: add the workoutSession to the WorkoutRecords
    public void addWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSessions.add(workoutSession);
        EventLog.getInstance().logEvent(new Event("An Workout Session with duration "
                + workoutSession.getTotalDuration() + " minutes added to Workout Records"));
    }

    // EFFECTS: get total workout duration (in hours) round to the nearest hundredth digit
    public double getTotalWorkoutHours() {
        int totalMinutes = 0;

        for (WorkoutSession workoutSession : this.workoutSessions) {
            totalMinutes += workoutSession.getTotalDuration();
        }

        double totalHours = (double) totalMinutes / 60;
        return Math.round(totalHours * 100.0) / 100.0;
    }

    // EFFECTS: get total workoutSession Number
    public int getTotalWorkoutSessionNum() {
        return this.workoutSessions.size();
    }

    // REQUIRES: a <= getTotalWorkoutSessionNum()
    // EFFECTS: get the number a workout session in the list start from 1
    public WorkoutSession getThatWorkoutSession(int a) {
        return this.workoutSessions.get(a - 1);
    }

    // EFFECTS: get the workout records (all workout sessions)
    public List<WorkoutSession> getAllWorkoutSession() {
        return this.workoutSessions;
    }

    // EFFECTS: returns Workout Records as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("workoutRecords", workoutSessionToJson());
        return json;
    }

    //EFFECTS: returns all Workout Session in this workout records as a JSON array
    private JSONArray workoutSessionToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WorkoutSession ws : workoutSessions) {
            jsonArray.put(ws.toJson());
        }

        return jsonArray;
    }
}
