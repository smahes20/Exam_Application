import java.util.Scanner;
import java.io.*;
import java.io.FileWriter;


public class ExamGrader {
    public static void main(String[] args) {
        String examFile;
        String answerFile;

        System.out.println("\nEnter the exam file name: ");
        Scanner scan1 = ScannerFactory.getScanner();
        examFile = scan1.nextLine();

        System.out.println("\nEnter the answer file name: ");
        Scanner scan2 = ScannerFactory.getScanner();
        answerFile = scan2.nextLine();

        Scanner examScan = null;
        Scanner answerScan = null;

        // load the exam file
        try{
            examScan = new Scanner(new BufferedReader(new FileReader(examFile)));
        }
        catch (FileNotFoundException e) {
            System.out.println("\nError: Exam file not found.");
            e.printStackTrace();
        }

        // load the answer file
        try{
            answerScan = new Scanner(new BufferedReader(new FileReader(answerFile)));
        }
        catch (FileNotFoundException e) {
            System.out.println("\nError: Answer file not found");
            e.printStackTrace();
        }

        // Retreive the examfile name from the answerfile
        String studentName = answerScan.nextLine();
        String examFileName = answerScan.nextLine();

        // compare the name of the exam file and the second line of the exam file
        if (!(examFile.equals(examFileName))){
            System.out.println("\nError: Answer file does not correspond to the correct Exam file.");
            System.exit(0);
        }

        Exam e1 = new Exam(examScan);
        e1.restoreStudentAnswers(answerScan);

        PrintWriter pw = null;
        // create csv file
        try {
            pw = new PrintWriter(new File("save.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double totalScore = e1.reportQuestionValues();
        String total = Double.toString(totalScore);

        // write student name to csv file
        pw.append(studentName);
        pw.append('\n');

        // write total score to csv file
        pw.append("Total Score:");
        pw.append(',');
        pw.append(total);
        pw.append('\n');
        e1.questionCreditToCsv(pw);

        pw.close();
    }
}
