package com.softec.entity;

public class Expertise {
    private final String name;
    private Attribute baseAttribute;
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

        this.name = builder.name;
        this.baseAttribute = builder.baseAttribute;
        this.trainedOnly = builder.trainedOnly;
        this.chargePenalty = builder.chargePenalty;
        this.kitNeeded = builder.kitNeeded;
    }

    public static class Builder {
        private String name;
        private Attribute baseAttribute;
        private boolean trainedOnly;
        private boolean chargePenalty;
        private boolean kitNeeded;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder baseAttribute(Attribute baseAttribute) {
            this.baseAttribute = baseAttribute;
            return this;
        }

        public Builder trainedOnly(boolean trainedOnly) {
            this.trainedOnly = trainedOnly;
            return this;
        }

        public Builder chargePenalty(boolean chargePenalty) {
            this.chargePenalty = chargePenalty;
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

    public String getName() {
        return name;
    }

    public Attribute getBaseAttribute() {
        return baseAttribute;
    }

    public void setBaseAttribute(Attribute baseAttribute) {
        this.baseAttribute = baseAttribute;
    }

    public boolean isTrainedOnly() {
        return trainedOnly;
    }

    public void setTrainedOnly(boolean trainedOnly) {
        this.trainedOnly = trainedOnly;
    }

    public boolean isChargePenalty() {
        return chargePenalty;
    }

    public void setChargePenalty(boolean chargePenalty) {
        this.chargePenalty = chargePenalty;
    }

    public boolean isKitNeeded() {
        return kitNeeded;
    }

    public void setKitNeeded(boolean kitNeeded) {
        this.kitNeeded = kitNeeded;
    }
}
