package org.cloud;

import java.util.Random;
import java.util.StringJoiner;

public class SampleQuestions {
    public static void main(String[] args){
        //adding suffix and prefix in the string
        StringJoiner stringJoiner=new StringJoiner("::","Bharath","Talladi");
        stringJoiner.add("Kumar");
        System.out.println(stringJoiner);

        //ten random numbers using for each
        Random random=new Random();
        random.ints().limit(10).forEach(System.out::println);
    }
}
