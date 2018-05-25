import java.io.*;
import java.util.Scanner;

public class ExamBuilder {

    public static void main(String[] args) {

        // initialize an instance of Exam
        Exam exam = null;

        while(true) {

            System.out.println("\n***********************************************");
            System.out.println("Menu:");
            System.out.println("\t 1) Load a saved exam from a file" +
                    "\n\t 2) Add a question" +
                    "\n\t 3) Remove a question" +
                    "\n\t 4) Reorder questions/answers" +
                    "\n\t 5) Print Exam to the screen or to a file" +
                    "\n\t 6) Save Exam" +
                    "\n\t 7) Quit");
            System.out.println("\n***********************************************");

            System.out.println("\nPlease select one of the menu items from above:");
            Scanner scan = ScannerFactory.getScanner();
            int input = scan.nextInt();

            // read up to next line
            scan.nextLine();

            switch(input) {
                case 1:
                    // read in input from a file
                    System.out.println("Enter the name of your exam file:");
                    String examFileName = scan.nextLine();
                    try {
                        scan = new Scanner(new BufferedReader(new FileReader(examFileName)));
                        System.out.println("\n*** File was successfully loaded ***");
                    }
                    catch (Exception e) {
                        System.out.println("\nError: Exam file was not found");
                        e.printStackTrace();
                    }

                    // create the exam
                    exam = new Exam(scan);
                    break;

                // add a question
                case 2:
                    exam.addQuestion();
                    break;

                // remove a question
                case 3:
                    exam.removeQuestion();
                    break;

                // reorder the questions/answers
                case 4:
                    if(exam == null) {
                        System.out.println("\nThe exam does not exist. Please load a saved exam.");
                    }
                    else {
                        System.out.println("\nReorder options:" +
                                "\n\t1) Questions" +
                                "\n\t2) Answers" +
                                "\n\t3) Both Questions and Answers" +
                                "\n\t4) Go back to main menu");

                        System.out.println("\nSelect one of the available reordering options:");

                        int reorderInput = scan.nextInt();
                        scan.nextLine();

                        if(reorderInput == 1) {
                            exam.reorderQuestions();
                        }
                        else if(reorderInput == 2) {
                            exam.reorderMCAnswers(-1);
                        }
                        else if(reorderInput == 3) {
                            exam.reorderQuestions();
                            exam.reorderMCAnswers(-1);
                        }
                        else if(reorderInput == 4) {
                            break;
                        }
                        else {
                            System.out.println("\nYour choice was not available.");
                        }
                    }
                    break;

                // print the exam
                case 5:
                    if(exam == null) {
                        System.out.println("\nThe exam does not exist. Please load a saved exam.");
                    }
                    else {
                        System.out.println("\nPrint options:" +
                                "\n\t1) Print to the screen" +
                                "\n\t2) Print to a file" +
                                "\n\t3) Go back to main menu");

                        System.out.println("\nSelect one of the available printing options:");

                        int printInput = scan.nextInt();
                        scan.nextLine();

                        // print exam to screen
                        if(printInput == 1) {
                            exam.print();
                        }
                        // print exam to file
                        else if(printInput == 2) {
                            String examFile = "Exam.txt";
                            PrintWriter printWriter = null;
                            try {
                                printWriter = new PrintWriter(examFile);
                            }
                            catch(FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            exam.saveToFile(printWriter);
                            System.out.println("*** Exam printed to Exam.txt ***");
                            printWriter.close();
                        }
                        else if(printInput == 3) {
                            break;
                        }
                        else {
                            System.out.println("\nYour choice was not available.");
                        }
                    }
                    break;

                // save the exam
                case 6:
                    // save the exam in a new file
                    if(exam == null) {
                        System.out.println("\nThe exam does not exist. Please load a saved exam.");
                    }
                    else {
                        String examFile = "ExamFileSave.txt";
                        PrintWriter printWriter = null;
                        try {
                            printWriter = new PrintWriter(examFile);
                        }
                        catch(FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        System.out.println("\nSaving the exam...");
                        exam.save(printWriter);
                        printWriter.close();
                    }
                    break;

                // quit
                case 7:
                    System.out.println("\nExiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nYour choice was not available. Please try again.");
                    break;
            }
        }
    }
}
