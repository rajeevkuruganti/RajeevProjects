package org.kuruganti.github.issues.issues;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.kuruganti.github.issues.issues.helper.IssueHelper;
import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.github.issues.issues.service.IssueService;

import com.google.gson.JsonObject;

@Path("/gitissues")
public class IssueResource {
	private IssueService service = new IssueService();
	List<Issue> issues = new ArrayList<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> getIssuesForAllusers(@QueryParam("userRepos") List<String> userRepos) throws ParseException {		
		for (int k = 0; k < userRepos.size(); k++) {
			List<Issue> issuesRepos=service.getGitIssues(userRepos.get(k));
			if (issuesRepos != null) {
				issues.addAll( issuesRepos);
			}
		}
		JsonObject x = IssueHelper.getTopDay(issues);

		return issues;
		
	}

}
