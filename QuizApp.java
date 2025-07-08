import java.util.*;
import java.util.concurrent.*;

public class QuizApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // Add questions manually
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"Berlin", "Paris", "Rome", "Madrid"},
            2
        ));
        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"Earth", "Saturn", "Mars", "Venus"},
            3
        ));
        questions.add(new Question(
            "Who developed Java?",
            new String[]{"James Gosling", "Guido van Rossum", "Dennis Ritchie", "Bjarne Stroustrup"},
            1
        ));

        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1));
            questions.get(i).displayQuestion();

            int userAnswer = getUserAnswerWithTimeout(10); // 10-second timer

            if (userAnswer == -1) {
                System.out.println(" No answer given.");
            } else if (questions.get(i).isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
            }
        }

        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + " out of " + questions.size());
        sc.close();
    }

    // Method to get user input with timeout
    public static int getUserAnswerWithTimeout(int seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<Integer> task = () -> {
            Scanner sc = new Scanner(System.in);
            System.out.print("Your answer (1-4): ");
            return sc.nextInt();
        };

        try {
            Future<Integer> future = executor.submit(task);
            return future.get(seconds, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("\n⏰ Time's up!");
            return -1;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -1;
        } finally {
            executor.shutdownNow();
        }
    }
}
