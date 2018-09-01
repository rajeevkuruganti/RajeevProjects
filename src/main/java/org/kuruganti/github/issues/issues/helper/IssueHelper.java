package org.kuruganti.github.issues.issues.helper;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.util.DateUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class IssueHelper {

	public static JsonObject getTopDay(List<Issue> listIssues) throws ParseException {
		int sizeOfList = listIssues.size();
		Map<LocalDate, Integer> topDay = new HashMap<>();
		for (int i = 0; i < sizeOfList; i++) {
			LocalDate topDayDate = DateUtils.getLocalDate(listIssues.get(i).getCreated_at().toString());
			if (!topDay.containsKey(topDayDate)) {
				topDay.put(topDayDate, 1);
			} else {
				int total = topDay.get(topDayDate) + 1;
				topDay.put(topDayDate, total);
			}
		}
		LocalDate maxDate = getMaxDateForRepos(topDay); 
		JsonObject repoCounts = getRepoCountForDate(listIssues, maxDate);
		JsonObject jsonObjectAnswer = bundleJsonObject(listIssues,repoCounts);
		
		return jsonObjectAnswer;

	}

	private static JsonObject bundleJsonObject(List<Issue> listIssues, JsonObject repoCounts) {
		Gson gson = new Gson();

		JsonObject finalObj = new JsonObject();
		JsonElement elem = gson.toJsonTree(listIssues);		
		finalObj.add("issues", elem);
		finalObj.add("top_Day", repoCounts);
		return finalObj;
	}

	public static JsonObject getRepoCountForDate(List<Issue> listIssues, LocalDate topDayDate) {
		Map<String, Integer> topRepos = new HashMap<>();
		int sizeOfList = listIssues.size();
		for (int i = 0; i < sizeOfList; i++) {
			LocalDate theDate = DateUtils.getLocalDate(listIssues.get(i).getCreated_at().toString());
			String repo = listIssues.get(i).getRepository().trim();		
			if (topDayDate.equals(theDate)) {
				if (topRepos.containsKey(repo)) {
					int total = topRepos.get(repo) + 1;
					topRepos.put(repo, total);
				} else {
					topRepos.put(repo, 1);
				}
			} else {
				topRepos.put(repo, 0);
			}
		}
		Map<String, Integer> sortedRepos = new TreeMap<>();
		sortedRepos.putAll(topRepos);
		Gson gson = new Gson();
		JsonObject topJsonObj = new JsonObject();
	    JsonElement element = gson.toJsonTree(sortedRepos);
		topJsonObj.addProperty("day", topDayDate.toString());
		topJsonObj.add("occurrences", element);
		return topJsonObj;
	}
  public static LocalDate getMaxDateForRepos(Map topDay) throws ParseException{
		Map.Entry<LocalDate,Integer> valueMap = (Entry<LocalDate, Integer>) topDay
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue(Integer::compareTo))
				.get();
		 Integer maxOccurs = valueMap.getValue();
		 Map<LocalDate, Integer> maxOccursMap = new LinkedHashMap<LocalDate,Integer>();
		 topDay.forEach((k, v) -> {
			    if(v.equals(maxOccurs)) {
			      maxOccursMap.put((LocalDate) k,(Integer) v);
			    }
	            
	        });
		 Map.Entry<LocalDate,Integer> maxDateOccurs= maxOccursMap.entrySet().stream()
				 .max(Map.Entry.comparingByKey(LocalDate::compareTo)).get();
		 
		return maxDateOccurs.getKey();
	
  }

}
