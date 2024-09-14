/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Camilo
 */
public class FormatoFecha {
        public static boolean validarFormato(String[] fecha){
            if (fecha.length  == 2) {
                if(fecha[0].length() != 2 && fecha[1].length() != 2){
                    return false;
                }
            }else{
                if(fecha[0].length() != 4){
                    return false;
                }else if(fecha[1].length() != 2 && fecha[2].length() != 2){
                    return false;
                }
            }
            return true;
        }


        public static int[] formatoFecha(String fecha){
        int[] formato = new int[3];
        formato[0]= Integer.parseInt(fecha.substring(0,4));
        if(fecha.charAt(4) == '0') {
            formato[1] = Integer.parseInt(fecha.substring(5, 6));
        }else{
            formato[1] = Integer.parseInt(fecha.substring(4, 6));
        }
        if(fecha.charAt(6) == '0') {
            formato[2] = Integer.parseInt(fecha.substring(fecha.length()-1));
        }else{
            formato[2] = Integer.parseInt(fecha.substring(6, fecha.length()));
        }
        return formato;
    }
    public static int[] formatoHora(String hora){
        int[] formato = new int [2];
        formato[0] = Integer.parseInt(hora.substring(0, 2));
        formato[1] = Integer.parseInt(hora.substring(2,hora.length()-1));
        return formato;
    }
}
