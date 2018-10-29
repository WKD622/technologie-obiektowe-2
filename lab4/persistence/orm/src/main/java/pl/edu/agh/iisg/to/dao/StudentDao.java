package pl.edu.agh.iisg.to.dao;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.persistence.PersistenceException;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

public class StudentDao extends GenericDao<Student> {

	public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
		Student student = new Student(firstName, lastName, indexNumber);
		try {
			save(student);
			return Optional.of(student);
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Student> findByIndexNumber(final int indexNumber) {
		Student student = currentSession().createQuery("SELECT s FROM Student s WHERE s.id =:index", Student.class)
				.setParameter("index", indexNumber).getSingleResult();
		return Optional.empty();
	}

	public Map<Course, Float> createReport(final Student student) {
		// TODO additional task
		return Collections.emptyMap();
	}

}
