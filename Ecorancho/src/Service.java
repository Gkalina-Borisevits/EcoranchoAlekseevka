
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {

  private String serviceName;
  private static int guest;
  private double price;
  private static double baseCost;
  private static double guestCost;

  public Service(String serviceName, int guest, double baseCost, double guestCost) {
    this.serviceName = serviceName;
    this.guest = guest;
    this.baseCost = baseCost;
    this.guestCost = guestCost;
    calculateCost();
  }

  public Service(String serviceName, int guest, double price) {
    this.serviceName = serviceName;
    this.guest = guest;
    this.price = calculateCost();
  }

  public String getServiceName() {
    return serviceName;
  }

  public double getBaseCost() {
    return baseCost;
  }

  public double getGuestCost() {
    return guestCost;
  }

  public int getGuest() {
    return guest;
  }

  public double getPrice() {
    return price;
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

  public void setCost(double baseCost) {
    this.baseCost = baseCost;
    calculateCost();
  }

  public static double calculateCost() {
    return baseCost + (guestCost * guest);
  }


  public static void printAvailableServices(List<Service> services) {
    System.out.println("\nДоступные услуги:");
    for (int i = 0; i < services.size(); i++) {
      System.out.println((i + 1) + ". " + services.get(i).getServiceName());
    }
  }

  public static Service interactiveSelect(Scanner scanner, List<Service> availableServices) {
    printAvailableServices(availableServices);

    System.out.println("Выберите номер услуги:");
    int choice = scanner.nextInt();
    scanner.nextLine();

    if (choice >= 1 && choice <= availableServices.size()) {
      return availableServices.get(choice - 1);
    } else {
      System.out.println("Некорректный выбор. Выберите существующий номер.");
      return null;
    }
  }
  public static Service interactiveRead(Scanner scanner, List<Service> availableServices) {
    selectServices(scanner, availableServices);
    System.out.println("Введите название услуги:");
    String name = scanner.nextLine();
    System.out.print("Введите предполагаемое количество гостей: ");
    while (!scanner.hasNextInt()) {
      System.out.println("Некорректный ввод: " + scanner.nextLine());
      System.out.print("Введите целое число: ");
    }
    int guest = scanner.nextInt();
    scanner.nextLine();

    double totalCost = 0.0;
    List<Service> selectedServices = new ArrayList<>();

    while (true) {
      Service.printAvailableServices(availableServices);
      Service selectedService = Service.interactiveSelect(scanner, availableServices);
      if (selectedService == null) {
        break;
      }

      double cost = selectedService.calculateCost();
      totalCost += cost;
      System.out.println("Стоимость услуги " + selectedService.getServiceName() +
          " для " + guest + " гостей составит: " + cost);

      selectedServices.add(selectedService);
    }

    System.out.println("Общая стоимость выбранных услуг для " + guest + " гостей составит: " + totalCost);

    return new Service(name, guest, totalCost);
  }

  public static List<Service> selectServices(Scanner scanner, List<Service> availableServices) {
    List<Service> selectedServices = new ArrayList<>();

    while (true) {
      ServiceCommand.printMenu();
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          Service.printAvailableServices(availableServices);
          Service selectedService = Service.interactiveSelect(scanner, availableServices);
          if (selectedService != null) {
            selectedServices.add(selectedService);
          }
          break;
        case 0:
          System.out.println("Завершение выбора.");
          break;
        default:
          System.out.println("Некорректный выбор. Повторите ввод.");
      }

      if (choice == 0) {
        break;
      }
    }
    return selectedServices;
  }

  public static Service parseFromCSVLine(String s, String delimiter) {
    String[] cells = s.split(delimiter);
    try {
      return new Service(cells[0], Integer.parseInt(cells[1]), Double.parseDouble(cells[2]),
          Double.parseDouble(cells[3]));
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
    return String.join(delimiter, serviceName, Integer.toString(guest), Double.toString(baseCost),
        Double.toString(guestCost));
  }


}
