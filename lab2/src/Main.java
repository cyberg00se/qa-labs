package lab2.src;

import java.io.InvalidObjectException;

import lab1.src.ExceptionManager;

public class Main {

    
    public static void main(String[] args) throws Exception {

        ExceptionManager exs=new ExceptionManager();

        System.out.println(exs.isCritical(new InvalidObjectException("hell")));
        System.out.println(exs.isCritical(new NullPointerException()));

        exs.manageException(new InvalidObjectException("hell"));
        exs.manageException(new NullPointerException());
        exs.manageException(new ArrayIndexOutOfBoundsException());

        System.out.println(exs.getCritCounter());
        System.out.println(exs.getUsualCounter());
    }

}