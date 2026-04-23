package com.softec.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Token {
    private final UUID id;
    private final String name;
    private final int nex;
    private final List<Expertise> expertises = new ArrayList<>(5);
    private final UUID originId;
    private final UUID roleId;

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

        this.id = UUID.randomUUID();
        this.name = builder.name;
        this.nex = builder.nex;
        this.originId = builder.originId;
        this.roleId = builder.roleId;
        this.expertises.addAll(builder.expertises);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(id, token.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private String name;
        private int nex;
        private final List<Expertise> expertises = new ArrayList<>(5);
        private UUID originId;
        private UUID roleId;

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
            this.originId = origin.getId();
            this.expertises.addAll(origin.getExpertises());
            return this;
        }

        public Builder role(Role role) {
            this.roleId = role.getId();
            return this;
        }

        public Token build() {
            return new Token(this);
        }
    }

    @Override
    public String toString() {
        return "Token{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", nex=" + nex +
                ", expertises=" + expertises +
                ", originId=" + originId +
                ", roleId=" + roleId +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNex() {
        return nex;
    }

    public List<Expertise> getExpertises() {
        return expertises;
    }

    public UUID getOriginId() {
        return originId;
    }

    public UUID getRoleId() {
        return roleId;
    }
}
