/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author Paulo
 */
public class PersonTransferable implements Transferable{

    /**
     *
     */
    public static DataFlavor PERSON_FLAVOUR = new DataFlavor(Person.class, "person");
   private  Person person;
    private static final DataFlavor[] SUPPORTED_FLAVOURS = {PERSON_FLAVOUR};

    public PersonTransferable() {
    }
   
    
     public PersonTransferable(Person person) {
        this.person = person;
    }
  
    @Override
    public DataFlavor[] getTransferDataFlavors() {
   return SUPPORTED_FLAVOURS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
     return PERSON_FLAVOUR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
         if (isDataFlavorSupported(flavor)) {
            return person;
        }
        throw new UnsupportedFlavorException(flavor);
    }
    
}
