package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsCounter {
	

	
	public static void main(String args[]) throws Exception {
		
		 // reading the symptoms file
		
        ReadSymptomDataFromFile rsdff = new ReadSymptomDataFromFile("./Project02Eclipse/symptoms.txt","./Project02Eclipse/result.out");
        List<String> symptoms = rsdff.GetSymptoms();

        // count all occurrences of any symptoms listed in the file
        
        Map<String, Integer> groupedSymptoms = rsdff.groupSymptoms(symptoms);

        // Sort the symptoms in alphabetical order
        
        groupedSymptoms = rsdff.sortSymptoms(groupedSymptoms);

        // Generating a new text file named results.out that lists each symptom in alphabetical order.        
       
        rsdff.writeFile(groupedSymptoms);
		
	}
	
	
	
}
