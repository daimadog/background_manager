package com.background.manager.constants;

public enum DeviceTypeEnum {

    PC(1, "pc", "PC端"),
    APP(2, "app", "APP端"),
    FACTORY(3, "factory", "FACTORY端");

    private final Integer primary;
    private final String device;
    private final String description;

    public DeviceTypeEnum findDeviceByPrimary(Integer primary) {
        DeviceTypeEnum[] values = values();
        DeviceTypeEnum[] var3 = values;
        int var4 = values.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            DeviceTypeEnum value = var3[var5];
            if (primary.equals(value.getPrimary())) {
                return value;
            }
        }

        return null;
    }

    public Integer getPrimary() {
        return this.primary;
    }

    public String getDevice() {
        return this.device;
    }

    public String getDescription() {
        return this.description;
    }

    private DeviceTypeEnum(Integer primary, String device, String description) {
        this.primary = primary;
        this.device = device;
        this.description = description;
    }
}
