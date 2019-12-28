package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	
	/**
	 * List of symptoms 
	 * @see com.hemebiotech.analytics#ISymptomReader.getSymptoms(String filepath);
	 */
	
	@Override
	public List<String> getSymptoms(String filepath) {
		ArrayList<String> result = new ArrayList<>();
		
		if (filepath != null) {
			try(BufferedReader reader = new BufferedReader (new FileReader(filepath))) {				
				String line = reader.readLine();				
				while (line != null) {
					// lowercase for easy sorting
					result.add(line.toLowerCase());
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
		
    
     /**
     * write the sorted symptoms in the result.out file      
     * @see com.hemebiotech.analytics#ISymptomReader.writeFile(String fileOut, Map<String, Integer> groupedSymptoms)
     * @param groupedSymptoms 
     */
   
	@Override
	public void writeFile(String fileOut, HashMap<String, Integer> groupedSymptoms) {
		try(FileWriter writer = new FileWriter(fileOut)) {            
            for (Entry<String, Integer> key : groupedSymptoms.entrySet()) {
                writer.write(key.getKey() + " = " + groupedSymptoms.get(key.getKey()) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
