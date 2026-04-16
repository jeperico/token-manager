package com.softec.entity;

import java.util.ArrayList;
import java.util.List;

public class Origin {
    private String name;
    private String description;
    private final List<Expertise> expertises = new ArrayList<>(2);
    private String powerName;
    private String powerDescription;

    public Origin(Builder builder) {
        if (builder.name == null || builder.name.isEmpty()) {
            throw new IllegalArgumentException("Origin name cannot be null or empty");
        }
        if (builder.description == null || builder.description.isEmpty()) {
            throw new IllegalArgumentException("Origin description cannot be null or empty");
        }
        if (builder.expertises.isEmpty()) {
            throw new IllegalArgumentException("Origin expertises cannot be null or empty");
        }
        if (builder.powerName == null || builder.powerName.isEmpty()) {
            throw new IllegalArgumentException("Origin powerName cannot be null or empty");
        }
        if (builder.powerDescription == null || builder.powerDescription.isEmpty()) {
            throw new IllegalArgumentException("Origin powerDescription cannot be null or empty");
        }

        this.name = builder.name;
        this.description = builder.description;
        this.expertises.add(builder.expertises.getFirst());
        this.expertises.add(builder.expertises.getLast());
        this.powerName = builder.powerName;
        this.powerDescription = builder.powerDescription;
    }

    public static class Builder {
        private String name;
        private String description;
        private final List<Expertise> expertises = new ArrayList<>(2);
        private String powerName;
        private String powerDescription;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder firstExpertise(Expertise expertise) {
            this.expertises.set(0, expertise);
            return this;
        }

        public Builder secondExpertise(Expertise expertise) {
            this.expertises.set(1, expertise);
            return this;
        }

        public Builder powerName(String powerName) {
            this.powerName = powerName;
            return this;
        }

        public Builder powerDescription(String powerDescription) {
            this.powerDescription = powerDescription;
            return this;
        }

        public Origin build(Builder builder) {
            return new Origin(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Expertise> getExpertises() {
        return expertises;
    }

    public void setExpertise(int index, Expertise expertise) {
        if (index < 0 || index >= 2) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        this.expertises.set(index, expertise);
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerDescription() {
        return powerDescription;
    }

    public void setPowerDescription(String powerDescription) {
        this.powerDescription = powerDescription;
    }
}
