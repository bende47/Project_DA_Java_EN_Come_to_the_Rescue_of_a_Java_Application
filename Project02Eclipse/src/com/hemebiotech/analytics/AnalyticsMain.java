package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class AnalyticsMain {

	public static void main(String args[]) {

		ISymptomReader rsdff = new ReadSymptomDataFromFile();
		List<String> symptoms = rsdff.getSymptoms("./Project02Eclipse/symptoms.txt");

		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		Map<String, Integer> groupedSymptoms = analyticsCounter.groupSymptoms(symptoms);

		groupedSymptoms = analyticsCounter.sortSymptoms(groupedSymptoms);

		rsdff.writeFile("./Project02Eclipse/result.out", groupedSymptoms);

	}

}
