import java.util.Scanner;

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

  public static ServiceCommand serviceCommand(Scanner scanner) {
    boolean isRun = true;
    while (isRun) {
      printMenu();
      if (!scanner.hasNextInt()) {
        throw new RuntimeException("Ожидаем ввод команды");
      }
      int command = scanner.nextInt();
      scanner.nextLine();
      switch (command) {
        case 1:
          System.out.println("Вы выбрали: " + LISTOFSERVICES.displayName);
          break;
        case 2:
          System.out.println("Вы выбрали: " + ADDSERVICE.displayName);
          break;
        case 3:
          System.out.println("Вы выбрали: " + DELETESERVICE.displayName);
          break;
        case 4:
          System.out.println("Вы выбрали: " + SAVEEVENT.displayName);
          break;
        case 5:
          System.out.println("Вы выбрали: " + EDITEVENT.displayName);
          break;
        case 6:
          System.out.println("Вы выбрали: " + CREATEEVENTPLAN.displayName);
          break;
        case 7:
          System.out.println("Вы выбрали: " + PRINTEVENTPLAN.displayName);
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
