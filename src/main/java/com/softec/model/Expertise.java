package com.softec.model;

import java.util.Objects;
import java.util.UUID;

public class Expertise {
    private final UUID id;
    private final String name;
    private UUID baseAttributeId;
    private boolean trainedOnly;
    private boolean chargePenalty;
    private boolean kitNeeded;

    public Expertise(Builder builder) {
        if (builder.name == null || builder.name.isEmpty()) {
            throw new IllegalArgumentException("Expertise name cannot be null or blank");
        }
        if (builder.baseAttribute == null) {
            throw new IllegalArgumentException("Expertise baseAttribute cannot be null");
        }

        this.id = UUID.randomUUID();
        this.name = builder.name;
        this.baseAttributeId = builder.baseAttribute;
        this.trainedOnly = builder.trainedOnly;
        this.chargePenalty = builder.chargePenalty;
        this.kitNeeded = builder.kitNeeded;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Expertise expertise = (Expertise) o;
        return Objects.equals(id, expertise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private String name;
        private UUID baseAttribute;
        private boolean trainedOnly = false;
        private boolean chargePenalty = false;
        private boolean kitNeeded = false;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder baseAttribute(Attribute baseAttribute) {
            this.baseAttribute = baseAttribute.getId();
            return this;
        }

        public Builder trainedOnly() {
            this.trainedOnly = true;
            return this;
        }

        public Builder chargePenalty() {
            this.chargePenalty = true;
            return this;
        }

        public Builder kitNeeded() {
            this.kitNeeded = true;
            return this;
        }

        public Expertise build() {
            return new Expertise(this);
        }
    }

    @Override
    public String toString() {
        return "Expertise{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", baseAttributeId=" + baseAttributeId +
                ", trainedOnly=" + trainedOnly +
                ", chargePenalty=" + chargePenalty +
                ", kitNeeded=" + kitNeeded +
                '}';
    }
}
