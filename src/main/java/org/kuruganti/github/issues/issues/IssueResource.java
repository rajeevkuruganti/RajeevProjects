package org.kuruganti.github.issues.issues;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.kuruganti.github.issues.issues.model.Issue;
import org.kuruganti.github.issues.issues.service.IssueService;

@Path("/gitissues")
public class IssueResource {
	private IssueService service = new IssueService();
	List<Issue> issues = new ArrayList<>();
	@GET
	@Path("/{list}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> getIssues(@PathParam("list") String userRepos) {
		List<Issue> issues = service.getGitIssues(userRepos);
		return issues;

	}

	@GET
	@Path("list/{userRepos}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Issue> getIssuesForAllusers(@PathParam("userRepos") String userRepos) {
	
		String[] userRepository = userRepos.split(",");
		for (int k = 0; k < userRepository.length; k++) {
			issues.addAll( service.getGitIssues(userRepository[k]));
		}

		return issues;
		// Here you need to use String tokenizer to make the array from the string.
	}

}
