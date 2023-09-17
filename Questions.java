public class Questions {
  Question parent;
  Question currentQuestion;
  int noOfQuestions = 0;

  public Questions() {
    parent = null;
  }

  public boolean isQuestionAnswer() {
    return !currentQuestion.isQuestion;
  }

  
  public void displayTree(Question root) {
    root.displayTree(parent);
  }


  public Question getParent() {
    return parent;
  }

  public boolean isEmpty() {
    return parent == null;
  }

  public void startGame() {
    currentQuestion = parent;
  }

  public static void printBinaryTree(Question root, int level) {
    if (root == null){
      return;
    }
    printBinaryTree(root.getYes(), level + 1);
    if (level != 0) {
      for (int i = 0; i < level - 1; i++) {
        System.out.println("|\t");
        System.out.println("|-------" + root.getMessage());
      }
    }
    else {
      System.out.println(root.getMessage());
    }
    printBinaryTree(root.getYes(), level + 1);
  } 
  
  public Question getCurrent() {
    return currentQuestion;
  }

  public String ask() {
    return currentQuestion.getMessage();
  }

  public Question nextQuestion(boolean isYes) {
    if (isYes) {
      currentQuestion = currentQuestion.yes;
    } else {
      currentQuestion = currentQuestion.no;
    }
    return currentQuestion;
  }

  public void addNewQuestion(String question, String answer, boolean isYes) {
    Question newQuestion = new Question(question, true);
    newQuestion.parent = currentQuestion.parent;
    newQuestion.parentYesResponse = currentQuestion.parentYesResponse;

    Question newAnswer = new Question(answer, false);
    newAnswer.parent = newQuestion;

    if (isYes) {
      newQuestion.yes = newAnswer;
      newQuestion.no = currentQuestion;
      newAnswer.parentYesResponse = true;
      currentQuestion.parentYesResponse = false;
      currentQuestion.parent = newQuestion;
    }
    else {
      newQuestion.no = newAnswer;
      newQuestion.yes = currentQuestion;
      newAnswer.parentYesResponse = false;
      currentQuestion.parentYesResponse = true;
      currentQuestion.parent = newQuestion;
    }

    if (newQuestion.parentYesResponse) {
      newQuestion.parent.yes = newQuestion;
    } else {
      newQuestion.parent.no = newQuestion;
    }

  }

  public boolean search(String message ) {
    return search(parent, message);
  }
  public boolean search(Question q,String message ) {
    if (q.getMessage().equals(message)) {
      this.setCurrent(q);
      return true;
    }
    if (q.getNo() != null) {
      if (search(q.getNo(),message)) {
        return true;
      }
    }
    if (q.getYes() != null) {
      if (search(q.getYes(),message)) {
        return true;
      }
    }
    return false;
  }

  public void setCurrent(Question q) {
    this.currentQuestion = q;
  }

  public void addFirstQuestion(String question, String yesAnswer, String noAnswer) {
    Question newQuestion = new Question(question, true);

    Question newYesAnswer = new Question(yesAnswer, false);
    newYesAnswer.parent = newQuestion;
    newYesAnswer.parentYesResponse = true;
    Question newNoAnswer = new Question(noAnswer, false);
    newNoAnswer.parent = newQuestion;
    newNoAnswer.parentYesResponse = false;

    newQuestion.yes = newYesAnswer;
    newQuestion.no = newNoAnswer;

    currentQuestion = newQuestion;
    parent = newQuestion;
  }


  
}