package bank;
import java.util.Scanner;

public class StudentInfo {

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take input from user
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter student age: ");
        int age = sc.nextInt();

        System.out.print("Enter student course: ");
        sc.nextLine(); // consume leftover newline
        String course = sc.nextLine();

        // Create a Student object
        Student student = new Student(name, age, course);

        // Display student details
        student.displayInfo();

        sc.close();
    }
}

// Student class
class Student {
    String name;
    int age;
    String course;

    // Constructor
    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Method to display student info
    void displayInfo() {
        System.out.println("\n--- Student Details ---");
        System.out.println("Name   : " + name);
        System.out.println("Age    : " + age);
        System.out.println("Course : " + course);
    }
}
