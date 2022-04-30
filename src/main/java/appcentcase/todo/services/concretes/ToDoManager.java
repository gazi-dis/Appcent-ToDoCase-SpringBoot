package appcentcase.todo.services.concretes;

import appcentcase.todo.core.business.Rules;
import appcentcase.todo.entities.ToDo;
import appcentcase.todo.models.dto.ToDoAddDto;
import appcentcase.todo.models.dto.ToDoUpdateDto;
import appcentcase.todo.repository.ToDoRepository;
import appcentcase.todo.services.abstracts.ToDoService;
import appcentcase.todo.services.abstracts.TokenValidationService;
import appcentcase.todo.utils.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Service
@RequiredArgsConstructor
public class ToDoManager implements ToDoService {

    @Autowired
    private final ToDoRepository toDoRepository;

    private final TokenValidationService tokenValidationService;

    @Override
    public Result addToDo(ToDoAddDto toDoAddDto) {
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDoAddDto.getUserId()));
        if (result != null){
            return new ErrorResult(result.getMessage());
        }
        ToDo toDo = new ToDo(0,
                toDoAddDto.getUserId(),
                toDoAddDto.getTitle(),
                false,
                LocalDate.now(),
                null
        );
        toDoRepository.save(toDo);
        return new SuccessResult("ToDo başarılı bir şekilde eklendi");
    }

    @Override
    public Result deleteToDo(int id) {
        ToDo toDo = toDoRepository.findById(id).orElse(null);
        if (toDo==null){
            return new ErrorResult("ToDo bulunamadı");
        }
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDo.getUserId()));
        if (result != null){
            return new ErrorResult(result.getMessage());
        }
        toDoRepository.deleteById(id);
        return new SuccessResult("Seçilen ToDo başarılı bir şekilde silindi");
    }

    @Override
    public Result updateToDo(ToDoUpdateDto toDoUpdateDto) {
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDoUpdateDto.getUserId()));
        if (result != null){
            return new ErrorResult(result.getMessage());
        }
        var todo = toDoRepository.findById(toDoUpdateDto.getId()).orElse(null);
        if (todo == null){
            return new ErrorResult("ToDo bulunamadı");
        }
        todo.setTitle(toDoUpdateDto.getTitle());
        toDoRepository.save(todo);
        return new SuccessResult("ToDo başarılı bir şekilde güncellendi");
    }

    @Override
    public Result doneToDo(int id){
        var toDo = toDoRepository.findById(id).orElse(null);
        if (toDo == null){
            return new ErrorDataResult<ToDo>(toDo,"ToDo bulanamadı");
        }
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDo.getUserId()));
        if (result != null){
            return new ErrorResult(result.getMessage());
        }
        toDo.setIsDone(!toDo.getIsDone());
        toDoRepository.save(toDo);
        return new SuccessResult("Tamamlanma durumu güncellendi");
    }

    @Override
    public DataResult<ToDo> getById(int id) {
        var toDo = toDoRepository.findById(id).orElse(null);
        if (toDo == null){
            return new ErrorDataResult<ToDo>(toDo,"ToDo bulanamadı");
        }
        var isClaim = Rules.exec(tokenValidationService.tokenValidaton(toDo.getUserId()));
        if (isClaim != null){
            return new ErrorDataResult<ToDo>(null,isClaim.getMessage());
        }
        return new SuccessDataResult<ToDo>(toDo);
    }

    @Override
    public DataResult<List<ToDo>> getTodosByUserId(int userId) {
        var toDos = toDoRepository.getToDosByUserId(userId);
        if (toDos.size() == 0) {
            return new ErrorDataResult<List<ToDo>>("Bu kullanıcıya ait ToDo bulunamadı");
        }
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDos.get(0).getUserId()));
        if (result != null){
            return new ErrorDataResult<List<ToDo>>(result.getMessage());
        }
        return new SuccessDataResult<List<ToDo>>(toDos);
    }

    @Override
    public DataResult<List<ToDo>> getDoneTodosByUserId(int userId) {
        var toDos = toDoRepository.getToDosByUserIdAndIsDone(userId,true);
        if (toDos.size() == 0) {
            return new ErrorDataResult<List<ToDo>>("Bu kullanıcıya ait tamamlanmış ToDo bulunamadı");
        }
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDos.get(0).getUserId()));
        if (result != null){
            return new ErrorDataResult<List<ToDo>>(result.getMessage());
        }
        return new SuccessDataResult<List<ToDo>>(toDos);
    }

    @Override
    public DataResult<List<ToDo>> getPendingDoneTodosByUserId(int userId) {
        var toDos = toDoRepository.getToDosByUserIdAndIsDone(userId,false);
        if (toDos.size() == 0) {
            return new ErrorDataResult<List<ToDo>>("Bu kullanıcıya ait tamamlanmamış ToDo bulunamadı");
        }
        var result = Rules.exec(tokenValidationService.tokenValidaton(toDos.get(0).getUserId()));
        if (result != null){
            return new ErrorDataResult<List<ToDo>>(result.getMessage());
        }
        return new SuccessDataResult<List<ToDo>>(toDos);
    }
}
