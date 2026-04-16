package com.softec.data;

import com.softec.entity.BaseStatus;

public class BaseStatusData {
    public static final BaseStatus COMBATANT = new BaseStatus.Builder()
            .hpBase(20)
            .hpLevel(4)
            .epBase(2)
            .epLevel(2)
            .sanBase(12)
            .sanLevel(3)
            .build();

    public static final BaseStatus SPECIALIST = new BaseStatus.Builder()
            .hpBase(16)
            .hpLevel(3)
            .epBase(3)
            .epLevel(3)
            .sanBase(16)
            .sanLevel(4)
            .build();

    public static final BaseStatus OCCULTIST = new BaseStatus.Builder()
            .hpBase(12)
            .hpLevel(2)
            .epBase(4)
            .epLevel(4)
            .sanBase(20)
            .sanLevel(5)
            .build();
}
