/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadcode;

/**
 *
 * @author 17101538
 */

class ThreadCode extends Thread{
     int a,b; 
    static int sum=0;
    public ThreadCode(int a, int b){
        this.a = a;
        this.b = b;
      //  run();
    }
    public void run() {
        for(int i=a;i<=b;i++){
            sum += i;
           //     System.out.println(sum);     
        }
          System.out.println("Sum of "+a+" to "+ b+" numbers = "+sum);   
    }
    
    public static void main(String[] args) {
        ThreadCode t1 = new ThreadCode(1,50);
        ThreadCode t2 = new ThreadCode(51,100);
        t1.start();
        t2.start();
        
    }
}
