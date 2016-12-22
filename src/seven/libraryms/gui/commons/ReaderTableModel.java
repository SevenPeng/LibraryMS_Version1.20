package seven.libraryms.gui.commons;

import javax.swing.table.AbstractTableModel;

import seven.libraryms.model.AbstractModel;
import seven.libraryms.model.Reader;

/**
 * @author Seven
 * 	@Data 2016-12-12
 * @version 1.00
 *
 */
public class ReaderTableModel extends AbstractTableModel{
	Reader[] records = null;
	String[] columnNames = null;
	String[] methodNames = null;

	public ReaderTableModel(String[] columnNames, String[] methodNames) {
		this.columnNames = columnNames;
		this.methodNames = methodNames;
	}

	public void setRecords(Reader[] records) {
		this.records = records;
	}

	public Reader getReaderAt(int row) {
		return records[row];
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return columnNames == null ? 0 : columnNames.length;
	}

	@Override
	public int getRowCount() {
		return records == null ? 0 : records.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Reader record = records[row];
		try {
			return record.getFieldValue(record.getClass(), methodNames[col]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
