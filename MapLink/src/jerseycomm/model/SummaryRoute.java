package jerseycomm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SummaryRoute implements java.io.Serializable {
    private long duration; // in seconds
    private long distance; // in meters
    private List<TollRoute> tollFees = new ArrayList<>();

    public SummaryRoute() {}

    public long getDuration() {
        return duration;
    }

    public String getDurationHR() {
        long minutes = this.duration % 60;
        long hours = this.duration / 3600;

        String humanReadable = "";
        if (hours > 0) humanReadable += hours + "h";
        if (minutes > 0) humanReadable += minutes + "m";

        return humanReadable;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDistance() {
        return distance;
    }

    public String getDistanceHR() {
        long km = this.distance / 1000;
        long m = this.distance % 1000;

        String humanReadable = "";
        if (km > 0) {
            humanReadable += km;
            if (m > 0) humanReadable += "," + m + "km";
        } else {
            if (m > 0) humanReadable += "m";
        }

        return humanReadable;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public List<TollRoute> getTollFees() {
        return tollFees;
    }

    public double getTollFeesSum() {
        double sum = 0.0;

        for (TollRoute tollRoute : this.tollFees) {
            PriceRoute priceRoute = tollRoute.getPrices();
            if (priceRoute != null) sum += priceRoute.getCar();
        }

        return sum;
    }

    public void setTollFees(List<TollRoute> tollFees) {
        this.tollFees = tollFees;
    }

    @Override
    public String toString() {
        return "SummaryRoute{" +
                "duration=" + this.getDurationHR() +
                ", distance=" + this.getDistanceHR() +
                ", tollFees=" + this.getTollFeesSum() +
                '}';
    }
}
