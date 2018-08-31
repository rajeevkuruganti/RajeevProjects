package org.kuruganti.github.issues.issues.helper;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;
import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.util.DateUtils;

import com.google.gson.JsonObject;

public class IssueHelperTest {

	@Test
	public void testGetTopDay() throws ParseException  {
		List<Issue> listIssues = new ArrayList<>();
		
		Issue issue1 = new Issue(955154,convertDateForData("2012/12/28"),"closed","Update Modules/user/login_block.php\\","emoncms/emoncms");
		Issue issue2 = new Issue(95515347,convertDateForData("2012/12/28"),"closed","Cannot create a new user","emoncms/emoncms");
		Issue issue22 = new Issue(95515347,convertDateForData("2012/12/12"),"open","is it working. test please","emoncms/emoncms");
		Issue issue33 =  new Issue(9557,convertDateForData("2012/12/12"),"closed","add Logging","rkuruganti/Myprojects");
		Issue issue3 =  new Issue(9557,convertDateForData("2012/12/12"),"closed","Degree sign (UTF-8) for widget unit","rkuruganti/projects");
		Issue issue4 =  new Issue(9557,convertDateForData("2012/12/28"),"open","Unable to add anything to dashboard using Chrome on Mac","rkuruganti/projects");
		Issue issue5 =  new Issue(93557,convertDateForData("2012/12/23"),"open","checking how hashmaps","rkuruganti/projects");
		listIssues.add(issue1);
		listIssues.add(issue2);
		listIssues.add(issue22);
		listIssues.add(issue3);
		listIssues.add(issue33);
		listIssues.add(issue4);
		listIssues.add(issue5);
		
       JsonObject x =  IssueHelper.getTopDay(listIssues);
	   assertTrue(x != null); 
	}
	@Test
	public void testGetRepCount() throws ParseException {
List<Issue> listIssues = new ArrayList<>();
		
		Issue issue1 = new Issue(955154,convertDateForData("2012/12/28"),"closed","Update Modules/user/login_block.php\\","emoncms/emoncms");
		Issue issue2 = new Issue(95515347,convertDateForData("2012/12/28"),"closed","Cannot create a new user","emoncms/emoncms");
		Issue issue22 = new Issue(95515347,convertDateForData("2012/12/12"),"open","is it working. test please","emoncms/emoncms");
		Issue issue33 =  new Issue(9557,convertDateForData("2012/12/12"),"closed","add Logging","rkuruganti/Myprojects");
		Issue issue3 =  new Issue(9557,convertDateForData("2012/12/12"),"closed","Degree sign (UTF-8) for widget unit","rkuruganti/projects");
		Issue issue4 =  new Issue(9557,convertDateForData("2012/12/28"),"open","Unable to add anything to dashboard using Chrome on Mac","rkuruganti/projects");
		Issue issue5 =  new Issue(93557,convertDateForData("2012/12/23"),"open","checking how hashmaps","rkuruganti/projects");
		listIssues.add(issue1);
		listIssues.add(issue2);
		listIssues.add(issue22);
		listIssues.add(issue3);
		listIssues.add(issue33);
		listIssues.add(issue4);
		listIssues.add(issue5);
		IssueHelper.getRepoCountForDate(listIssues, DateUtils.getLocalDate("Fri Dec 28 21:24:10 EDT 2012"));
	
	}
	
	
	private static Date convertDateForData(String s) throws ParseException {
		Date localDateTime = new SimpleDateFormat("yyyy/MM/dd").parse(s);
		return localDateTime;
}

}
