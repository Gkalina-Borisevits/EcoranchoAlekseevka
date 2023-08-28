import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class TasksFile {

  private final File file;
  private final String separator;


  public TasksFile(String fileName, String delimiter) throws IOException {
    file = new File(fileName);
    if (!file.exists()) {
      if (!file.getParentFile().mkdirs() || !file.createNewFile()) {
        throw new RuntimeException("Не получилось создать файл: " + fileName);
      }
    }
    if (!(file.canRead() && file.canWrite())) {
      throw new IllegalArgumentException("Файл не доступен: " + fileName);
    }
    this.separator = delimiter;
  }

  public TasksFile(File file, String separator) {
    this.file = file;
    this.separator = separator;
  }

  public Map<LocalDate, Task> readTasks() throws FileNotFoundException {
    Map<LocalDate, Task> tasks = new TreeMap<>();
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Task task = Task.parseFromCSVLinen(line, separator);
      tasks.put(task.getLocalDate(), task);
    }
    scanner.close();
    return tasks;
  }

  public void writeTasks(Map<LocalDate, Task> tasks) throws IOException {
    try (FileWriter writer = new FileWriter(file)) {
      for (Map.Entry<LocalDate, Task> entry : tasks.entrySet()) {
        Task task = entry.getValue();
        writer.write(task.getCSVLine(separator));
      }
    }
  }
}
