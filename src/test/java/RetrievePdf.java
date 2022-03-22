import org.testng.annotations.Test;

import pages.PDFContentValidation;

public class RetrievePdf {
	
	@Test
	public void pdfTest() throws Exception {
		
		PDFContentValidation pdf = new PDFContentValidation();
		pdf.PdfStart();
		
		
	}

}
