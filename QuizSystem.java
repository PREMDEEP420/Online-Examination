import java.util.*;

public class QuizSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static String currentUser = null;
    private static Map<String, String> userDatabase = new HashMap<>();
    private static final Map<Integer, String> questions = new HashMap<>();
    private static final Map<Integer, String[]> options = new HashMap<>();
    private static final Map<Integer, String> answers = new HashMap<>();

    public static void main(String[] args) {
        initializeData();
        mainMenu();
    }

    // Initialize user data and questions
    private static void initializeData() {
        userDatabase.put("premdeep", "premdeep123");

        questions.put(1, "What is the capital of India ?");
        options.put(1, new String[]{"A) New Delhi", "B) Munich", "C) Frankfurt", "D) Hamburg"});
        answers.put(1, "A");

        questions.put(2, "Which programming language is used in Android development?");
        options.put(2, new String[]{"A) Python", "B) Java", "C) C#", "D) Kotlin"});
        answers.put(2, "B");

        questions.put(3, "Who is the prime minister of india ?");
        options.put(3, new String[]{"A) Shri Mohan Yadav", "B) Shri Narendar Modi ", "C) Manmohan Singh", "D) Atal Bihari Vajpayee "});
        answers.put(3, "B");
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Thank you for using the Quiz System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            currentUser = username;
            System.out.println("Login successful! Welcome, " + username + ".");
            userMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Update Password");
            System.out.println("2. Take Quiz");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    updatePassword();
                    break;
                case 2:
                    takeQuiz();
                    break;
                case 3:
                    currentUser = null;
                    System.out.println("You have been logged out.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void updatePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        userDatabase.put(currentUser, newPassword);
        System.out.println("Password updated successfully.");
    }

    private static void takeQuiz() {
        System.out.println("\n--- Quiz Time ---");
        int score = 0;
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 30_000; // 30 seconds

        for (Map.Entry<Integer, String> entry : questions.entrySet()) {
            if (System.currentTimeMillis() > endTime) {
                System.out.println("\nTime is up!");
                break;
            }

            System.out.println(entry.getKey() + ". " + entry.getValue());
            for (String option : options.get(entry.getKey())) {
                System.out.println(option);
            }
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().toUpperCase();

            if (answers.get(entry.getKey()).equals(userAnswer)) {
                score++;
            }
        }
        System.out.println("Quiz finished! Your score: " + score);
    }
}
