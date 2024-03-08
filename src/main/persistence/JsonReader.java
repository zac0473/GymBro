package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Represents a reader that reads workout records from JSON data stored in file
// from example
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Workout Records from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkoutRecords read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutRecords(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Workout Records from JSON object and returns it
    private WorkoutRecords parseWorkoutRecords(JSONObject jsonObject) {
        WorkoutRecords wr = new WorkoutRecords();
        JSONArray jsonArray = jsonObject.getJSONArray("workoutRecords");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addWorkoutSessions(wr, nextExercise);
        }
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses Workout Sessions from JSON object and adds them to Workout Records
    private void addWorkoutSessions(WorkoutRecords wr, JSONObject jsonObject) {
        int totalDuration = jsonObject.getInt("totalDuration");
        WorkoutSession ws = new WorkoutSession(totalDuration);
        JSONArray jsonArray = jsonObject.getJSONArray("listOfExercise");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(ws, nextExercise);
        }
        wr.addWorkoutSession(ws);
    }

    // MODIFIES: ws
    // EFFECTS: parses Exercise from JSON object and adds it to workout session
    private void addExercise(WorkoutSession ws, JSONObject jsonObject) {
        String exerciseType = jsonObject.getString("exerciseType");

        if (Cardio.class.getName().equals(exerciseType)) {
            parseCardioExercise(ws, jsonObject);
        } else {
            parseLiftWeightExercise(ws, jsonObject);
        }
    }

    // MODIFIES: ws
    // EFFECTS: parses Cardio Exercise from JSON object and adds it to workout session
    private void parseCardioExercise(WorkoutSession ws, JSONObject jsonObject) {
        CardioExercises cardioType = CardioExercises.valueOf(jsonObject.getString("cardioType"));
        int duration = jsonObject.getInt("duration");
        Cardio cardioExercises = new Cardio(cardioType, duration);
        ws.addExercise(cardioExercises);
    }

    // MODIFIES: ws
    // EFFECTS: parses Lift Weight Exercise from JSON object and adds it to workout session
    private void parseLiftWeightExercise(WorkoutSession ws, JSONObject jsonObject) {
        WeightExercises liftType = WeightExercises.valueOf(jsonObject.getString("liftType"));
        double weight = jsonObject.getDouble("weight");
        int sets = jsonObject.getInt("sets");
        LiftWeight liftWeightExercise = new LiftWeight(liftType, weight, sets);
        ws.addExercise(liftWeightExercise);
    }
}

