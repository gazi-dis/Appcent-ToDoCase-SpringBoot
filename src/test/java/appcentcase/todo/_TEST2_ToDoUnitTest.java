package appcentcase.todo;

import appcentcase.todo.controllers.AuthController;
import appcentcase.todo.controllers.ToDoController;
import appcentcase.todo.models.dto.ToDoAddDto;
import appcentcase.todo.models.dto.ToDoUpdateDto;
import appcentcase.todo.models.dto.UserLoginDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class _TEST2_ToDoUnitTest extends TodoApplicationTests{

    @Autowired
    private ToDoController toDoController;

    @Order(1)
    @Test
    public void addTodoTest() throws Exception{
        ToDoAddDto toDoAddDto = new ToDoAddDto(1,"Test Todo");
        var result = toDoController.add(toDoAddDto);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(2)
    @Test
    public void updateTodoTest() throws Exception{
        ToDoUpdateDto toDoUpdateDto = new ToDoUpdateDto(1,1,"Test Todo 2");
        var result = toDoController.update(toDoUpdateDto);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(3)
    @Test
    public void doneChangeTodoTest() throws Exception{
        var result = toDoController.doneChange(1);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(4)
    @Test
    public void getTodoByIdTest() throws Exception{
        var result = toDoController.getById(1);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(5)
    @Test
    public void getTodoByUserIdTest() throws Exception{
        var result = toDoController.getAllByUserId(1);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(6)
    @Test
    public void getAllDoneTodosByUserId() throws Exception{
        var result = toDoController.getDoneTodosByUserId(1);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(7)
    @Test
    public void getAllNotDoneTodosByUserId() throws Exception{
        var result = toDoController.getPendingDoneTodosByUserId(1);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(8)
    @Test
    public void deleteTodoById() throws Exception{
        var result = toDoController.delete(1);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }
}
