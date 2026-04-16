package com.softec.entity;

public class Attribute {
    private final String name;
    private final String shortName;
    private String description;

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

        this.name = builder.name;
        this.shortName = builder.shortName;
        this.description = builder.description;
    }

    public static class Builder {
        private String name;
        private String shortName;
        private String description;

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

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
