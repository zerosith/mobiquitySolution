package com.mobiquityinc;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;

public class App 
{
    public static void main( String[] args ) throws APIException {
        String filePath = "input.txt";
        System.out.println(Packer.pack(filePath));
    }
}
