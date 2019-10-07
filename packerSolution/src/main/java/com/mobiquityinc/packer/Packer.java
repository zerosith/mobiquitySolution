package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Packer {

  private Packer() {
  }

  public static String pack(String filePath) throws APIException {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(filePath);
    double maxValue;
    String solution="";
    try
    {
      String line;
      BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( is ) );

      while( (line = bufferedReader.readLine()) != null )
      {

        //Extracting weightLimit
        double weightLimit = Double.parseDouble(line.substring(0,line.indexOf(':')).trim());

        if (weightLimit >100){
          throw new APIException("Package max weight is greater than 15");
        }


        String items = line.substring(line.indexOf(':'));
        StringBuilder sb = new StringBuilder(items);
        //Remove colon
        sb.deleteCharAt(0);
        items = sb.toString().trim();

        String[] splitted = Arrays.stream(items.split(" "))
                .map(String::trim)
                .toArray(String[]::new);


        //The data structure used is the array, we need to store the wights and tha values of the objects
        //The following part extracts those values for each item.


        //TO DO: Comment this
        //System.out.println(Arrays.toString(splitted));

        int numberOfItems = splitted.length;
        if (numberOfItems > 15){
          throw new APIException("Total number of items greater than 15");
        }

        double[] weights = new double[numberOfItems];
        double[] values = new double[numberOfItems];

        for(int i=0;i<splitted.length;i++){
          String [] data = splitted[i].split(",");

          if(Double.parseDouble(data[1]) > 100 || Integer.parseInt(removeUnwantedCharacters(data[2])) > 100  ){
            throw new APIException("Weight or cost of item is greater that 100");
          }

          weights[i]= Double.parseDouble(data[1]);
          values[i] = Integer.parseInt(removeUnwantedCharacters(data[2]));
        }

        //This next part is the algorithm solution, now we have all the information wee need to resolve the problem
        double [][] mat = packItems(numberOfItems,(int)weightLimit,weights,values);

        maxValue = mat[numberOfItems][(int)weightLimit];
        //System.out.println("maxValue= "+ maxValue);
        List<Integer>  itemsSolution= findItemIndexes(mat, weights, values, (int)weightLimit, maxValue, numberOfItems);
        if(maxValue == 0){
          solution = solution + "-" + System.lineSeparator();
        }else{
          for (int index : itemsSolution) {
            solution = solution + index+ ",";
          }
          solution = solution.substring(0, solution.length() - 1);
          solution = solution + System.lineSeparator();
        }
      }
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }
    return solution;

  }

  private static List<Integer> findItemIndexes( double[][] mat,double[] weights,double[] values,  int weightLimit, double maxValue, int numberOfItems) {


    int w = weightLimit;
    List<Integer> itemsSolution = new ArrayList<>();

    for (int i = numberOfItems; i > 0  &&  maxValue > 0; i--) {
      if (maxValue != mat[i-1][weightLimit]) {
        itemsSolution.add(i);
        maxValue -= values[i-1];
        weightLimit -= weights[i-1];
      }
    }

    return itemsSolution;
  }


  private static double[][] packItems(int numberOfItems, int weightLimit, double[] weights, double[] values) {
    double[][] mat = new double[numberOfItems + 1][weightLimit + 1];

    for (int i = 1; i <= numberOfItems; i++) {
      for (int j = 0; j <= weightLimit; j++) {
        if (i == 0 || j == 0)
          mat[i][j] = 0;
        else if (weights[i - 1] > j)
          mat[i][j] = mat[i - 1][j];
        else
          mat[i][j] = Math.max(mat[i - 1][j], mat[i - 1][j - (int) weights[i - 1]] + values[i - 1]);
      }
    }

    return mat;
  }

  static String removeUnwantedCharacters(String value){
    StringBuilder sb = new StringBuilder(value);
    sb.deleteCharAt(0);
    sb.deleteCharAt(sb.lastIndexOf(")"));
    return sb.toString().trim();
  }

}
