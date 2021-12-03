package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int min_temp = -273;
    private double[] temperatureSeries;
    private int size;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries){
            if (temp < min_temp){
                throw new InputMismatchException("The temperature exceeds minimal bound.");
            }
        }
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        double result = 0;
        for (double temp: temperatureSeries){
            result += temp;
        }
        return result / size;
    }

    public double deviation() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        double average = this.average(), deviation = 0;
        for (double temp: temperatureSeries) {
            deviation += (temp - average)
                    * (temp - average);
        }
        return Math.sqrt(deviation / size);
    }

    public double min() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        double min_value = Double.POSITIVE_INFINITY;
        for (double temp: temperatureSeries){
            min_value = Math.min(min_value, temp);
        }
        return min_value;
    }

    public double max() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        double max_value = Double.NEGATIVE_INFINITY;
        for (double temp: temperatureSeries){
            max_value = Math.max(max_value, temp);
        }
        return max_value;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        double difference = Double.POSITIVE_INFINITY, closest_value = 0;
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < difference) {
                difference = Math.abs(temp - tempValue);
                closest_value = temp;
            } else if (Math.abs(temp - tempValue) == difference && temp > 0) {
                difference = Math.abs(temp - tempValue);
                closest_value = temp;
            }
        }
        return closest_value;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        int length = 0, index = 0;
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                length++;
            }
        }
        double[] temps_less = new double[length];
        for (double temp : temperatureSeries){
            if (temp < tempValue){
                temps_less[index] = temp;
                index++;
            }
        }
        return temps_less;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        int length = 0, index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                length++;
            }
        }
        double[] temps_greater = new double[length];
        for (double temp : temperatureSeries){
            if (temp >= tempValue){
                temps_greater[index] = temp;
                index++;
            }
        }
        return temps_greater;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (size == 0){
            throw new IllegalArgumentException("Temperature Series does not have any value.");
        }
        for (double temp: temps){
            if (temp < min_temp){
                throw new InputMismatchException("The temperature exceeds minimal bound.");
            }
        }
        int length = size, summary = 0;
        if (length < temps.length + size) {
            length *= 2;;
        }
        double[] newTempSeries = new double[length];
        int index = 0;
        for (double temp : temperatureSeries) {
            newTempSeries[index] = temp;
            summary += temp;
            index++;
        }
        for (double temp : temps) {
            newTempSeries[index] = temp;
            summary += temp;
            index++;
        }
        this.temperatureSeries = newTempSeries;
        this.size = length;
        return summary;
    }
}
