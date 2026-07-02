package com.housing.javaapi.dto;

public class MarketSummary {

    private double averagePrice;
    private double averageArea;
    private double minimumPrice;
    private double maximumPrice;
    private long totalProperties;

    public MarketSummary() {
    }

    public MarketSummary(
            double averagePrice,
            double averageArea,
            double minimumPrice,
            double maximumPrice,
            long totalProperties
    ) {
        this.averagePrice = averagePrice;
        this.averageArea = averageArea;
        this.minimumPrice = minimumPrice;
        this.maximumPrice = maximumPrice;
        this.totalProperties = totalProperties;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getAverageArea() {
        return averageArea;
    }

    public void setAverageArea(double averageArea) {
        this.averageArea = averageArea;
    }

    public double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public double getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(double maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public long getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(long totalProperties) {
        this.totalProperties = totalProperties;
    }
}