//sakshi 
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Math.abs;

public class NumAnswer extends Answer {
    protected double value;
    protected double tolerance;

    public NumAnswer(double value){
        this.value = value;
    }
    public NumAnswer(double value, double tolerance){
        this.tolerance = tolerance;
        this.value = value;
    }
    public NumAnswer(Scanner sc){
        super(sc);
        value = sc.nextDouble();
        tolerance = sc.nextDouble();
    }
    public void print(){
        System.out.println(value);
    }
    public double getCredit(Answer rightAnswer){
        double correctAnswer = ((NumAnswer)(rightAnswer)).value;
        double diff = abs(correctAnswer - value);

        if ((diff < tolerance)||(diff == tolerance) ||(correctAnswer == value)){ // as we compare
            return 1.0;
        }
        else{
            return 0.0;
        }
    }
    public void save(PrintWriter p){
        p.println(value);
        p.println(tolerance);
    }
}