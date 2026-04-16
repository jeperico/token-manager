package com.softec.entity;

import java.util.ArrayList;
import java.util.List;

public class Token {
    private String name;
    private int nex;
    private final List<Expertise> expertises = new ArrayList<>(5);
    private Origin origin;

    public Token(Builder builder) {
        if (builder.name == null || builder.name.isEmpty()) {
            throw new IllegalArgumentException("Token name cannot be null or empty");
        }
        if (builder.nex < 0 || builder.nex > 99) {
            throw new IllegalArgumentException("Token nex must be 0 <> 99");
        }
        if (builder.expertises.isEmpty()) {
            throw new IllegalArgumentException("Token expertises cannot be empty");
        }
        if (builder.origin.getName().isEmpty()) {
            throw new IllegalArgumentException("Token origin name cannot be empty");
        }

        this.name = builder.name;
        this.nex = builder.nex;
        this.origin = builder.origin;
        this.expertises.addAll(builder.expertises);
    }

    public static class Builder {
        private String name;
        private int nex;
        private final List<Expertise> expertises = new ArrayList<>(5);
        private Origin origin;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder nex(int nex) {
            this.nex = nex;
            return this;
        }

        public Builder addExpertise(Expertise expertise) {
            this.expertises.add(expertise);
            return this;
        }

        public Builder origin(Origin origin) {
            this.origin = origin;
            this.expertises.addAll(origin.getExpertises());
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }

    @Override
    public String toString() {
        return "Token{" +
                "name='" + name + '\'' +
                ", nex=" + nex +
                ", expertises=" + expertises +
                ", origin=" + origin +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNex() {
        return nex;
    }

    public void setNex(int nex) {
        this.nex = nex;
    }

    public List<Expertise> getExpertises() {
        return expertises;
    }

    public void addExpertise(Expertise expertise) {
        this.expertises.add(expertise);
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
}
