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

package io.kamax.vbox4_3.storage;

import io.kamax.hboxd.hypervisor.storage._RawMedium;
import io.kamax.hboxd.hypervisor.storage._RawMediumAttachment;
import io.kamax.hboxd.hypervisor.storage._RawStorageController;
import io.kamax.hboxd.hypervisor.vm._RawVM;
import io.kamax.vbox4_3.vm.VBoxMachine;
import org.virtualbox_4_3.IMediumAttachment;

public final class VBoxMediumAttachment implements _RawMediumAttachment {

   private VBoxMachine machine;
   private VBoxMedium medium;
   private VBoxStorageController controller;
   private long portId;
   private long deviceId;
   private String deviceType;
   private boolean passThrough;

   public VBoxMediumAttachment(String machineUuid, IMediumAttachment medAttach) {
      machine = new VBoxMachine(machineUuid);
      if (medAttach.getMedium() != null) { // can be null for removable devices, see IMediumAttachment::medium
         medium = new VBoxMedium(medAttach.getMedium());
      }
      controller = new VBoxStorageController(machine, medAttach.getController());
      portId = medAttach.getPort();
      deviceId = medAttach.getDevice();
      deviceType = medAttach.getType().toString();
      passThrough = medAttach.getPassthrough();
   }

   @Override
   public _RawVM getMachine() {
      return machine;
   }

   @Override
   public _RawMedium getMedium() {
      return medium;
   }

   @Override
   public _RawStorageController getController() {
      return controller;
   }

   @Override
   public long getPortId() {
      return portId;
   }

   @Override
   public long getDeviceId() {
      return deviceId;
   }

   @Override
   public String getDeviceType() {
      return deviceType;
   }

   @Override
   public boolean isPassThrough() {
      return passThrough;
   }

   @Override
   public boolean hasMedium() {
      return medium != null;
   }

}
