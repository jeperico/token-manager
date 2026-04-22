package com.softec;

import com.softec.data.ExpertiseData;
import com.softec.data.OriginData;
import com.softec.data.RoleData;
import com.softec.model.Token;

public class Main {
    public static void main(String[] args) {
        Token pc1 = new Token.Builder()
                .name("Afonso")
                .nex(15)
                .origin(OriginData.FIGHTER)
                .role(RoleData.COMBATANT)
                .addExpertise(ExpertiseData.REFLEXES)
                .build();

        Token pc2 = new Token.Builder()
                .name("Shin")
                .nex(35)
                .origin(OriginData.MILITARY)
                .role(RoleData.OCCULTIST)
                .addExpertise(ExpertiseData.INVESTIGATION)
                .addExpertise(ExpertiseData.TACTICS)
                .addExpertise(ExpertiseData.WILL)
                .build();

        Token pc3 = new Token.Builder()
                .name("Kira")
                .nex(25)
                .origin(OriginData.TI)
                .role(RoleData.SPECIALIST)
                .addExpertise(ExpertiseData.CRIME)
                .build();

        System.out.println(pc1);
        System.out.println(pc2);
        System.out.println(pc3);
    }
}
