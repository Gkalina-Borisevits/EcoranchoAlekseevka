import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task {

  private LocalDate localDate;
  private String userName;
  private static TreeMap<LocalTime, Service> tasks;
  //private ArrayList<String> services;

  public Task(LocalDate localDate, String userName, TreeMap<LocalTime, Service> tasks) {
    this.localDate = localDate;
    this.userName = checkTaskName(userName);
    this.tasks = new TreeMap<>();
  }

  private String checkTaskName(String userName) {
    while (userName.isEmpty()) {
      System.out.println("Это поле не может быть пустым");
    }
    return checkTaskName(userName);
  }

  public String getUserName() {
    return userName;
  }

  public static TreeMap<LocalTime, Service> getTasks() {
    return tasks;
  }

  public static Task interactiveTask(Scanner scanner) {
    System.out.print("Введите Ваше имя: ");
    String userName = scanner.nextLine();
    System.out.print("Введите предполагаемую дату мероприятия (dd-MM-yyyy): ");
    LocalDate startDate = null;
    boolean isStartDate = false;
    while (!isStartDate) {
      System.out.print("Введите запланированную дату мероприятия  в формате: (dd-MM-yyyy): ");
      String startDateInput = scanner.nextLine();
      try {
        startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        isStartDate = true;
      } catch (DateTimeParseException e) {
        System.out.println("Не корректный ввод: " + startDateInput);
        System.out.print("Введите время в формате: HH:MM:  ");
      }
    }
    Service service = Service.interactiveService(scanner);
    Task task = new Task(startDate, userName, tasks);
    task.addService(service.getStartTime(), service);

    return task;
  }


  public static Task parseFromCSVLinen(String s, String delimiter) {
    String[] cells = s.split(delimiter);
    try {
      LocalDate localDate = LocalDate.parse(cells[0]);
      String userName = cells[1];
      Map<LocalTime, Service> tasks = new TreeMap<>();
      for (int i = 2; i < cells.length; i++) {
        Service service = Service.parseFromCSVLine(cells[i], " : ");
        tasks.put(service.getStartTime(), service);
      }
      return new Task(localDate, userName, (TreeMap<LocalTime, Service>) tasks);
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new IllegalArgumentException("NO CORRECT " + s);
    }
  }


  public LocalDate getLocalDate() {
    return localDate;
  }

  public void setLocalDateTime(LocalDate localDate) {
    this.localDate = localDate;
  }


  @Override
  public String toString() {
    return "Task{" +
        "localDateTime =" + localDate +
        ", name ='" + userName + '\'' +
        '}';
  }

  public void addService(LocalTime startTime, Service service) {
    tasks.put(startTime, service);
  }

  public String getCSVLine(String delimiter) {
    StringBuilder csvLine = new StringBuilder();

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = localDate.format(dateFormatter);

    csvLine.append(formattedDate).append(delimiter).append(userName);

    for (Map.Entry<LocalTime, Service> entry : tasks.entrySet()) {
      DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
      String formattedTime = entry.getKey().format(timeFormatter);
      csvLine.append(formattedTime).append(" : ").append(entry.getValue().getCSVLine(delimiter));
    }
    return csvLine.toString();
  }
}