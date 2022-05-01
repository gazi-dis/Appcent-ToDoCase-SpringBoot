package appcentcase.todo.controllers;

import appcentcase.todo.models.dto.ToDoAddDto;
import appcentcase.todo.models.dto.ToDoUpdateDto;
import appcentcase.todo.services.abstracts.ToDoService;
import appcentcase.todo.utils.response.ErrorDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@RestController
@RequestMapping("/rest/todo/")
@RequiredArgsConstructor
public class ToDoController {

    @Autowired
    private final ToDoService toDoService;

    @PostMapping("add")
    public ResponseEntity<?> add(@Valid @RequestBody ToDoAddDto toDoAddDto){
        var operationResult = toDoService.addToDo(toDoAddDto);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@Valid @RequestBody ToDoUpdateDto toDoUpdateDto){
        var operationResult = toDoService.updateToDo(toDoUpdateDto);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        var operationResult = toDoService.deleteToDo(id);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("doneChange")
    public ResponseEntity<?> doneChange(@RequestParam int id){
        var operationResult = toDoService.doneToDo(id);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("getById")
    public ResponseEntity<?> getById(@RequestParam int id){
        var operationResult = toDoService.getById(id);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("getAllByUserId")
    public ResponseEntity<?> getAllByUserId(@RequestParam int userId){
        var operationResult = toDoService.getTodosByUserId(userId);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("getDoneTodosByUserId")
    public ResponseEntity<?> getDoneTodosByUserId(@RequestParam int userId){
        var operationResult = toDoService.getDoneTodosByUserId(userId);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @PostMapping("getPendingDoneTodosByUserId")
    public ResponseEntity<?> getPendingDoneTodosByUserId(@RequestParam int userId){
        var operationResult = toDoService.getPendingDoneTodosByUserId(userId);
        if (!operationResult.isSuccess()){
            return new ResponseEntity<Object>(operationResult, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(operationResult);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        Map<String, String> errors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ErrorDataResult<Object>(errors, "Geçersiz formda veri girişi");
    }

}
