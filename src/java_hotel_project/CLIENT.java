/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_hotel_project;

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
public class CLIENT { //client class
    
    My_Connection myconnection = new My_Connection();
    
    
    //create a function to add client
    public boolean addClient(String fname, String lname, String Phone, String email)
    {
        PreparedStatement st;
        ResultSet rs;
        String addQuery = "Insert into clients(`first_name`,`last_name`,`phone`,`email`) values (?,?,?,?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, Phone);
            st.setString(4, email);
            
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
    
    //create a function to edit the selected client
    public boolean editClient(int id, String fname, String lname, String Phone, String email)
    {
        PreparedStatement st;
        ResultSet rs;
        String UpdateQuery = "Update clients set `first_name`=?, `last_name`=? , `phone`=? , `email`=? where `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(UpdateQuery);
            
            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, Phone);
            st.setString(4, email);
            st.setInt(5,id);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    //create a function to remove the selected client
    
    public boolean removeClient(int id)
    {
        PreparedStatement st;
        ResultSet rs;
        String deleteQuery = "Delete from clients where `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            st.setInt(1,id);
            
            return (st.executeUpdate()>0);
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             return false;
        }
    }
    
    //create a function to populate the jtable with all the client in Database

    
    public void fillClientJTable(JTable table)
    {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery= "Select * from clients";
        
        try {
            ps = myconnection.createConnection().prepareStatement(selectQuery);
            rs = ps.executeQuery();
            DefaultTableModel tableModel =(DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
                row = new Object[5];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3); 
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                
                tableModel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
