/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment6SchoolSystem;

import Assignment6SchoolSystem.BE.Teacher;
import Assignment6SchoolSystem.BLL.PersonManager;
import Assignment6SchoolSystem.BE.Student;
import Assignment6SchoolSystem.GUI.MainMenu;
import Assignment6SchoolSystem.BE.GradeInfo;

/**
 *
 * @author jeppjleemoritzled
 */
public class Test
{
    public void startTest()
    {
        PersonManager personManager = PersonManager.getInstance();
        
        Teacher bent = new Teacher("Bent", "bht");
        bent.setSalary(34543);
        bent.addSubject("Programming");
        bent.addSubject("Databases");
        
        Teacher stig = new Teacher("Stig", "ssi");
        stig.setSalary(32434);
        stig.addSubject("ITO");
        stig.addSubject("Unity game programming");
        
        personManager.addPerson(bent);
        personManager.addPerson(stig);
        
        Student mads = new Student("Mads", "CS");
        GradeInfo gi1 = new GradeInfo("SCO", 7);
        GradeInfo gi2 = new GradeInfo("SDE", 2);
        GradeInfo gi3 = new GradeInfo("ITO", 10);
        GradeInfo gi4 = new GradeInfo("Unity", 10);
        mads.addGrade(gi1);
        mads.addGrade(gi2);
        mads.addGrade(gi3);
        mads.addGrade(gi4);
        
        personManager.addPerson(mads);
        personManager.removePerson(35234); // doesn't exist
        
        MainMenu menu = new MainMenu();
        menu.run();
    }
}
