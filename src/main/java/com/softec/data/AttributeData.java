package com.softec.data;

import com.softec.entity.Attribute;

public class AttributeData {
    public static final Attribute AGILITY = new Attribute.Builder()
            .name("Agility")
            .shortName("AGI")
            .description("Agility attr!")
            .build();

    public static final Attribute INTELLECT = new Attribute.Builder()
            .name("Intellect")
            .shortName("INT")
            .description("Intellect attr!")
            .build();

    public static final Attribute PRESENCE = new Attribute.Builder()
            .name("Presence")
            .shortName("PRE")
            .description("Presence attr!")
            .build();

    public static final Attribute STRENGTH = new Attribute.Builder()
            .name("Strength")
            .shortName("STR")
            .description("Strength attr!")
            .build();

    public static final Attribute VIGOR = new Attribute.Builder()
            .name("Vigor")
            .shortName("VIG")
            .description("Vigor attr!")
            .build();
}
