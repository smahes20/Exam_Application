/*Name: Sakshi Maheshwari
NetID: smahes20*/

import java.io.PrintWriter;
import java.util.Scanner;
public class SAAnswer extends Answer{

    protected String text;

    public SAAnswer(String text){

        this.text = text;
    }
    public SAAnswer(Scanner sc){
        super(sc);
        text = sc.nextLine();
    }
    public void print(){
        System.out.println(text);
    }
    public double getCredit(Answer rightAnswer){

        //typecast
        String correctAnswer = ((SAAnswer)rightAnswer).text;

        if (text.equals(correctAnswer)){ // as we compare references to object class
            return 1.0;
        }
        else{
            return 0.0;
        }
    }
    public void save(PrintWriter p){
        p.println(text);
    }
}