package com.softec;

import com.softec.entity.Attribute;
import com.softec.entity.Expertise;
import com.softec.entity.Origin;
import com.softec.entity.Token;

public class Main {
    public static void main(String[] args) {
        // [CREATE ATTRIBUTES]
        Attribute agiAttribute = new Attribute.Builder()
                .name("Agility")
                .shortName("AGI")
                .description("Agility attr!")
                .build();

        Attribute intAttribute = new Attribute.Builder()
                .name("Intellect")
                .shortName("INT")
                .description("Intellect attr!")
                .build();

        Attribute preAttribute = new Attribute.Builder()
                .name("Presence")
                .shortName("PRE")
                .description("Presence attr!")
                .build();

        Attribute strAttribute = new Attribute.Builder()
                .name("Strength")
                .shortName("STR")
                .description("Strength attr!")
                .build();

        Attribute vigAttribute = new Attribute.Builder()
                .name("Vigor")
                .shortName("VIG")
                .description("Vigor attr!")
                .build();

        // [CREATE EXPERTISES]
        Expertise wilExpertise = new Expertise.Builder()
                .name("Willing")
                .baseAttribute(preAttribute)
                .build();

        Expertise invExpertise = new Expertise.Builder()
                .name("Investigation")
                .baseAttribute(intAttribute)
                .build();

        Expertise criExpertise = new Expertise.Builder()
                .name("Crime")
                .baseAttribute(agiAttribute)
                .chargePenalty()
                .trainedOnly()
                .kitNeeded()
                .build();

        Expertise figExpertise = new Expertise.Builder()
                .name("Fight")
                .baseAttribute(strAttribute)
                .build();

        Expertise tecExpertise = new Expertise.Builder()
                .name("Technology")
                .baseAttribute(intAttribute)
                .trainedOnly()
                .kitNeeded()
                .build();

        Expertise refExpertise = new Expertise.Builder()
                .name("Reflection")
                .baseAttribute(agiAttribute)
                .build();

        Expertise aimExpertise = new Expertise.Builder()
                .name("Aim")
                .baseAttribute(agiAttribute)
                .build();

        Expertise tatExpertise = new Expertise.Builder()
                .name("Tatics")
                .baseAttribute(intAttribute)
                .trainedOnly()
                .build();


        // [CREATE ORIGINS]
        Origin tiOrigin = new Origin.Builder()
                .name("T.I.")
                .description("Programador, engenheiro de software ou simplesmente “o cara da T.I.”, você tem treinamento e experiência para lidar com sistemas informatizados.")
                .addExpertise(invExpertise)
                .addExpertise(tecExpertise)
                .powerName("Motor de Busca")
                .powerDescription("A critério do Mestre, sempre que tiver acesso a internet, você pode gastar 2 PE para substituir um teste de perícia qualquer por um teste de Tecnologia.")
                .build();

        Origin figOrigin = new Origin.Builder()
                .name("Fighter")
                .description("Você pratica uma arte marcial ou esporte de luta, ou cresceu em um bairro perigoso onde aprendeu briga de rua.")
                .addExpertise(figExpertise)
                .addExpertise(refExpertise)
                .powerName("Mão Pesada")
                .powerDescription("Você recebe +2 em rolagens de dano com ataques corpo a corpo.")
                .build();

        Origin milOrigin = new Origin.Builder()
                .name("Military")
                .description("Você serviu em uma força militar, como o exército ou a marinha.")
                .addExpertise(aimExpertise)
                .addExpertise(tatExpertise)
                .powerName("Para Bellum")
                .powerDescription("Você recebe +2 em rolagens de dano com armas de fogo.")
                .build();

        // [CREATE TOKENS]
        Token pc1 = new Token.Builder()
                .name("Afonso")
                .nex(15)
                .origin(figOrigin)
                .addExpertise(refExpertise)
                .build();

        Token pc2 = new Token.Builder()
                .name("Shin")
                .nex(35)
                .origin(milOrigin)
                .addExpertise(invExpertise)
                .addExpertise(tatExpertise)
                .addExpertise(wilExpertise)
                .build();

        System.out.println(pc1.toString());
        System.out.println(pc2.toString());
    }
}