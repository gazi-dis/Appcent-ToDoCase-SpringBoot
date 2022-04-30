package appcentcase.todo.utils.response;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public class SuccessResult extends Result{
    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
