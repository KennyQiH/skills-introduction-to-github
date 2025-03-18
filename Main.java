import java.util.ArrayList;

public class Main {
	
	private ArrayList<Grade> GradeValue = new ArrayList<Grade>();
	public ArrayList<Grade> returnGrade(){
		return this.GradeValue;
	}
	
	public void addGrade(Grade addGradeValue) {
		GradeValue.add(addGradeValue);
	}
	
	public static void main(String[] args) {
		Student student1 = new Student("Ayano");
		Student student2 = new Student("Fumiko");
		Student student3 = new Student("Yumi");
		
		Course course1 = new Course("Computer Science");
		Course course2 = new Course("Programming");
		
		int n = 100;
		int RandomNumber = (int)(Math.random() * n);
		int RandomNumber2 = (int)(Math.random() * n);
		int RandomNumber3 = (int)(Math.random() * n);
		Grade grade1 = new Grade(RandomNumber);
		Grade grade2 = new Grade(RandomNumber2);
		Grade grade3 = new Grade(RandomNumber3);
		
		student1.addCourse(course1);
		student2.addCourse(course1);
		student3.addCourse(course2);
		
		course1.addStudent(student1);
		course2.addStudent(student3);
		
		grade1.addGradeValue(grade1);
		grade2.addGradeValue2(grade2);
		grade3.addGradeValue3(grade3);
	
		//ArrayList<Student> Students = new ArrayList<Student>();
			//System.out.print(Students);
		
		//ArrayList<Course>course = student1.returnCourse();
			//course1 = course.get(0);
		
			System.out.println(student1 + ", " + course1 + ", " +grade1);
			System.out.println(student2 + ", " + course1 + ", " +grade2);
			System.out.println(student3 + ", " + course2 + ", " +grade3);
			System.out.println("The total average score is: " + (Math.round((RandomNumber3 + RandomNumber2 + RandomNumber)
					/3)));
	}
}
