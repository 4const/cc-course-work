package ru.nstu.cs.cconst.controller.json;

public class GroupJson {

    public Integer id;
    public String name;
    public Double grant;

    public GroupJson() {
    }

    public GroupJson(Integer id, String name, Double grant) {
        this.id = id;
        this.name = name;
        this.grant = grant;
    }
}
