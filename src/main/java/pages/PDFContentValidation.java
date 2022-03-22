package pages;
import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;

//import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

//import com.pdftopdf. tests. RetrivePdf;

import de.redsix.pdfcompare.PdfComparator;
import static de.redsix.pdfcompare.Utilities.blockingExecutor;

public class PDFContentValidation {
	

		static String filepath = "C:\\PDFComparison\\src\\main\\resources\\sample.pdf"; 
		static String filepath1 = "C:\\PDFComparison\\src\\main\\resources\\pdf-sample.pdf"; 
		String resultfile = "C:\\PDFComparison\\src\\main\\resources\\results\\Result";
		
		String difference1;
		String difference2;
		
		static String textpath = "C:\\PDFComparison\\src\\main\\resources\\New Text Document.txt"; 
		static String textpath1 = "C:\\PDFComparison\\src\\main\\resources\\New Text Document1.txt";
		
	public PDFContentValidation PDFContentValidation () { 
		return this;
	}

	 
		public void PdfStart() throws Exception {
		PDFContentValidation pdfRead = new PDFContentValidation();
		pdfRead.readContentfromPdf (filepath1); 
		pdfRead.PrintContentOfPdf(textpath1, filepath1); 
		pdfRead.readContentfromPdf (filepath); 
		pdfRead.PrintContentOfPdf (textpath, filepath); 
		pdfRead.PdfComparision(); 
		pdfRead.log(difference1);
		 pdfRead.log(difference2); 
		pdfRead.PdfAllignmentCheck();
	}

		public String readContentfromPdf (String filepath2) throws Exception {
		
		File file = new File(filepath2); 
		Thread.sleep(2000); 
		FileInputStream fi = new FileInputStream(file);
		PDDocument doc = null;
		String output = null;
		
		try {
			doc= PDDocument.load(fi);
			//PDDocument doc1 = load(fi);
			output = new PDFTextStripper().getText (doc); 
			System.out.println(output);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return output;
		}
		public void PrintContentOfPdf (String textpath1, String filepath2) throws Exception {
			BufferedWriter out = new BufferedWriter(new FileWriter(textpath1));
			PrintWriter outputfile = new PrintWriter (out); 
			PDFContentValidation pdfRead = new PDFContentValidation(); 
			String output = pdfRead.readContentfromPdf (filepath2);
				/*if(output.contains ("@MDATA")) {
					 //System.out.println("123"); 
					output = output.
				}*/ 
			outputfile.print (output);
			outputfile.close();
		return;
		}
		public void PdfComparision() {
		HashSet <String> al = new HashSet <String> ();
		 HashSet <String> al1 = new HashSet<String>(); 
		HashSet <String> diff1 = new HashSet <String>(); 
		HashSet <String> diff2 = new HashSet <String>(); 
		String str = null; 
		String str2 = null;
		try {
		BufferedReader in = new BufferedReader(new FileReader(textpath)); 
		while ((str = in.readLine()) != null) {
		 		al.add(str);
			}
			 in.close();
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
		try {
		BufferedReader in = new BufferedReader (new FileReader(textpath1));
		 while ((str2 = in.readLine()) != null) { 
			al1.add(str2);
			}
			 in.close();
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		for (String str3 : al) { 
			if (al1.contains (str3)) { 
				diff1.add(str3);
			}
		} 
		for (String str5 : al1) { 
		if (al.contains (str5)) { 
			diff2.add(str5);
			}
		}
		for (String str4 : diff1) {
			difference1 = "Not present in content2/Expected/pdf:  "+str4;
		}
		for (String str4 : diff2) {
			difference2= "Not present in content1/Actual/Coded Sample: "+str4;
		}
		}
		
		public void log (String message) throws IOException {
			
			PrintStream out= new PrintStream(new FileOutputStream ("C:\\PDFComparison\\src\\main\\resources\\Resultfile.txt", true), true);
			// out.print(message); 
			out.println(difference1); 
			out.println(difference2);
			out.close();
			}
		public void PdfAllignmentCheck() throws Exception {
			new PdfComparator(filepath, filepath1).compare().writeTo(resultfile);
			boolean isEquals = new PdfComparator(filepath, filepath1).compare().writeTo(resultfile);
			System.out.println("Are Files Similar: "+isEquals);
			System.out.println("Process completed");
		}
		}