package appcentcase.todo.repository;

import appcentcase.todo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    User findByUserName(String userName);
}
