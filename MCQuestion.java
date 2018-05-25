/*
    Name: Alessandra Laudando
    ACCC: alaudand
*/

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.*;

public abstract class MCQuestion extends Question {

    protected ArrayList<Answer> answers;

    protected MCQuestion(String text, double maxValue) {
        super(text, maxValue);
        answers = new ArrayList<Answer>();
    }

    protected MCQuestion(Scanner scan) {
        super(scan);
        answers = new ArrayList<Answer>();
    }

    public void addAnswer(Answer a) {
        answers.add(a);
    }

    public void reorderAnswers() {
        Collections.shuffle(answers, new Random());
    }

    public void print() {
        System.out.println(text);

        int i = 0;

        for(Answer a: answers) {
            System.out.print("\t" + (char)(97 + i) + ")" );
            a.print();
            i++;
        }
    }

    public double getValue(MCAnswer studentAnswer) {

        for(int i = 0; i < answers.size(); i++) {
            // check if studentAnswer is correct, if so, return the value
            if(studentAnswer.getCredit(answers.get(i)) > 0) {
                return maxValue * studentAnswer.getCredit(answers.get(i));
            }
        }
        return 0;
    }

    public void save(PrintWriter printWriter) {}

    public void saveAnswersToFile(PrintWriter printWriter) {
        int i = 0;

        // print out items in the arrayList: answers
        for(Answer a: answers) {
            printWriter.print("\t" + (char)(97 + i) + ")" );
            printWriter.println(((MCAnswer)a).text);
            i++;
        }
    }

    public void restoreStudentAnswers(Scanner scan) {}
}