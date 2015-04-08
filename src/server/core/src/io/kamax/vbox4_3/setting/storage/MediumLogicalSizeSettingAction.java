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

package io.kamax.vbox4_3.setting.storage;

import org.altherian.hbox.constant.MediumAttribute;
import org.altherian.hbox.exception.HyperboxException;
import org.altherian.setting.PositiveNumberSetting;
import org.altherian.setting._Setting;
import io.kamax.vbox4_3.setting._MediumSettingAction;
import org.virtualbox_4_3.IMedium;

public class MediumLogicalSizeSettingAction implements _MediumSettingAction {

   @Override
   public String getSettingName() {
      return MediumAttribute.LogicalSize.toString();
   }

   @Override
   public void set(IMedium medium, _Setting setting) {
      throw new HyperboxException("Read-only setting");
   }

   @Override
   public _Setting get(IMedium medium) {
      return new PositiveNumberSetting(MediumAttribute.LogicalSize, medium.getLogicalSize());
   }

}
