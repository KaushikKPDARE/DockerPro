package publisherUI;

import java.io.*;
import java.util.*;

public class RecordOne extends Thread{

    double count_write=0.0;
    double count_read=0.0;
    double freq=0.0, seq=0.0, rnd=0.0;
	long a1, b1=0, sizesum=0, sum, seqsize=0, rndsize=0;
	double r;
    String sums = "";
    private String line;
    ArrayList<String> str=new ArrayList<String>();
	private static Scanner scanner;
    
    public RecordOne(String line){
    	this.line = line;
    }
    
	public void run(){
	  synchronized(this){
		try{
			String a,b;
           
            //if(line.equals(sums)){seq++;}
            //else {rnd++;}

            if(line.charAt(0)=='W'){
                 a = line.substring(3,line.indexOf(','));
	                b = line.substring(line.indexOf(',')+1,line.indexOf(',')+4);
	                str.add(a);
                 if(a.equals(sums)){seq++;
                 seqsize = seqsize+b1;
                 }
                 else {rnd++;
                 rndsize = rndsize+b1;
                 }
                 //System.out.println(b);
                 a1 = Long.parseLong(a);
			        b1 = Long.parseLong(b);
			        sum = a1+b1;
                 sums = Long.toString(sum);
                 //System.out.println(sums);
                 //System.out.println(a1+"+"+b1+"= "+sum);
                 count_write++;
             }

            else if(line.charAt(0)=='R'){
                 a = line.substring(3,line.indexOf(','));
                 b = line.substring(line.indexOf(',')+1,line.indexOf(',')+4);
			        str.add(a);
                 if(a.equals(sums)){seq++;
                 seqsize = seqsize+b1;
                 }
                 else {rnd++;
                 rndsize = rndsize+b1;
                 }
                 a1 = Long.parseLong(a);
			        b1 = Long.parseLong(b);
			        sum = a1+b1;
                 sums = Long.toString(sum);
                 //System.out.println(sums);
                 //System.out.println(a1+"+"+b1+"= "+sum);
                 //System.out.println(b);
                 count_read++;
             }

                 sizesum = sizesum + b1;
                 //System.out.println(line);
                 
         r = (count_read/count_write);

         HashMap<String,Integer> cmap= new HashMap<String,Integer>();
         for(String in: str){
             if(cmap.containsKey(in)){
                   cmap.put(in,cmap.get(in)+1);
			       freq++;
             }
         else{
             cmap.put(in,1);
         }
        }
	   }
		
		catch(Exception e){
			e.printStackTrace();
		}
	  }
		
	}
	
	public static void main(String[] args){
		
		File file = new File("test.txt");
		Thread t;
		RecordOne one = null;
		try{
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                   String line = scanner.nextLine();
                   one = new RecordOne(line);
                   t = new Thread(one);
                   t.start();
            }
		}
		
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		finally{
			System.out.println("File has not been found");
		}
		
        double l = one.str.size();
        double f = one.freq/l;
        System.out.println("Results: \n");
	    System.out.println("   Containers.....: 1");
        System.out.println("   Total Length...: "+(int)l);
        System.out.println("   Total Size.....: "+one.sizesum);
        System.out.println("   Write..........: "+(int)one.count_write);
        System.out.println("   Write %........: "+(one.count_write/l));
        System.out.println("   Read...........: "+(int)one.count_read); 
        System.out.println("   Read %.........: "+(one.count_read/l));
        System.out.println("   Read/Write %...: "+one.r);
        System.out.println("   Sequential.....: "+(int)one.seq); 
        System.out.println("   Seq Size.......: "+one.seqsize);
        System.out.println("   Sequential %...: "+(one.seq/l));
        System.out.println("   Random.........: "+(int)one.rnd);
        System.out.println("   Rand Size......: "+one.rndsize);
        System.out.println("   Random %.......: "+(one.rnd/l));
        System.out.println("   Frequency......: "+(int)one.freq);
        System.out.println("   Frequency %....: "+f);
	}
	
}