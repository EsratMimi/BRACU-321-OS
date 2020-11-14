/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |0 Templates
 * and open the template in the editor.
 */
//package bankers;

/**
 *
 * @author esrat
 */
import java.util.Scanner;
public class Bankers{
    static int need[][],allocate[][],max[][],avail[][],np,nr;
    static int safeseq [] ;
     
    private void input(){
     Scanner sc=new Scanner(System.in);
     System.out.print("Enter no. of processes and resources : ");
     np=sc.nextInt();  //no. of process
     nr=sc.nextInt();  //no. of resources
     need=new int[np][nr];  
     max=new int[np][nr];
     safeseq=new int [np];
     allocate=new int[np][nr];
     avail=new int[1][3];
      
     System.out.println("Enter allocation matrix -->");
     for(int i=0;i<np;i++)
          for(int j=0;j<nr;j++)
         allocate[i][j]=sc.nextInt();  //allocation matrix
       
     System.out.println("Enter max matrix -->");
     for(int i=0;i<np;i++)
          for(int j=0;j<nr;j++)
         max[i][j]=sc.nextInt();  //max matrix
       
        System.out.println("Enter available matrix -->");
        for(int j=0;j<3;j++)
         avail[0][j]=sc.nextInt();  //available matrix
         
        sc.close();
    }
    
    
     
    private int[][] calc_need(){
       for(int i=0;i<np;i++)
         for(int j=0;j<nr;j++)  //calculating need matrix
          need[i][j]=max[i][j]-allocate[i][j];
        
       return need;
    }
  
    private boolean check(int i){
       //checking if all resources for ith process can be allocated
       for(int j=0;j<nr;j++) 
       if(avail[0][j]<need[i][j])
          return false;
    
    return true;
    }
 
    public void isSafe(){
       input();
       calc_need();
       boolean done[]=new boolean[np];
       int j=0, count=0;
 
       while(j<np){  //until all process allocated
       boolean allocated=false;
       for(int i=0;i<np;i++)
        if(!done[i] && check(i)){  //trying to allocate
            for(int k=0;k<nr;k++)
            avail[0][k]=avail[0][k]-need[i][k]+max[i][k];
         System.out.println("Allocated process : "+i);
         allocated=done[i]=true;
               j++;
               safeseq[count++]=j;
             }
          if(!allocated) break;  //if no allocation
       }
       if(j==np)  //if all processes are allocated
        System.out.println("\nSafely allocated");
       else
        System.out.println("All proceess cant be allocated safely");
       
       
    }
     
    public static void main(String[] args) {
       new Bankers().isSafe();
       
//       System.out.println(nr);
//       for(int i=0; i<np ; i++){
//           System.out.print("Process "+safeseq[i]+" ");
//       }
//       
       
       
    }
}
