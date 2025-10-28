package eci.edu.dows.profesorSuperO.Util.Exceptions;

public class StudentWithoutScheduleException extends RuntimeException {
    public StudentWithoutScheduleException(String message) {
        super(message);
    }
}
