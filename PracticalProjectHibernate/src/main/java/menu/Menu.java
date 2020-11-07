package menu;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Scanner;
import model.Author;
import repository.AuthorRepository;
import repository.BooksRepository;

public class Menu {

    AuthorRepository authorRepository;
    BooksRepository booksRepository;

    public Menu() {
        authorRepository = new AuthorRepository();
        booksRepository = new BooksRepository();
    }

    public int mainMenu(Scanner input) {
        System.out.println("\n/***************************************************/");
        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println();
        System.out.println("1: Add Author");
        System.out.println("2: Update Author");
        System.out.println("3: Delete Author");
        System.out.println("4: Search Author by ID");
        System.out.println("5: Search Author by First Name");
        System.out.println("6: List all authors");
        System.out.println("7: Authors report");
        System.out.println("8: Search Books by Genre");
        System.out.println("100 - Quit");

        return input.nextInt();
    }

    public void menuSaveAuthor(Scanner input) {
        Author author = new Author();
        System.out.println("Please enter the first name");
        author.setFirstName(input.next());
        System.out.println("Please enter the last name");
        author.setLastName(input.next());
        authorRepository.saveAuthor(author);
    }

    public void menuUpdateAuthor(Scanner input) {
        menuSearchListAllAuthors();
        System.out.println("Please enter the Author ID: ");
        Author author = authorRepository.searchAuthorById(input.nextInt());
        if (author != null) {
            System.out.println("Please enter the first name");
            author.setFirstName(input.next());
            System.out.println("Please enter the last name");
            author.setLastName(input.next());
            authorRepository.updateAuthorById(author);
            menuSearchListAllAuthors();
        } else {
            System.out.println("Author not found.");
        }
    }

    public void menuDeleteAuthor(Scanner input) {
        menuSearchListAllAuthors();
        System.out.println("Please enter the Author ID: ");
        Author author = authorRepository.searchAuthorById(input.nextInt());
        if (author != null) {
            authorRepository.deleteAuthorById(author);
            menuSearchListAllAuthors();
        } else {
            System.out.println("Author not found.");
        }
    }

    public void menuSearchById(Scanner input) {
        System.out.println("Please enter the Author ID: ");
        Author author = authorRepository.searchAuthorById(input.nextInt());
        if (author != null) {
            System.err.println("Result: ");
            System.out.println(author.toString());
        } else {
            System.out.println("Author not found.");
        }
    }

    public void menuSearchByFirstName(Scanner input) {
        System.out.println("Please enter the Author First Name: ");
        List<Author> listAuthor = authorRepository.searchAuthorByFirstName(input.next());
        if (listAuthor != null && !listAuthor.isEmpty()) {
            System.err.println("Result: ");
            for (Author author : listAuthor) {
                System.out.println(author.toString());
            }
        } else {
            System.out.println("Authors not found.");
        }
    }

    public void menuSearchListAllAuthors() {
        List<Author> listAuthor = authorRepository.listAllAuthors();
        if (listAuthor != null && !listAuthor.isEmpty()) {
            System.err.println("Result: ");
            for (Author author : listAuthor) {
                System.out.println(author.toString());
            }
        } else {
            System.out.println("Authors not found.");
        }
    }

    public void printReport() {
        //Read about the library Itext
        //https://howtodoinjava.com/java/library/read-generate-pdf-java-itext/
        //https://www.vogella.com/tutorials/JavaPDF/article.html

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File("src/pdf/author_report.pdf")));
            document.open();

            //Inserting Image in PDF
            Image image = Image.getInstance ("src/pdf/java.png");
            image.scaleAbsolute(120f, 60f);//image width,height
            document.add(image);
            //Title
            document.add(new Paragraph("Author Report", new Font(Font.FontFamily.TIMES_ROMAN, 12,
                    Font.BOLD)));
            //New line
            document.add( Chunk.NEWLINE );

            //List of authors
            PdfPTable table = new PdfPTable(3); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            PdfPCell c1 = new PdfPCell(new Phrase("ID"));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);

            PdfPCell c2 = new PdfPCell(new Phrase("First Name"));
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c2);

            PdfPCell c3 = new PdfPCell(new Phrase("Last Name"));
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c3);

            List<Author> listAuthor = authorRepository.listAllAuthors();
            for (Author author : listAuthor) {
//                PdfPCell cell1 = new PdfPCell(new Paragraph(String.valueOf(author.getAuthorsId())));
//                PdfPCell cell2 = new PdfPCell(new Paragraph(author.getFirstName()));
//                PdfPCell cell3 = new PdfPCell(new Paragraph(author.getLastName()));
                table.addCell(String.valueOf(author.getAuthorsId()));
                table.addCell(author.getFirstName());
                table.addCell(author.getLastName());
            }
            document.add(table);
            document.close();
            writer.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBookByGenre(Scanner input)
    {
        System.out.println("Please enter the genre you are looking for: ");
        booksRepository.searchBookByGenre(input);
    }
}
