import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Task {

  private LocalDate localDate;
  private String userName;
  private static TreeMap<LocalTime, Service> tasks;


  public Task(LocalDate localDate, String userName, TreeMap<LocalTime, Service> tasks) {
    this.localDate = localDate;
    this.userName = checkTaskName(userName, new Scanner(System.in));
    this.tasks = new TreeMap<>();
  }

  public Task(LocalDate localDate, String userName) {
    this.localDate = localDate;
    this.userName = checkTaskName(userName, new Scanner(System.in));
    this.tasks = new TreeMap<>();
  }

  private String checkTaskName(String userName, Scanner sc) {
    while (userName.isEmpty() || userName == null) {
      System.out.println("Это поле не может быть пустым");
      userName = sc.nextLine();
    }
    return userName;
  }

  public String getUserName() {
    return userName;
  }

  public static TreeMap<LocalTime, Service> getTasks() {
    return tasks;
  }

  public static void printTasks(TreeMap<LocalDate, Task> tasks) {
    System.out.println("Список задач:");
    for (Map.Entry<LocalDate, Task> entry : tasks.entrySet()) {
      System.out.println(entry.getKey() + " - " + entry.getValue());
    }
  }


  public static Task interactiveTask(Scanner scanner) {
    TreeMap<LocalTime, Service> tasks = new TreeMap<>();
    System.out.print("Введите Ваше имя: ");
    String userName = scanner.nextLine();
    System.out.print("Введите предполагаемую дату мероприятия (dd-MM-yyyy): ");
    LocalDate startDate = null;
    boolean isStartDate = false;
    while (!isStartDate) {

      String startDateInput = scanner.nextLine();
      try {
        startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        isStartDate = true;

        if (tasks.containsKey(startDate)) {
          System.out.println("Эта дата занята. Выберите другую дату");
          isStartDate = false;
        }
      } catch (DateTimeParseException e) {
        System.out.println("Не корректный ввод: " + startDateInput);
        System.out.print("Введите дату в формате: dd-MM-yyyy:  ");
      }
    }
    TreeMap<LocalTime, Service> selectedServices = Service.interactiveServices(scanner);
    tasks.putAll(selectedServices);
    Task task = new Task(startDate, userName, tasks);
    task.addService();

    return task;
  }

  private void addService() {
  }

  public static Task parseFromCSVLinen(String s, String delimiter) {
    String[] cells = s.split(delimiter);
    try {
      if (cells.length >= 3) {
        String[] dateParts = cells[2].split("-");
        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        LocalDate taskDate = LocalDate.of(day, month, year);
        String userName = cells[1];
        TreeMap<LocalTime, Service> tasks = new TreeMap<>();

        for (int i = 2; i < cells.length; i++) {
          Service service = Service.parseFromCSVLine(cells[i], "; ");
          tasks.put(service.getStartTime(), service);
        }

        return new Task(taskDate, userName, tasks);
      } else {
        throw new IllegalArgumentException("Некорректное количество ячеек: " + s);
      }
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new IllegalArgumentException("Некорректная строка: " + s);
    }
  }

  public LocalDate getLocalDate() {
    return localDate;
  }

  @Override
  public String toString() {
    return "Task{" +
        "localDateTime =" + localDate +
        ", name ='" + userName + '\'' +
        '}';
  }


  public String getCSVLines(String delimiter) {
    StringBuilder csvLine = new StringBuilder();

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedDate = localDate.format(dateFormatter);

    csvLine.append(formattedDate).append(delimiter).append(userName).append('\n');

    for (Map.Entry<LocalTime, Service> entry : tasks.entrySet()) {
      csvLine.append(entry.getValue().getCSVLine(delimiter));
    }
    return csvLine.toString();
  }
}