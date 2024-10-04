//package edu.curtin.oose2024s2.assignment2;
package edu.curtin.App;

import edu.curtin.oose2024s2.assignment2.TownsInput;
import java.io.*;

/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
public class App
{
    public static void main(String[] args)
    {
        TownsInput inp = new TownsInput();
        //TownsInput inp = new TownsInput(123);  // Seed for the random number generator
        // inp.setErrorProbability(0.0);

        try
        {
            while(System.in.available() == 0)
            {
                // ... ?

                // For illustration purposes -- this just prints out the messages as they come in.
                System.out.println("---");
                String msg = inp.nextMessage();
                while(msg != null)
                {
                    System.out.println(msg);
                    msg = inp.nextMessage();
                }

                // Wait 1 second
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    throw new AssertionError(e);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("Error reading user input");
        }
    }
}
