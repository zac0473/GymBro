package model;

import java.util.ArrayList;
import java.util.List;

// Represents a workout records having a list of WorkoutSession
public class WorkoutRecords {

    private List<WorkoutSession> workoutSessions;

    // EFFECTS: constructs a Workout Records with a empty list of WorkoutSession
    public WorkoutRecords() {
        this.workoutSessions = new ArrayList<WorkoutSession>();
    }

    // MODIFIES: this
    // EFFECTS: add the workoutSession to the WorkoutRecords
    public void addWorkoutSession(WorkoutSession workoutSession) {
        this.workoutSessions.add(workoutSession);
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

}
