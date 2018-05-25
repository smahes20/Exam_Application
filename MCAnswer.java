// Joe
import java.io.*;
import java.util.*;

public abstract class MCAnswer extends Answer {

	protected String text;
	protected double creditIfSelected;

	protected MCAnswer(String text, double creditIfSelected) {
		this.text = text;
		this.creditIfSelected = creditIfSelected;
	}

	public MCAnswer(Scanner inp) {
		creditIfSelected = inp.nextDouble();
		text = inp.nextLine();
	}

	public void print() {
		System.out.println(text);
	}

	public double getCredit(Answer rightAnswer) {

		String rightAns = ((MCAnswer)rightAnswer).text;

        if(text.equals(rightAns)) {
            return creditIfSelected;
        }
        else
        {
            return 0;
        }
	}

	public void save(PrintWriter pw) {
		pw.println(creditIfSelected + " " + text);
	}

}