package com.company;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import java.util.stream.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.text.ParseException;

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
        ////////////////////////////////////////////////////////////////////////////////////////////////////


    }
}
