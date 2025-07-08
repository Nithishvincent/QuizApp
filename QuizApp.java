import java.util.*;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // 🧾 Add questions
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

        // Start quiz
        for (int i = 0; i < questions.size(); i++) {
            System.out.println("\nQuestion " + (i + 1));
            questions.get(i).displayQuestion();

            System.out.print("Your answer (1-4): ");
            int userAnswer = sc.nextInt();

            if (questions.get(i).isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong!");
            }
        }

        // Show result
        System.out.println("\nQuiz finished!");
        System.out.println("Your score: " + score + " out of " + questions.size());

        sc.close();
    }
}
