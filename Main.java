import java.util.*;
import java.io.*;



class Main {


  Questions questions = new Questions();

  public static void main(String[] args) throws ClassNotFoundException, IOException {
    File fileObject = new File("PrintStream.txt");
    FileOutputStream object2 = new FileOutputStream(fileObject, true);
    PrintStream printer = new PrintStream(object2);
    Scanner console = new Scanner(System.in);
    Questions questions = new Questions();
    questions.addFirstQuestion("Is it a mammal", "dog", "fish");
    if (fileObject.length() != 0) {
      setUpTree(fileObject, questions, object2);
    }
    questions.startGame();
    displayMenu();   
    char ans = console.next(".").charAt(0);
    do {
      if (ans == 'd') {
        Questions.printBinaryTree(questions.getParent(), 0);
        displayMenu();
        ans = console.next(".").charAt(0);
      } else {
        // If not a question
        if (questions.getCurrent().isQuestion == false) {
          // Ask final question
          System.out.println("Is it a " + questions.getCurrent().question);
          ans = getResponse(console);
          if (ans == 'y') {
            System.out.println("Im a  god. I know this thing");
            questions.startGame();
            displayMenu();
          } else {
            addNewQuestion(questions, console, printer);
            questions.startGame();

          }
        } else {
          System.out.println(questions.ask());
          ans = getResponse(console);
          questions.nextQuestion(ans == 'y');

        }
      }
    } while (ans != 'x');
    System.out.println("Game Over");
    console.close();
  }

  public static void setUpTree(File fileObject, Questions questions, FileOutputStream object2) throws FileNotFoundException, NullPointerException {
    Scanner parser = new Scanner(fileObject);
    while(parser.hasNextLine()) {
      String question = parser.nextLine();
      String answer = parser.nextLine();
      boolean isYes = parser.nextLine().contains("t");
      String messageOfPrevious = parser.nextLine();
      questions.search(messageOfPrevious);
      questions.addNewQuestion(question, answer, isYes);
    }
  }

  public static void addNewQuestion(Questions questions, Scanner console, PrintStream printer) {
    System.out.println("I thought I knew everything");
    System.out.println("Tell me what it was");
    console.nextLine();
    String newAnswer = console.nextLine();
    System.out.println("What yes or no question would you ask for a " + newAnswer);
    String newQuestion = console.nextLine();
    System.out.println("Would you answer y or n for this question");
    char input = console.next().charAt(0);
    questions.addNewQuestion(newQuestion, newAnswer, input == 'y');
    boolean isYes = (input == 'y');
    printer.println(newQuestion);
    printer.println(newAnswer);
    printer.println(isYes);
    printer.println(questions.getCurrent().getMessage());
  }

  

  public static void displayMenu() {
    System.out.println("input x to exit");
    System.out.println("input d to Display Tree");
    System.out.println("input any other character to continue");
  }

  public static char getResponse(Scanner console) {
    System.out.println("input 'y' or 'n'");
    String ans = console.next();
    while (ans.length() != 1) {
      System.out.println("Incorrect input, try agan");
      System.out.println("input 'y' or 'n'");
      ans = console.next();
    }
    return ans.charAt(0);
  }
}