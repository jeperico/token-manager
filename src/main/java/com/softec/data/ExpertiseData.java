package com.softec.data;

import com.softec.entity.Expertise;

public class ExpertiseData {
    public static final Expertise ACROBATICS = new Expertise.Builder()
            .name("Acrobatics")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise ATHLETICS = new Expertise.Builder()
            .name("Athletics")
            .baseAttribute(AttributeData.STRENGTH)
            .build();

    public static final Expertise CURRENT_AFFAIRS = new Expertise.Builder()
            .name("Current Affairs")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise SCIENCES = new Expertise.Builder()
            .name("Sciences")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise CRIME = new Expertise.Builder()
            .name("Crime")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise DIPLOMACY = new Expertise.Builder()
            .name("Diplomacy")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise DECEPTION = new Expertise.Builder()
            .name("Deception")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise FORTITUDE = new Expertise.Builder()
            .name("Fortitude")
            .baseAttribute(AttributeData.VIGOR)
            .build();

    public static final Expertise STEALTH = new Expertise.Builder()
            .name("Stealth")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise INITIATIVE = new Expertise.Builder()
            .name("Initiative")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise INTIMIDATION = new Expertise.Builder()
            .name("Intimidation")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise INTUITION = new Expertise.Builder()
            .name("Intuition")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise INVESTIGATION = new Expertise.Builder()
            .name("Investigation")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise FIGHT = new Expertise.Builder()
            .name("Fight")
            .baseAttribute(AttributeData.STRENGTH)
            .build();

    public static final Expertise MEDICINE = new Expertise.Builder()
            .name("Medicine")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise OCCULTISM = new Expertise.Builder()
            .name("Occultism")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise PERCEPTION = new Expertise.Builder()
            .name("Perception")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise PILOTING = new Expertise.Builder()
            .name("Piloting")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise AIM = new Expertise.Builder()
            .name("Aim")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise PROFESSION = new Expertise.Builder()
            .name("Profession")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise REFLEXES = new Expertise.Builder()
            .name("Reflexes")
            .baseAttribute(AttributeData.AGILITY)
            .build();

    public static final Expertise RELIGION = new Expertise.Builder()
            .name("Religion")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise SURVIVAL = new Expertise.Builder()
            .name("Survival")
            .baseAttribute(AttributeData.PRESENCE)
            .build();

    public static final Expertise TACTICS = new Expertise.Builder()
            .name("Tactics")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise TECHNOLOGY = new Expertise.Builder()
            .name("Technology")
            .baseAttribute(AttributeData.INTELLECT)
            .build();

    public static final Expertise WILL = new Expertise.Builder()
            .name("Will")
            .baseAttribute(AttributeData.PRESENCE)
            .build();
}
