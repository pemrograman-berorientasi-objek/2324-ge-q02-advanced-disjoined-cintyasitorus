package academic.driver;

import academic.model.Student;
import academic.model.Course;
import java.util.*;
import academic.model.Lecturer;
import academic.model.Enrollment;
import academic.model.CourseOpening;

/**
 * @author 12S22051 SEFANYA YEMIMA SINAGA
 * @author 12S22045 Cintya Sitorus
 */
public class Driver1 {

    public static void main(String[] _args) {

        Scanner masukan = new Scanner(System.in);
        ArrayList<Student> student = new ArrayList<Student>();
        ArrayList<Course> course = new ArrayList<Course>();
        ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
        ArrayList<CourseOpening> courseOpening = new ArrayList<CourseOpening>();
        ArrayList<Lecturer> lecturer = new ArrayList<Lecturer>();

        while (masukan.hasNext()) {
            String input = masukan.nextLine();

            if (input.equals("---")) {
                break;
            }
            // split input
            String[] data = input.split("#");

            if (data[0].equals("student-add")) {
                // student-add#12S20999#Wiro Sableng#2020#Information Systems
                // buat objek student
                Student s = new Student(data[1], data[2], data[3], data[4]);
                // menambahkan objek student ke dalam arraylist dan tidak ada duplikat
                boolean isExist = false;
                for (Student stu : student) {
                    if (stu.getName().equals(data[2])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist)
                    student.add(s);
                // Collections.sort(course, (a,b) -> a.getCode().compareTo(b.getCode()));
            }
            if (data[0].equals("course-add")) {
                // Add course
                Course c = new Course(data[1], data[2], Integer.parseInt(data[3]), data[4]);
                course.add(c);
            } else if (data[0].equals("course-open")) {
                String[] lecturerInitials = data[4].split(",");
                List<String> lecturerInitialList = Arrays.asList(lecturerInitials);

                CourseOpening co = new CourseOpening(data[1], data[2], data[3], lecturerInitialList);

                courseOpening.add(co);

                // Print combined output of course-add and course-open
            } else if (data[0].equals("course-history")) {

                for (CourseOpening co : courseOpening) {
                    for (Course c : course) {

                        // cek apakah Academic year odd atau tidak
                        if (co.getYear().equals("odd")) {
                            System.out.print(co.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|"
                                    + c.getPassingGrade() + "|" + co.getAcademicYear() + "|" + co.getYear() + "|");
                            // Combine course opening details with associated lecturer details
                            StringBuilder lecturerDetails = new StringBuilder();
                            for (String initial : co.getLecturerInitialList()) {
                                for (Lecturer l : lecturer) {
                                    if (l.getInitial().equals(initial)) {
                                        lecturerDetails.append(initial).append(" (").append(l.getEmail()).append(");");
                                        break;
                                    }
                                }
                            }
                            // Remove the trailing semicolon if present
                            if (lecturerDetails.length() > 0) {
                                lecturerDetails.deleteCharAt(lecturerDetails.length() - 1);
                            }
                            System.out.println(lecturerDetails.toString());
                            for (Enrollment e : enrollment) {
                                // cek apakah year dan academic year sama
                                if (e.getAcademicYear().equals(co.getAcademicYear())
                                        && e.getSemester().equals(co.getYear()) && e.getCourse().equals(co.getCode())) {
                                    // Print enrollment details
                                    if (e.getPreviousGrade() != null && !e.getPreviousGrade().equals("None")) {
                                        System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                + e.getAcademicYear() + "|" + e.getSemester() + "|" + e.getGrade() + "("
                                                + e.getPreviousGrade() + ")");
                                    } else if (e.getPreviousGrade() != null && e.getPreviousGrade().equals("None")) {
                                        System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                + e.getAcademicYear() + "|" + e.getSemester() + "|" + e.getGrade());
                                    }
                                }

                            }
                        }

                    }

                }
                for (CourseOpening co : courseOpening) {
                    for (Course c : course) {

                        // cek apakah Academic year odd atau tidak
                        if (co.getYear().equals("even")) {
                            System.out.print(co.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|"
                                    + c.getPassingGrade() + "|" + co.getAcademicYear() + "|" + co.getYear() + "|");
                            // Combine course opening details with associated lecturer details
                            StringBuilder lecturerDetails = new StringBuilder();
                            for (String initial : co.getLecturerInitialList()) {
                                for (Lecturer l : lecturer) {
                                    if (l.getInitial().equals(initial)) {
                                        lecturerDetails.append(initial).append(" (").append(l.getEmail()).append(");");
                                        break;
                                    }
                                }
                            }
                            // Remove the trailing semicolon if present
                            if (lecturerDetails.length() > 0) {
                                lecturerDetails.deleteCharAt(lecturerDetails.length() - 1);
                            }
                            System.out.println(lecturerDetails.toString());
                            for (Enrollment e : enrollment) {
                                // cek apakah year dan academic year sama
                                if (e.getAcademicYear().equals(co.getAcademicYear())
                                        && e.getSemester().equals(co.getYear()) && e.getCourse().equals(co.getCode())) {
                                    // Print enrollment details
                                    if (e.getPreviousGrade() != null && !e.getPreviousGrade().equals("None")) {
                                        System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                + e.getAcademicYear() + "|" + e.getSemester() + "|" + e.getGrade() + "("
                                                + e.getPreviousGrade() + ")");
                                    } else if (e.getPreviousGrade() != null && e.getPreviousGrade().equals("None")) {
                                        System.out.println(e.getCourse() + "|" + e.getStudent() + "|"
                                                + e.getAcademicYear() + "|" + e.getSemester() + "|" + e.getGrade());
                                    }
                                }

                            }
                        }

                    }

                }

                // print enrollment

            } else if (data[0].equals("enrollment-add")) {
                // enrollment-add#12S20999#IF1234#A#2020
                // Check if course exists
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.getCode().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                }
                // If course or student doesn't exist, print error message and continue to next
                // input
                if (!courseExists) {
                    continue;
                }
                if (!studentExists) {

                    continue;
                }
                // Add enrollment only if course and student exist
                Enrollment e = new Enrollment(data[1], data[2], data[3], data[4], "None");
                enrollment.add(e);
            } else if (data[0].equals("lecturer-add")) {
                // buat objek lecturer
                Lecturer l = new Lecturer(data[1], data[2], data[3], data[4], data[5]);
                // menambahkan objek lecturer ke dalam arraylist dan tidak terjadi duplikat
                boolean isExist = false;
                for (Lecturer lec : lecturer) {
                    if (lec.getId().equals(data[1])) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist)
                    lecturer.add(l);

            } else if (data[0].equals("enrollment-grade")) {
                // enrollment-grade#12S20999#IF1234#A#2020
                // Check if course exists
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.getCode().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                }

                if (!courseExists) {

                    continue;
                }
                if (!studentExists) {

                    continue;
                }

                // Add enrollment only if course and student exist
                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[2]) && e.getCourse().equals(data[1])
                            && e.getAcademicYear().equals(data[3]) && e.getSemester().equals(data[4])) {
                        e.setGrade(data[5]);
                    }
                }
            } else if (data[0].equals("student-details")) {
                // student-details#12S20999
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[1])) {
                        studentExists = true;
                        // cek gpa
                        double gpa = 0.0;
                        double oldgpa = 0.0;
                        int totalCredit = 0;
                        // Map to store course and its grade for the student
                        Map<String, String> courseGrades = new HashMap<>();
                        for (Enrollment e : enrollment) {
                            if (e.getStudent().equals(data[1])) {
                                // Only update the course grade if it's not "None"
                                if (!e.getGrade().equals("None")) {
                                    courseGrades.put(e.getCourse(), e.getGrade());
                                }
                            }  
                        }     
                        // Calculate GPA and total credits using only the latest course grades
                        for (String courseCode : courseGrades.keySet()) {
                            for (Course c : course) {
                                if (c.getCode().equals(courseCode)) {
                                    totalCredit += c.getCredit();
                                    String grade = courseGrades.get(courseCode);
                                    if (grade.equals("A")) {
                                        oldgpa += 4 * c.getCredit();
                                    } else if (grade.equals("AB")) {
                                        oldgpa += 3.5 * c.getCredit();
                                    } else if (grade.equals("B")) {
                                        oldgpa += 3 * c.getCredit();
                                    } else if (grade.equals("BC")) {
                                        oldgpa += 2.5 * c.getCredit();
                                    } else if (grade.equals("C")) {
                                        oldgpa += 2 * c.getCredit();
                                    } else if (grade.equals("D")) {
                                        oldgpa += 1 * c.getCredit();
                                    }
                                }
                            }
                        }

                        // hitung gpa sebeulum dan sesudah remedial
                        double oldGpa = oldgpa / totalCredit;

                        // Print student details
                        System.out.println(s.getId() + "|" + s.getName() + "|" + s.getYear() + "|" + s.getStudyProgram()
                                + "|" + String.format("%.2f", oldGpa) + "|" + totalCredit);

                        break;

                    }
                }
                // If student doesn't exist, print error message
                if (!studentExists) {
                    System.out.println("Student not found");
                }
            } else if (data[0].equals("find-the-best-student")) {
                String academicYear = data[1];
                String semesterType = data[2];
            
                Student bestStudent = null;
                double highestGpa = 0.0;
            
                for (Student s : student) {
                    double gpa = 0.0;
                    int totalCredit = 0;
            
                    for (Enrollment e : enrollment) {
                        if (e.getStudent().equals(s.getId()) && e.getAcademicYear().equals(academicYear)) {
                            if ((semesterType.equals("odd") && e.getSemester() % 2 != 0) || 
                                (semesterType.equals("even") && e.getSemester() % 2 == 0)) {
                                gpa += calculateGpa(e.getGrade()) * e.getCourse().getCredit();
                                totalCredit += e.getCourse().getCredit();
                            }
                        }
                    }
            
                    gpa /= totalCredit;
            
                    if (gpa > highestGpa) {
                        highestGpa = gpa;
                        bestStudent = s;
                    }
                }
            
                if (bestStudent != null) {
                    System.out.println("Best student in " + academicYear + " " + semesterType + " semester: " + bestStudent.getName());
                } else {
                    System.out.println("No student found for " + academicYear + " " + semesterType + " semester");
                }
            }
            else if (data[0].equals("enrollment-remedial")) {

                // cek
                boolean courseExists = false;
                for (Course c : course) {
                    if (c.getCode().equals(data[1])) {
                        courseExists = true;
                        break;
                    }
                }
                // Check if student exists
                boolean studentExists = false;
                for (Student s : student) {
                    if (s.getId().equals(data[2])) {
                        studentExists = true;
                        break;
                    }
                }
                // If course or student doesn't exist, print error message and continue to next
                // input
                if (!courseExists) {
                    continue;
                }
                if (!studentExists) {
                    continue;
                }
                // Add enrollment only if course and student exist
                for (Enrollment e : enrollment) {
                    if (e.getStudent().equals(data[2]) && e.getCourse().equals(data[1])
                            && e.getAcademicYear().equals(data[3]) && e.getSemester().equals(data[4])) {
                        if (e.getGrade().equals("None")) {
                            continue;
                        }
                        if (e.getStudent().equals(data[2]) && !e.isRemedialUsed()) {
                            for (Course c : course) {
                                if (c.getCode().equals(data[1])) {
                                    e.setGrade(e.getGrade());
                                    e.setGrade(data[5]);

                                    // Mark that the student has used remedial for this course
                                    e.setRemedialUsed(true);
                                }
                            }
                        }

                    }
                }

            }
        }

        // print lecturer
        for (Lecturer l : lecturer) {
            System.out.println(l.getId() + "|" + l.getName() + "|" + l.getInitial() + "|" + l.getEmail() + "|"
                    + l.getStudyProgram());

        }
        for (Course c : course) {
            System.out.println(c.getCode() + "|" + c.getName() + "|" + c.getCredit() + "|" + c.getPassingGrade());
        }

        // print student
        for (Student stu : student) {
            System.out.println(stu);
        }

        // print enrollment
        for (Enrollment e : enrollment) {

            if (e.getPreviousGrade() != null && !e.getPreviousGrade().equals("None")) {
                System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getAcademicYear() + "|"
                        + e.getSemester() + "|" + e.getGrade() + "(" + e.getPreviousGrade() + ")");
            } else if (e.getPreviousGrade() != null && e.getPreviousGrade().equals("None")) {
                System.out.println(e.getCourse() + "|" + e.getStudent() + "|" + e.getAcademicYear() + "|"
                        + e.getSemester() + "|" + e.getGrade());

            }
        }

        masukan.close();
    }

}