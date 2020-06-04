package com.company;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingDouble;
//import static java.util.stream.Collectors.toList;
import java.util.*;
import java.util.stream.*;
import java.io.*;

import java.text.ParseException;

import java.util.DoubleSummaryStatistics;

public class Main {
    public static void main(String[] args) throws Exception  {
	    // DATA INPUT
        /*
            This Section Is Only For Extracting Data From File
            Q1 to Q8 below this section only used Streams, Lambdas, and Method Reference
        */
        // DATA INPUT
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(new File("DATA.csv"));

        while (scanner.hasNextLine())  //returns a boolean value
        {
            //FN-LN-LOC-DATE-GENDER-MATH-ENGLISH-CHINESE-AVERAGE-ID
            String buffer = scanner.nextLine();
            Scanner lineScanner = new Scanner(buffer);
            lineScanner.useDelimiter(",");
            Student input = new Student();

            input.Info.FirstName = lineScanner.next();

            input.Info.LastName = lineScanner.next();
            input.Info.city = City.fromId(Integer.parseInt(lineScanner.next()));
            input.Info.BirthDay = lineScanner.next();
            input.Info.gender = Gender.fromId(Integer.parseInt(lineScanner.next()));


            input.Scores.Math = Integer.parseInt(lineScanner.next());
            input.Scores.English = Integer.parseInt(lineScanner.next());
            input.Scores.Chinese = Integer.parseInt(lineScanner.next());
            input.Scores.Average = Double.parseDouble(lineScanner.next());

            input.Id = lineScanner.next();

            lineScanner.close();
            students.add(input);
        }
        scanner.close();  //closes the scanner
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //Q1
        System.out.println("Q1(1) : ");
        //List result = students.stream().sorted(comparing(Student::getId)).collect(toList());
        Stream<Student> Q1OutputA = students.stream().sorted(comparing(Student::getId));
        Q1OutputA.forEach(student -> System.out.println(student.Info.FirstName + " " + student.Info.LastName));
        System.out.println("Q1(2) : ");
        Stream<Student> Q1OutputB = students.stream().sorted((o1, o2) -> {
            try{
                return o2.getDate().compareTo(o1.getDate());
            }catch (ParseException e){
                System.out.println("Date Converter Error");
            }
            return 0;
        });
        Q1OutputB.forEach(student -> System.out.println(student.Info.FirstName + " " + student.Info.LastName));
        System.out.println();
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //Q2
        System.out.println("Q2 : ");
        Set<Person> Q2Persons = students.stream().map(Student::getInfo).collect(Collectors.toSet());
        Set<City> Q2City = Q2Persons.stream().map(Person::getCity).collect(Collectors.toSet());
        Set<String> Q2Output = Q2City.stream().map(City::getIDString).collect(Collectors.toSet());
        Q2Output.forEach(System.out::println);
        System.out.println();
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //Q3
        System.out.println("Q3 : ");
        Map<String, List<Person>> Q3Output = students.stream().map(Student::getInfo).collect(Collectors.groupingBy(Person -> Person.city.getIDString()));
        Q3Output.forEach((name, list) -> {
            System.out.println(name + ":");
            list.forEach(Person -> System.out.println(Person.getFirstName() + " " + Person.getLastName()));
            System.out.println();
        });
        System.out.println();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //Q4
        System.out.println("Q4 : ");
        Map<Boolean, List<Person>> Q4Output = students.stream().map(Student::getInfo).collect(partitioningBy(Person -> Person.gender.getId() == 0));
        Q4Output.forEach((gender, list) -> {
            System.out.println(gender ? "Male:" : "Female:");
            list.forEach(Person -> System.out.println(Person.getFirstName() + " " + Person.getLastName()));
            System.out.println();
        });
        System.out.println();

        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //Q5
        System.out.println("Q5 : ");
        DoubleSummaryStatistics mathInfo = students.stream().map(Student::getScores).collect(summarizingDouble(ScoreSheet::getMath)),
                                englishInfo = students.stream().map(Student::getScores).collect(summarizingDouble(ScoreSheet::getEnglish)),
                                chineseInfo = students.stream().map(Student::getScores).collect(summarizingDouble(ScoreSheet::getChinese));
        System.out.println( "Math:\n" +
                            "\tAverage:\t" + mathInfo.getAverage() + "\n" +
                            "\tMinimum:\t" + mathInfo.getMin() + "\n" +
                            "\tMaximum:\t" + mathInfo.getMax() + "\n");
        System.out.println( "English:\n" +
                            "\tAverage:\t" + englishInfo.getAverage() + "\n" +
                            "\tMinimum:\t" + englishInfo.getMin() + "\n" +
                            "\tMaximum:\t" + englishInfo.getMax() + "\n");
        System.out.println( "Chinese:\n" +
                            "\tAverage:\t" + chineseInfo.getAverage() + "\n" +
                            "\tMinimum:\t" + chineseInfo.getMin() + "\n" +
                            "\tMaximum:\t" + chineseInfo.getMax() + "\n");
        System.out.println();



    }
    
}
