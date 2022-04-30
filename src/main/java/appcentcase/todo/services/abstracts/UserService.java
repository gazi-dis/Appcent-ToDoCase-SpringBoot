package appcentcase.todo.services.abstracts;

import appcentcase.todo.entities.User;
import appcentcase.todo.models.dto.UserLoginDto;
import appcentcase.todo.models.dto.UserRegisterDto;
import appcentcase.todo.utils.response.DataResult;
import appcentcase.todo.utils.response.Result;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public interface UserService {
    Result register(UserRegisterDto userRegisterDto);
    Result login(UserLoginDto userLoginDto);
    DataResult<User> getById(int id);
    DataResult<User> getByMail(String mail);
    DataResult<User> getByUserName(String userName);
}
