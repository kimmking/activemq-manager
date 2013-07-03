package org.kimmking.am.agent.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String zipfile = args[0];
		String outDir = args[1];

		long startTime = System.currentTimeMillis();
		try {
			ZipInputStream Zin = new ZipInputStream(new FileInputStream(zipfile));
			BufferedInputStream Bin = new BufferedInputStream(Zin);

			File Fout = null;
			ZipEntry entry;
			try {
				while ((entry = Zin.getNextEntry()) != null) {
					Fout = new File(outDir, entry.getName());
					if (!Fout.exists()) {
						if( entry.isDirectory()){
							Fout.mkdirs();
							continue;
						}else{
							(new File(Fout.getParent())).mkdirs();
						}
					}
					FileOutputStream out = new FileOutputStream(Fout);
					BufferedOutputStream Bout = new BufferedOutputStream(out);
					int b;
					while ((b = Bin.read()) != -1) {
						Bout.write(b);
					}
					Bout.close();
					out.close();
					System.out.println(Fout + " unzip.");
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("total zip use " + (endTime - startTime) + " ms");

	}

}
