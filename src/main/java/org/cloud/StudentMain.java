package org.cloud;

import java.util.*;
import java.util.stream.Collectors;

public class StudentMain {
    public static void main(String[] args){

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        studentList.add(new Student(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        studentList.add(new Student(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        studentList.add(new Student(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        studentList.add(new Student(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        studentList.add(new Student(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        studentList.add(new Student(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        studentList.add(new Student(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        studentList.add(new Student(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        studentList.add(new Student(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        studentList.add(new Student(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        studentList.add(new Student(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        studentList.add(new Student(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        studentList.add(new Student(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        studentList.add(new Student(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        studentList.add(new Student(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        studentList.add(new Student(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        //Number of male and female students
        Map<String, Long> noOfStudentsByGender=studentList.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
        noOfStudentsByGender.entrySet().forEach(System.out::println);

        //Name of all departments in organization
        studentList.stream().map(Student::getDepartment).distinct().forEach(System.out::println);

        //average age of male and female student
        Map<String, Double> averageAgeByGender=studentList.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.averagingDouble(Student::getAge)));
        averageAgeByGender.entrySet().forEach(System.out::println);

        //details of highest paid student
        Student highestPaidStudent= studentList.stream().max(Comparator.comparingDouble(Student::getSalary)).get();
        System.out.println("Highest Paid Student Name is: "+highestPaidStudent.getName());
        System.out.println("Highest Paid Student Department is: "+highestPaidStudent.getDepartment());
        System.out.println("Highest Paid Student Salary is: "+highestPaidStudent.getSalary());

        //Names of all students who joined after 2015
        long studentJoiningDate=studentList.stream().filter(e->e.getYearOfJoining()>2015).count();
        System.out.println("No of Students who joined after 2015 are "+studentJoiningDate);
        studentList.stream().filter(s->s.getYearOfJoining()>2015).map(Student::getName).forEach(System.out::println);

        //Count the number of students in each department
        Map<String, Long> noOfStudentDeptWise=studentList.stream().collect(Collectors.groupingBy(Student::getDepartment, Collectors.counting()));
        noOfStudentDeptWise.forEach((key, value) -> System.out.println(key + ":" + value));

        //avg salary of each department
        Map<String, Double> avgSalaryByDept=studentList.stream().collect(Collectors.groupingBy(Student::getDepartment, Collectors.averagingDouble(Student::getSalary)));
        avgSalaryByDept.forEach((key, value) -> System.out.println(key + ":" + value));

        //youngest male student in product development department
        Optional<Student> youngMaleStudent = Optional.of(studentList.stream().filter(s -> "Product Development".equals(s.getDepartment())).min(Comparator.comparingInt(Student::getAge)).get());
        System.out.println(youngMaleStudent);

        //most working experience student in organization
        Student experiencedStudent = studentList.stream().sorted(Comparator.comparingInt(Student::getYearOfJoining)).findFirst().get();
        System.out.println(experiencedStudent);

        //male and females students in sales and marketing team
        Map<String, List<Student>> detailsOfStudentsInSM = studentList.stream().filter(s -> "Sales And Marketing".equalsIgnoreCase(s.getDepartment())).collect(Collectors.groupingBy(Student::getGender));
        detailsOfStudentsInSM.forEach((key, value) -> System.out.println(key + ":" + value));
        Map<String, Long> noOfStudentsInSM = studentList.stream().filter(s -> "Sales And Marketing".equalsIgnoreCase(s.getDepartment())).collect(Collectors.groupingBy(Student::getGender, Collectors.counting()));
        noOfStudentsInSM.forEach((key, value) -> System.out.println(key + ":" + value));

        //average salary of male and female students
        Map<String, Double> avgSalaryByGender = studentList.stream().collect(Collectors.groupingBy(Student::getGender, Collectors.averagingDouble(Student::getSalary)));
        avgSalaryByGender.forEach((k, v) -> System.out.println(k + ":" + v));

        //names of all students in each department
        Map<String, List<Student>> studentsByDept = studentList.stream().collect(Collectors.groupingBy(Student::getDepartment));
        studentsByDept.forEach((key, studnetlist) -> {
            System.out.println(key + ":");
            for (Student student : studnetlist) {
                System.out.println(student.getName());
            }
        });

        //average salary and total salary of whole organization
        DoubleSummaryStatistics totalAvgSalary=studentList.stream().collect(Collectors.summarizingDouble(Student::getSalary));
        System.out.println("Total salary of whole org is :"+totalAvgSalary.getSum());
        System.out.println("Average salary of whole org is :"+totalAvgSalary.getAverage());

        //students whose age is equal or less than 25
        studentList.stream().filter(s -> s.getAge() <= 25).toList().forEach(System.out::println);
        Map<Boolean, List<Student>> partitionStudentByAge=studentList.stream().collect(Collectors.partitioningBy(s->s.getAge()<=25));
        partitionStudentByAge.forEach((key, studnetlist) -> {
            System.out.println(key + ":");
            for (Student student : studnetlist) {
                System.out.println(student.getName());
            }
        });

        //oldest student in org. His age and dept
        Student oldestStudent = studentList.stream().max(Comparator.comparingInt(Student::getAge)).get();
        System.out.println("Oldest Student Name is "+ oldestStudent.getName());
        System.out.println("Oldest Student Age is "+ oldestStudent.getAge());
        System.out.println("Oldest Student Dept is "+oldestStudent.getDepartment());

        //student salary descending order
         studentList.stream().sorted((s1, s2) -> (int) (s2.getSalary() - s1.getSalary())).collect(Collectors.toList()).forEach(System.out::println);

        //student salary descending order limit to 3
        studentList.stream().sorted((s1, s2) -> (int) (s2.getSalary() - s1.getSalary())).limit(3).collect(Collectors.toList()).forEach(System.out::println);




    }
}
