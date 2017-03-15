/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment6SchoolSystem.BE;

import Assignment6SchoolSystem.BE.Person;
import Assignment6SchoolSystem.BE.GradeInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class Student extends Person
{
    
    private List<GradeInfo> gradeReport = new ArrayList<>();
    private String education;

    public Student(String name, String education)
    {
        super(name);
        this.education = education;
    }
    
    /**
     * Gets the grade for the specified subject 
     *
     * @return the grade for specific subject,  
     *         -1 if the subject is not found
     */
    public int getGrade(String subject)
    {
        for (GradeInfo gradeInfo : gradeReport)
        {
            // if(gradeInfo.getSubject()==subject)
            if(gradeInfo.getSubject().equals(subject))
            {
                return gradeInfo.getGrade();
            }
        }
        return -1;
    }
    
    public void addGrade(GradeInfo gi)
    {
        gradeReport.add(gi);
    }
    
    public static String getHeaderAsString()
    {
        return Person.getHeaderAsString()+"\tEDUCATION\tAVG GRADE";
    }
    
    /**
     * Gets the calculated average grade, from all grades 
     *
     * @return average grade from all grades, -1 if student has no grades
     */
    public double getAverageGrade()
    {
        if(gradeReport.isEmpty())
        {    
            return -1;
        }
        
        double sumOfGrades = 0;
        
        for (GradeInfo currentGradeInfo : gradeReport) // enhanced for loop fore
        {
            sumOfGrades = sumOfGrades + currentGradeInfo.getGrade();
        }
        
        return sumOfGrades/gradeReport.size();
    }
    
    /**
     * Get the value of education
     *
     * @return the value of education
     */
    public String getEducation()
    {
        String edu = "";
        if (education!=null)
            edu=education;
        return education;
    }
    
    /**
     * Get the value of gradeReport
     *
     * @return the value of gradeReport
     */
    public List<GradeInfo> getGradeReport()
    {
        return gradeReport;
    }

    @Override
    public String toString()
    {
        double avgGrade = getAverageGrade();
        String grade = "";
        if(avgGrade>=0)
            grade = avgGrade+"";
        
        return super.toString() + 
                "\t" + getEducation() + "\t\t" + grade; //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
