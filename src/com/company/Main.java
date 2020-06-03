package com.company;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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
        scanner.useDelimiter(",");
        while (scanner.hasNext())  //returns a boolean value
        {
            //FN-LN-LOC-DATE-GENDER-MATH-ENGLISH-CHINESE-AVERAGE-ID
            Student input = new Student();
            input.Info.FirstName = scanner.next();
            input.Info.LastName = scanner.next();

            input.Info.city = City.fromId(Integer.parseInt(scanner.next()));
            input.Info.BirthDay = scanner.next();
            input.Info.gender = Gender.fromId(Integer.parseInt(scanner.next()));

            input.Scores.Math = Integer.parseInt(scanner.next());
            input.Scores.English = Integer.parseInt(scanner.next());
            input.Scores.Chinese = Integer.parseInt(scanner.next());
            input.Scores.Average = Double.parseDouble(scanner.next());

            input.Id = scanner.next();

            students.add(input);
        }
        scanner.close();  //closes the scanner
        ////////////////////////////////////////////////////////////////////////////////////////////////////
        //Q1
        List result = students.stream().sorted(comparing(Student::getId)).collect(toList());

    }
}
