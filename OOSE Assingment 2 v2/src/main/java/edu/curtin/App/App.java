//package edu.curtin.oose2024s2.assignment2;
package edu.curtin.App;

import main.java.edu.GUI.Graphics;
import main.java.edu.FileIO.FileProcessor;
import edu.curtin.oose2024s2.assignment2.TownsInput;
import main.java.edu.State.RailwayController;
import main.java.edu.Observer.*;
import main.java.edu.Factory.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
public class App
{
    public static Integer day = 0;

    public static void main(String[] args)
    {
        List<String> msgList = new ArrayList<String>(); // msgList holds inp messages and used for object factories

        TownsInput inp = new TownsInput();
        //TownsInput inp = new TownsInput(123);  // Seed for the random number generator
        // inp.setErrorProbability(0.0);

        // object factory
        ObjectFactory factory = new ObjectFactory();

        // Retrieve the list of RailwayController from the factory for graphics object
        List<RailwayController> railwayControllers = factory.getRailwayControllers();

        // create objects in from packages
        Graphics GUI = new Graphics(railwayControllers);
        FileProcessor FileIO = new FileProcessor(railwayControllers);

        // call package objects to check if they been imported properly
        GUI.CreateObject();
        FileIO.CreateFileObject();


        // observer pattern subject object
        Subject subject = new Subject();

        // add GUI and FileIO objects to the subject
        subject.addObserver(GUI);
        subject.addObserver(FileIO);


        try
        {
            while(System.in.available() == 0)
            {

                // For illustration purposes -- this just prints out the messages as they come in later put this into the gui part.
                System.out.println("---");
                System.out.println("DAY " + day +":\n"); // display day later move this into the GUI object when observer pattern has been implemented
                String msg = inp.nextMessage();
                while(msg != null)
                {
                    System.out.println(msg);
                    msgList.add(msg); // add all todays messages into msgList
                    msg = inp.nextMessage();
                }

                System.out.println("\n\n\nChecking msgList items");
                for (String item : msgList)
                {
                    System.out.println(item); //LOG THIS LATER
                }

                
                // Inject message list into factory every timestep
                factory.setMessages(msgList);

                // Process the messages
                factory.processMessages();

                // notify observers of changes
                subject.receiveUpdate(factory.getTowns(), factory.getRailways()); // get town new info from object factory lists

                // clear megList
                msgList.clear();

                // Wait 1 second
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    throw new AssertionError(e);
                }

                day++;
            }
        }
        catch(IOException e)
        {
            System.out.println("Error reading user input");
        }
    }
}
