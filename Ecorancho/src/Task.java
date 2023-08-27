import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task {

  private LocalDateTime localDateTime;
  private String task;
  private Map<LocalDateTime, Service> tasks;
  private ArrayList<String> services;

  public Task(LocalDateTime localDateTime, String task) {
    this.localDateTime = localDateTime;
    this.task = task;
    this.tasks = new TreeMap<>();
    this.services = new ArrayList<String>();
  }

  public Task(LocalDateTime localDateTime, String task, ArrayList<Service> services) {
    this.localDateTime = localDateTime;
    this.task = task;
    this.services = new ArrayList<String>();
  }

  public Task(LocalDateTime localDateTime, String task, Map<LocalDateTime, String> tasks) {
    this.localDateTime = localDateTime;
    this.task = task;
    // this.tasks = tasks;
  }

  public static Task parseFromCSVLine(String s, String delimiter) {
    String[] cells = s.split(delimiter);
    try {

      LocalDateTime localDateTime = LocalDateTime.parse(cells[0]);
      String task = cells[1];
      Map<LocalDateTime, String> tasks = new TreeMap<>();
      for (int i = 2; i < cells.length; i++) {
        String[] taskEntry = cells[i].split(" : ");
        LocalDateTime entryDateTime = LocalDateTime.parse(taskEntry[0]);
        String entryTask = taskEntry[1];
        tasks.put(entryDateTime, entryTask);
      }
      return new Task(localDateTime, task, tasks);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new IllegalArgumentException("NO CORRECT " + s);
    }
  }

  public String getTask() {
    return task;
  }

  public ArrayList<String> getServices() {
    return services;
  }

  public void setServices(ArrayList<String> services) {
    this.services = services;
  }

  public LocalDateTime getLocalDateTime() {
    return localDateTime;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public void setTask(String task) {
    this.task = task;
  }

  @Override
  public String toString() {
    return "Task{" +
        "localDateTime=" + localDateTime +
        ", task='" + task + '\'' +
        '}';
  }

  public String getCSVLine(String delimiter) {
    StringBuilder csvLine = new StringBuilder();
    csvLine.append(localDateTime).append(delimiter).append(task);

    for (Map.Entry<LocalDateTime, Service> entry : tasks.entrySet()) {
      csvLine.append(entry.getKey()).append(" : ").append(entry.getValue());
    }
    return csvLine.toString();
  }

  public void interactiveAddService(Scanner scanner) {
    System.out.println("Введите дату и время для услуги (dd-MM-yyyy HH:mm): ");
    String dateTimeInput = scanner.nextLine();
    LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

    System.out.println("Введите название услуги: ");
    String serviceName = scanner.nextLine();

    System.out.println("Введите количество гостей: ");
    int guest = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Введите базовую стоимость услуги: ");
    double baseCost = scanner.nextDouble();
    scanner.nextLine();

    System.out.println("Введите стоимость услуги для гостя: ");
    double guestCost = scanner.nextDouble();
    scanner.nextLine();

    Service service = new Service(serviceName, guest, baseCost, guestCost);
    tasks.put(dateTime, service);
  }
}
