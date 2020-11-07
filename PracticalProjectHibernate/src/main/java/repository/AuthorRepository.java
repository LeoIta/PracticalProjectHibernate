package repository;

import java.util.List;
import javax.persistence.EntityManager;
import model.Author;
import util.DBUtil;

public class AuthorRepository {

    private final EntityManager em;

    public AuthorRepository() {
        this.em = DBUtil.getEntityManager();
    }

    public void saveAuthor(Author author) {
        try {
            this.em.getTransaction().begin();
            this.em.persist(author);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
        }
    }

    public void updateAuthorById(Author author) {
        try {
            this.em.getTransaction().begin();
            this.em.merge(author);
            this.em.getTransaction().commit();
        } catch (Exception e) {
            this.em.getTransaction().rollback();
        }
    }

    public void deleteAuthorById(Author author) {
        try {
            this.em.getTransaction().begin();
            this.em.remove(em.merge(author));
            this.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.em.getTransaction().rollback();
        }
    }

    public Author searchAuthorById(int authorId) {
        return em.find(Author.class, authorId);
    }

    public List<Author> searchAuthorByFirstName(String firstName) {
        String sql = "FROM Author A WHERE A.firstName = :firstName";

        return em.createQuery(sql)
                .setParameter("firstName", firstName).getResultList();
    }

    public List<Author> listAllAuthors() {
        String sql = "FROM Author";
        return em.createQuery(sql).getResultList();
    }
}
