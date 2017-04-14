package model;

public class Address {
    private String street;
    private String number;
    private String complement;
    private String city;
    private String state;
    private String lat;
    private String lon;

    public Address() {}

    public Address(String street, String number, String complement, String city, String state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    public Address(String street, String number, String complement, String city, String state, String lat, String lon) {
        this(street, number, complement, city, state);
        this.lat = lat;
        this.lon = lon;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAddress(boolean useNumber) {
        String address = null;

        if (this.street != null && !"".equals(this.street)) {
            address = this.street;

            if (useNumber && this.number != null && !"".equals(this.number)) address += " " + this.number;
            if (this.city != null && !"".equals(this.city)) address += " " + this.city;
            if (this.state != null && !"".equals(this.state)) address += " " + this.state;
        }

        return address;
    }

    public String getLatLonCSV() {
        if (this.lat != null && !"".equals(this.lat) && this.lon != null && !"".equals(this.lon))
            return this.lat + "," + this.lon;
        return null;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
