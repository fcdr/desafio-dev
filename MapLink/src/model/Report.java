package model;

import jerseycomm.model.SummaryRoute;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Report {
    private double consumption = 13.34;
    private double fuelPrice = 3.65;

    private long duration;
    private String totalDuration;
    private long distance;
    private String totalDistance;
    private double totalTollCost;
    private double totalFuelCost;

    public Report() {}

    public Report(SummaryRoute summaryRoute) {
        this.duration = summaryRoute.getDuration();
        this.totalDuration = summaryRoute.getDurationHR();
        this.distance = summaryRoute.getDistance();
        this.totalDistance = summaryRoute.getDistanceHR();
        this.totalTollCost = summaryRoute.getTollFeesSum();
        calculateTotalFuelCost();
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(String totalDuration) {
        this.totalDuration = totalDuration;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getTotalTollCost() {
        return totalTollCost;
    }

    public void setTotalTollCost(double totalTollCost) {
        this.totalTollCost = totalTollCost;
    }

    public double getTotalFuelCost() {
        return totalFuelCost;
    }

    public void setTotalFuelCost(double totalFuelCost) {
        this.totalFuelCost = totalFuelCost;
    }

    public void calculateTotalFuelCost() {
        this.totalFuelCost = this.fuelPrice * ((this.distance / 1000.0) / this.consumption);
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        double totalCost = this.totalTollCost + this.totalFuelCost;
        return "\nRelatório" +
                "\nTempo total da rota:\t" + this.totalDuration +
                "\nDistância total da rota:\t" + this.totalDistance +
                "\nCusto total:\t" + formatter.format(totalCost) +
                "\n\tcusto pedágio: " + formatter.format(this.totalTollCost) +
                "\n\tcusto combustível: " + formatter.format(this.totalFuelCost) +
                "\n\nConsumo usado (km/l): " + this.consumption +
                "\nPreço de combustível usado ($/l): " + this.fuelPrice;
    }
}
