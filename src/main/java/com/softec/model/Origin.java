package com.softec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Origin {
    private final UUID id;
    private final String name;
    private final String description;
    private final String powerName;
    private final String powerDescription;

    // BUSINESS RULE: AN ORIGIN MUST BE ONLY 2 EXPERTISES
    private final List<Expertise> expertises = new ArrayList<>(2);

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

        this.id = UUID.randomUUID();
        this.name = builder.name;
        this.description = builder.description;
        this.expertises.add(builder.expertises.getFirst());
        this.expertises.add(builder.expertises.getLast());
        this.powerName = builder.powerName;
        this.powerDescription = builder.powerDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Origin origin = (Origin) o;
        return Objects.equals(id, origin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
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

        public Builder addExpertise(Expertise expertise) {
            this.expertises.add(expertise);
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

        public Origin build() {
            return new Origin(this);
        }
    }

    @Override
    public String toString() {
        return "Origin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", expertises=" + expertises +
                ", powerName='" + powerName + '\'' +
                ", powerDescription='" + powerDescription + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
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
}
