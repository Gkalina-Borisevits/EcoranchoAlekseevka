import java.util.Scanner;

public enum MenuCommand {
  EXIT(0, "ВЫХОД", "EXIT"),
  WEDDING(1, "СВАДЬБА", "WEDDING"),
  CORPORATE(2, "КОРПОРАТИВ", "CORPORATE"),
  BIRTHDAY(3, "ДЕНЬ РОЖДЕНИЯ", "BIRTHDAY"),
  TEAM_BUILDING(4, "ТИМ-БИЛДИНГ", "TEAM_BUILDING"),
  UNEXEPTED(10, "", "");


  private final int num;
  private final String displayNameRus;
  private final String displayNameEng;

  MenuCommand(int num, String displayNameRus, String displayNameEng) {
    this.num = num;
    this.displayNameRus = displayNameRus;
    this.displayNameEng = displayNameEng;
  }

  public static void printMenu() {
    System.out.println("welcome to ecorancho\n");
    for (MenuCommand command : values()) {

      if (command != UNEXEPTED) {
        System.out.println(
            command.num + ". " + command.displayNameRus + " / " + command.displayNameEng);
      }
    }
  }

  public static MenuCommand commandRead(Scanner scanner) {
    printMenu();
    System.out.println("Введите планируемое мероприятие");
    if (!scanner.hasNext()) {
      throw new RuntimeException("Введите необходимое мероприятие");
    }
    String input = scanner.nextLine().toUpperCase();

    switch (input) {
      case "1":
      case "WEDDING":
      case "СВАДЬБА":
        return WEDDING;

      case "2":
      case "CORPORATE":
      case "КОРПОРАТИВ":
        return CORPORATE;

      case "3":
      case "BIRTHDAY":
      case "ДЕНЬ РОЖДЕНИЯ":
        return BIRTHDAY;

      case "4":
      case "TEAM_BUILDING":
      case "ТИМ-БИЛДИНГ":
        return TEAM_BUILDING;

      case "0":
      case "EXIT":
      case "ВЫХОД":
        return EXIT;

      default:
        return UNEXEPTED;
    }
  }
}
