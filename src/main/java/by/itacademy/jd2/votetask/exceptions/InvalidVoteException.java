package by.itacademy.jd2.votetask.exceptions;

import java.util.List;

public class InvalidVoteException extends RuntimeException{

    private final List<String> exceptionList;
    public InvalidVoteException(List<String> exceptionList) {
        this.exceptionList = exceptionList;
    }

    public List<String> getExceptionList() {
        return exceptionList;
    }
}
