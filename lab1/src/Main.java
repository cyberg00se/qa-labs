package lab1.src;

import java.io.InvalidObjectException;

public class Main {

    
    public static void main(String[] args) throws Exception {

        Exceptions exs=new Exceptions();

        System.out.println(exs.isCritical(new InvalidObjectException("hell")));
        System.out.println(exs.isCritical(new NullPointerException()));

        exs.manageException(new InvalidObjectException("hell"));
        exs.manageException(new NullPointerException());
        exs.manageException(new ArrayIndexOutOfBoundsException());

        System.out.println(exs.critCounter);
        System.out.println(exs.usualCounter);
    }

}