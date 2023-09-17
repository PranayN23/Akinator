
public class Question {
  String question;
 public boolean isQuestion = true;

  Question parent;
  boolean parentYesResponse;
  Question no;//left
  Question yes;//right

  public Question(String question, boolean isQuestion) {
    this.question = question;
    this.isQuestion = isQuestion;
    no = null;
    yes = null;
  }

  public void displayTree(Question q) {
    if (q == null) {
      return;
    }
    System.out.print(q.question + " ");
    displayTree(q.no);
    displayTree(q.yes);
  }

  public String getMessage() {
    return question;
  }

  public void setMessage(String message) {
    this.question = message;
  }

  public void setNo(Question q) {
    if (no == null)
    no = q;
  }

  public void setYes(Question q) {
    if (yes == null)
    yes = q;
  }

  public Question getNo() {
    return no;
  }

  public Question getYes() {
    return yes;
  }

  

  

  
  
}
