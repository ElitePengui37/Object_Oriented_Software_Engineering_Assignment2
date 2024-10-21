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
import java.util.logging.Logger;

/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
public class App
{
    private static final Logger log = Logger.getLogger(App.class.getName()); // imports logger
    public static Integer day = 0;

    public static void main(String[] args)
    {
        List<String> msgList = new ArrayList<String>(); // msgList holds inp messages and used for object factories

        TownsInput inp = new TownsInput();
        //TownsInput inp = new TownsInput(123);  // Seed for the random number generator
        //inp.setErrorProbability(0.0);

        ObjectFactory factory = new ObjectFactory(); // object factory

        List<RailwayController> railwayControllers = factory.getRailwayControllers(); // Retrieve railway controller list from the factory for graphics object (allows graphics object see state of railway)

        // create objects in from packages
        Graphics GUI = new Graphics(railwayControllers);
        FileProcessor FileIO = new FileProcessor(railwayControllers);

        // call package objects to check if they been imported properly
        GUI.CreateObject();
        FileIO.CreateFileObject();

        Subject subject = new Subject(); // observer pattern subject object

        // add GUI and FileIO objects to the subject
        subject.addObserver(GUI);
        subject.addObserver(FileIO);


        try
        {
            while(System.in.available() == 0)
            {

                System.out.println("---");
                System.out.println("DAY " + day +":\n"); // display day
                
                String msg = inp.nextMessage(); // retrieve first message
                
                while(msg != null) // display all of todays messages and add theem to a message list
                {
                    System.out.println(msg);
                    msgList.add(msg); // add all todays messages into msgList
                    msg = inp.nextMessage();
                }
                System.out.println("\n");


                // log all messages in msglist
                log.info("msgList items:\n\n\n");
                for (String item : msgList)
                {
                    log.info(item);
                }


                factory.setMessages(msgList); // Inject updated message list into factory every timestep

                factory.processMessages();

                // notify observers of changes
                subject.receiveUpdate(factory.getTowns(), factory.getRailways()); // get new info from object factory lists

                msgList.clear(); // clear msgList to prevent old messages going into next day

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
