/**
 * 
 */
package org.kuruganti.github.issues.issues.model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author rajeev
 *
 */
public class Issue {
	private long id;
    private String state;
    private String title;
    private String repository;
    private Date created_at;
    public Issue() {
    	
    }
    public Issue(long id, Date created_at,String state,String title, String repository) {
    	this.id = id;
    	this.created_at = created_at;
    	this.state= state;
    	this.title = title;
    	this.repository = repository;
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
    
    public String toString() {
    	return "id: " + this.id
    		  +"\n state: " +this.state
    		  +"\n title: " + this.title
    		  +"\n repository: " + this.repository
    		  +"\n created_at " + this .created_at; 
    }
}
