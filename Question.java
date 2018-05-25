/*
    Name: Alessandra Laudando
    ACCC: alaudand
*/

import java.util.*;
import java.io.*;


public abstract class Question {

    protected String text;
    protected Answer rightAnswer;
    protected Answer studentAnswer;
    protected double maxValue;


    protected Question(String text, double maxValue) {
        this.text = text;
        this.maxValue = maxValue;
    }

    public Question(Scanner scan) {}

    public abstract Answer getNewAnswer();

    public abstract void getAnswerFromStudent();

    public abstract double getValue();

    public void setRightAnswers(Answer ans) { rightAnswer = ans; }

    public void print() {
        System.out.println(text);
    }

    public abstract void save(PrintWriter printWriter);

    public abstract void saveStudentAnswers(PrintWriter printWriter);

    public void restoreStudentAnswers(Scanner scan) {}
}