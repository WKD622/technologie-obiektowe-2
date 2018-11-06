package pl.edu.agh.school;

import java.io.Serializable;
import java.util.Collection;

public class Student extends Person implements Serializable {

	private static final long serialVersionUID = -2700888841101214174L;

	private SchoolClass _class;

	public Student(String name, String surname) {
		super(name, surname);
		personType = PersonType.Student;
	}

	public void setSchoolClass(SchoolClass schoolClass) {
		_class = schoolClass;
	}

	public Collection<Term> getSchedule() {
		if (_class == null) {
			return null;
		}

		return _class.getSchedule();
	}
}
