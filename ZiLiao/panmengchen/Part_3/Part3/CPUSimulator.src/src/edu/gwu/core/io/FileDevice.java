package edu.gwu.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.gwu.core.basic.Word;
import edu.gwu.exception.IllegalDeviceStatusException;

public class FileDevice extends IODevice {

	public static final int IOTYPE_INPUT = 0;
	public static final int IOTYPE_OUTPUT = 1;
	
	private boolean useByteAs18BitWord = true;
	private File file;
	private String defaultFileName;
	private FileInputStream fin;
	private FileOutputStream fout;
	private int ioType;

	public boolean isInputFile(){
		return ioType == IOTYPE_INPUT;
	}
	
	public FileDevice(int devid, int type) {
		this.devid = devid;
		this.ioType = type;
	}

	public FileDevice(int devid, int type,boolean useByteAs18BitWord) {
		this.devid = devid;
		this.ioType = type;
		this.useByteAs18BitWord = useByteAs18BitWord;
	}

	public void setDefaultFile(String fileName) {
		this.defaultFileName = fileName;
		file = new File(this.defaultFileName);
		try {
			reset();
		} catch (IllegalDeviceStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setFile(File file) {
		this.file = file;
		try {
			reset();
		} catch (IllegalDeviceStatusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File getFile(){
		return file;
	}

	@Override
	public void reset() throws IllegalDeviceStatusException {
		try {

			if(ioType == IOTYPE_INPUT){
				// close input stream
				if (fin != null)
					fin.close();
				// initial input stream
				if (file != null) {
					fin = new FileInputStream(file);
				}
				
			}else{
				// close output stream
				if (fout != null)
					fout.close();

				// initial input and output stream
				if (file != null) {
					if (!file.exists()) {
						file.createNewFile();
					}
					fout = new FileOutputStream(file);
				}
			}
			
			this.setChanged();
			this.notifyObservers();
			
		} catch (IOException e) {
			throw new IllegalDeviceStatusException("");
		}
	}

	@Override
	public void write(Word value) throws IllegalDeviceStatusException {
		try {
			if(this.useByteAs18BitWord){
				fout.write(value.intValue());
				
				fout.flush();
			}else{
				//TODO
			}
		} catch (IOException e) {
			throw new IllegalDeviceStatusException("Cannot read from Device("+devid+")");
		} finally{
			
			this.setChanged();
			this.notifyObservers();
		}
	}

	@Override
	public Word read() throws IllegalDeviceStatusException {
		
		try {
			if(this.useByteAs18BitWord){
				int data = fin.read();
				if(data<0)
					data = 0;
				
				return new Word(data);
			}else{
				return null;
			}
		} catch (IOException e) {
			throw new IllegalDeviceStatusException("Cannot read from Device("+devid+")");
		} finally{
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	public int getAvailableSize(){
		if(fin==null)
			return 0;
		try {
			return fin.available();
		} catch (IOException e) {
			return 0;
		}
	}
}
