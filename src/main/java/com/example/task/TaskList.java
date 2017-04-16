package com.example.task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
  private static final List<Task> tList = new ArrayList<Task>();

  static {
    // Create list of task
    tList.add(
        new Task.TaskBuilder().id()
        .subject("Test Subject")
        .detail("sleep")
        .status("Done")
        .build()
    );
	
	tList.add(
        new Task.TaskBuilder().id()
        .subject("Test 2")
        .detail("Walk")
        .status("Not")
        .build()
    );
  }
  
  private TaskList(){}
  
  public static List<Task> getTaskList(){
    return tList;
  }
  
  public static void testList(){
    for (Task task : tList) {
		System.out.println(task.toString()+"\n");
	}
  }
    
  public static void main(String[] args) {
	TaskList.testList();
  }
  
}