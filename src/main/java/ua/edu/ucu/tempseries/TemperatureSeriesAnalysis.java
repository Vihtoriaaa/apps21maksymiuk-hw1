package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int MIN_VALUE = -273;
    private double[] temperatureSeries;
    private int size;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
        this.size = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp: temperatureSeries) {
            if (temp < MIN_VALUE) {
                throw new
                    InputMismatchException("The temperature exceeds min bound");
            }
        }
        this.temperatureSeries =
                Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.size = temperatureSeries.length;
    }

    public double average() {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        double result = 0;
        for (double temp: temperatureSeries) {
            result += temp;
        }
        return result / size;
    }

    public double deviation() {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        double average = this.average(), deviation = 0;
        for (double temp: temperatureSeries) {
            deviation += (temp - average)
                    * (temp - average);
        }
        return Math.sqrt(deviation / size);
    }

    public double min() {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        double minValue = Double.POSITIVE_INFINITY;
        for (double temp: temperatureSeries) {
            minValue = Math.min(minValue, temp);
        }
        return minValue;
    }

    public double max() {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        double maxValue = Double.NEGATIVE_INFINITY;
        for (double temp: temperatureSeries) {
            maxValue = Math.max(maxValue, temp);
        }
        return maxValue;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        double difference = Double.POSITIVE_INFINITY, closeValue = 0;
        for (double temp : temperatureSeries) {
            if (Math.abs(temp - tempValue) < difference) {
                difference = Math.abs(temp - tempValue);
                closeValue = temp;
            } else if (Math.abs(temp - tempValue) == difference && temp > 0) {
                difference = Math.abs(temp - tempValue);
                closeValue = temp;
            }
        }
        return closeValue;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        int length = 0, index = 0;
        for (double temp: temperatureSeries) {
            if (temp < tempValue) {
                length++;
            }
        }
        double[] tempsLess = new double[length];
        for (double temp : temperatureSeries) {
            if (temp < tempValue) {
                tempsLess[index] = temp;
                index++;
            }
        }
        return tempsLess;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        int length = 0, index = 0;
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                length++;
            }
        }
        double[] tempsGreater = new double[length];
        for (double temp : temperatureSeries) {
            if (temp >= tempValue) {
                tempsGreater[index] = temp;
                index++;
            }
        }
        return tempsGreater;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        if (size == 0) {
            throw new
                    IllegalArgumentException("Temperature Series is empty.");
        }
        for (double temp: temps) {
            if (temp < MIN_VALUE) {
                throw new
                    InputMismatchException("Temperature exceeds min bound");
            }
        }
        int length = size, summary = 0;
        if (length < temps.length + size) {
            length *= 2;
        }
        double[] newTempSeries = new double[length];
        int index = 0;
        for (double temp: temperatureSeries) {
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
