package appcentcase.todo.utils.response;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
public class ErrorDataResult<T> extends DataResult<T>{

    public ErrorDataResult() {
        super(null, false);
    }

    public ErrorDataResult(T data) {
        super(data, false);
    }

    public ErrorDataResult(String message) {
        super(null, false, message);
    }

    public ErrorDataResult(T data, String message) {
        super(data, false, message);
    }

}
