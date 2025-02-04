/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.enums;

/**
 *
 * @author Paulo
 */
public enum Users {
    ADMIN,USER;
    
    
    
      public static Users fromString(String value) {
        if (value != null) {
            switch (value.toUpperCase()) {
                 case "ADMIN" -> {
                    return ADMIN;
                }
                case "DEFAULT" -> {
                    return USER;
                }
               
            }
        }
        throw new IllegalArgumentException("Incorrect user: " + value);
    }
}
