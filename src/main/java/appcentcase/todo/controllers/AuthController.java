package appcentcase.todo.controllers;

import appcentcase.todo.core.jwt.JwtManager;
import appcentcase.todo.models.dto.UserLoginDto;
import appcentcase.todo.models.dto.UserRegisterDto;
import appcentcase.todo.services.abstracts.UserService;
import appcentcase.todo.utils.response.ErrorDataResult;
import appcentcase.todo.utils.response.SuccessDataResult;
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
@RequestMapping("/rest/auth/")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private  final UserService userService;

    private final JwtManager jwtManager;

    @PostMapping("register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody UserRegisterDto userRegisterDto){
        var result = userService.register(userRegisterDto);
        if (!result.isSuccess()){
            return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserLoginDto userLoginDto) throws Exception{
        var result = userService.login(userLoginDto);
        if (!result.isSuccess()) {
            return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new SuccessDataResult<Object>(jwtManager.createToken(userLoginDto),"Kullanıcı girişi başarılı"));
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
