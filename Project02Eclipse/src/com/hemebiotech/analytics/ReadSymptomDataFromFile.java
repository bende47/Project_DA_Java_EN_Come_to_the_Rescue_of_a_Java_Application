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

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filepath;
	private String fileOut;
	
	/**
	 * 
	 * @param filepath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filepath,String fileOut) {
		this.filepath = filepath;
		this.fileOut= fileOut;
	}
	
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<String>();
		
		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filepath));
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
     * Read alphabeticSymptomList and create a Map with name and number
     *
     * @param alphabeticSymptomList
     * @return Map of symptom and its number
     */
    public Map<String, Integer> groupSymptoms(List<String> alphabeticSymptomList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String symptom : alphabeticSymptomList) {
            Integer nombre = map.get(symptom);
            if (nombre == null) {
                nombre = 0;
            }
            nombre++;
            map.put(symptom, nombre);
        }
        return map;
    }

    /**
     * Sort the map of grouped symptoms
     *
     * @param groupedSymptoms
     * @return
     */
    public Map<String, Integer> sortSymptoms(Map<String, Integer> groupedSymptoms) {        
        
        Map<String, Integer> result = new LinkedHashMap<>();
        groupedSymptoms.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));     
        return result;
        
    }
    
     /**
     * write the sorted symptoms in the result.out file 
     * 
     * @param groupedSymptoms 
     */

    public void writeFile(Map<String, Integer> groupedSymptoms) {
        try {
            FileWriter writer = new FileWriter(fileOut);
            for (String key : groupedSymptoms.keySet()) {
                writer.write(key + " = " + groupedSymptoms.get(key) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
