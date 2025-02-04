/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Paulo
 */
public class DateAdapter extends XmlAdapter<String, LocalDateTime> {
    
      @Override
    public LocalDateTime unmarshal(String tito) throws Exception {
        return LocalDateTime.parse(tito, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
      @Override
    public String marshal(LocalDateTime tito) throws Exception {
        return tito.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    

  
}
