/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view.model;

import hr.algebra.model.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Paulo
 */
public class MovieTable extends AbstractTableModel{

     private List<Movie> movie_list;
     
    /**
     *
     * @param movies
     */
    public MovieTable(List<Movie> movies) {
        this.movie_list = movies;
     }
     private static final String[] COLUMN_NAMES = {
        "Id","Title","Published date","Description","Picture path","Duration","Year"
    };
    
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
     * @return
     */
    @Override
    public int getRowCount() {
        return movie_list.size();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         switch (columnIndex) {
            case 0 -> {
                return movie_list.get(rowIndex).getId();
              }
            case 1 -> {
                return movie_list.get(rowIndex).getTitle();
              }
            case 2 -> {
                return movie_list.get(rowIndex).getPublishedDate().format(Movie.DATE_FORMATTER);
              }
            case 3 -> {
                return movie_list.get(rowIndex).getDescription();
              }
            case 4 -> {
                return movie_list.get(rowIndex).getPicturePath();
              }
            case 5 -> {
                return movie_list.get(rowIndex).getDuration();
              }
            case 6 -> {
                return movie_list.get(rowIndex).getYear();
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
    
    /**
     *
     * @param movies
     */
    public void setMovies(List<Movie> movies) {
        this.movie_list = movies;
        fireTableDataChanged();
    }
}
