package ru.nstu.cs.cconst;

public class GroupHasStudents extends RuntimeException {

    public GroupHasStudents(Integer groutId) {
        super("К группе + " + groutId + " привязаны студенты..");
    }
}
