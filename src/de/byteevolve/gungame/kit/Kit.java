package de.byteevolve.gungame.kit;

import de.byteevolve.gungame.kit.kits.*;

public enum Kit {
    LEVEL_0(0, new Level_0()),
    LEVEL_1(1, new Level_1()),
    LEVEL_2(2, new Level_2()),
    LEVEL_3(3, new Level_3()),
    LEVEL_4(4, new Level_4()),
    LEVEL_5(5, new Level_5()),
    LEVEL_6(6, new Level_6()),
    LEVEL_7(7, new Level_7()),
    LEVEL_8(8, new Level_8()),
    LEVEL_9(9, new Level_9()),
    LEVEL_10(10, new Level_10()),

    LEVEL_11(11, new Level_11()),
    LEVEL_12(12, new Level_12()),
    LEVEL_13(13, new Level_13()),
    LEVEL_14(14, new Level_14()),
    LEVEL_15(15, new Level_15()),
    LEVEL_16(16, new Level_16()),
    LEVEL_17(17, new Level_17()),
    LEVEL_18(18, new Level_18()),
    LEVEL_19(19, new Level_19()),
    LEVEL_20(20, new Level_20()),
    LEVEL_21(21, new Level_21()),

    LEVEL_22(22, new Level_22()),
    LEVEL_23(23, new Level_23()),
    LEVEL_24(24, new Level_24()),
    LEVEL_25(25, new Level_25()),
    LEVEL_26(26, new Level_26()),
    LEVEL_27(27, new Level_27()),
    LEVEL_28(28, new Level_28()),
    LEVEL_29(29, new Level_29()),
    LEVEL_30(30, new Level_30()),
    LEVEL_31(31, new Level_31()),
    LEVEL_32(32, new Level_32()),

    LEVEL_33(33, new Level_33()),
    LEVEL_34(34, new Level_34()),
    LEVEL_35(35, new Level_35()),
    LEVEL_36(36, new Level_36()),
    LEVEL_37(37, new Level_37()),
    LEVEL_38(38, new Level_38()),
    LEVEL_39(39, new Level_39()),
    LEVEL_40(40, new Level_40()),
    LEVEL_41(41, new Level_41()),
    LEVEL_42(42, new Level_42()),
    LEVEL_43(43, new Level_43()),
    LEVEL_44(44, new Level_44()),
    LEVEL_45(45, new Level_45()),
    LEVEL_46(46, new Level_46()),
    LEVEL_47(47, new Level_47()),
    LEVEL_48(48, new Level_48()),
    LEVEL_49(49, new Level_49()),
    LEVEL_50(50, new Level_50()),
    LEVEL_51(51, new Level_51()),
    LEVEL_52(52, new Level_52()),
    LEVEL_53(53, new Level_53()),
    LEVEL_54(54, new Level_54()),
    LEVEL_55(55, new Level_55()),
    LEVEL_56(56, new Level_56()),
    LEVEL_57(57, new Level_57()),
    LEVEL_58(58, new Level_58()),
    LEVEL_59(59, new Level_59()),
    LEVEL_60(60, new Level_60());

    private int id;
    private KitInventory kitInventory;

    public int getId() {
        return id;
    }

    public KitInventory getKitInventory() {
        return kitInventory;
    }

    Kit(int id, KitInventory kitInventory) {
        this.kitInventory = kitInventory;
        this.id = id;
    }

}
