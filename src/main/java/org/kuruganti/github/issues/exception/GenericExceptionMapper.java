package org.kuruganti.github.issues.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kuruganti.github.issues.issues.model.ErrorMessage;



@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
		ErrorMessage errMsg = new ErrorMessage(500,ex.getMessage(),"http://something");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errMsg)
				.build();
	}
}
