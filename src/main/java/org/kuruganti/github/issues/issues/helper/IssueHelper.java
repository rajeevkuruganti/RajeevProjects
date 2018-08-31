package org.kuruganti.github.issues.issues.helper;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.util.DateUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
		Map<LocalDate, Integer> sortedDates = new TreeMap<>();
		sortedDates.putAll(topDay);
		Map.Entry<LocalDate, Integer> maxDate = sortedDates.entrySet().stream()
				.max(Map.Entry.comparingByKey(LocalDate::compareTo)).get();
		JsonObject y = getRepoCountForDate(listIssues, maxDate.getKey());
		Gson gson = new Gson();

		JsonObject finalObj = new JsonObject();
		JsonArray issueJson = new JsonArray();
		issueJson.add(gson.toJson(listIssues));

		finalObj.add("issues", issueJson);
		finalObj.add("top_Day", y);

		return finalObj;

	}

	public static JsonObject getRepoCountForDate(List<Issue> listIssues, LocalDate topDayDate) {
		Map<String, Integer> topRepos = new HashMap<>();
		int sizeOfList = listIssues.size();
		for (int i = 0; i < sizeOfList; i++) {
			LocalDate theDate = DateUtils.getLocalDate(listIssues.get(i).getCreated_at().toString());
			String repo = listIssues.get(i).getRepository().trim();
			topRepos.put(repo, 0);
			if (topDayDate.equals(theDate)) {
				if (topRepos.containsKey(repo)) {
					int total = topRepos.get(repo) + 1;
					topRepos.put(listIssues.get(i).getRepository(), total);
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
		JsonArray jsonArray = new JsonArray();
		jsonArray.add(gson.toJson(sortedRepos));
		topJsonObj.addProperty("day", topDayDate.toString());
		topJsonObj.add("occurrences", jsonArray);
		return topJsonObj;
	}

}
