/*
    Name: Alessandra Laudando
    ACCC: alaudand
*/

import java.util.*;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Exam {

    private ArrayList<Question> questions;
    private String title;
    private String studentName;

    public Exam(String text) {
        this.title = text;
        questions = new ArrayList<Question>();
    }

    public Exam(Scanner scan) {

        questions = new ArrayList<Question>();

        // read in the title of the exam
        String str = scan.nextLine();
        title = str;

        while(scan.hasNext())
        {
            str = scan.nextLine();

            if(str.equals("MCSAQuestion")) {
                MCSAQuestion mcQ = new MCSAQuestion(scan);
                questions.add(mcQ);

                // number of answers
                int numAnswers = scan.nextInt();
                scan.nextLine();

                // create the answers for the question
                for(int i = 0; i < numAnswers; i++) {
                    MCSAAnswer answer = new MCSAAnswer(scan);
                    mcQ.answers.add(answer);
                }
            }
            else if(str.equals("MCMAQuestion")) {
                MCMAQuestion maQ = new MCMAQuestion(scan);
                questions.add(maQ);

                // number of answers
                int numAnswers = scan.nextInt();

                // create the answer, and set it to the right answer
                for(int i = 0; i < numAnswers; i++) {
                    // create the answers for the question
                    MCMAAnswer answer = new MCMAAnswer(scan);
                    maQ.answers.add(answer);
                }
            }
            else if(str.equals("SAQuestion")) {
                SAQuestion saQ = new SAQuestion(scan);
                questions.add(saQ);

                // create the answer, and set it to the right answer
                SAAnswer answer = new SAAnswer(scan);
                saQ.rightAnswer = answer;
            }
            else if(str.equals("NumQuestion")) {
                NumQuestion numQ = new NumQuestion(scan);
                questions.add(numQ);

                // create the answer, and set it to the right answer
                NumAnswer answer = new NumAnswer(scan);
                numQ.rightAnswer = answer;
            }
        }
    }

    public void print() {
        System.out.println("\n*** " + title + " ***");

        int index = 0;

        // print out items in the arrayList: questions
        for(Question q: questions) {
            System.out.print("\nQ" + (index + 1) + ": ");
            q.print();
            index++;
        }
    }

    public void reorderQuestions() {
        Collections.shuffle(questions);
    }

    public void reorderMCAnswers(int position) {

        if (position < 0)
        {
            for(Question q: questions) {
                if(q instanceof MCSAQuestion) {
                    ((MCSAQuestion)q).reorderAnswers();
                }
                else if(q instanceof MCMAQuestion) {
                    ((MCMAQuestion)q).reorderAnswers();
                }
            }
        }
        else
        {
            if(position <= questions.size())
            {
                int index = position - 1;

                if(questions.get(index) instanceof MCSAQuestion)
                {
                    ((MCSAQuestion)(questions.get(index))).reorderAnswers();
                }
                if(questions.get(index) instanceof MCMAQuestion)
                {
                    ((MCMAQuestion)(questions.get(index))).reorderAnswers();
                }
            }
        }
    }

    public double reportQuestionValues() {

        System.out.println("\n\n*** Exam Results ***");
        System.out.println("\n Question  |   Score");
        System.out.println("______________________");

        // print out each questions' value
        double examTotal = 0;
        Question q;

        for(int i = 0; i < questions.size(); i++) {
            q = questions.get(i);

            System.out.println( "\n    " + (i + 1) + "\t   |" + "\t" + q.getValue());

            examTotal = examTotal + q.getValue();
        }

        System.out.println("______________________");
        System.out.println("\n  Total    |   " + examTotal);

        return examTotal;
    }

    public void save(PrintWriter printWriter) {
        printWriter.println(title);
        printWriter.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        for(Question q: questions) {
            q.save(printWriter);
        }
    }

    public void saveToFile(PrintWriter printWriter) {
        printWriter.println("\n*** " + title + " ***");

        int index = 0;

        // print out items in the arrayList: questions
        for(Question q: questions) {
            printWriter.print("\nQ" + (index + 1) + ": ");
            printWriter.println(q.text);

            if(q instanceof MCQuestion) {
                ((MCQuestion)q).saveAnswersToFile(printWriter);
            }
            index++;
        }
    }

    public void getAnswerFromStudent(int index) {

        Question q = questions.get(index);
        q.getAnswerFromStudent();
    }

    public int getNumQuestions() {
        return questions.size();
    }

    public void saveStudentAnswers(PrintWriter printWriter, String fileName) {
        
        printWriter.println(fileName);
        printWriter.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        for(Question q: questions) {
            q.saveStudentAnswers(printWriter);
        }
    }

    public void restoreStudentAnswers(Scanner scan)
    {
        String str;
        int index = 0;

        while( scan.hasNext() ) {
            str = scan.nextLine();

            if (str.equals("MCSAAnswer") || str.equals("MCMAAnswer") || str.equals("SAAnswer") || str.equals("NumAnswer")){
                questions.get(index).restoreStudentAnswers(scan);
                index++;
            }
        }
    }

    public void addQuestion() {
        String questionText;
        String answerText;
        int questionType;
        int numAnswers = 0;
        double maxValue;
        double baseCredit;
        double creditIfSelected;
        double numberAnswer;
        double tolerance;

        Scanner scan = ScannerFactory.getScanner();

        System.out.println("\nQuestion types: " +
                "\n\t1) Mulitple Choice: Single answer" +
                "\n\t2) Multiple Choice: Multiple answer" +
                "\n\t3) Essay Question:  Short answer (text)" +
                "\n\t4) Essay Question:  Numerical answer" +
                "\n\t5) Go back to main menu");

        System.out.println("\nChoose what type of question you would like to add: ");
        questionType = scan.nextInt();
        scan.nextLine();

        // MCSAQuestion and MCMAQuestion
        if(questionType == 1 || questionType == 2) {
            System.out.println("\nEnter your question text: ");
            questionText = scan.nextLine();

            System.out.println("\nEnter the maximum value for your question: ");
            maxValue = scan.nextDouble();
            scan.nextLine();

            // add answers to your question
            boolean isTrue = true;
            while(isTrue) {
                System.out.println("\nHow many answers would you like to add? (min: 3 max: 5) ");
                numAnswers = scan.nextInt();
                scan.nextLine();
                isTrue = false;

                if(numAnswers > 5 && numAnswers < 3) {
                    System.out.println("The number of answers you have chosen is not between 3 and 5. Please choose another value.");
                }
            }

            // create question
            if(questionType == 1) {
                MCSAQuestion question = new MCSAQuestion(questionText, maxValue);
                questions.add(question);

                for(int i = 0; i < numAnswers; i++) {
                    System.out.println("\nEnter the text for answer number " + (i + 1));
                    answerText = scan.nextLine();
                    System.out.println("\nEnter the amount of credit if this answer is selected:");
                    creditIfSelected = scan.nextDouble();
                    scan.nextLine();

                    // create and add the answer to the question
                    MCSAAnswer answer = new MCSAAnswer(" " + answerText, creditIfSelected);
                    question.addAnswer(answer);
                }
            }
            else if(questionType == 2) {
                System.out.println("\nEnter the base credit for your question: ");
                baseCredit = scan.nextDouble();
                scan.nextLine();

                MCMAQuestion question = new MCMAQuestion(questionText, maxValue, baseCredit);
                questions.add(question);

                for(int i = 0; i < numAnswers; i++) {
                    System.out.println("\nEnter the text for your answer number " + (i + 1));
                    answerText = scan.nextLine();
                    System.out.println("\nEnter the amount of credit if this answer is selected:");
                    creditIfSelected = scan.nextDouble();
                    scan.nextLine();

                    // create and add the answer to the question
                    MCMAAnswer answer = new MCMAAnswer(" " + answerText, creditIfSelected);
                    question.addAnswer(answer);
                }
            }
        }
        // SAQuestion and NumQuestion
        else if(questionType == 3 || questionType == 4) {
            System.out.println("\nEnter your question text: ");
            questionText = scan.nextLine();

            System.out.println("\nEnter the maximum value for your question: ");
            maxValue = scan.nextDouble();
            scan.nextLine();

            if(questionType == 3) {
                SAQuestion question = new SAQuestion(questionText, maxValue);
                questions.add(question);

                // add answer
                System.out.println("\nEnter the text for the answer:");
                answerText = scan.nextLine();
                SAAnswer answer = new SAAnswer(answerText);
                question.rightAnswer = answer;
            }
            else if(questionType == 4) {
                NumQuestion question = new NumQuestion(questionText, maxValue);
                questions.add(question);

                // add answer
                System.out.println("\nEnter the value for the answer:");
                numberAnswer = scan.nextDouble();
                scan.nextLine();

                System.out.println("\nEnter the tolerance for the answer:");
                tolerance = scan.nextDouble();
                scan.nextLine();

                NumAnswer answer = new NumAnswer(numberAnswer, tolerance);
                question.rightAnswer = answer;
            }

        }
        else if(questionType == 5){
            return;
        }
        else {
            System.out.println("Your choice is not available.");
        }
    }

    public void removeQuestion() {
        int questionNumber = 0;
        boolean isTrue = true;
        Scanner scan = ScannerFactory.getScanner();

        while(isTrue) {
            System.out.println("\nWhich question would you like to remove? (choose numbers 1-" + questions.size() + ").");

            questionNumber = scan.nextInt();
            scan.nextLine();

            if(questionNumber < 1 && questionNumber > questions.size()) {
                System.out.println("\nQuestion does not exist. Please choose another question.");
            }
            else {
                questions.remove(questionNumber-1);
                System.out.println("\nQuestion " + questionNumber + " has been removed.");
                isTrue = false;
            }
        }
    }

    public void questionCreditToCsv(PrintWriter pw) {

        String questionNumber;
        String questionCredit;

        for (int i = 0; i < questions.size(); i++){
            questionNumber = Integer.toString(i+1);
            questionCredit = Double.toString((questions.get(i)).getValue());

            pw.append("Question " + questionNumber + ":");
            pw.append(',');
            pw.append(questionCredit);
            pw.append('\n');
        }
    }
}