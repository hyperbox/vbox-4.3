/*
 * Hyperbox - Virtual Infrastructure Manager
 * Copyright (C) 2014 Maxime Dor
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

package io.kamax.vbox4_3.vm.guest;

import io.kamax.hbox.exception.HyperboxException;
import io.kamax.hboxd.hypervisor.vm.guest._RawGuest;
import io.kamax.hboxd.hypervisor.vm.guest._RawGuestNetworkInterface;
import io.kamax.hboxd.hypervisor.vm.guest._RawHypervisorTools;
import io.kamax.tool.AxStrings;
import io.kamax.vbox4_3.VBox;
import io.kamax.vbox4_3.manager.VBoxSessionManager;
import io.kamax.vbox4_3.vm.VBoxMachine;
import java.util.ArrayList;
import java.util.List;
import org.virtualbox_4_3.AdditionsRunLevelType;
import org.virtualbox_4_3.IMachine;
import org.virtualbox_4_3.ISession;
import org.virtualbox_4_3.LockType;
import org.virtualbox_4_3.VBoxException;

public class VBoxGuest implements _RawGuest {

   private String machineUuid;
   private ISession session;

   public VBoxGuest(VBoxMachine vm) {
      machineUuid = vm.getUuid();
   }

   private IMachine getVm() {
      return VBox.get().findMachine(machineUuid);
   }

   private void lockAuto() {
      session = VBoxSessionManager.get().lockAuto(machineUuid, LockType.Shared);
   }

   private void unlockAuto() {
      unlockAuto(false);
   }

   private void unlockAuto(boolean saveSettings) {
      VBoxSessionManager.get().unlockAuto(machineUuid, saveSettings);
      session = null;
   }

   @Override
   public boolean hasHypervisorTools() {
      lockAuto();
      try {
         return !session.getConsole().getGuest().getAdditionsStatus(AdditionsRunLevelType.None);
      } catch (VBoxException e) {
         throw new HyperboxException(e);
      } finally {
         unlockAuto();
      }
   }

   @Override
   public _RawHypervisorTools getHypervisorTools() {
      // TODO Auto-generated method stub
      lockAuto();
      try {
         return null;
      } catch (VBoxException e) {
         throw new HyperboxException(e);
      } finally {
         unlockAuto();
      }
   }

   private int getNicCount() {
      return Integer.parseInt(AxStrings.getNonEmpty(getVm().getGuestPropertyValue("/VirtualBox/GuestInfo/Net/Count"), "0"));
   }

   @Override
   public List<_RawGuestNetworkInterface> listNetworkInterfaces() {
      List<_RawGuestNetworkInterface> list = new ArrayList<_RawGuestNetworkInterface>();
      for (int i = 0; i < getNicCount(); i++) {
         list.add(new VBoxGuestNetworkInterface(machineUuid, i));
      }
      return list;
   }

   @Override
   public _RawGuestNetworkInterface getNetworkInterfaceByMac(String macAddress) {
      macAddress = macAddress.replace(":", "").toUpperCase();
      for (int i = 0; i < getNicCount(); i++) {
         if (getVm().getGuestPropertyValue("/VirtualBox/GuestInfo/Net/" + i + "/MAC").contentEquals(macAddress.toUpperCase())) {
            return new VBoxGuestNetworkInterface(machineUuid, i);
         }
      }
      return null;
   }

}
