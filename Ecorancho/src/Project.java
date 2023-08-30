import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Project {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    TreeMap<Task, Service> tasks = new TreeMap<>();
    TreeMap<LocalTime, Service> service = new TreeMap<>();
    TasksFile tasksFile = new TasksFile("res/task.csv", "; ");

    while (true) {
      MenuCommand command = MenuCommand.commandRead(scanner);

      switch (command) {
        case UNEXEPTED:
          System.out.println("Не корректный ввод");
          break;

        case WEDDING:
          System.out.println("Добро пожаловать в организацию мероприятия: " + MenuCommand.WEDDING);
          ServiceCommand.serviceCommand(scanner);
          break;

        case CORPORATE:
          System.out.println(
              "Добро пожаловать в организацию мероприятия: " + MenuCommand.CORPORATE);
          ServiceCommand.serviceCommand(scanner);
          break;

        case BIRTHDAY:
          System.out.println("Добро пожаловать в организацию мероприятия: " + MenuCommand.BIRTHDAY);
          ServiceCommand.serviceCommand(scanner);
          break;

        case TEAM_BUILDING:
          System.out.println(
              "Добро пожаловать в организацию мероприятия: " + MenuCommand.TEAM_BUILDING);
          ServiceCommand.serviceCommand(scanner);
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
