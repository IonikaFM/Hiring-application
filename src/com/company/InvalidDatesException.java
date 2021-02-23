package com.company;

public class InvalidDatesException extends Exception{
    InvalidDatesException(){
        super("Entered dates are invalid!");
    }
}
