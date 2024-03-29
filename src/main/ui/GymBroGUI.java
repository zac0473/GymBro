package ui;

import model.WorkoutRecords;
import model.WorkoutSession;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Gym Bro (workout tracker) graphic user interface
public class GymBroGUI extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/myWorkoutRecords.json";
    private static final String splashImagePath = "./data/splashscreen.jpeg";

    private WorkoutRecords workoutRecords;
    private JLabel totalWorkoutLabel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JButton jsonReaderButton;
    private JButton jsonWriterButton;
    private JButton startButton;
    private JButton exitButton;
    private JButton createWorkoutButton;
    private JButton viewWorkoutButton;
    private ImageIcon welcomePage;

    // EFFECTS: initialize fields and create welcome page and let user choose
    public GymBroGUI() {
        workoutRecords = new WorkoutRecords();
        totalWorkoutLabel = new JLabel("You did workout 0 hours in total");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setTitle("Gym Bro - Workout Tracker");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Welcome Panel
        JPanel welcomePanel = new JPanel(new GridLayout(3, 1));
        JLabel welcomeLabel = new JLabel("Welcome to Gym Bro - Track and view your workout "
                + "effortlessly.");
        welcomePanel.add(welcomeLabel);
        startButton = new JButton("Start my workout journey");
        startButton.addActionListener(this);
        welcomePanel.add(startButton);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        welcomePanel.add(exitButton);
        add(welcomePanel);
        setVisible(true);
    }

    // REQUIRES: duration >= 0
    // EFFECTS: display splash screen with given image and duration, size of the window based on the size of the image
    private static void displaySplashScreen(String imagePath, int duration) {
        JWindow splashScreen = new JWindow();
        ImageIcon icon = new ImageIcon(imagePath);
        JLabel label = new JLabel(icon);
        splashScreen.getContentPane().add(label, BorderLayout.CENTER);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.dispose();
    }

    // EFFECTS: update the workout label (workout hour)
    private void updateTotalWorkoutLabel() {
        double totalWorkoutHour = workoutRecords.getTotalWorkoutHours();
        totalWorkoutLabel.setText("You did workout " + totalWorkoutHour + " hours in total");
    }

    // EFFECTS: create a new frame with given width and height
    private JFrame createANewFrame(int width, int height, String title) {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        return frame;
    }

    // EFFECTS: override actionPerformed to call specific method when specific button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startToRecord();
        } else if (e.getSource() == createWorkoutButton) {
            addWorkoutSession();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == jsonWriterButton) {
            writeJson();
        } else if (e.getSource() == jsonReaderButton) {
            readJson();
        } else if (e.getSource() == viewWorkoutButton) {
            viewWorkout();
        }
    }

    // EFFECTS: create a new window and let user choose to view their workout records
    private void viewWorkout() {
        JFrame viewWorkoutFrame = createANewFrame(800, 600, "Gym Bro - View Workout Records");
        JPanel panel = new JPanel(new GridLayout(2, 1));
        JButton viewSpecificButton = new JButton("View specific workout session");

        viewSpecificButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSpecific();
                viewWorkoutFrame.dispose();
            }
        });

        JButton viewByDurationButton = new JButton("View by duration");

        viewByDurationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewByDuration();
                viewWorkoutFrame.dispose();
            }
        });
        panel.add(viewSpecificButton);
        panel.add(viewByDurationButton);
        viewWorkoutFrame.add(panel);
        viewWorkoutFrame.setVisible(true);
    }

    // EFFECTS: create a new window and let user view their workout records by duration
    private void viewByDuration() {
        JFrame viewByDurationFrame = createANewFrame(800, 600, "Gym Bro - View by Duration");

        JPanel panel = new JPanel(new GridLayout(3, 3));

        JLabel totalSessionsLabel = new JLabel("You have total " + workoutRecords.getTotalWorkoutSessionNum()
                + " workout sessions, choose to view the duration");
        panel.add(totalSessionsLabel);

        String[] conditions = {"Less than or equals to", "Greater than or equals to"};
        JComboBox<String> conditionComboBox = new JComboBox<>(conditions);
        panel.add(conditionComboBox);

        JTextField durationTextField = new JTextField();
        panel.add(durationTextField);

        JLabel minutesLabel = new JLabel("minutes");
        panel.add(minutesLabel);

        JButton viewButton = addViewDurationButton(viewByDurationFrame, panel, conditionComboBox, durationTextField);
        panel.add(viewButton);

        JButton backToMainFrameButton = addBackButton(viewByDurationFrame);
        panel.add(backToMainFrameButton);
        viewByDurationFrame.add(panel);
        viewByDurationFrame.setVisible(true);
    }

    // EFFECTS: create a view button that display the result depends on users' input
    private JButton addViewDurationButton(JFrame viewByDurationFrame, JPanel panel,
                                          JComboBox<String> conditionComboBox, JTextField durationTextField) {
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCondition = (String) conditionComboBox.getSelectedItem();

                int duration;
                try {
                    duration = Integer.parseInt(durationTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(viewByDurationFrame, "Please enter a valid integer "
                            + "for duration.");
                    return;
                }

                int count = countWorkoutSessionByCondition(selectedCondition, duration);

                String resultMessage = "There are " + workoutRecords.getTotalWorkoutSessionNum()
                        + " workout sessions in total, and " + count
                        + " of them have duration " + selectedCondition.toLowerCase() + " " + duration + " minutes.";
                JOptionPane.showMessageDialog(viewByDurationFrame, resultMessage);
            }
        });
        return viewButton;
    }

    //EFFECTS: count the number of workout sessions in the records by given condition and duration
    private int countWorkoutSessionByCondition(String selectedCondition, int duration) {
        int count = 0;
        for (WorkoutSession workoutSession : workoutRecords.getAllWorkoutSession()) {
            int sessionDuration = workoutSession.getTotalDuration();
            if ((selectedCondition.equals("Less than or equals to")
                    && sessionDuration <= duration)
                    || (selectedCondition.equals("Greater than or equals to")
                    && sessionDuration >= duration)) {
                count++;
            }
        }
        return count;
    }

    //EFFECTS: create a new window and let user view specific workout records that they chose
    private void viewSpecific() {
        JFrame viewSpecificFrame = createANewFrame(800, 600, "Gym Bro - View Specific One");

        JPanel chooseSpecificPanel = new JPanel();

        int totalWorkoutSessions = workoutRecords.getTotalWorkoutSessionNum();
        Integer[] sessionNumbers = new Integer[totalWorkoutSessions];
        for (int i = 0; i < totalWorkoutSessions; i++) {
            sessionNumbers[i] = i + 1;
        }

        JComboBox<Integer> sessionComboBox = new JComboBox<>(sessionNumbers);
        chooseSpecificPanel.add(sessionComboBox);

        JButton viewButton = addViewSpecificButton(sessionComboBox, viewSpecificFrame);
        chooseSpecificPanel.add(viewButton);

        JButton backToMainPageButton = addBackButton(viewSpecificFrame);
        chooseSpecificPanel.add(backToMainPageButton);

        viewSpecificFrame.add(chooseSpecificPanel);
        viewSpecificFrame.setVisible(true);
    }

    //EFFECTS: create a back button that close present page
    private JButton addBackButton(JFrame viewSpecificFrame) {
        JButton backToMainPageButton = new JButton("close");
        backToMainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSpecificFrame.dispose();
            }
        });
        return backToMainPageButton;
    }

    //EFFECTS: create a view button for view specific page that shows the result of user's chosen workout session
    private JButton addViewSpecificButton(JComboBox<Integer> sessionComboBox, JFrame viewSpecificFrame) {
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedSessionNumber = (int) sessionComboBox.getSelectedItem();
                WorkoutSession selectedSession = workoutRecords.getThatWorkoutSession(selectedSessionNumber);
                if (selectedSession != null) {
                    int sessionDuration = selectedSession.getTotalDuration();
                    JOptionPane.showMessageDialog(viewSpecificFrame, "You exercised " + sessionDuration
                            + " minutes in this workout session");
                } else {
                    JOptionPane.showMessageDialog(viewSpecificFrame, "No workout session found for the "
                            + "selected number");
                }
            }
        });
        return viewButton;
    }

    //EFFECTS: create a new page and add a workout session according to user's input
    private void addWorkoutSession() {
        JFrame addWorkoutFrame = createANewFrame(800, 600, "Gym Bro - Add a New Workout Session");
        JPanel panel = new JPanel();
        JLabel label = new JLabel("How long did you spend on this section (in minutes):");
        JTextField textField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hours = textField.getText();
                WorkoutSession workoutSession = new WorkoutSession(Integer.valueOf(hours));
                workoutRecords.addWorkoutSession(workoutSession);
                updateTotalWorkoutLabel();
                addWorkoutFrame.dispose();
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(submitButton);
        addWorkoutFrame.add(panel, BorderLayout.CENTER);
        addWorkoutFrame.setVisible(true);
    }

    //EFFECTS: display the main page and buttons
    private void startToRecord() {
        JFrame mainFrame = createANewFrame(800, 600, "Gym Bro - Main Menu");
        mainFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        createWorkoutButton = new JButton("Add a workout session");
        createWorkoutButton.addActionListener(this);
        panel.add(createWorkoutButton);

        viewWorkoutButton = new JButton("View workout sessions");
        viewWorkoutButton.addActionListener(this);
        panel.add(viewWorkoutButton);

        jsonWriterButton = new JButton("Save my workout records");
        jsonWriterButton.addActionListener(this);
        panel.add(jsonWriterButton);

        jsonReaderButton = new JButton("Reload my previous workout records");
        jsonReaderButton.addActionListener(this);
        panel.add(jsonReaderButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        panel.add(exitButton);

        panel.add(totalWorkoutLabel);
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    //EFFECTS: save the workout records to the Json file
    private void writeJson() {
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
    // EFFECTS: loads workout records from Json file
    private void readJson() {
        try {
            this.workoutRecords = jsonReader.read();
            System.out.println("Loaded your previous workout records from " + JSON_STORE);
            updateTotalWorkoutLabel();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: main method used to display splash screen and the main page
    public static void main(String[] args) {
        displaySplashScreen(splashImagePath, 2500);

        SwingUtilities.invokeLater(() -> {
            GymBroGUI gui = new GymBroGUI();
        });
    }
}


