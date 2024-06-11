package lab4;

public class Person {

	// fields
	private String name;
	private int age;
	
	// constructor
	public Person(String myName, int myAge) {
		name = myName;
		age = myAge;
	}
	
	// accessor method
	public int getAge() {
		return age;
	}
	
	// accessor method
	public String getName() {
		return name;
	}
	
	// mutator method
	public void setAge(int newAge) {
		age = newAge;
	}
	
	// method
	public void printDetails() {
		System.out.println("The name of this person is " + name + ".");
	}
	
	// main method
	public static void main(String[] args) {
		Person p = new Person("Asif", 19);
		p.printDetails();
	}
}
