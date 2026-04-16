package com.softec.data;

import com.softec.entity.Role;

public class RoleData {
    public static final Role COMBATANT = new Role.Builder()
            .name("Combatant")
            .proficiencies("Simple weapons, tactical weapons, lightweight protection")
            .addExpertise(ExpertiseData.FIGHT)
            .addExpertise(ExpertiseData.AIM)
            .baseExpertises(3)
            .status(BaseStatusData.COMBATANT)
            .build();

    public static final Role OCCULTIST = new Role.Builder()
            .name("Occultist")
            .proficiencies("Simple weapons")
            .addExpertise(ExpertiseData.WILL)
            .addExpertise(ExpertiseData.OCCULTISM)
            .baseExpertises(4)
            .status(BaseStatusData.OCCULTIST)
            .build();

    public static final Role SPECIALIST = new Role.Builder()
            .name("Specialist")
            .proficiencies("Simple weapons, lightweight protection")
            .addExpertise(ExpertiseData.INVESTIGATION)
            .addExpertise(ExpertiseData.TECHNOLOGY)
            .baseExpertises(5)
            .status(BaseStatusData.SPECIALIST)
            .build();
}
