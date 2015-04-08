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

package io.kamax.hboxc.gui.vbox4_3;

import net.miginfocom.swing.MigLayout;
import org.altherian.hbox.comm.in.HypervisorIn;
import org.altherian.hbox.comm.io.BooleanSettingIO;
import org.altherian.hbox.comm.io.StringSettingIO;
import org.altherian.hbox.comm.out.hypervisor.HypervisorOut;
import org.altherian.hboxc.front.gui.hypervisor._GlobalConfigureView;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GlobalConfigureView implements _GlobalConfigureView {

   private JLabel machineFolderLabel;
   private JTextField machineFolderValue;
   private JLabel consoleModuleLabel;
   private JTextField consoleModuleValue;
   private JLabel virtExLabel;
   private JCheckBox virtExValue;
   private JPanel panel;

   public GlobalConfigureView() {
      machineFolderLabel = new JLabel("Default Machine Folder");
      consoleModuleLabel = new JLabel("Default Extention Pack");
      virtExLabel = new JLabel("Hardware Virtualization Exclusive");

      machineFolderValue = new JTextField();
      consoleModuleValue = new JTextField();
      virtExValue = new JCheckBox();

      panel = new JPanel(new MigLayout("ins 0"));
      panel.add(machineFolderLabel);
      panel.add(machineFolderValue, "growx, pushx, wrap");
      panel.add(consoleModuleLabel);
      panel.add(consoleModuleValue, "growx, pushx, wrap");
      panel.add(virtExLabel);
      panel.add(virtExValue, "growx, pushx, wrap");
   }

   @Override
   public JComponent getComponent() {
      return panel;
   }

   @Override
   public void update(HypervisorOut hypOut) {
      machineFolderValue.setText(hypOut.getSetting("vbox.global.machineFolder").getString());
      consoleModuleValue.setText(hypOut.getSetting("vbox.global.consoleModule").getString());
      virtExValue.setSelected(hypOut.hasSetting("vbox.global.virtEx") ? hypOut.getSetting("vbox.global.virtEx").getBoolean() : false);
   }

   @Override
   public HypervisorIn getUserInput() {
      HypervisorIn hypIn = new HypervisorIn();
      hypIn.setSetting(new StringSettingIO("vbox.global.machineFolder", machineFolderValue.getText()));
      hypIn.setSetting(new StringSettingIO("vbox.global.consoleModule", consoleModuleValue.getText()));
      hypIn.setSetting(new BooleanSettingIO("vbox.global.virtEx", virtExValue.isSelected()));
      return hypIn;
   }

}
