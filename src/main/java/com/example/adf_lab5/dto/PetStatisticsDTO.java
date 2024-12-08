package com.example.adf_lab5.dto;

public class PetStatisticsDTO {
    private final double averageAge;
    private final int oldestAge;
    private final long totalCount;

    public PetStatisticsDTO(double averageAge, int oldestAge, long totalCount) {
        this.averageAge = averageAge;
        this.oldestAge = oldestAge;
        this.totalCount = totalCount;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public int getOldestAge() {
        return oldestAge;
    }

    public long getTotalCount() {
        return totalCount;
    }
}
