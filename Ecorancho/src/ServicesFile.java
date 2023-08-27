import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

  public ArrayList<Service> readAvailableServicesFromFile() throws FileNotFoundException {
    ArrayList<Service> services = new ArrayList<>();
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      services.add(Service.parseFromCSVLine(scanner.nextLine(), separator));
    }
    scanner.close();
    return services;
  }

  public void writeServices(List<Service> services) throws IOException {
    try (FileWriter writer = new FileWriter(file)) {
      for (Service s : services) {
        writer.write(s.getCSVLine(separator));
      }
    }
  }
}
