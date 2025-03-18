import java.util.ArrayList;

public class Course {
	private String CourseName;
	private int CourseCode;
	private int Grade;
	private ArrayList<Student> Students = new ArrayList<Student>();
	
	public Course(String CourseName) {
		this.CourseName = CourseName;
	}
	
	public ArrayList<Student> returnStudent(){
		return this.Students;
	}
	
	public void addStudent(Student addedStudent) {
		Students.add(addedStudent);
	}
	
	public String toString() {
		return "Course: " + CourseName;
	}
}
