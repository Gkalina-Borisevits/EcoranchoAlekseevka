import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ServicesFile {

  private final File file;
  private final String separator;

  public ServicesFile(String fileName, String delimiter) throws IOException {
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

  public TreeMap<LocalTime, Service> readAvailableServicesFromFile() throws FileNotFoundException {
    TreeMap<LocalTime, Service> services = new TreeMap<>();
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      Service service = Service.parseFromCSVLine(line, separator);
      services.put(service.getStartTime(), service);
    }
    scanner.close();
    return services;
  }

  public void writeService(TreeMap<LocalTime, Service> services) throws IOException {

    try (FileWriter writer = new FileWriter(file)) {
      for (Map.Entry<LocalTime, Service> entry : services.entrySet()) {
        Service service = entry.getValue();
        writer.write(service.getCSVLine(separator));
      }
    }
  }
}
