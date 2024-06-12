import java.text.DecimalFormat;

public class Item {
    private int serialNo;
    private String description;
    private String productNo;
    private String manufacturer;
    private double quantity;
    private String unit;

    private DecimalFormat formatter = new DecimalFormat("#########.00");

    public Item(int serialNo, String description, String productNo, String manufacturer, double quantity, String unit) {
        this.serialNo = serialNo;
        this.description = description;
        this.productNo = productNo;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return serialNo + "," + description + "," + productNo + "," + manufacturer + "," + formatter.format(quantity) + "," + unit;
    }

    public static Item parse(String[] record){
        int serialNo = Integer.parseInt(record[0]);
        String description = record[1];
        String productNo = record[2];
        String manufacturer = record[3];
        double quantity = Double.parseDouble(record[4]);
        String unit = record[5];
        return new Item(serialNo, description, productNo, manufacturer, quantity, unit);
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(DecimalFormat formatter) {
        this.formatter = formatter;
    }
}
