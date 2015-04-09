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

package io.kamax.vbox4_3.setting.cpu;

import io.kamax.hbox.constant.MachineAttribute;
import io.kamax.setting.PositiveNumberSetting;
import io.kamax.setting._Setting;
import io.kamax.vbox.settings.cpu.CpuExecCapSetting;
import io.kamax.vbox4_3.setting._MachineSettingAction;
import org.virtualbox_4_3.IMachine;
import org.virtualbox_4_3.LockType;

public class CpuExecCapSettingAction implements _MachineSettingAction {

   @Override
   public LockType getLockType() {
      return LockType.Shared;
   }

   @Override
   public String getSettingName() {
      return MachineAttribute.CpuExecCap.toString();
   }

   @Override
   public void set(IMachine machine, _Setting setting) {
      machine.setCPUExecutionCap(((PositiveNumberSetting) setting).getValue());
   }

   @Override
   public _Setting get(IMachine machine) {
      return new CpuExecCapSetting(machine.getCPUExecutionCap());
   }

}
