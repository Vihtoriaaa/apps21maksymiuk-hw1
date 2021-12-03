package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double average_temp;
    private final double deviation_temp;
    private final double min_temp;
    private final double max_temp;


    public TempSummaryStatistics(double averageTemp, double devTemp, double minTemp, double maxTemp) {
        this.average_temp = averageTemp;
        this.deviation_temp = devTemp;
        this.min_temp = minTemp;
        this.max_temp = maxTemp;
    }

    @Override
    public String toString() {
        return "TempSummaryStatistics{"
                +
                "average_temperature="
                +
                average_temp
                +
                " | deviation_temperature="
                +
                deviation_temp
                +
                " | min_temperature="
                +
                min_temp
                +
                " | max_temperature="
                +
                max_temp
                +
                "}";
    }
}
