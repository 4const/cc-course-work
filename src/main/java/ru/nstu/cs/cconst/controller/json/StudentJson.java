package ru.nstu.cs.cconst.controller.json;

public class StudentJson {
	public Integer id;
	public String lastName;
	public String firstName;
	public String patronymic;
	public Integer groupId;
	public String group;
	public double grant;

    public StudentJson() {
    }

    public StudentJson(Integer id, String lastName, String firstName,
                       String patronymic, Integer groupId, String group, double grant) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.groupId = groupId;
		this.group = group;
		this.grant = grant;
	}
}
