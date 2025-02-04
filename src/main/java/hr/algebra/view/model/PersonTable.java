/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paulo
 */
public class PersonTable extends AbstractTableModel{

     private List<Person> people;
    
      private static final String[] COLUMN_NAMES = {
        "Id", 
        "Name",
        "Role"
    };

    /**
     *
     * @param people
     */
    public PersonTable(List<Person> people) {
        this.people = people;
    }

    /**
     *
     * @param people
     */
    public void setPeople(List<Person> people) {
        this.people = people;
        fireTableDataChanged();
    }

    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
         return people.size();
    }

    /**
     *
     * @return
     */
    @Override
    public int getColumnCount() {
      return COLUMN_NAMES.length;
 }

    /**
     *
     * @param index_row
     * @param index_column
     * @return
     */
    @Override
    public Object getValueAt(int index_row, int index_column) {
        
      switch (index_column) {
            case 0 -> {
                return people.get(index_row).getId();
             }
            case 1 -> {
                return people.get(index_row).getName();
             }
            case 2 -> {
                return people.get(index_row).getTitle().name();
             }
            default -> throw new AssertionError();
        }
      
    
    }

    /**
     *
     * @param column
     * @return
     */
    @Override
     public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
     
    /**
     *
     * @param index_column
     * @return
     */
    @Override
    public Class<?> getColumnClass(int index_column) {
        switch (index_column) {
            case 0 -> {
                return Integer.class;
             }
        }
        return super.getColumnClass(index_column);
    }
}
