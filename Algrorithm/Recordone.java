import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collections;
import java.util.*;

public class Recordone {
    public static void main(String[] args) {
      
    File file = new File("test.txt");

        try {
            double count_write=0.0;
            double count_read=0.0;
	        double freq=0.0, seq=0.0, rnd=0.0;
			long a1, b1=0, sizesum=0, sum, seqsize=0, rndsize=0;
            String sums = "";
	        ArrayList<String> str=new ArrayList<String>();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                   String a,b;
                   String line = scanner.nextLine();
             
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
                        count_write++;}

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
                        count_read++;}
    
                        sizesum = sizesum + b1;
                        //System.out.println(line);
                }
        double r = (count_read/count_write);

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

        double l = str.size();
        double f = freq/l;
        System.out.println("Results: \n");
		System.out.println("   Containers.....: 1");
        System.out.println("   Total Length...: "+(int)l);
        System.out.println("   Total Size.....: "+sizesum);
        System.out.println("   Write..........: "+(int)count_write);
        System.out.println("   Write %........: "+(count_write/l));
        System.out.println("   Read...........: "+(int)count_read); 
        System.out.println("   Read %.........: "+(count_read/l));
        System.out.println("   Read/Write %...: "+r);
        System.out.println("   Sequential.....: "+(int)seq);
        System.out.println("   Seq Size.......: "+seqsize);
        System.out.println("   Sequential %...: "+(seq/l));
        System.out.println("   Random.........: "+(int)rnd);
        System.out.println("   Rand Size......: "+rndsize);
        System.out.println("   Random %.......: "+(rnd/l));
	    System.out.println("   Frequency......: "+(int)freq);
        System.out.println("   Frequency %....: "+f);
		//Iterator itr = str.iterator();
		/*while(itr.hasNext()){
			//String p = itr.next();
			//System.out.println(itr.next());
		}*/
		
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}