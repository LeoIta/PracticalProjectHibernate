package repository;

import model.Books;
import util.DBUtil;

import javax.persistence.EntityManager;
import java.awt.print.Book;
import java.util.List;
import java.util.Scanner;

public class BooksRepository {

    private final EntityManager em;

    public BooksRepository() {
        this.em = DBUtil.getEntityManager();
    }

    public void searchBookByGenre(Scanner input) {
        String genre = input.next();
        genre = genre.toUpperCase();
        List<Books> booksList = em.createQuery("FROM Books b WHERE b.genre= '"+genre+"'", Books.class).getResultList();
        if(booksList.isEmpty())
        {
            System.out.println("There are no books for "+genre+" genre");
        }
        else
        {
            for (Books book:booksList) {
                System.out.println(book.getTitle());
            }
        }

    }


}
