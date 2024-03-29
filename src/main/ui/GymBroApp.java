package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Gym Bro (workout tracker) application
public class GymBroApp {
    private static final String JSON_STORE = "./data/myWorkoutRecords.json";
    private WorkoutRecords workoutRecords;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: print out main manu, make choice for first page
    public GymBroApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);

        while (true) {
            displayMainMenu();

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: createWorkoutRecord();
                    break;
                case 2: System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                    break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // EFFECTS: print out menu for first page
    private static void displayMainMenu() {
        System.out.println("Welcome to gym bro");
        System.out.println("Track and manage your workout sessions effortlessly.");
        System.out.println("Choose an option by entering the corresponding number:");
        System.out.println("1. Start to record my workout data");
        System.out.println("2. Exit");
        System.out.print("Please enter your choice: ");
    }


    // EFFECTS: create a new workout record and finish all workout session inside,
    // and save or load the workout records so far
    private void createWorkoutRecord() {
        workoutRecords = new WorkoutRecords();
        System.out.println("You successfully created a workout record.");

        while (true) {
            displayWorkoutRecordMenu();

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: workoutRecords.addWorkoutSession(createWorkoutSession(getTotalDuration()));
                    break;
                case 2: viewWorkoutSession(workoutRecords);
                    break;
                case 3:
                    System.out.println("You did workout " + workoutRecords.getTotalWorkoutHours() + " hours in total");
                    break;
                case 4: saveWorkoutRecords();
                    break;
                case 5: loadWorkoutRecords();
                    break;
                case 6: System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // EFFECTS: get total duration for workout session
    private int getTotalDuration() {
        int totalDuration = 0;

        while (true) {
            System.out.println("How long did you spend on workout for this session? (in minutes)");
            totalDuration = input.nextInt();
            input.nextLine();
            if (totalDuration > 600) {
                System.out.println("It's longer than human being can bear, please try again.");
                continue;
            }
            break;
        }
        return totalDuration;
    }


    // EFFECTS: get a workout session and print a report for it
    private void viewWorkoutSession(WorkoutRecords workoutRecord) {
        if (workoutRecords.getTotalWorkoutSessionNum() <= 0) {
            System.out.println("You don't have any record yet. Please add some in first.");
        } else {

            System.out.print("Please choose which workout session you want to view (from 1 to "
                    + workoutRecord.getTotalWorkoutSessionNum() + "): ");

            int choice = input.nextInt();
            input.nextLine();

            WorkoutSession workoutSessionWanted = workoutRecord.getThatWorkoutSession(choice);
            workoutSessionReport(workoutSessionWanted);
        }
    }


    // EFFECTS: print a report for given workout session
    private void workoutSessionReport(WorkoutSession workoutSession) {
        List<Exercise> exerciseList = workoutSession.getExerciseList();
        for (Exercise exercise : exerciseList) {
            if (exercise instanceof Cardio) {
                Cardio cardioExercise = (Cardio) exercise;
                if (cardioExercise.getDuration() > 0) {
                    System.out.println("You did " + cardioExercise.getExerciseName() + " for "
                            + cardioExercise.getDuration() + " minutes.");
                }
            } else {
                LiftWeight liftWeightExercise = (LiftWeight) exercise;
                System.out.println("You did " + liftWeightExercise.getExerciseName()
                        + " of " + liftWeightExercise.getWeight() + " lbs for " + liftWeightExercise.getSetsNum()
                        + " sets.");
            }
        }
    }


    // EFFECTS: print out menu for inside workout record page
    private static void displayWorkoutRecordMenu() {
        System.out.println("Choose an option by entering the corresponding number:");
        System.out.println("1. Add Workout Session");
        System.out.println("2. View Workout Sessions");
        System.out.println("3. View Total Workout Hours");
        System.out.println("4. Save your Workout Records");
        System.out.println("5. Load your workout records from file");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }


    // EFFECTS: create a new workout session with given total duration and add exercise into it
    private WorkoutSession createWorkoutSession(int totalDuration) {
        WorkoutSession thisWorkoutSession = new WorkoutSession(totalDuration);
        boolean returnToLastMenu = true;

        System.out.println("You successfully created a workout session, let's add some data:");

        while (returnToLastMenu) {
            displayWorkoutSessionMenu();

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    Cardio thisCardio = getCardio(thisWorkoutSession.getTotalCardioDuration(), totalDuration);
                    thisWorkoutSession.addExercise(thisCardio);
                    break;
                case 2:
                    thisWorkoutSession.addExercise(getLiftWeight());
                    break;
                case 3:
                    returnToLastMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
        return thisWorkoutSession;
    }

    // EFFECTS: create a cardio exercise within total duration
    private Cardio getCardio(int totalCardioDuration, int totalDuration) {
        Cardio thisCardio = null;
        int duration = 0;
        CardioExercises exerciseType = cardioExerciseSelector();

        while (true) {
            System.out.println("How long did you spend on it? (in minutes)");
            duration = input.nextInt();
            input.nextLine();

            if (duration + totalCardioDuration > totalDuration) {
                System.out.println("The duration is longer than total duration, please try again.");
                continue;
            } else {
                thisCardio = new Cardio(exerciseType, duration);
                break;
            }
        }
        return thisCardio;
    }

    // EFFECTS: get a valid data weight lifting exercise
    private LiftWeight getLiftWeight() {
        WeightExercises exerciseType = weightExerciseSelector();
        double weight = 0;
        int sets = 0;

        while (true) {
            System.out.println("How much did you lift? (in lbs)");
            weight = input.nextDouble();
            input.nextLine();
            if (weight > 700) {
                System.out.println("The weight data is too crazy to be real, please try again.");
                continue;
            }
            sets = getSets();
            break;
        }
        LiftWeight thisLiftWeight = new LiftWeight(exerciseType, weight, sets);
        return thisLiftWeight;
    }

    // EFFECTS: get the set of the chosen weight lifting exercise
    private int getSets() {
        int sets = 0;
        while (true) {
            System.out.println("How many set did you do?");
            sets = input.nextInt();
            input.nextLine();
            if (sets > 50) {
                System.out.println("The sets data is too crazy to be real, please try again.");
                continue;
            }
            break;
        }
        return sets;
    }

    // EFFECTS: display menu inside workout session page
    private static void displayWorkoutSessionMenu() {
        System.out.println("Choose an option by entering the corresponding number:");
        System.out.println("1. Add Cardio");
        System.out.println("2. Add Weight Lifting");
        System.out.println("3. Back to Last Menu");
        System.out.print("Enter your choice: ");
    }


    // EFFECTS: get a cardio exercise (one of enum)
    private CardioExercises cardioExerciseSelector() {
        while (true) {
            displayCardioMenu();
            System.out.print("Enter the number of the cardio exercise you want to choose: ");

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                if (choice >= 1 && choice <= CardioExercises.values().length) {
                    CardioExercises selectedExercise = CardioExercises.values()[choice - 1];
                    System.out.println("You selected: " + selectedExercise.getExerciseName());
                    return selectedExercise;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    // EFFECTS: print out menu of cardio exercise user can choose from
    private static void displayCardioMenu() {
        System.out.println("Please choose a cardio exercise from below:");
        System.out.println("(Choose an option by entering the corresponding number:)");
        int index = 1;
        for (CardioExercises cardioExercise : CardioExercises.values()) {
            System.out.println(index + ". " + cardioExercise.getExerciseName());
            index++;
        }
    }


    // EFFECTS: get a weight lifting exercise (one of enum)
    private WeightExercises weightExerciseSelector() {
        while (true) {
            displayWeightMenu();

            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();

                if (choice >= 1 && choice <= WeightExercises.values().length) {
                    WeightExercises selectedExercise = WeightExercises.values()[choice - 1];
                    System.out.println("You selected: " + selectedExercise.getWeightExerciseName());
                    return selectedExercise;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine();
            }
        }
    }

    // EFFECTS: print out menu of weight lifting exercise user can choose from
    private static void displayWeightMenu() {
        System.out.print("Please choose a weight lifting exercise from below:");
        System.out.println("(Choose an option by entering the corresponding number:)");
        int index = 1;
        for (WeightExercises weightExercises : WeightExercises.values()) {
            System.out.println(index + ". " + weightExercises.getWeightExerciseName());
            index++;
        }
    }

    // EFFECTS: saves the workoutRecords to file
    private void saveWorkoutRecords() {
        try {
            jsonWriter.open();
            jsonWriter.write(workoutRecords);
            jsonWriter.close();
            System.out.println("Saved your workout records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout records from file
    private void loadWorkoutRecords() {
        try {
            this.workoutRecords = jsonReader.read();
            System.out.println("Loaded your previous workout records from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
