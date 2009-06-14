/*
 * SatPropertyPanel.java
 * =====================================================================
 * Copyright (C) 2008 Shawn E. Gano
 * 
 * This file is part of JSatTrak.
 * 
 * JSatTrak is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * JSatTrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with JSatTrak.  If not, see <http://www.gnu.org/licenses/>.
 * =====================================================================
 *
 * Created on August 3, 2007, 11:29 PM
 *
 * // 13 June 2009 -- change name in table from Longitude of the Asc Node [deg] to RAAN (checked out in STK)
 */

package jsattrak.gui;

import javax.swing.table.DefaultTableModel;
import jsattrak.objects.AbstractSatellite;

/**
 *
 * @author  sgano
 */
public class SatPropertyPanel extends javax.swing.JPanel implements java.io.Serializable
{
    
    // satellite prop object used to derive all the properties
    AbstractSatellite satProp;
    
    // table model
    DefaultTableModel propTableModel;
    
    /** Creates new form SatPropertyPanel -- bean */
    public SatPropertyPanel()
    {
        initComponents();
        
//        // will not work correctly
//        satProp = new SatelliteTleSGP4("Test","1","2");
//
//        // get table model
//        propTableModel = (DefaultTableModel)satPropertyTable.getModel();
        
    }
    
    // normal constructor - pass in a SatelliteProps object
    public SatPropertyPanel(AbstractSatellite satProp)
    {
        initComponents();
        this.satProp = satProp;
        
        // get table model
        propTableModel = (DefaultTableModel)satPropertyTable.getModel(); 
        
        // set renderer
         // set cell renderer
        try
        {
            
            // everything is a string in this table
            satPropertyTable.setDefaultRenderer(Class.forName( "java.lang.String" ),new BoldFontCellRenderer());
            
//        TableColumnModel tcm = satPropertyTable.getColumnModel();
//        TableColumn tc = tcm.getColumn(1);
//        tc.setCellRenderer( new BoldFontCellRenderer() );
//
//        System.out.println("here!");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        satPropertyTable = new javax.swing.JTable();

        satPropertyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"TLE Age", null},
                {"Latitude [deg]", null},
                {"Longitude [deg]", null},
                {"Altitude [m]", null},
                {"Position (J2000):", null},
                {"x [m]", null},
                {"y [m]", null},
                {"z [m]", null},
                {"Velocity (J2000)", null},
                {"dx/dt [m/s]", null},
                {"dy/dt [m/s]", null},
                {"dz/dt [m/s]", null},
                {"Keplarian Elem. (Osc)", null},
                {"Semimajor axis (a) [m]", null},
                {"Eccentricity (e) ", null},
                {"Inclination (i) [deg]", null},
                {"RAAN [deg]", null},
                {"Argument of pericenter [deg]", null},
                {"Mean anomaly [M] [deg]", null}
            },
            new String [] {
                "Property", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(satPropertyTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable satPropertyTable;
    // End of variables declaration//GEN-END:variables
    
    // function used to update all the properties in the table
    public void updateProperties()
    {
        // get needed properties that are arrays
        double[] pos = satProp.getJ2000Position();
        double[] vel = satProp.getJ2000Velocity();
                
        // set new values in the table .setValueAt(object, row, column)
        propTableModel.setValueAt(""+satProp.getTleAgeDays(),0,1);  // TLE AGE
        
        propTableModel.setValueAt(""+satProp.getLatitude()*180.0/Math.PI,1,1); // lat
        propTableModel.setValueAt(""+satProp.getLongitude()*180.0/Math.PI,2,1); // long
        propTableModel.setValueAt(""+satProp.getAltitude(),3,1); // alt
        
        propTableModel.setValueAt(""+pos[0],5,1); // x
        propTableModel.setValueAt(""+pos[1],6,1); // y
        propTableModel.setValueAt(""+pos[2],7,1); // z
        
        propTableModel.setValueAt(""+vel[0],9,1); // dx
        propTableModel.setValueAt(""+vel[1],10,1); // dy
        propTableModel.setValueAt(""+vel[2],11,1); // dz
        
        // try for keplarian elements -- might be singular
        try
        {
            double[] kep = satProp.getKeplarianElements();
                    
            propTableModel.setValueAt(""+kep[0],13,1);
            propTableModel.setValueAt(""+kep[1],14,1);
            propTableModel.setValueAt(""+kep[2]*180.0/Math.PI,15,1);
            propTableModel.setValueAt(""+kep[3]*180.0/Math.PI,16,1);
            propTableModel.setValueAt(""+kep[4]*180.0/Math.PI,17,1);
            propTableModel.setValueAt(""+kep[5]*180.0/Math.PI,18,1);
            
        }
        catch(Exception e)
        {
            propTableModel.setValueAt("-",13,1);
            propTableModel.setValueAt("-",14,1);
            propTableModel.setValueAt("-",15,1);
            propTableModel.setValueAt("-",16,1);
            propTableModel.setValueAt("-",17,1);
            propTableModel.setValueAt("-",18,1);
        } // Keplarian Elements
        
        
    } // updateProperties
    
    public String getSatName()
    {
        // return sat name for the sat Properties
        return satProp.getName();
    }
    
    
} // SatPropertyPanel
