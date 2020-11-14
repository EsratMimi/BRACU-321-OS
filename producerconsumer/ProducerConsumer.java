/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

/**
 *
 * @author 17101538
 */
import java.util.LinkedList; 
 
public class ProducerConsumer {

   public static void main(String[] args) 
                        throws InterruptedException 
    { 
        final PC pc = new PC(); 
  
        Thread t1 = new Thread(new Runnable() 
        { 

            public void run() 
            { 
                try
                { 
                    pc.produce(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
   
        Thread t2 = new Thread(new Runnable() 
        { 
           
            public void run() 
            { 
                try
                { 
                    pc.consume(); 
                } 
                catch(InterruptedException e) 
                { 
                    e.printStackTrace(); 
                } 
            } 
        }); 
  
   
        t1.start(); 
        t2.start(); 
  
     
        t1.join(); 
        t2.join(); 
    } 
  
   public static class PC 
    { 
       LinkedList<Integer> list = new LinkedList<>();  // buffer
        int capacity = 2;  // buffer size 
  
        public void produce() throws InterruptedException 
        { 
            int value = 0; 
            while (true) 
            { 
                synchronized (this) 
                { 
                   while (list.size()==capacity) 
                        wait(); // producer sleep a jabe, consumer ke uthaiya dibe
  
                    System.out.println("Producer produced-"
                                                  + value); 
                    System.out.println("total items "+list.size());
  
                    list.add(value++); 
                 
                    notify(); 
  
                   Thread.sleep(1000); 
                } 
            } 
        } 
  
        public void consume() throws InterruptedException 
        { 
            while (true) 
            { 
                synchronized (this) // object pass kortesi this class er 
                { 
                    while (list.size()==0) 
                        wait(); 
  
                   int val = list.removeFirst(); 
  
                    System.out.println("Consumer consumed-"
                                                    + val); 
                    System.out.println("total items "+list.size());
  
                    notify(); // sleep theke tule deijegula shared resource a wait kortese
  
                   Thread.sleep(1000); 
                } 
            } 
        } 
    } 
} 