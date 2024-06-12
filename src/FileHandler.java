import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
    private final String[] headers = { "SrNo", "Description", "ProductNo", "Manufacturer", "Quantity", "Unit" };
    private final String filePath = "stocklist.csv";

    private String getHeaders() {
        String csvHeaders = "";
        for (int i = 0; i < headers.length; i++)
            csvHeaders += headers[i] + ",";
        return csvHeaders.substring(0, csvHeaders.length() - 1);
    }

    public boolean save(List<Item> list) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            writer.print("");
            writer.println(getHeaders());
            for (Item item : list)
                writer.println(item);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.getStackTrace();
            return false;
        }
        return true;
    }

    public List<Item> load() {
        File file = new File(filePath);
        List<Item> list = new ArrayList<Item>();
        if (!file.exists()) {
            save(list);
            return list;
        } else {
            try {
                Scanner reader = new Scanner(file);
                reader.nextLine(); // remove headers
                while (reader.hasNext()) {
                    String[] record = reader.nextLine().split(",");
                    list.add(Item.parse(record));
                }
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            return list;
        }
    }
}
