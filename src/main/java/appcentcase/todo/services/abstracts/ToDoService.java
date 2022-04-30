package appcentcase.todo.services.abstracts;

import appcentcase.todo.entities.ToDo;
import appcentcase.todo.models.dto.ToDoAddDto;
import appcentcase.todo.models.dto.ToDoUpdateDto;
import appcentcase.todo.utils.response.DataResult;
import appcentcase.todo.utils.response.Result;

import java.util.List;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public interface ToDoService {
    Result addToDo(ToDoAddDto toDoAddDto);
    Result deleteToDo(int id);
    Result updateToDo(ToDoUpdateDto toDoUpdateDto);
    Result doneToDo(int id);
    DataResult<ToDo> getById(int id);
    DataResult<List<ToDo>> getTodosByUserId(int userId);
    DataResult<List<ToDo>> getDoneTodosByUserId(int userId);
    DataResult<List<ToDo>> getPendingDoneTodosByUserId(int userId);
}
