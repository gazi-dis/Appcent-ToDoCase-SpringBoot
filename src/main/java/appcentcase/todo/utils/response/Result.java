package appcentcase.todo.utils.response;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public class Result {
    private boolean success;
    private String message;

    public  Result(boolean success){
        this.success = success;
    }

    public Result(boolean success,String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess(){ return this.success; }

    public String getMessage(){ return  this.message; }

}
