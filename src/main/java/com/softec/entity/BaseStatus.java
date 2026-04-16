package com.softec.entity;

public class BaseStatus {
    private final int hpBase;
    private final int hpLevel;
    private final int epBase;
    private final int epLevel;
    private final int sanBase;
    private final int sanLevel;

    public BaseStatus(Builder builder) {
        if (builder.hpBase < 0) {
            throw new IllegalArgumentException("HP base cannot be negative");
        }
        if (builder.hpLevel < 0) {
            throw new IllegalArgumentException("HP level cannot be negative");
        }
        if (builder.epBase < 0) {
            throw new IllegalArgumentException("EP base cannot be negative");
        }
        if (builder.epLevel < 0) {
            throw new IllegalArgumentException("EP level cannot be negative");
        }
        if (builder.sanBase < 0) {
            throw new IllegalArgumentException("SAN base cannot be negative");
        }
        if (builder.sanLevel < 0) {
            throw new IllegalArgumentException("SAN level cannot be negative");
        }

        this.hpBase = builder.hpBase;
        this.hpLevel = builder.hpLevel;
        this.epBase = builder.epBase;
        this.epLevel = builder.epLevel;
        this.sanBase = builder.sanBase;
        this.sanLevel = builder.sanLevel;
    }

    public static class Builder {
        private int hpBase;
        private int hpLevel;
        private int epBase;
        private int epLevel;
        private int sanBase;
        private int sanLevel;

        public Builder hpBase(int hpBase) {
            this.hpBase = hpBase;
            return this;
        }

        public Builder hpLevel(int hpLevel) {
            this.hpLevel = hpLevel;
            return this;
        }

        public Builder epBase(int epBase) {
            this.epBase = epBase;
            return this;
        }

        public Builder epLevel(int epLevel) {
            this.epLevel = epLevel;
            return this;
        }

        public Builder sanBase(int sanBase) {
            this.sanBase = sanBase;
            return this;
        }

        public Builder sanLevel(int sanLevel) {
            this.sanLevel = sanLevel;
            return this;
        }

        public BaseStatus build() {
            return new BaseStatus(this);
        }
    }

    @Override
    public String toString() {
        return "BaseStatus{" +
                "hpBase=" + hpBase +
                ", hpLevel=" + hpLevel +
                ", epBase=" + epBase +
                ", epLevel=" + epLevel +
                ", sanBase=" + sanBase +
                ", sanLevel=" + sanLevel +
                '}';
    }

    public int getHpBase() {
        return hpBase;
    }

    public int getHpLevel() {
        return hpLevel;
    }

    public int getEpBase() {
        return epBase;
    }

    public int getEpLevel() {
        return epLevel;
    }

    public int getSanBase() {
        return sanBase;
    }

    public int getSanLevel() {
        return sanLevel;
    }
}
