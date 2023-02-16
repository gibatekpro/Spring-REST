package com.gibatekpro.jackson_json_databinding;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) {

        try {

            //create object mapper
            ObjectMapper objectMapper = new ObjectMapper();

            //read JSON file and map/convert to Java POJO:
            //data/sample-lite.json

            Student student = objectMapper.readValue(new File("data/sample-full.json"), Student.class);

            //print first name and last name
            System.out.println(student);
            System.out.println("First name = " + student.getFirstName());
            System.out.println("Last name = " + student.getLastName());

            Address address = student.getAddress();

            System.out.println(address);

        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
