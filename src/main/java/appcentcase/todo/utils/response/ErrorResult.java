package appcentcase.todo.utils.response;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public class ErrorResult extends Result{

    public ErrorResult() {
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }

}
