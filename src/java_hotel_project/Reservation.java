/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_hotel_project;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author USER
 */
public class Reservation {
    
    //In the reservation table we need to add two foreign keys:
    // 1 for the client
    //alter table reservations ADD CONSTRAINT fk_client_id FOREIGN KEY(client_id) REFERENCES clients(id) on DELETE CASCADE

    // 2 for the room
    //alter table reservations ADD CONSTRAINT fk_room_number FOREIGN KEY(room_number) REFERENCES rooms(r_number) on DELETE CASCADE

    // add another foreign key between table types and rooms
    //ALTER TABLE rooms ADD CONSTRAINT fk_type_id FOREIGN KEY (type) REFERENCES type(id) on DELETE CASCADE
    
    
    My_Connection myconnection = new My_Connection();
    
    
     //create a function to add a new reservation
          public boolean addReservation(int client_id,int room_number, String dateIn, String dateOut)
    {
        PreparedStatement st;
        String addQuery = "Insert into `reservations` (`client_id`,`room_number`,`date_in`,`date_out`) values (?,?,?,?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, client_id);
            st.setInt(2, room_number);
            st.setString(3, dateIn);
            st.setString(4, dateOut);
           
          
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
          
                  //create a function to edit the selected reservations
    public boolean editReservation(int id, int client_id, int room_number, String dateIn, String dateOut)
    {
        PreparedStatement st;
        String UpdateQuery = "Update reservations set `client_id`=?, `room_number`=? , `date_in`=? , `date_out`=? where `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(UpdateQuery);
            
          
            st.setInt(1,client_id);
            st.setInt(2, room_number);
            st.setString(3, dateIn);
            st.setString(4, dateOut);
            st.setInt(5, id);
          
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
    
    //create a function to remove the selected reservations
    
    public boolean removeReservation(int reservation_id)
    {
        PreparedStatement st;
        ResultSet rs;
        String deleteQuery = "Delete from reservation where `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            st.setInt(1,reservation_id);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
    
     // create a function to display all rooms in jtable
      public void fillReservationsJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery= "Select * from reservations";
        
        try {
            ps = myconnection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel =(DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getInt(2);
                row[2] = rs.getInt(3); 
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
}
