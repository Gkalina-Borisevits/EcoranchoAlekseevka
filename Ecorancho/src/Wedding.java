import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Wedding extends Event {

  public Wedding(LocalDateTime localDateTime, String nameCustomer, double cost) {
    super(localDateTime, nameCustomer, cost);
  }

  public Wedding() {
    super(MenuCommand.WEDDING);
  }

  public static Wedding interactiveRead(Scanner scanner) throws IOException {

    TasksFile tasksFile = new TasksFile("res/task.csv", "; ");
    TreeMap<LocalDate, Task> tasks = (TreeMap<LocalDate, Task>) tasksFile.readTasks();
    Task.printTasks(tasks);
    Task newTask = Task.interactiveTask(scanner);
    tasks.put(newTask.getLocalDate(), newTask);

    tasksFile.writeTasks(tasks);
    return interactiveRead(scanner);
  }
}
