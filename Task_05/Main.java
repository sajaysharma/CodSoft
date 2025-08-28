import java.io.Serializable;
import java.io.*;
import java.util.*;
import java.util.Scanner;


class Student implements Serializable {
    private String name;
    private String rollNumber;
    private String grade;
    private int age;

    // Constructor
    public Student(String name, String rollNumber, String grade, int age) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
        this.age = age;
    }

    // Getters & Setters
    public String getName() { return name; }
    public String getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + name + '\'' +
                ", Roll No='" + rollNumber + '\'' +
                ", Grade='" + grade + '\'' +
                ", Age=" + age +
                '}';
    }
}


class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat"; // File to store students

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add Student
    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
    }

    // Remove Student
    public boolean removeStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                students.remove(s);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    // Search Student
    public Student searchStudent(String rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                return s;
            }
        }
        return null;
    }

    // Display All Students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    // Save to File
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load from File
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> tempList = (ArrayList<?>) obj;
                students = new ArrayList<>();
                for (Object o : tempList) {
                    if (o instanceof Student) {
                        students.add((Student) o);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Ignore if file doesn’t exist yet
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}


public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Roll Number: ");
                    String roll = sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age;
                    try {
                        age = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid age. Try again!");
                        break;
                    }
                    Student s = new Student(name, roll, grade, age);
                    sms.addStudent(s);
                    System.out.println("Student added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    String rollRemove = sc.nextLine();
                    if (sms.removeStudent(rollRemove)) {
                        System.out.println("Student removed successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    String rollSearch = sc.nextLine();
                    Student found = sms.searchStudent(rollSearch);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
