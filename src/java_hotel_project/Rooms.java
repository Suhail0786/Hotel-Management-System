
package java_hotel_project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Rooms {
    
    My_Connection myconnection = new My_Connection();
    // create a function to display all rooms in jtable
      public void fillRoomsJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery= "Select * from rooms";
        
        try {
            ps = myconnection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel =(DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[4];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getString(3); 
                row[3] = rs.getString(4);
                
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
       // create a function to display all rooms type in jtable
      public void fillRoomsTypeJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery= "Select * from type";
        
        try {
            ps = myconnection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel =(DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[3];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3); 
                
                
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      //create a function to fill combobox with the rooms id
        public void fillRoomsTypeJCombobox(JComboBox combobox)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery= "Select * from type";
        
        try {
            ps = myconnection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                combobox.addItem(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        //create a function to add a new room
          public boolean addRoom(int number,int type, String Phone)
    {
        PreparedStatement st;
        ResultSet rs;
        String addQuery = "Insert into rooms(`r_number`,`type`,`phone`,`reserved`) values (?,?,?,?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, number);
            st.setInt(2, type);
            st.setString(3, Phone);
            // when we add a new room
            // the reserved column will be set to no
            // the reserved column mean i this room is free or not
            st.setString(4, "No");
            
            if(st.executeUpdate() > 0)
            {
                return true;
            }
            else{
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
          
           //create a function to edit the selected rooms
    public boolean editRoom(int number,int type, String Phone, String isReserved)
    {
        PreparedStatement st;
        ResultSet rs;
        String UpdateQuery = "Update rooms set `type`=?, `phone`=? , `reserved`=? where `r_number`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(UpdateQuery);
            
            st.setInt(1,type);
            st.setString(2, Phone);
            st.setString(3,isReserved);
            st.setInt(4,number);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
     //create a function to remove the selected rooms
    
    public boolean removeRoom(int roomNumber)
    {
        PreparedStatement st;
        ResultSet rs;
        String deleteQuery = "Delete from rooms where `r_number`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            st.setInt(1,roomNumber);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
}
