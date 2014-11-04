package ru.nstu.cs.cconst.model;

import javax.persistence.*;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 20, nullable = false)
	private String lastName;

	@Column(length = 20, nullable = false)
	private String firstName;

	private String patronymic;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
	private Group group;

	@Column(name = "_grant")
	private double grant;

	public Student() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public double getGrant() {
		return grant;
	}

	public void setGrant(double grant) {
		this.grant = grant;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}

		Student student = (Student)o;

		if (Double.compare(student.grant, grant) != 0) {
			return false;
		}
		if (id != student.id) {
			return false;
		}
		if (firstName != null ? !firstName.equals(student.firstName) : student.firstName != null) {
			return false;
		}
		if (group != null ? !group.equals(student.group) : student.group != null) {
			return false;
		}
		if (lastName != null ? !lastName.equals(student.lastName) : student.lastName != null) {
			return false;
		}
		if (patronymic != null ? !patronymic.equals(student.patronymic) : student.patronymic != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = id;
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
		result = 31 * result + (group != null ? group.hashCode() : 0);
		temp = Double.doubleToLongBits(grant);
		result = 31 * result + (int)(temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "Student{" +
			"id=" + id +
			", lastName='" + lastName + '\'' +
			", firstName='" + firstName + '\'' +
			", patronymic='" + patronymic + '\'' +
			", group=" + group +
			", grant=" + grant +
			'}';
	}
}