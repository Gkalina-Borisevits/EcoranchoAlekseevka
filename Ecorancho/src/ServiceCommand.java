import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public enum ServiceCommand {
  UNEXEPTED(10, ""),
  LISTOFSERVICES(1, "Вывести список услуг"),
  ADDSERVICE(2, "Добавить услугу"),
  DELETESERVICE(3, "Удалить услугу"),
  SAVEEVENT(4, "Сохранить заказ"),
  EDITEVENT(5, "Редактировать заказ"),
  CREATEEVENTPLAN(6, "Создать план мероприятия"),
  PRINTEVENTPLAN(7, "Вывести план мероприятия"),
  EXIT(0, "Завершить выбор");

  private final int num;
  private final String displayName;
  private ServicesFile servicesFile;
  private static TreeMap<LocalDate, Task> tasks = new TreeMap<>();


  ServiceCommand(int num, String displayName) {
    this.num = num;
    this.displayName = displayName;


  }

  public static void printMenu() {
    System.out.println("Выберите действие:");
    for (ServiceCommand command : values()) {

      if (command != UNEXEPTED) {
        System.out.println(
            command.num + ". " + command.displayName);
      }
    }
  }

  public static ServiceCommand serviceCommand(Scanner scanner) throws IOException {
    printMenu();
    boolean isRun = true;

    while (isRun) {

      if (!scanner.hasNextInt()) {
        throw new RuntimeException("Ожидаем ввод команды");
      }
      int command = scanner.nextInt();
      scanner.nextLine();
      switch (command) {
        case 1:
          System.out.println("Вы выбрали: " + LISTOFSERVICES.displayName);
          TasksFile.printServices();
          break;
        case 2:
          System.out.println("Вы выбрали: " + ADDSERVICE.displayName);


          Wedding.interactiveRead(scanner);
          //Service.interactiveService(scanner);
          System.out.println("Услуга добавлена");
          break;
        case 3:
          System.out.println("Вы выбрали: " + DELETESERVICE.displayName);
          Events.deleteService(scanner);

          break;
        case 4:
          System.out.println("Вы выбрали: " + SAVEEVENT.displayName);

          //TasksFile.writeTasks("res/services.csv",);
          break;
        case 5:
          System.out.println("Вы выбрали: " + EDITEVENT.displayName);

          Events.editEvent("res/services.csv");
          break;
        case 6:
          System.out.println("Вы выбрали: " + CREATEEVENTPLAN.displayName);
          break;
        case 7:
          System.out.println("Вы выбрали: " + PRINTEVENTPLAN.displayName);
          Events.printEventPlan();
          break;
        case 8:
          System.out.println("Вы выбрали: " + EXIT.displayName);
          isRun = false;
          break;
        default:
          return UNEXEPTED;

      }
    }
    return serviceCommand(scanner);
  }
}
