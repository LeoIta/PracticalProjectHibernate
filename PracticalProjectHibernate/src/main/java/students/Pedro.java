package students;

import model.Author;
import model.Books;
import repository.AuthorRepository;
import repository.BooksRepository;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Pedro {
    public static void main(String[] args) {

        //Create a manager to be able to access the list of all books and put them in a list
        BooksRepository booksManager = new BooksRepository();
        List<Books> listOfBooks = booksManager.listAllBooks();

        System.out.println(listOfBooks);

       //Create a predicate that will be our filter - in this case authors First Name
        Predicate<Books> byFirstName = books -> books.getAuthor().getFirstName().equals("Author1");
        // Once we have the predicate we create a new list of books that
        // will be the first list of books filtered by the predicate.
        List<Books> resultByFirstName = listOfBooks.stream()
                .filter(byFirstName).collect(Collectors.toList());

        System.out.println(resultByFirstName);

    }

}

