package pl.edu.agh.iisg.to.model;

import java.sql.SQLException;

import pl.edu.agh.iisg.to.executor.QueryExecutor;

public class Grade {

    public static final String TABLE_NAME = "grade";

    private final int id;

    private final float grade;

    private Grade(final int id, final float grade) {
        this.id = id;
        this.grade = grade;
    }

    public static boolean gradeStudent(final Student student, final Course course, final float grade) {
    	//Klasa Grade odpowiada pojedynczej ocenie wystawionej studentowi w danym kursie.
    	String gradeStudentSql = String.format("INSERT INTO %s (grade, student_id, course_id) VALUES ('%f', '%d', '%d');", TABLE_NAME,
		grade, student.id(), course.id());
    	try {
			QueryExecutor.read(gradeStudentSql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }

    public int id() {
        return id;
    }

    public float grade() {
        return grade;
    }

    public static class Columns {

        public static final String ID = "id";

        public static final String GRADE = "grade";

        public static final String STUDENT_ID = "student_id";

        public static final String COURSE_ID = "course_id";

    }

}
