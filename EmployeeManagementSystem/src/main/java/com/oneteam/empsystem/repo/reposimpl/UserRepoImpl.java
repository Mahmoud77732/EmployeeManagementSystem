package com.oneteam.empsystem.repo.reposimpl;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.User;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.repos.UserRepo;
import org.hibernate.Session;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.st_getSession;

public class UserRepoImpl extends GenericRepoImpl<User, Long> implements UserRepo {

    public UserRepoImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        String jpqlQuery = "SELECT u FROM User u WHERE u.username = :name";
        try(Session session = st_getSession()){
            return session.createQuery(jpqlQuery, User.class)
                    .setParameter("name", username).uniqueResult();
        }
    }

}
