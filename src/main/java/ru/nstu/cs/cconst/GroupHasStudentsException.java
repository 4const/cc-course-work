package ru.nstu.cs.cconst;

public class GroupHasStudentsException extends RuntimeException {

    public GroupHasStudentsException(Integer groutId) {
        super("К группе + " + groutId + " привязаны студенты..");
    }
}
