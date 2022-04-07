package calculator;

public class Result {
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
}
