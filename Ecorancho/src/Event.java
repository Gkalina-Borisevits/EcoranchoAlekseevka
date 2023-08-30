import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

abstract class Event {

  protected static LocalDateTime localDateTime;
  protected static String nameCustomer;
  protected double cost;
  protected MenuCommand menuCommand;
  protected TreeMap<LocalDate, Task> tasks;
  protected TreeMap<Task, Service> service;


  public Event(LocalDateTime localDateTime, String nameCustomer, double cost) {
    this.localDateTime = localDateTime;
    this.nameCustomer = nameCustomer;
    this.cost = cost;
    this.tasks = new TreeMap<>();
    this.service = new TreeMap<>();
  }
  public Event(MenuCommand menuCommand) {
    this.menuCommand = menuCommand;
  }


  public MenuCommand getMenuCommand() {
    return menuCommand;
  }

  public TreeMap<Task, Service> getService() {
    return service;
  }

  public TreeMap<LocalDate, Task> getTasks() {
    return tasks;
  }

  public String getNameCustomer() {
    return nameCustomer;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public void setNameCustomer(String nameCustomer) {
    this.nameCustomer = nameCustomer;
  }



  public void setTasks(TreeMap<LocalDate, Task> tasks) {
    this.tasks = tasks;
  }
}
