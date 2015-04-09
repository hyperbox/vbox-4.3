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

package io.kamax.vbox4_3.setting.network;

import io.kamax.hbox.constant.NetworkInterfaceAttribute;
import io.kamax.setting.BooleanSetting;
import io.kamax.setting._Setting;
import io.kamax.vbox.settings.network.NicEnabledSetting;
import io.kamax.vbox4_3.setting._NetworkInterfaceSettingAction;
import org.virtualbox_4_3.INetworkAdapter;
import org.virtualbox_4_3.LockType;

public class NicEnabledSettingAction implements _NetworkInterfaceSettingAction {

   @Override
   public LockType getLockType() {
      return LockType.Write;
   }

   @Override
   public String getSettingName() {
      return NetworkInterfaceAttribute.Enabled.toString();
   }

   @Override
   public void set(INetworkAdapter nic, _Setting setting) {
      nic.setEnabled(((BooleanSetting) setting).getValue());
   }

   @Override
   public _Setting get(INetworkAdapter nic) {
      return new NicEnabledSetting(nic.getEnabled());
   }

}
