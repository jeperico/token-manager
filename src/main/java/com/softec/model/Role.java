package com.softec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Role {
    private final UUID id;
    private String name;
    private String proficiencies;
    private final List<Expertise> expertises = new ArrayList<>(2);
    private int baseExpertises;
    private BaseStatus status;

    public Role(Builder builder) {
        if (builder.name == null || builder.name.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }
        if (builder.proficiencies == null || builder.proficiencies.isEmpty()) {
            throw new IllegalArgumentException("Role proficiencies cannot be null or empty");
        }
        if (builder.expertises.isEmpty()) {
            throw new IllegalArgumentException("Role expertises cannot be empty");
        }
        if (builder.baseExpertises < 0) {
            throw new IllegalArgumentException("Role baseExpertises cannot be negative");
        }
        if (builder.status == null) {
            throw new IllegalArgumentException("Role status cannot be null");
        }

        this.id = UUID.randomUUID();
        this.name = builder.name;
        this.proficiencies = builder.proficiencies;
        this.expertises.addAll(builder.expertises);
        this.baseExpertises = builder.baseExpertises;
        this.status = builder.status;
    }

    public static class Builder {
        private String name;
        private String proficiencies;
        private final List<Expertise> expertises = new ArrayList<>(2);
        private int baseExpertises;
        private BaseStatus status;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder proficiencies(String proficiencies) {
            this.proficiencies = proficiencies;
            return this;
        }

        public Builder addExpertise(Expertise expertise) {
            this.expertises.add(expertise);
            return this;
        }

        public Builder baseExpertises(int baseExpertises) {
            this.baseExpertises = baseExpertises;
            return this;
        }

        public Builder status(BaseStatus status) {
            this.status = status;
            return this;
        }

        public Role build() {
            return new Role(this);
        }
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", proficiencies='" + proficiencies + '\'' +
                ", expertises=" + expertises +
                ", baseExpertises=" + baseExpertises +
                ", status=" + status +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(String proficiencies) {
        this.proficiencies = proficiencies;
    }

    public List<Expertise> getExpertises() {
        return expertises;
    }

    public void addExpertise(Expertise expertise) {
        this.expertises.add(expertise);
    }

    public int getBaseExpertises() {
        return baseExpertises;
    }

    public void setBaseExpertises(int baseExpertises) {
        this.baseExpertises = baseExpertises;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }
}
