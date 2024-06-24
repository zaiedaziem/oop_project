public class Address implements Home {
    private String lot;
    private String area;
    private String roadName;
    private String zipcode;
    private String city;
    private String state;

    public Address(String lot, String area, String roadName, String zipcode, String city, String state) {
        this.lot = lot;
        this.area = area;
        this.roadName = roadName;
        this.zipcode = zipcode;
        this.city = city;
        this.state = state;
    }

    // Getters
    public String getLot() {
        return lot;
    }

    public String getArea() {
        return area;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    // Setters
    public void setLot(String lot) {
        this.lot = lot;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getAddress() {
        return lot + ", " + area + ", " + roadName + ", " + zipcode + ", " + city + ", " + state;
    }
}
