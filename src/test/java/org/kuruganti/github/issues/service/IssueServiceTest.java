package org.kuruganti.github.issues.service;




import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.kuruganti.github.issues.issues.service.IssueService;

public class IssueServiceTest {
    IssueService iService = new IssueService();
    
	@Test
	public void testGetGitIssues() {
		iService.getGitIssues("rajeevkuruganti:AndroidProjects");
		
		fail("Not yet implemented");
	}
	@Test
	public void testConnectionAndResponse() {
		Response response =	iService.connectAndGetResponse("rajeevkuruganti:2018-Projects");
		assertTrue(200==response.getStatus());
//		fail("Not yet implemented");
	}
	@Test
	public void testConnectionNoUserExists() {
	Response response = iService.connectAndGetResponse("rajeevk:AndroidProjects");
		assertTrue(404==response.getStatus());
	//ail("Not yet implemented");
	}
}
