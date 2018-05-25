/*Name: Sakshi Maheshwari
NetID: smahes20*/

import java.io.PrintWriter;
import java.util.*;
import java.util.Random;  //adds Random utility from java library

public abstract class Answer {

    protected Answer(){
    }
    public Answer(Scanner sc){
    }
    public abstract void print();
    public abstract double getCredit(Answer rightAnswer);
    public abstract void save(PrintWriter p);

}