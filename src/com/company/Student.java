package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    String Id;
    Person Info = new Person();
    ScoreSheet Scores = new ScoreSheet();

    public int getId(){
        return Integer.parseInt(this.Id);
    }

    public Date getDate() throws ParseException {
        Date result = new SimpleDateFormat("dd/MM/yyyy").parse(this.Info.BirthDay);
        return result;
    }
}
