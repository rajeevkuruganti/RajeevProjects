package org.kuruganti.github.issues.service;




import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.github.issues.issues.service.IssueService;

public class IssueServiceTest {
    IssueService iService = new IssueService();
    
	@Test
	public void testGetGitIssues() throws ParseException {
	 List<Issue> issues =	iService.getGitIssues("rajeevkuruganti/AndroidProjects");
		assertTrue(4==issues.size());
	}
	@Test
	public void testConnectionAndResponse() {
		Response response =	iService.connectAndGetResponse("rajeevkuruganti/AndroidProjects");
		assertTrue(200==response.getStatus());
	}
	@Test
	public void testConnectionNoUserExists() {
	Response response = iService.connectAndGetResponse("rajeevk/AndroidProjects");
		assertTrue(404==response.getStatus());
	}
}
