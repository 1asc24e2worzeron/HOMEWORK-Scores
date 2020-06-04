package com.company;

public class Student
{
    String Id;
    Person Info = new Person();
    ScoreSheet Scores = new ScoreSheet();

    public int getId()
    {
        return Integer.parseInt(this.Id);
    }
    
    public Person getInfo()
    {
        return Info;
    }
}
