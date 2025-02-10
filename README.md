# My Personal Project: Gym Bro

## Gym Workout Tracker

**About the Project**

Gym Bro is a Gym Workout Tracker, a comprehensive exercise-tracking program designed to help users record and analyze their workout sessions. The application allows users to create workout records, specifying the total workout duration for a single time, and the details for different types of exercise (cardio or weight). For cardio exercises, users can choose from various equipment options, inputting the duration of each activity. For weight exercises, users can select specific muscle groups, and choose the equipment used, records the weight lifted, and the number of sets performed. Additionally, users can  view their workout data over time, allowing them to track their performance and make informed decisions about their fitness plan.

**Use Case**

This application is designed for, fitness enthusiasts, gym lovers, athletes, or individuals who want to maintain a structured and organized approach to their workouts. It is suitable for users of all fitness levels, from beginners to advanced, who wish to track their progress, set goals, and gain insights into their exercise routines to make better workout plans. Trainers and fitness coaches can also utilize the application to monitor their clients' progress and tailor workouts accordingly.

**Why**

This idea interests me as I embarked on my fitness journey a few months ago. During my training sessions, I had the challenge of recording my workout data. The multitude of exercise types, different equipment, and diverse weight and set combinations made it difficult to maintain an organized record. Resorting to use the Notes app on my iPhone proved to be cumbersome and led to forgetting or feeling too lazy to type in my workouts. Not having a clear picture of my progress makes it difficult to formulate an effective and tailored fitness plan.

After exploring existing fitness apps, I found many to either require payment for basic features or inundate users with many advertisements. Moreover, these apps often lacked the simplicity I sought in a workout tracking tool.

## User Stories

- As a user, I want to be able to create a new workout session (with duration) and add it to workout records.
- As a user, I want to be able to select a workout session and add a new exercise (including equipment, duration, or weight/sets corresponding to different exercises) to that session.
- As a user, I want to be able to select a workout session and view a list of exercises with details (duration or weight/sets corresponding to different exercises) performed during that session.
- As a user, I want to be able to see the total hours spend on workout.
- As a user, I want to be able to save my workout records (if I so choose)
- As a user, I want to be able to load the workout records I saved before from file (if I so choose)

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking the "Add a workout session" button on main menu and type in an integer (duration in minutes) in the input box then click submit
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the "View workout sessions" button on main menu and choose to view specific one or by duration. Or there is a display on main menu shows the total duration in hours (duration of all workout sessions in workout records add up)
- You can locate my visual component by in the splash screen
- You can save the state of my application by clicking the "Save my workout records" on the main menu
- You can reload the state of my application by clicking the "Reload my previous workout records" on the main menu

Sat Apr 06 14:41:24 PDT 2024 An Workout Session with duration 87 minutes added to Workout Records

Sat Apr 06 14:41:29 PDT 2024 An Workout Session with duration 61 minutes added to Workout Records

Sat Apr 06 14:41:32 PDT 2024 An Workout Session with duration 30 minutes added to Workout Records

Sat Apr 06 14:41:35 PDT 2024 An Workout Session with duration 45 minutes added to Workout Records

If I have more time to work on it, I will improve the overall design by applying Observer design pattern to decouple the UI components from the underlying model. Currently, the UI components directly interact with the model and the panel updated by manually calling the update method, which can lead to tight coupling and difficulties in maintaining the application.

By applying the Observer pattern, the UI components would subscribe to changes in the model. For example, after users add new workout sessions to the workout records by clicking the submit button, the panel update process would be automated through the Observer's update method. This can help the user interface to remain consistent with the underlying data.
