import java.util.ArrayList;

public class Student {
	private String name;
	private String StudentID;
	private ArrayList<Course> CourseName = new ArrayList<Course>();
	
	public Student(String name) {
		this.name = name;
	}
	
	public ArrayList<Course> returnCourse(){
		return this.CourseName;
	}
	
	public void addCourse(Course addedCourse) {
		CourseName.add(addedCourse);
	}
	public String toString() {
		return "Student: " + name;
	}
}
