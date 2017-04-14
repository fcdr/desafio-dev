package jerseycomm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceRoute implements java.io.Serializable {
    private double car;

    public PriceRoute() {}

    public double getCar() {
        return car;
    }

    public void setCar(double car) {
        this.car = car;
    }
}
