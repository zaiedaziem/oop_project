public class Product {
    String p_id;
    String p_name;
    String p_desc;
    double p_price;
    int p_quantity;

    public Product(String p_id, String p_name, String p_desc, double p_price, int p_quantity) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_desc = p_desc;
        this.p_price = p_price;
        this.p_quantity = p_quantity;
    }

    public Product(){
        this.p_id = "";
        this.p_name = "";
        this.p_desc = "";
        this.p_price = 0;
        this.p_quantity = 0;
    }

    public String getP_id() {
        return p_id;
    }
    
    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_desc() {
        return p_desc;
    }

    public void setP_desc(String p_desc) {
        this.p_desc = p_desc;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public int getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }

}
