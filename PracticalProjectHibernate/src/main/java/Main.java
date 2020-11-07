import menu.Menu;
import java.util.Scanner;
import util.DBUtil;


public class Main {
    public static void main(String[] args) {
        DBUtil.getEntityManager();
        Scanner input = new Scanner(System.in);
        Menu menuOption = new Menu();

        int userChoice;
        do {
            userChoice = menuOption.mainMenu(input);
            switch (userChoice) {
                case 1:
                    menuOption.menuSaveAuthor(input);
                    break;
                case 2:
                    menuOption.menuUpdateAuthor(input);
                    break;
                case 3:
                    menuOption.menuDeleteAuthor(input);
                    break;
                case 4:
                    menuOption.menuSearchById(input);
                    break;
                case 5:
                    menuOption.menuSearchByFirstName(input);
                    break;
                case 6:
                    menuOption.menuSearchListAllAuthors();
                    break;
                case 7:
                    menuOption.printReport();
                    break;
                case 100:
                    DBUtil.shutdown();
                    input.close();
                    break;
                default:
                    System.out.println("\nSorry, please enter valid Option");
                    menuOption.mainMenu(input);
                    break;
            }// End of switch statement
        } while (userChoice != 100);
        System.out.println("Closing system...");
        System.out.println("Thank you. Good Bye.");
    }
}
