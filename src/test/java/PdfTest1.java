import java.io.IOException;

import de.redsix.pdfcompare.PdfComparator;

public class PdfTest1 {
	
	public static void main(String[] args) throws IOException {
		
		String filePath1 = "C:\\PDFComparison\\src\\main\\resources\\sample.pdf";
		String filePath2 = "C:\\PDFComparison\\src\\main\\resources\\pdf-sample.pdf";
		String resultPath = "C:\\PDFComparison\\src\\main\\resources\\results\\result";
		String ignore = "C:\\PDFComparison\\ignore.confg"; 
				
		
		
		boolean isEquals = new PdfComparator(filePath1, filePath2).withIgnore(ignore).compare().writeTo(resultPath);
		if (!isEquals) {
		    System.out.println("Are PDF files simiar: " + isEquals);
		}
		
	     System.out.println("PDF comparison completed");
	     

		
	}
	
	

}
