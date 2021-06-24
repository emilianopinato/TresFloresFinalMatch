package Clases;

import java.awt.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas extends DefaultTableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
                                                   boolean isSelected, 
                                                   boolean hasFocus, 
                                                   int row, 
                                                   int column) {
        String p = String.valueOf(table.getValueAt(row, 5));
        if(!p.isEmpty()){
            Float pendiente = Float.parseFloat(p);
            Float total = (Float) table.getValueAt(row, 4);

            if (Objects.equals(pendiente, total)) {
                setBackground(new Color(255, 161, 161));//rojo
            }else if(pendiente < total && pendiente > 0){
                setBackground(new Color(255, 249, 140));//amarillo
            }else if(pendiente == 0){
                setBackground(new Color(161, 255, 167));//verde
            }
        }else{
            setBackground(Color.WHITE);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
