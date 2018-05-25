import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExamTaker {

    public static void main(String[] args) throws FileNotFoundException {
    	// Printing UIC Info
    	System.out.println("ExamTaker by Joseph Campanotto; netID: jcampa5");
    	System.out.println("Group Mates: Alessandra Laudando and Sakshi Maheshwari");
    	System.out.print("\n\n\n");
        
        // Retrieve student information
        Scanner studentInp = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String studentName = studentInp.nextLine();

        // Loading exam file
        System.out.print("Enter the filename for the name: ");
        String examFileName = studentInp.nextLine();
		Scanner examFileInp = new Scanner(new BufferedReader(new FileReader(examFileName)));
		Exam currentExam = new Exam(examFileInp);

		/* Getting Student Answer */

		System.out.println("Beginning Test..");
		currentExam.print(); //Print the Exam
		int numOfQuestions = currentExam.getNumQuestions();
		for (int x=0; x < numOfQuestions; x++) {
			System.out.print("\n\nAnswer for Q" + (x+1) + ": ");
			currentExam.getAnswerFromStudent(x);
		}

		System.out.println("----------------------------------------------------------\n\n");
		System.out.println("Test Complete.");

		int currentQuestion = 1;
		while (currentQuestion != 0) {
			System.out.println("You can still answer skipped questions or change answers.");
			System.out.println("To change an answer or back to a skipped question, type the question number below");
			System.out.print("Otherwise, type 0 to save and exit: ");
			currentQuestion = studentInp.nextInt();
			if (currentQuestion == 0) break;
			System.out.print("\n\nAnswer for Q" + (currentQuestion) + ": ");
			currentExam.getAnswerFromStudent(currentQuestion-1); // -1 for indexing start @ 0
		}
		studentInp.nextLine(); // advance the line
        
        // Saving the students answers
        System.out.print("Enter the name of the answer file: ");
        String answerFileName = studentInp.nextLine();
        File answerFile = new File(answerFileName);
        PrintWriter answerFileOut = new PrintWriter(answerFileName);
		studentInp.close(); // Close scanner. Done using it.

        //answerFileOut.println(studentName);
        answerFileOut.println(studentName);
        currentExam.saveStudentAnswers(answerFileOut, examFileName);
        

        answerFileOut.close(); // Close out so it actually saves the file
    }
}
