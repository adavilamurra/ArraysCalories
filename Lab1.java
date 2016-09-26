/************************************************************************************
 **                                                                                **
 **    Code by: Alejandro Davila Murra                                             **
 **                                                                                **
 **    CS 2401: Elementary Data Structures and Algorithms                          **
 **    Instructor: Mahmud Shahriar Hossain                                         **
 **    TAs: Debakar Shamanta, Anthony M. Ortiz Cepeda, Andres H Olivas Aguilar     **
 **    Lab Assignment #1                                                           **
 **    Objective: Refresh memory of 1-dimensional arrays, in preparation           **
 **               for the following lab on multi-dimensional arrays.               **
 **    Background: A local fitness company has contracted you to create a simple   **
 **                diet tracking software that will help clients count calories.   **
 **    Due Date: Sunday, January 31                                                **
 **    Last modification: 1/30/2016                                                **
 **                                                                                **
 ************************************************************************************/

import java.io.*;
import java.util.*;

public class Lab1 {
  
  public static void main(String[] args) throws FileNotFoundException { 
   try{ 
    //Declare and Initialize scanner
    Scanner myInFile = new Scanner(new FileReader("input1_1.txt"));
    
    //Declare arrays for meals and days of week
    int[] arBreakfast = new int[7];
    int[] arLunch = new int[7];
    int[] arDinner = new int[7];
    String[] lines = new String[7];
    String[] dayWeek = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    //read data from file
    while(myInFile.hasNext()){
      try{
          for(int i = 0; i < lines.length; i++){                                          //Store each line from file in a String Array
              lines[i] = myInFile.nextLine();   
          }
      }
          catch(NoSuchElementException error){
            System.out.println("ERROR. You can only have calories from 7 days.");          //Print error if there are more than 7 lines
            System.exit(0);
          }
    }
    
    
    for(int i = 0; i < lines.length; i++){                                                  //Split stored Strings into 3 different Strings (3 different meals)
      String[] meals = lines[i].split("\\W+");                                              //Store those strings in an array
      if(meals.length != 3){
        System.out.print("ERROR. You need calories of exactly 3 meals for each day.");      //print error if there are not exactly 3 different Strings stored in the array.
        System.exit(0);  
      }
      arBreakfast[i] = Integer.parseInt(meals[0]);                                          //Convert String with calories to int and then store it in Breakfast, Lunch, or Dinner
      arLunch[i] = Integer.parseInt(meals[1]);       
      arDinner[i] = Integer.parseInt(meals[2]);      
    }
        
    int[] totalCalsDay = findTotalCalsDay(arBreakfast, arLunch, arDinner);            //call method to get total cals per day
    double[] avgCalsDay = findAvgCalsDay(arBreakfast, arLunch, arDinner);             //call method to get avg cals per day
    double[] avgCalsMeal = findAvgCalsMeal(arBreakfast, arLunch, arDinner);           //call method to get avg cals per type of meal
    int[] maxCalsDay = findMaxCalsDay(arBreakfast, arLunch, arDinner);                //call method to get max cals from any specific day
    int maxCalsMeal = findMaxCalsMeal(maxCalsDay);                                    //call method to get max cals from any meal
    
    //print Total Calories per day
    System.out.println("Total calories consumed on each day:");
    for(int i = 0; i < totalCalsDay.length; i++){
      System.out.println(dayWeek[i] + ": " + totalCalsDay[i]);
    }
    
    System.out.println(); //blank line
    
    //Print average calories consumed each day
    System.out.println("Average calories from three meals consumed on each day:");
    for(int i = 0; i < avgCalsDay.length; i++){
      System.out.println(dayWeek[i] + ": " + (Math.round(avgCalsDay[i]*100.0)/100.0));
    }
    
    System.out.println(); //blank line
    
    //Print average calories consumed for type of meal
    System.out.println("The average calories consumed for each type of meal: ");
    System.out.println("Breakfast: " + (Math.round(avgCalsMeal[0]*100.0)/100.0));
    System.out.println("Lunch: " + (Math.round(avgCalsMeal[1]*100.0)/100.0));
    System.out.println("Dinner: " + (Math.round(avgCalsMeal[2]*100.0)/100.0));
    
    System.out.println(); //blank line
    
    //print Max Calories consumed in any specific day
    System.out.println("Max Calories consumed in any specific day:");
    for(int i = 0; i < maxCalsDay.length; i++){
      System.out.println(dayWeek[i] + ": " + maxCalsDay[i]);
    }
    
    System.out.println(); //blank line
    
    //print Max Calories consumed from any meal
    System.out.println("Max Calories consumed from any meal: " + maxCalsMeal);
    
    myInFile.close(); //close scanner
   }
   catch(FileNotFoundException error)
       System.out.println("File not found. Try again.");
  }
  
  //method to find Total Calories consumed each day
  public static int[] findTotalCalsDay (int[] b, int[] l, int[] d){
    
    int[] total = new int[7];
    
    for(int i = 0; i < total.length; i++){
      total[i] = b[i] + l[i] + d[i];
    }
    return(total);
  }
  
    //method to find Average Calories consumed from three meals each day
  public static double[] findAvgCalsDay (int[] b, int[] l, int[] d){
    
    double[] avgDay = new double[7];
    
    for(int i = 0; i < avgDay.length; i++){
      avgDay[i] = (b[i] + l[i] + d[i]) / 3.0;
    }
    return(avgDay);
  }
  
  //method to find Average Calories consumed from each type of meal
  public static double[] findAvgCalsMeal (int[] b, int[] l, int[] d){
    
    double[] avgMeal = new double[3];
    
    for(int i = 0; i < 7; i++){
      avgMeal[0] += b[i];
      avgMeal[1] += l[i];
      avgMeal[2] += d[i];
    }
      avgMeal[0] /= 7.0;
      avgMeal[1] /= 7.0;
      avgMeal[2] /= 7.0;
      
    return(avgMeal);
  }
  
    //method to find Max Calories consumed in any specific day
  public static int[] findMaxCalsDay (int[] b, int[] l, int[] d){
    
    int[] maxCals = new int[7];
    
    for(int i = 0; i < maxCals.length; i++){
      maxCals[i] = b[i];
      if(b[i] > l[i] && b[i] > d[i]){
        maxCals[i] = b[i];
      }
      else if(l[i] > b[i] && l[i] > d[i]){
        maxCals[i] = l[i];
      }
      else if(d[i] > b[i] && d[i] > l[i]){
        maxCals[i] = d[i];
      }
    }
    return(maxCals);
  }
  
  //method to find Max Calories consumed in any meal
  public static int findMaxCalsMeal (int[] m){
    
    int maxMeal = 0;
    
    for(int i = 0; i < m.length; i++){
      if(maxMeal < m[i]){
        maxMeal = m[i];
      }

    }
    return(maxMeal);
  }
  
}
