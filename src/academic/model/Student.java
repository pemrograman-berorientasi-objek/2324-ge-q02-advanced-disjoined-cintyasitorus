package academic.model;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 * @author 12S22045 Cintya Sitorus
 */
public class Student extends  Inheritence {

    // class definition
    //Variabel ID ,NAME, Year, study program
    private double gpa;
    private int totalCredit;
    
    //konstruktor
    public Student(String id, String name, String year, String studyProgram) {
        super(name, year, studyProgram,id, null, null, null, null, null, null);
    }

    //getter
    public double getGpa() {
        return gpa;
    }

    //setter
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    //getter
    public int getTotalCredit() {
        return totalCredit;
    }

    //setter
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    //Display student adalah 12S20999|Wiro Sableng|2020|Information Systems
    @Override
    public String toString() {
        return getId() + "|" + getName() + "|" + getYear() + "|" + getStudyProgram();
    }


}