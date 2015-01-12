package ru.nstu.cs.cconst.controller.json;

import com.fasterxml.jackson.annotation.JsonCreator;

public class StudentJson {
	public int id;
	public String lastName;
	public String firstName;
	public String patronymic;
	public Integer groupId;
	public String group;
	public double grant;

	public StudentJson() {
	}

	@JsonCreator
	public StudentJson(int id, String lastName, String firstName, String patronymic, Integer groupId, String group, double grant) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.groupId = groupId;
		this.group = group;
		this.grant = grant;
	}
}
