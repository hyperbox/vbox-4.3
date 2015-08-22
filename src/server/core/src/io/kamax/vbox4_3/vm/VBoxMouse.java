/*
 * Hyperbox - Virtual Infrastructure Manager
 * Copyright (C) 2013 Maxime Dor
 * 
 * http://kamax.io/hbox/
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package io.kamax.vbox4_3.vm;

import io.kamax.hbox.constant.EntityType;
import io.kamax.hbox.constant.MachineAttribute;
import io.kamax.hboxd.hypervisor.vm.device._RawMouse;
import io.kamax.setting.StringSetting;
import io.kamax.setting._Setting;
import io.kamax.vbox.settings.general.MouseModeSetting;
import io.kamax.vbox4_3.manager.VBoxSettingManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VBoxMouse implements _RawMouse {

    private VBoxMachine machine;

    public VBoxMouse(VBoxMachine machine) {
        this.machine = machine;
    }

    @Override
    public String getMouseMode() {
        return ((StringSetting) machine.getSetting(MachineAttribute.MouseMode)).getValue();
    }

    @Override
    public void setMouseMode(String modeId) {
        setSetting(new MouseModeSetting(modeId));
    }

    @Override
    public List<_Setting> listSettings() {
        List<_Setting> settings = new ArrayList<_Setting>();
        for (MachineAttribute setting : MachineAttribute.values()) {
            if (setting.getDeviceType().equals(EntityType.Mouse)) {
                getSetting(setting);
            }
        }
        return settings;
    }

    @Override
    public _Setting getSetting(Object getName) {
        return VBoxSettingManager.get(machine, getName);
    }

    @Override
    public void setSetting(_Setting s) {
        machine.setSetting(Arrays.asList(s));
    }

    @Override
    public void setSetting(List<_Setting> s) {
        machine.setSetting(s);
    }

}
