package pl.edu.agh.iisg.to.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import pl.edu.agh.iisg.to.executor.QueryExecutor;

public class Course {

	public static final String TABLE_NAME = "course";

	private static final Logger logger = Logger.getGlobal();

	private final int id;

	private final String name;

	private List<Student> enrolledStudents;

	private boolean isStudentsListDownloaded = false;

	private Course(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public static Optional<Course> create(final String name) {
		String insertSql = String.format("INSERT INTO %s (name) VALUES ('%s');", TABLE_NAME, name);
		try {
			int id = QueryExecutor.createAndObtainId(insertSql);
			return Course.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<Course> findById(final int id) {
		String findByIdSql = String.format("SELECT * FROM %s WHERE id = %d", TABLE_NAME, id);

		try {
			ResultSet rs = QueryExecutor.read(findByIdSql);
			return Optional.of(new Course(rs.getInt("id"), rs.getString("name")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public boolean enrollStudent(final Student student) {
		String enrollStudentSql = String.format("INSERT INTO %s (student_id, course_id) VALUES ('%d', '%d');",
				"student_course", this.id, student.id(), this.id());
		try {
			QueryExecutor.createAndObtainId(enrollStudentSql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public List<Student> studentList() {
		String findStudentListSql = String
				.format("SELECT student_course.student_id, student.first_name, student.last_name, student.index_number FROM student_course INNER JOIN student ON student.id = student_course.student_id "
						+ " WHERE student_course.course_id = %d", this.id());
		List<Student> students = new LinkedList<>();
		try {
			ResultSet rs = QueryExecutor.read(findStudentListSql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				int indexNumber = rs.getInt(4);
				students.add(new Student(id, firstName, lastName, indexNumber));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TOTO implement

		return students;
	}

	public List<Student> cachedStudentsList() {
		// TOTO implement
		return enrolledStudents;
	}

	public int id() {
		return id;
	}

	public String name() {
		return name;
	}

	public static class Columns {

		public static final String ID = "id";

		public static final String NAME = "name";

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Course course = (Course) o;

		if (id != course.id)
			return false;
		return name.equals(course.name);
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		return result;
	}
}
