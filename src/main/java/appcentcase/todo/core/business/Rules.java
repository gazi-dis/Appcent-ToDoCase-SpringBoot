package appcentcase.todo.core.business;

import appcentcase.todo.utils.response.Result;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

public class Rules {
    public static Result exec(Result... checks){
        for(Result result : checks){
            // if get any error, return result; else return null
            if (!result.isSuccess())
                return result;
        }
        return null;
    }
}
