package alexshulga.view.theme;

import com.doszhan.CustomServiceStub;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelTableThemes extends AbstractTableModel {
    private static final int FIRST_COLUMN = 0;
    private static final int SECOND_COLUMN = 1;
    private static final int THIRD_COLUMN = 2;
    private static final int COLUMN_COUNT = 3;
    private List<CustomServiceStub.Theme> tableData;

    public ModelTableThemes()
    {
        tableData = new ArrayList<>();
    }

    public String getColumnName(int columnIndex)
    {
        switch(columnIndex)
        {
            case FIRST_COLUMN: return "Номер";
            case SECOND_COLUMN: return "Тема";
            case THIRD_COLUMN: return "Содержание";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CustomServiceStub.Theme rows = tableData.get(rowIndex);

        String str = null;

        if(columnIndex == FIRST_COLUMN)
        {
            str = String.valueOf(rows.getId());
        }
        if(columnIndex == SECOND_COLUMN)
        {
            str = rows.getTitle();
        }
        else if(columnIndex == THIRD_COLUMN)
        {
            str = rows.getContent();
        }

        return str;
    }

    public int getRowCount()
    {
        return tableData.size();
    }

    public int getColumnCount()
    {
        return COLUMN_COUNT;
    }

    public void addDate(CustomServiceStub.Theme theme)
    {
        tableData.add(theme);
        fireTableDataChanged();
    }

    public void addNotation(List<CustomServiceStub.Theme> list)
    {
        deleteAllNotations();

        if(list != null) {
            for (int i = 0; i < list.size(); i++) {
                addDate(list.get(i));
            }
        }
    }

    public void deleteAllNotations()
    {
        tableData.clear();
        fireTableDataChanged();
    }
}
