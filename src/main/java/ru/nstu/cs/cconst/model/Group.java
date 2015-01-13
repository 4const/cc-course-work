package ru.nstu.cs.cconst.model;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20, nullable = false)
	private String name;

	public Group() {
	}

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Group)) {
			return false;
		}

		Group group = (Group)o;

		if (!id.equals(group.id)) {
			return false;
		}
		if (name != null ? !name.equals(group.name) : group.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
        Integer result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Group{" +
			"id=" + id +
			", name='" + name + '\'' +
			'}';
	}
}
