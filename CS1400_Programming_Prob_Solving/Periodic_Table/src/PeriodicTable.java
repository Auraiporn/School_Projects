//
// Name: 		Auraiporn Auksorn 
// Project: 	3 
// Due:			Wednesday October 23, 2019 
// Course: 		cs-1400-03-sp19 
// Description: 
// 				A program PeriodicTable will read the data from the file, and then this program will print out the data in 3 different ways. First, if 
// 				the user enters the command line "java PeriodicTable", the program will print out the programmer's name and the number of the elements in the 
// 				periodic table. Second, if the user enters the command line "java PeriodicTable atomic filename", the program will print out the 
// 				periodic table sorted by the atomic number, 1-118. Third, if the user enters the command line "java PeriodicTable name filename", the 
// 				program will print out the periodic table sorted by the atomic name, A-Z. If the user does not enter the command line correctly,
// 				the program will say "Unknown action.". 

import java.util.Scanner;
import java.io.*;

public class PeriodicTable {
         public static void main (String [] args) throws IOException {

                final int MAX_ELEMENT = 130;
                int[] atomicNumber = new int [MAX_ELEMENT];
                String [] symbol = new String [MAX_ELEMENT];
                String [] name = new String [MAX_ELEMENT];
                float [] atomicMass = new float[MAX_ELEMENT];

                File myFile = new File("/user/tvnguyen7/data/periodictable.dat");
                Scanner inputFile  = new  Scanner (myFile);

                int  elementSize = 0;
                while (inputFile.hasNext()){
                        atomicNumber[elementSize] = inputFile.nextInt();
                        symbol[elementSize] = inputFile.next();
                        atomicMass[elementSize] = inputFile.nextFloat();
                        name[elementSize] = inputFile.next();
                        elementSize++;
                }
                inputFile.close();

                if (args.length==0){
                        System.out.println("Periodic Table by A. Auksorn");
                        System.out.println();
                        System.out.println( elementSize + " elements");
                }


                PrintWriter outputFile = new PrintWriter("periodictable.txt");
                outputFile.println("Periodic Table " + "("+ elementSize +")");
                outputFile.println();
                outputFile.println("ANo. "+"Name                "+" Abr "+"   Mass");
                outputFile.println("---- -------------------- --- -------");
                if((args.length > 0) && (args[1].equals("atomic")) && (args[2].equals("periodictable.txt"))){
                    int startScan, index, minIndex, minValue;
                                   for (startScan = 0; startScan < (elementSize - 1) ; startScan++){
                                           minIndex = startScan;
                                           minValue = atomicNumber[startScan];
                                           for (index = startScan+1; index < elementSize ; index++){
                                                   if(atomicNumber[index] < minValue){
                                                           minValue = atomicNumber[index];
                                                           minIndex = index;
                                                   }
                                           }
                                           atomicNumber[minIndex]= atomicNumber[startScan];
                                           atomicNumber[startScan] = minValue;
                                   }
                   double total = 0;
                   double average = 0;
                           for (int i = 0; i < elementSize ; i++){
                                   outputFile.printf("%4d", atomicNumber[i]);
                                   outputFile.printf(" %-1s" , name[i]);
                                           if (name[i].length() > 10){
                                                   outputFile.printf("\t  %s" , symbol[i]);
                                           }
                                           else{
                                                   outputFile.printf("\t\t  %s", symbol[i]);
                                           }

                                           if (symbol[i].length()==1){
                                                   outputFile.printf("%10.2f\n", atomicMass[i]);
                                           }
                                           else if (symbol[i].length()==2){
                                                   outputFile.printf("  %7.2f\n", atomicMass[i]);
                                           }
                                           else{
                                                   outputFile.printf("%8.2f\n", atomicMass[i]);
                                           }

                                           total += atomicMass[i];
                                           average = total / atomicMass.length;

                                   }
                           outputFile.println();
                           outputFile.printf("The average mass:%19.2f\n", average);

                           outputFile.close();
                           System.out.println("Output file printed.");

           }
           else if ((args.length > 0) && (args[1].equals("name")) && (args[2].equals("periodictable.txt"))){
                    for(int i = 0; i < elementSize -1; i++){
                                           for(int j = i+1; j < elementSize; j++){
                                                   if(name[i].compareTo(name[j]) >0){
                                                           String tempName = name[i];
                                                           name[i] = name[j];
                                                           name[j] = tempName;

                                                           int tempNumber = atomicNumber[i];
                                                           atomicNumber[i] = atomicNumber[j];
                                                           atomicNumber[j] = tempNumber;

                                                           String tempSymbol = symbol[i];
                                                           symbol[i] = symbol[j];
                                                           symbol[j] = tempSymbol;

                                                           float tempMass = atomicMass[i];
                                                           atomicMass[i] = atomicMass[j];
                                                           atomicMass[j] = tempMass;
                                                   }
                                           }
                                   }
                                   double total = 0;
                                   double average = 0;
                                   for (int i = 0; i < elementSize ; i++){
                                           outputFile.printf("%4d", atomicNumber[i]);
                                           outputFile.printf(" %-1s" , name[i]);
                                           if (name[i].length() > 10){
                                                    outputFile.printf("\t  %s" , symbol[i]);
                                           }
                                           else{
                                                    outputFile.printf("\t\t  %s", symbol[i]);
                                           }

                                           if (symbol[i].length()==1){
                                               outputFile.printf("%10.2f\n", atomicMass[i]);
                                      }
                                      else if (symbol[i].length()==2){
                                               outputFile.printf("  %7.2f\n", atomicMass[i]);
                                      }
                                      else{
                                       outputFile.printf("%8.2f\n", atomicMass[i]);
                                      }
                                      total += atomicMass[i];
                                      average = total/atomicMass.length;
                              }
                               outputFile.println();
                               outputFile.printf("The average mass:%19.2f\n", average);
                               outputFile.close();
                               System.out.println("Output file printed.");
      }else {
                System.out.println("Unknown action.");
      }
}

}

