package com.upgrade.model.general;

import java.util.ArrayList;
import java.util.Objects;

public class GradeCategory {
    private String name;
    private double weightPercentage;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GradeCategory that)) return false;
        return Double.compare(weightPercentage, that.weightPercentage) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weightPercentage);
    }

    //Getters/Setters Below

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightPercentage() {
        return weightPercentage;
    }

    public void setWeightPercentage(double weightPercentage) {
        this.weightPercentage = weightPercentage;
    }
}
