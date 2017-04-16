package com.example.task;

import java.util.concurrent.atomic.AtomicLong;

public class Task {

	private long id;
	private String subject;
	private String detail;
	private String status;
	
	public Task(){
		Task task = new Task.TaskBuilder().id().build();
		this.id = task.getId();
		this.subject = task.getSubject();
		this.detail = task.getDetail();
		this.status = task.getStatus();
	}
	  
	public Task(String subject, String detail,String status){
		Task task = new Task.TaskBuilder().id()
		   .subject(subject)
		   .detail(detail)
		   .status(status)
		   .build();
		this.id = task.getId();
		this.subject = task.getSubject();
		this.detail = task.getDetail();
		this.status = task.getStatus();
	}
	
	private Task(TaskBuilder builder){
		this.id = builder.id;
		this.subject = builder.subject;
		this.detail = builder.detail;
		this.status = builder.status;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return this.id;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return this.subject;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDetail() {
		return this.detail;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return this.status;
	}
	
	@Override
	public String toString(){
		return "ID : " + id + "\n"
			+ "Subject : " + subject + "\n"
			+ "Detail : " + detail + "\n"
			+ "Status : " + status;
	} 

	public static class TaskBuilder{
		private long id;
		private String subject = "";
		private String detail = "";
		private String status = "";
		private static AtomicLong counter = new AtomicLong(1);
		
		public TaskBuilder id(){
		  this.id = this.counter.getAndIncrement();
		  return this;
		}

		public TaskBuilder subject(String subject){
		  this.subject = subject;
		  return this;
		}

		public TaskBuilder detail(String detail){
		  this.detail = detail;
		  return this;
		}
		
		public TaskBuilder status(String status){
		  this.status = status;
		  return this;
		}
		
		public Task build(){
		  return new Task(this);
		}
    
  }    
}