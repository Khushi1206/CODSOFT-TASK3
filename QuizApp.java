import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
class QuizApp 
{
    private static class QuizQuestion 
    {
        private String question;
        private List<String> options;
        private int correctAnswerIndex;
        public QuizQuestion(String question, List<String> options, int correctAnswerIndex) 
        {
            this.question = question;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }
        public String getQuestion() 
        {
            return question;
        }
        public List<String> getOptions() 
        {
            return options;
        }
        public boolean isCorrect(int answerIndex) 
        {
            return answerIndex == correctAnswerIndex;
        }
        public String getCorrectAnswer() 
        {
            return options.get(correctAnswerIndex);
        }
    }
    public static void main(String[] args) 
    {
        List<QuizQuestion> questions = Arrays.asList(
            new QuizQuestion("What is the capital of France?", Arrays.asList("Paris", "London", "Rome", "Berlin"), 0),
            new QuizQuestion("What is 2 + 2?", Arrays.asList("3", "4", "5", "6"), 1),
            new QuizQuestion("What is the color of the sky?", Arrays.asList("Blue", "Green", "Red", "Yellow"), 0),
            new QuizQuestion("What is the largest planet?", Arrays.asList("Earth", "Mars", "Jupiter", "Saturn"), 2)
        );
        int score = 0;
        List<Boolean> results = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < questions.size(); i++)
        {
            QuizQuestion question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) 
                System.out.println((j + 1) + ". " + options.get(j));
            long startTime = System.currentTimeMillis();
            int answer = -1;
            // Implementing a timer of 10 seconds for each question
            while (System.currentTimeMillis() - startTime < 10000 && answer == -1) {
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt() - 1;
                    if (answer < 0 || answer >= options.size()) {
                        System.out.println("Invalid option. Try again.");
                        answer = -1;
                    }
                }
            }
            if (answer == -1) 
            {
                System.out.println("Time is up! Moving to the next question.");
                results.add(false);
            } 
            else 
            {
                if (question.isCorrect(answer)) 
                {
                    System.out.println("Correct!");
                    score++;
                    results.add(true);
                } 
                else 
                {
                    System.out.println("Incorrect! The correct answer was: " + question.getCorrectAnswer());
                    results.add(false);
                }
            }
            System.out.println();
        }
        System.out.println("Quiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());
        for (int i = 0; i < questions.size(); i++) 
        {
            QuizQuestion question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestion());
            System.out.println("Your answer: " + (results.get(i) ? "Correct" : "Incorrect") + ". Correct answer: " + question.getCorrectAnswer());
        }
        scanner.close();
    }
}
