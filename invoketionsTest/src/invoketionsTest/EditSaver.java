package invoketionsTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditSaver {
	JFrame mnFrm;
	LocalizationProject localPrj;
	byte[] buffer = new byte[1];
	
	private EditSaver() {
	
	}
	
	public EditSaver(LocalizationProject localPrj) {
		this.localPrj = localPrj;
	}
	
	public void save() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "mercedes dashboard dumps", "bin");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(mnFrm);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			// Should be 448 kbyte size
			// .bin ext
//			 System.out.println("You chose to open this file: " +
//					 file.getName());
			
			
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				
				int fileLen = (int)file.length();
				if(buffer.length != fileLen){
					buffer = new byte[fileLen];
				}

				int bytesRead = fis.read(buffer);
				if(bytesRead != fileLen) {
					JOptionPane.showMessageDialog(mnFrm, "Ошибка! Не удалось прочитать данные!");
				}
			}	
			catch(FileNotFoundException e) {
				JOptionPane.showMessageDialog(mnFrm, "Exception Error: File not found! " + e);
			}
			catch(IOException ioe) {
				JOptionPane.showMessageDialog(mnFrm, "Ошибка! В процессе чтения файла произошел сбой! " + ioe);
			}
			finally {
				try {
					if(fis != null) {
						fis.close();
					}
				}
				catch(IOException ioe) {
					JOptionPane.showMessageDialog(mnFrm, "Ошибка при чтении файла!" + ioe);
				}
			}
		}
	}

}
