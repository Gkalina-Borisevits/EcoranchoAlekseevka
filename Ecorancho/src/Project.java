import java.io.IOException;
import java.util.Scanner;

public class Project {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      MenuCommand command = MenuCommand.commandRead(scanner);

      switch (command) {
        case UNEXEPTED:
          System.out.println("Не корректный ввод");
          break;

        case WEDDING:
          System.out.println("Добро пожаловать в организацию мероприятия: " + MenuCommand.WEDDING);
          break;

        case CORPORATE:
          System.out.println("Добро пожаловать в организацию мероприятия: " + MenuCommand.CORPORATE);
          break;

        case BIRTHDAY:
          System.out.println("Добро пожаловать в организацию мероприятия: " + MenuCommand.BIRTHDAY);
          break;

        case TEAM_BUILDING:
          System.out.println(
              "Добро пожаловать в организацию мероприятия: " + MenuCommand.TEAM_BUILDING);
          break;

        case EXIT:
          System.out.println("Вы забронировали онлайн следующее мероприятие: ");
          System.out.println("Всегда Вам рады! Ваше Ecorancho Alekseevka");
          return;
        default:
          throw new IllegalStateException("Unexpected value: " + command);
      }
    }
  }

}
