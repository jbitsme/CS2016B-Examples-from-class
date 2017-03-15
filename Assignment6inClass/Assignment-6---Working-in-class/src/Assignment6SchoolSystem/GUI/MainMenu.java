/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment6SchoolSystem.GUI;

import Assignment6SchoolSystem.BE.GradeInfo;
import Assignment6SchoolSystem.BLL.PersonManager;
import Assignment6SchoolSystem.BE.Student;
import Assignment6SchoolSystem.BE.Teacher;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jeppjleemoritzled
 */
public class MainMenu extends Menu
{

    private Scanner readInput = new Scanner(System.in);
    private PersonManager personManager;

    public MainMenu()
    {
        super(
                "Main menu",
                new String[]
                {
                    "Add student",
                    "Add teacher",
                    "List persons",
                    "Get person info",
                    "Add grades to student",
                    "Change teacher salary or subject"
                }
        );
        personManager = PersonManager.getInstance();
    }

    private void printStudents()
    {
        System.out.println(Student.getHeaderAsString());
        List persons = personManager.getAllStudents();
        for (Object person : persons)
        {
            System.out.println(person);
        }
    }

    private void printAll()
    {
        printStudents();
        printTeachers();
    }

    private void printTeachers()
    {
        System.out.println(Teacher.getHeaderAsString());
        List persons = personManager.getAllTeachers();
        for (Object person : persons)
        {
            System.out.println(person);
        }
    }

    @Override
    protected void doAction(int option)
    {
        if (option == EXIT_OPTION)
        {
            System.exit(0);
        }
        else if (option == 1)
        {
            System.out.println("Name: ");
            String name = readInput.next();

            System.out.println("Education: ");
            String education = readInput.next();

            Student student = new Student(name, education);
            personManager.addPerson(student);

        }
        else if (option == 2)
        {
            System.out.println("Name: ");
            String name = readInput.next();

            System.out.println("Initials: ");
            String initials = readInput.next();

            System.out.println("Salary: ");
            double salary = readInput.nextDouble();

            Teacher teacher = new Teacher(name, initials);
            teacher.setSalary(salary);
            personManager.addPerson(teacher);

        }
        else if (option == 3)
        {
            clear();
            printMenu.run();

        }
        else if (option == 4)
        {
            printAll();

            System.out.println("Please type id of person to view?");
            System.out.print(">");
            int userInput = readInput.nextInt();
            System.out.println(personManager.getPerson(userInput));
        }
        else if (option == 5)
        {
            // "Add grades to student",
            clear();
            printStudents();
            System.out.println("Please type id of student to add grades to?");
            System.out.print(">");
            int userInput = readInput.nextInt();
            Student currentStudent = (Student) personManager.getPerson(userInput);

            System.out.println();

            System.out.println("Subject: ");
            String subject = readInput.next();

            System.out.println("Grade: ");
            int grade = readInput.nextInt();
            GradeInfo gi = new GradeInfo(subject, grade);
            currentStudent.addGrade(gi);

            System.out.println("Grade added to student");
        }
        else if (option == 6)
        {// "Add subjects to teacher"
            
            teachersChangeMenu.run();
        }
        else
        {
            throw new UnsupportedOperationException("Options doAction not found");
        }

        //pause();
    }
    Menu teachersChangeMenu = new Menu("What du you want to change?", new String[]
    {
        "Salary", "Add subjects"
    })
    {
        @Override
        protected void doAction(int option)
        {
            clear();
            printTeachers();
            System.out.println("Please type id of teacher to change?");
            System.out.print(">");
            int userInput = readInput.nextInt();
            Teacher currentTeacher = (Teacher) personManager.getPerson(userInput);
            
            switch (option)
            {
                case 1:
                    System.out.println("Salary: ");
                    System.out.print(">");
                    double salary = readInput.nextDouble();
                    currentTeacher.setSalary(salary);
                    break;
                case 2:
                    System.out.println("Subject: ");
                    System.out.print(">");
                    String subject = readInput.next();
                    currentTeacher.addSubject(subject);
                    break;
                default:
                    return; // if invalid option chosen, stop
            }
            //pause();
        }
    };


    // This is called an anonymous inner class. It makes submenus a lot easier.
    Menu printMenu = new Menu("What du you want to list?", new String[]
    {
        "Teachers", "Students", "All"
    })
    {
        @Override
        protected void doAction(int option)
        {
            List persons = null;
            boolean printTeachers = false;
            boolean printStudents = false;
            switch (option)
            {
                case 1:
                    printTeachers();
                    break;
                case 2:
                    printStudents();
                    break;
                case 3:
                    printAll();
                    break;
                default:
                    return; // if invalid option chosen, stop
            }
            //pause();

        }

    };
}
