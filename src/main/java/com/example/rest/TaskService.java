package com.example.rest;

import java.util.List;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import com.example.task.*;

@Path("/tasks")
public class TaskService {

  private final List<Task> tList = TaskList.getTaskList();

  @GET
  @Path("/all")
  @Produces(MediaType.TEXT_PLAIN)
  public String getAllTasks() {
	String result = "---Task List---\n";
    for (Task task : tList) {
		result += task.toString()+"\n";
	}
	
	return result;
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public String getTask(@PathParam("id") long id) {
    String result = null;
	
    for (Task task : tList) {
		if (task.getId() == id) {
			result = task.toString();
		}
	}
	
    if (result != null) {
      return "--- Task ---\n" + result;
    } else {
      return "Task not found";
    }
  }
  
  @POST
  @Path("add")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String addTask(@FormParam("subject") String subject,@FormParam("detail") String detail,@FormParam("status") String status) {	
	tList.add(
        new Task.TaskBuilder().id()
        .subject(subject)
        .detail(detail)
        .status(status)
        .build()
	);
	return "Add task [subject = "+subject+", detail = "+detail+", status = "+status + "] completed";
  }
  
  @PUT
  @Path("edit")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String editTask(@FormParam("id") long id,
	@FormParam("subject") String subject,
	@FormParam("detail") String detail,
	@FormParam("status") String status) {	
		
		
    return updateTask(id,subject,detail,status);
	
  }
  
  @PUT
  @Path("updatestatus")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public String updateStatusTask(@FormParam("id") long id,
	@FormParam("status") String status) {		
    return updateTask(id,null,null,status);
  }
  
  @DELETE
  @Path("delete/{id}")
  @Produces(MediaType.TEXT_PLAIN)
  public String deleteTask(@PathParam("id") long id) {
    String result = null;
	
    for (Task task : tList) {
		if (task.getId() == id) {
			tList.remove(task);
			result = "Id : "+id+" deleted";
			break;
		}
	}
	
    if (result != null) {
      return "--- Task ---\n" + result;
    } else {
      return "Task not found";
    }
  }
  
  private String updateTask(long id,String subject,String detail,String status) {
	String result = null;	
    for (Task task : tList) {
		if (task.getId() == id) {
			if (subject != null) task.setSubject(subject);
			if (detail != null) task.setDetail(detail);
			if (status != null) task.setStatus(status);
			result = "Id : "+id+" updated";
			break;
		}
	}
	
    if (result != null) {
      return "--- Task ---\n" + result;
    } else {
      return "Task not found";
    }
  }
}