package org.kuruganti.github.issues.issues.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kuruganti.github.issues.issues.helper.IssueHelper;
import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.util.DateUtils;

public class IssueService {
	private final String gitURL = "https://api.github.com/repos";

	private final String parameters = "/issues";
	private Logger logger = Logger.getLogger(IssueService.class.getName());

	public List<Issue> getGitIssues(String userRepos) throws ParseException {
		Response response = connectAndGetResponse(userRepos);
		if (response.getStatus()==404) {
			logger.info(userRepos + " not found!");
			return null;
		}
		Issue[] issues = response.readEntity(Issue[].class);
		List<Issue> listIssues = new ArrayList<>();
		for (int j = 0; j < issues.length; j++) {
			// logger.info (issues[j].toString());
			issues[j].setRepository(userRepos);
			listIssues.add(issues[j]);
		}
		return listIssues;
	}

	public Response connectAndGetResponse(String userRepos) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(gitURL).path(userRepos + parameters).queryParam("state", "all")
				.queryParam("direction", "asc");
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON_TYPE);
		Response response = invocationBuilder.get();
		return response;
	}

}
