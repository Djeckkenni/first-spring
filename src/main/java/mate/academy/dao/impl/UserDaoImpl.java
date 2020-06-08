package mate.academy.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mate.academy.dao.UserDao;
import mate.academy.dto.UserResponseDto;
import mate.academy.exceptions.DataProcessingException;
import mate.academy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert User entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(User.class);
            criteriaQuery.from(User.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all users", e);
        }
    }

    @Override
    public UserResponseDto get(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<UserResponseDto> criteriaQuery = criteriaBuilder
                    .createQuery(UserResponseDto.class);
            Root<UserResponseDto> root = criteriaQuery.from(UserResponseDto.class);
            Predicate userPredicate = criteriaBuilder.equal(root.get("id"), userId);
            criteriaQuery.where(userPredicate);
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user with id = " + userId, e);
        }
    }
}
