package jerseycomm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsGeoCode implements java.io.Serializable {
    private LocationGeoCode location;

    public ResultsGeoCode() {}

    public LocationGeoCode getLocation() {
        return location;
    }

    public void setLocation(LocationGeoCode locationGeoCode) {
        this.location = locationGeoCode;
    }
}
