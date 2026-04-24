package com.softec.model;

import java.util.Objects;
import java.util.UUID;

public class Attribute {
    private final UUID id;
    private final String name;
    private final String shortName;
    private final String description;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public Attribute (Builder builder) {
        if (builder.name == null || builder.name.isEmpty()) {
            throw new IllegalArgumentException("Attribute name cannot be null or blank");
        }
        if (builder.shortName == null || builder.shortName.isEmpty()) {
            throw new IllegalArgumentException("Attribute short name cannot be null or blank");
        }
        if (builder.description == null || builder.description.isEmpty()) {
            throw new IllegalArgumentException("Attribute description cannot be null or blank");
        }

        this.id = builder.id != null ? builder.id : UUID.randomUUID();
        this.name = builder.name;
        this.shortName = builder.shortName;
        this.description = builder.description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(id, attribute.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private UUID id;
        private String name;
        private String shortName;
        private String description;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Attribute build() {
            return new Attribute(this);
        }
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
