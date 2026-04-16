package com.softec.data;

import com.softec.entity.Origin;

public class OriginData {
    public static final Origin TI = new Origin.Builder()
            .name("T.I.")
            .description("Programador, engenheiro de software ou simplesmente 'o cara da T.I.', você tem treinamento e experiência para lidar com sistemas informatizados.")
            .addExpertise(ExpertiseData.INVESTIGATION)
            .addExpertise(ExpertiseData.TECHNOLOGY)
            .powerName("Motor de Busca")
            .powerDescription("A critério do Mestre, sempre que tiver acesso a internet, você pode gastar 2 PE para substituir um teste de perícia qualquer por um teste de Tecnologia.")
            .build();

    public static final Origin FIGHTER = new Origin.Builder()
            .name("Fighter")
            .description("Você pratica uma arte marcial ou esporte de luta, ou cresceu em um bairro perigoso onde aprendeu briga de rua.")
            .addExpertise(ExpertiseData.FIGHT)
            .addExpertise(ExpertiseData.REFLEXES)
            .powerName("Mão Pesada")
            .powerDescription("Você recebe +2 em rolagens de dano com ataques corpo a corpo.")
            .build();

    public static final Origin MILITARY = new Origin.Builder()
            .name("Military")
            .description("Você serviu em uma força militar, como o exército ou a marinha.")
            .addExpertise(ExpertiseData.AIM)
            .addExpertise(ExpertiseData.TACTICS)
            .powerName("Para Bellum")
            .powerDescription("Você recebe +2 em rolagens de dano com armas de fogo.")
            .build();
}
