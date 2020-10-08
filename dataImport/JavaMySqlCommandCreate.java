/**
 * Insert large datasets into a MySQL command
 * to populate the DB. 
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ReadFile {

    public String[] readerMethod(String fileName) throws IOException{
        
        // Creats file reader object with the file being passed
        FileReader file = new FileReader(fileName);
        // reads the file 
        BufferedReader read = new BufferedReader(file);
        //Array list created
        List<String> myList = new ArrayList<String>();
        String line = null;
        //while loop to read each line
        while((line = read.readLine()) != null) {
            //adds each line as a element in the arraylist myList
            myList.add(line);
        }
        //BufferedReader Class must be closed to end reading of files
        read.close();
        //Converts ArrayList into an Array 
        //The size of the array is declared by the size of the ArrayList
        return myList.toArray(new String[myList.size()]);
    }
}

class Run {

    public static void main(String[] args){

        //Creates ReadFile object for use
        ReadFile readFile = new ReadFile();
        //The desired text file no extension is needed
        //This Script must be in the directory of the file on UNIX Based OS
        String file = "success";

        try {
            //Passes the intended file to be read to the readFile class
            String[] lines = readFile.readerMethod(file);
            //ForEach Loop that steps through the the Array
            // for(String line : lines){
            //     System.out.println(line);
            // }
            createSqlTextfile(lines);
            //Writes to External File
            // File must already be created for this section
            //Pass the file name 

         //In the event an Error was to occure this block catches the error without
         //Crashing the application "Unexpected Crash"   
        }catch(IOException e){
            //Print the exception that occured
            System.out.println("Unable to create "+
                file + ": " + e.getMessage());
        }
    }

    public static void createSqlTextfile(String[] arrVar){
        try{
            //BufferedWriter writeConstructor = new BufferedWriter(new FileWriter("MySQLCommands.txt"));
            //place in for loop
            // writeConstructor.write("aString");
            // writeConstructor.close();
            // System.out.println("File created successuflly");

            PrintWriter output = new PrintWriter("test.sql");
            for(int x = 0; x < arrVar.length; x++){
                output.println(
                    "INSERT INTO launch_vehicles(mission_success) VALUES(" +
                    "\""+ arrVar[x] +"\""+ "),");    
            }
            output.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

/**
 * Constructure for writing a file 
 *      PrintWriter output = new PrintWriter("temp.txt");
 *      output.print("text to print to file");
 *      output.close();
 *
 *  File targetFile = new File()
 */