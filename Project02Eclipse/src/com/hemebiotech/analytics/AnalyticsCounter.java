package com.hemebiotech.analytics;


import java.util.List;
import java.util.Map;

public class AnalyticsCounter {
	

	
	public static void main(String args[]) throws Exception {
		
		
        ReadSymptomDataFromFile rsdff = new ReadSymptomDataFromFile("./Project02Eclipse/symptoms.txt","./Project02Eclipse/result.out");
        List<String> symptoms = rsdff.GetSymptoms();

        
        Map<String, Integer> groupedSymptoms = rsdff.groupSymptoms(symptoms);

        
        groupedSymptoms = rsdff.sortSymptoms(groupedSymptoms);

       
        rsdff.writeFile(groupedSymptoms);
		
	}
	
	
	
}
