import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

abstract class Event {

  protected static LocalDateTime localDateTime;
  protected static String nameCustomer;
  protected double cost;
  protected Map<LocalDateTime, String> tasks;
  protected TreeMap<Task, Service> serviceMap;

  public Event(LocalDateTime localDateTime, String nameCustomer, double cost) {
    this.localDateTime = localDateTime;
    this.nameCustomer = nameCustomer;
    this.cost = cost;
    this.tasks = new TreeMap<>();
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public LocalDateTime getLocalDate() {
    return localDateTime;
  }

  public Map<LocalDateTime, String> getTasks() {
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

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public void setTasks(Map<LocalDateTime, String> tasks) {
    this.tasks = tasks;
  }
}
