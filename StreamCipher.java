import java.util.Random; // to generate random number
import java.io.*; // read and write files

public class StreamCipher {
	
    public static void main(String[] args){
    
        
        if(args.length !=6){ // number of arguments should be 6, 3 to say waht they are and 3 as their value
            System.out.println("Invalid number of arguements");
            return;
        }

        //initialising
        long key = 0;
        String inFile = null;
        String outFile = null;

        //parsing the arguement
        for(int i = 0; i < args.length; i+=2){
            switch (args[i]) {
                case "--key":
                    try{
                        key = Long.parseUnsignedLong(args[i+1]);
                    } catch( NumberFormatException e){
                        System.out.println("Invalid key");
                        return;
                    }
                    
                    break;
                case "--in":
                    inFile = args[i+1];
                    break;
                case "--out":
                    outFile = args[i+1];
                    break;
                default:
                    System.out.println("Unknown arguement" + args[i]);
                    return;
            }
        }
        //reading the files
        try(
            InputStream in = new FileInputStream(inFile);
            OutputStream out = new FileOutputStream(outFile);
        ){
            int data;
            Random prng = new Random(key);
            while((data = in.read()) !=-1){
                out.write(data ^ prng.nextInt(256)); //XOR and writing the files
            }

        }catch(FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }catch(IOException e){
            System.out.println("I/O error: " + e.getMessage());
        }
    }
}
