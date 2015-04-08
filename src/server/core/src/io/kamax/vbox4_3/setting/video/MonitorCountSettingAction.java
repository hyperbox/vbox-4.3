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

package io.kamax.vbox4_3.setting.video;

import org.altherian.hbox.constant.MachineAttribute;
import org.altherian.setting.PositiveNumberSetting;
import org.altherian.setting._Setting;
import io.kamax.vbox.settings.video.MonitorCountSetting;
import io.kamax.vbox4_3.setting._MachineSettingAction;
import org.virtualbox_4_3.IMachine;
import org.virtualbox_4_3.LockType;

public class MonitorCountSettingAction implements _MachineSettingAction {

   @Override
   public LockType getLockType() {
      return LockType.Write;
   }

   @Override
   public String getSettingName() {
      return MachineAttribute.MonitorCount.toString();
   }

   @Override
   public void set(IMachine machine, _Setting setting) {
      machine.setMonitorCount(((PositiveNumberSetting) setting).getValue());
   }

   @Override
   public _Setting get(IMachine machine) {
      return new MonitorCountSetting(machine.getMonitorCount());
   }

}
