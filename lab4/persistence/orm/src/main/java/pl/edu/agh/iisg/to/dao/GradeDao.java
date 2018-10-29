package pl.edu.agh.iisg.to.dao;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

public class GradeDao extends GenericDao<Grade> {

	public boolean gradeStudent(final Student student, final Course course, final float grade) {
		Grade grade2 = new Grade(student, course, grade);
		student.gradeSet().add(grade2);
		course.gradeSet().add(grade2);
		StudentDao studetnDao = new StudentDao();
		CourseDao courseDao = new CourseDao();
		studetnDao.update(student);
		courseDao.update(course);
		this.save(grade2);
		return true;
	}
}
