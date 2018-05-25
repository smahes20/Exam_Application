JCC = javac
JFLAGS = -g

examB: Answer.class Question.class Exam.class ExamBuilder.class 
examT: Answer.class Question.class Exam.class ExamTaker.class
examG: Answer.class Question.class Exam.class ExamGrader.class

Answer.class: Answer.java
	$(JCC) $(JFLAGS) Answer.java

Question.class: Question.java
	$(JCC) $(JFLAGS) Question.java

Exam.class: Exam.java
	$(JCC) $(JFLAGS) Exam.java

ExamBuilder.class: ExamBuilder.java Exam.class Question.class Answer.class
	$(JCC) $(JFLAGS) ExamBuilder.java

ExamTaker.class: ExamTaker.java Exam.class Question.class Answer.class
	$(JCC) $(JFLAGS) ExamTaker.java

ExamGrader.class: ExamGrader.java Exam.class Question.class Answer.class
	$(JCC) $(JFLAGS) ExamGrader.java

clean:
	$(RM) *.class


