package appcentcase.todo.services.abstracts;

import appcentcase.todo.utils.response.Result;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public interface TokenValidationService {
    Result tokenValidaton(int user_id);
}
