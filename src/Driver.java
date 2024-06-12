import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args) {

        // LOAD DATABASE
        List<Item> itemList = new ArrayList<>();
        FileHandler db = new FileHandler();
        itemList = db.load();

        // DO OCR
        String data = null;
        try {
            Tesseract tesseract = new Tesseract();

            tesseract.setDatapath("C:\\Users\\Abid357-PC\\Desktop\\Tess4J-3.4.8-src\\Tess4J\\tessdata");

            // the path of your tess data folder
            // inside the extracted file
             data = tesseract.doOCR(new File("C:\\Users\\Abid357-PC\\Desktop\\test-scribbled.jpeg"));

            // path of your image file
            System.out.print(data);
        }
        catch (TesseractException e) {
            e.printStackTrace();
        }

        // SKIP UNTIL HEADERS
        String[] rows = data.split("\n");
        int startRow = 0;
        while (!rows[startRow].contains("SrNo"))
            startRow++;


//        String srNo = rows[startRow].substring(0, rows[startRow].indexOf(" "));
//        Item selectedItem = null;
//        for (Item item : itemList){
//            if (item.getSerialNo() == Integer.parseInt(srNo)) {
//                selectedItem = item;
//                break;
//            }
//        }

        // GO OVER EACH ITEM AND DO CORRECTION
        for (Item selectedItem : itemList) {
            startRow++;

            String[] ocrTokens = rows[startRow].split(" ");
            int ocrIndex = 1;
            int tokenIndex = ocrIndex - 1;
            // description
            String[] descriptionTokens = selectedItem.getDescription().split(" ");
            while (tokenIndex != descriptionTokens.length && descriptionTokens[tokenIndex].equals(ocrTokens[ocrIndex])) {
                ocrIndex++;
                tokenIndex++;
            }

            // productNo
            if (ocrTokens[ocrIndex].equals(selectedItem.getProductNo()))
                ocrIndex++;
            else {
                selectedItem.setProductNo(ocrTokens[ocrTokens.length - 1]);
                continue;
            }

            // manufacturer
            tokenIndex = 0;
            String[] manufacturerTokens = selectedItem.getManufacturer().split(" ");
            while (tokenIndex != manufacturerTokens.length && manufacturerTokens[tokenIndex].equals(ocrTokens[ocrIndex])) {
                ocrIndex++;
                tokenIndex++;
            }

            // quantity
            double quantity = Double.parseDouble(ocrTokens[ocrIndex]);
            if (quantity == selectedItem.getQuantity())
                ocrIndex++;
            else {
                quantity = Double.parseDouble(ocrTokens[ocrTokens.length - 1]);
                selectedItem.setQuantity(quantity);
                continue;
            }

            // quantity
            if (ocrTokens[ocrIndex].equals(selectedItem.getUnit()))
                ocrIndex++;
            else {
                selectedItem.setUnit(ocrTokens[ocrTokens.length - 1]);
                continue;
            }
        }
    }
}
