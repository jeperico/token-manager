package com.softec.model;

import java.util.Objects;
import java.util.UUID;

public class Expertise {
    private final UUID id;
    private final String name;
    private final UUID baseAttributeId;
    private final boolean trainedOnly;
    private final boolean chargePenalty;
    private final boolean kitNeeded;

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

        public Builder baseAttributeId(UUID baseAttributeId) {
            this.baseAttribute = baseAttributeId;
            return this;
        }

        public Builder trainedOnly() {
            this.trainedOnly = true;
            return this;
        }

        public Builder trainedOnly(boolean trainedOnly) {
            this.trainedOnly = trainedOnly;
            return this;
        }

        public Builder chargePenalty() {
            this.chargePenalty = true;
            return this;
        }

        public Builder chargePenalty(boolean chargePenalty) {
            this.chargePenalty = chargePenalty;
            return this;
        }

        public Builder kitNeeded() {
            this.kitNeeded = true;
            return this;
        }

        public Builder kitNeeded(boolean kitNeeded) {
            this.kitNeeded = kitNeeded;
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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getBaseAttributeId() {
        return baseAttributeId;
    }

    public boolean isTrainedOnly() {
        return trainedOnly;
    }

    public boolean isChargePenalty() {
        return chargePenalty;
    }

    public boolean isKitNeeded() {
        return kitNeeded;
    }
}
