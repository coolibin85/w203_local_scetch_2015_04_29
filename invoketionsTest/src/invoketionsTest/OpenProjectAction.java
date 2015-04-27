package invoketionsTest;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenProjectAction extends AbstractAction {

	JFrame mnFrm;
	byte[] buffer = new byte[1];//new byte[458752];
	
	public OpenProjectAction(JFrame mnFrm) {
		super("Open Project");
		this.mnFrm = mnFrm;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
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
	
	private class TextRecognizer {
		public byte[] fileData = null;
		
		private TextRecognizer() {
	
		}
		
		public TextRecognizer(byte[] fd) {
			fileData = fd;
		}
		
		public int findText() {
			for(int i = 0; i < fileData.length; i++) {
				
			}
		}
		
		private int findHead(int bgnAdr) {
			for(int i = bgnAdr, cnt = 0; i < fileData.length; i++) {
				if(fileData[i] == cnt) {
					cnt++;
					if(cnt == 5){
						return i;
					}
				} else {
					cnt = 0;
				}
			}
			
			return -1;
		}
	}
}
