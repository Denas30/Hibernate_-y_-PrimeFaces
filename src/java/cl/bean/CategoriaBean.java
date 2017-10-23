/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bean;

import cl.util.HibernateUtil;
import d.pojos.Departamento;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Daniel
 */
@ManagedBean(name = "catBean")
@RequestScoped
public class CategoriaBean {

   private Departamento departamento;
   private List<Departamento> departamentos;
   
    public CategoriaBean() {
       departamento = new Departamento(); 
       departamentos = new ArrayList<>(); 
       CargarDepartamento();//Cuando se iniciael formulario se cargan los objetos de la lista departamentos
        
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
 
    //Este objeto se encarga de llamar en la lista llamada departamentos, los objetos de la base de datos
    private void CargarDepartamento (){
    SessionFactory se = HibernateUtil.getSessionFactory();
    Session agre = se.openSession();
    departamentos = agre.createQuery("from Departamento").list();
    
    
    }
   
    public List<Departamento> getCategorias(){
    
return departamentos;
        
    }
    public String guardar(){
        
        SessionFactory se = HibernateUtil.getSessionFactory();
    Session agre = se.openSession();
    Transaction tx = agre.beginTransaction();
    Departamento te = new Departamento(departamento.getNombre());
    agre.saveOrUpdate(te);
    tx.commit();
    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Departamento registrado con exito");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    departamento.setNombre("");
    CargarDepartamento(); //cuando se ingresa una nuevo departamento es necesario, recargar la lista del departamento
    return "index";
    }
    
    public String eliminar(){
       for(Departamento c: departamentos){
       if(c.isSelected()){
       SessionFactory se = HibernateUtil.getSessionFactory();
       Session gu = se.openSession();
       //Se realiza la busqueda del departamento esto es analogo
       //a realizar la  operacion select * from departamento where id=?
       Departamento te= (Departamento) gu.get(Departamento.class, c.getId());
       gu.delete(te);
       gu.beginTransaction().commit();
       
       }
       
       }
    FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Departamento eliminado con exito");
    FacesContext.getCurrentInstance().addMessage(null, msg);
    CargarDepartamento();//Se vuelven a actualizar la lista de departamento.
        return "index";
    }
    public void eventoFila (RowEditEvent event){
        
        Departamento c = (Departamento) event.getObject(); // cambia de un elemento a otro
        SessionFactory se= HibernateUtil.getSessionFactory();
        Session cam = se.openSession();
        Departamento elegida = (Departamento) cam.get(Departamento.class, c.getId());
        elegida.setNombre(c.getNombre());
        cam.update(elegida);
        cam.beginTransaction().commit();
        FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Departamento actualizado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        CargarDepartamento();// se vuelven a actualizar los datos
    
    }
}
