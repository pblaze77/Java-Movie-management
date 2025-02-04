/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.enums;

/**
 *
 * @author Paulo
 */


/**
 *
 * @author Paulo
 */
public enum Titles {
    DIRECTOR,ACTOR;
    
     public static Titles fromString(String value) {
        if (value != null) {
            switch (value.toUpperCase()) {
                 case "DIRECTOR" -> {
                    return DIRECTOR;
                }
                case "ACTOR" -> {
                    return ACTOR;
                }
               
            }
        }
        throw new IllegalArgumentException("Incorrect title: " + value);
    }
    
}
