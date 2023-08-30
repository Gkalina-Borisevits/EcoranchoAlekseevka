import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Service {

  private String serviceName;
  private static int guest;
  private LocalTime startTime;
  private static double price;
  private static double baseCost;
  private static double guestCost;

  public Service(LocalTime startTime, String serviceName, int guest, double baseCost,
      double price) {
    this.startTime = startTime;
    this.serviceName = checkServiceName(serviceName);
    this.guest = checkGuest(guest);
    this.baseCost = baseCost;
    this.guestCost = guestCost;
    this.price = calculateCost();
  }

  public Service(String serviceName, double price) {
    this.serviceName = checkServiceName(serviceName);
    this.guest = checkGuest(guest);
    this.price = calculateCost();
  }

  private int checkGuest(int guest) {
    if (guest < 0) {
      throw new IllegalArgumentException("Количество гостей не может быть отрицательным");
    }
    return guest;
  }

  private String checkServiceName(String serviceName) {
    while (serviceName.isEmpty()) {
      System.out.println("Название услуги не может быть пустой");
    }
    return serviceName;
  }


  public String getServiceName() {
    return serviceName;
  }

  public int getGuest() {
    return guest;
  }

  public double getBaseCost() {
    return baseCost;
  }

  public double getPrice() {
    return calculateCost();
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setGuest(int guest) {
    this.guest = guest;
    calculateCost();
  }

  public void setGuestCost(double guestCost) {
    this.guestCost = guestCost;
    calculateCost();
  }

  public void setBaseCost(double baseCost) {
    this.baseCost = baseCost;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public static double calculateCost() {
    return baseCost + (guestCost * guest);
  }


  public static Service interactiveService(Scanner scanner) {
    System.out.println("Введите название услуги:");
    String nameServiceInteractive = scanner.nextLine();
    System.out.print("Введите запланированное время начала услуги в формате: HH:MM: ");
    LocalTime startTime = LocalTime.now();
    boolean startTimeRead = false;
    while (!startTimeRead) {
      String startTimeInput = scanner.nextLine();
      try {
        startTime = LocalTime.parse(startTimeInput);
        startTimeRead = true;
      } catch (DateTimeParseException e) {
        System.out.println("Не корректный ввод: " + startTimeInput);
        System.out.print("Введите время в формате: HH:MM:  ");
      }
    }
    System.out.println("Введите предполагаемое количество гостей:");
    while (!scanner.hasNextInt()) {
      System.out.println("Не корректный ввод: " + scanner.nextLine());
      System.out.print("Введите целое число: ");
    }
    int guestNumber = scanner.nextInt();
    scanner.nextLine();
    return new Service(startTime, nameServiceInteractive, guestNumber, guestCost, price);
  }

  public static TreeMap<LocalTime, Service> interactiveServices(Scanner scanner) {
    TreeMap<LocalTime, Service> selectedServices = new TreeMap<>();

    while (true) {
      Service service = interactiveService(scanner);
      selectedServices.put(service.getStartTime(), service);

      System.out.println("Услуга успешно добавлена.");
      System.out.print("Введите 0 для завершения выбора услуг или любую цифру для продолжения:");
      int choice = scanner.nextInt();
      scanner.nextLine();
      while (!scanner.hasNextInt()) {
        System.out.println("Не корректный ввод: " + scanner.nextLine());
        System.out.print("Введите целое число: ");
      }
      if (choice == 0) {
        scanner.nextLine();
        break;
      }
    }
    return selectedServices;
  }

  public static Service parseFromCSVLine(String s, String delimiter) {
    String[] cells = s.split(delimiter);
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
      LocalTime startTime = LocalTime.parse(cells[0], formatter);
      return new Service(startTime, cells[1], Integer.parseInt(cells[2]),
          Double.parseDouble(cells[3]),
          Double.parseDouble(cells[4]));
    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
      throw new IllegalArgumentException("Некорректная строка: " + s);
    }
  }


  @Override
  public String toString() {
    return "Service{" +
        "serviceName='" + serviceName + '\'' +
        ", baseCost=" + baseCost +
        ", guest=" + guest +
        ", guestCost=" + guestCost +
        '}';
  }

  public String getCSVLine(String delimiter) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    String formattedTime = startTime.format(formatter);
    return String.join(delimiter, formattedTime, serviceName, Integer.toString(guest),
        Double.toString(baseCost),
        Double.toString(guestCost), Double.toString(price)) + '\n';
  }
}
