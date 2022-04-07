package calculator;

import java.io.Serializable;

public class Result implements Serializable {
    private Complex answer;
    private StatusCode statusCode;

    public Result(Complex answer, StatusCode statusCode){
        this.answer = answer;
        this.statusCode = statusCode;
    }

    public Complex getAnswer(){
        return answer;
    }
    public StatusCode getStatusCode() {
        return statusCode;
    }
    public boolean isOk(){
        return statusCode.equals(StatusCode.OK);
    }
}
