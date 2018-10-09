# RajeevProjects
This code is webapp to get issues (RESTful call) to GitHub for user/repository . 
Input:  
A List of 1 to n Strings with Github repositories references with the format "owner/repository"

Go to POSTMAN plugin or SOAPUI andfor the url tag on 
http://%domain.name%:%portNumber%/issues/webapi/gitissues?userRepos=username1/repository1&userRepos=username2/repositoy2 &userRepos=userName2/repository3 

e.g. http://localhost:8282/issues/webapi/gitissues?userRepos="rkuruganti/projects"&userRepos="iMontoya/revengeProjects"&userRepos="GentleGiant/Personal"
