package com.softec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Role {
    private final UUID id;
    private String name;
    private String proficiencies;
    private final List<Expertise> expertises = new ArrayList<>(2);
    private int baseExpertises;
    private UUID baseStatusId;

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
        if (builder.baseStatusId == null) {
            throw new IllegalArgumentException("Role status cannot be null");
        }

        this.id = UUID.randomUUID();
        this.name = builder.name;
        this.proficiencies = builder.proficiencies;
        this.expertises.addAll(builder.expertises);
        this.baseExpertises = builder.baseExpertises;
        this.baseStatusId = builder.baseStatusId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private String name;
        private String proficiencies;
        private final List<Expertise> expertises = new ArrayList<>(2);
        private int baseExpertises;
        private UUID baseStatusId;

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

        public Builder baseStatus(BaseStatus baseStatus) {
            this.baseStatusId = baseStatus.getId();
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
                ", status=" + baseStatusId +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProficiencies() {
        return proficiencies;
    }

    public List<Expertise> getExpertises() {
        return expertises;
    }

    public int getBaseExpertises() {
        return baseExpertises;
    }

    public UUID getBaseStatusId() {
        return baseStatusId;
    }
}
