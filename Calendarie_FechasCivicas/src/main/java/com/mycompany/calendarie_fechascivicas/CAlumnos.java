/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calendarie_fechascivicas;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Usuario
 */
public class CAlumnos {
    
    int codigo;
    String nombreAlumno;
    String ApellidosAlumno;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidosAlumno() {
        return ApellidosAlumno;
    }

    public void setApellidosAlumno(String ApellidosAlumno) {
        this.ApellidosAlumno = ApellidosAlumno;
    }
    
    public void InsertarAlumno(JTextField paramNombre, JTextField paramApellido){
        setNombreAlumno(paramNombre.getText());
        setApellidosAlumno(paramApellido.getText());// incorporados para resivirlo de la interfaz q se manekjan de nuetsros controles        
        CConexion objetoConexion = new CConexion();
        String consulta = "insert into Alumnos (nombres, apellidos) values (?,?);";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());  // con el set incoroporo el valor y con el get obtengo el valor
            cs.setString(2, getApellidosAlumno());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se inserto correctamente ala Persona");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno error " + e.toString());
        }
    }
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        CConexion objetoConeccion = new CConexion(); // crear coeccion un objeto preparacion
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo); //si le hago click en la cabeserapara q se ordena alfabeticamente como un excel
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla); // incorpara el orden de cabesera a :: paramTablaTotalAlumnos
        String sql="";
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "select * from Alumnos";
        
        String[] datos = new String[3]; //llenar depende de la consulta q aya seleccionado[1,2,3,4,5,6]
        Statement st; // preparando para recien ejecutar
        // longitud de 3
        try {
            
            st = objetoConeccion.estableceConexion().createStatement(); // realizando la conexion
            ResultSet rs = st.executeQuery(sql); // ejecutar
            
            while(rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);      
                modelo.addRow(datos);         
            }   
            paramTablaTotalAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros error " + e.toString());
        }
    }
    
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            if (fila>=0){
                paramId.setText(paramTablaAlumnos.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaAlumnos.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaAlumnos.getValueAt(fila, 2).toString());
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada Error");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de Selleccion : Error : " + e.toString());
        }
        
    }
    
    
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombre, JTextField paramApellido){
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumno(paramNombre.getText());
        setApellidosAlumno(paramApellido.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE alumnos.id=?;";        
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidosAlumno());
            cs.setInt(3, getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Modifico , error: " + e.toString());
        }
    }
    
    public void EliminarAlumnos(JTextField paramCodigo){
        setCodigo(Integer.parseInt(paramCodigo.getText())); 
        CConexion objetoConexion = new CConexion();
        String consulta = "DELETE FROM Alumnos WHERE alumnos.id=?;";
        try {
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setInt(1, getCodigo());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se Elimino Correctamente Error : " + e.toString());
        }
        
    }
}
