package org.kuruganti.github.issues.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kuruganti.github.issues.issues.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exp) {
		ErrorMessage errorMessage= new ErrorMessage (404,exp.getMessage(), "I am throwing this message");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}
	

}
