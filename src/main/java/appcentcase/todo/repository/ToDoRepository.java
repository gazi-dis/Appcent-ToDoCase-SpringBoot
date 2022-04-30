package appcentcase.todo.repository;

import appcentcase.todo.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public interface ToDoRepository extends JpaRepository<ToDo,Integer> {
    List<ToDo> getToDosByUserId(int userId);
    List<ToDo> getToDosByUserIdAndIsDone(int userId,Boolean isDone);

}
