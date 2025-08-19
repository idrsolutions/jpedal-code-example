import org.jpedal.examples.JPedal;
import org.jpedal.examples.PdfUtilities;
import org.jpedal.examples.images.ConvertPagesToImages;
import org.jpedal.examples.images.ExtractClippedImages;
import org.jpedal.examples.images.ExtractImages;
import org.jpedal.examples.printing.PrintPdfPages;
import org.jpedal.examples.text.ExtractTextAsWordlist;
import org.jpedal.examples.text.ExtractTextInRectangle;
import org.jpedal.examples.viewer.Viewer;
import org.jpedal.exception.PdfException;
import org.jpedal.parser.DecoderOptions;
import org.jpedal.utils.LogWriter;

import javax.print.PrintException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public final class JpedalExamples {
    private JpedalExamples() {
    }

    public static void main(final String[] args) {
        try {
            convertPdfPagesToImages();
            // extractClippedImagesFromPDF();
            // extractImagesFromPDF();
            // openPdfViewer();
            // printPdfPages();
            // extractMetaDataFromPdf();
            // extractTextFromPages();
            // extractWordListFromPages();
        } catch (final Exception e) {
            LogWriter.writeLog("Failed to run example");
        }
    }

    public static void openPdfViewer() {
        final JFrame frame = new JFrame();
        final JPanel rootContainer = new JPanel();

        final Viewer viewer = new Viewer(rootContainer,null);
        viewer.setupViewer();

        frame.add(rootContainer, BorderLayout.CENTER);
        frame.setTitle("Viewer");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void extractWordListFromPages() throws PdfException {

        // Simple static method to output all pages text as a wordlist to the specified directory
        // ExtractTextAsWordlist.writeAllWordlistsToDir("/path/to/input.pdf", "password_or_null", "/path/to/outputDir", -1);

        // Extract text as a wordlist with more control, the words can be handled one page at a time
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";

        final ExtractTextAsWordlist extract = new ExtractTextAsWordlist(inputFilename);
        if (password != null) {
            extract.setPassword(password);
        }

        if (extract.openPDFFile()) {
            List<String> wordList = new ArrayList<>();
            final int pageCount = extract.getPageCount();
            for (int page = 1; page <= pageCount; page++) {
                wordList = extract.getWordsOnPage(page);
            }
            
            // Do something with wordList here
            wordList.forEach(System.out::println);
        } else {
            LogWriter.writeLog("Unable to open file.");
        }


        extract.closePDFfile();
    }

    public static void extractTextFromPages() throws PdfException {

        // Simple static method to output all pages text to the specified directory
        // ExtractTextInRectangle.writeAllTextToDir("/path/to/input.pdf", "password_or_null", "/path/to/outputDir", -1);

        // Extract test with more control, the text can be handled one page at a time
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";


        final ExtractTextInRectangle extract = new ExtractTextInRectangle(inputFilename);
        if (password != null) {
            extract.setPassword(password);
        }

        if (extract.openPDFFile()) {
            final StringBuilder text = new StringBuilder();
            final int pageCount = extract.getPageCount();
            for (int page = 1; page <= pageCount; page++) {
                text.append(extract.getTextOnPage(page)).append('\n');
            }
            
            // Do something with wordList here
            System.out.println(text);
        } else {
            LogWriter.writeLog("Unable to open file.");
        }
        
        extract.closePDFfile();
    }

    public static void extractImagesFromPDF() throws PdfException {

        // Simple static method to output all images to a directory
        // ExtractImages.writeAllImagesToDir("/path/to/input.pdf", "/path/to/output/", "png", true, true);

        // Image extraction with more control, you can use the images as they are retrieved
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";
        final boolean outputImageAsDisplayedOnPage = false;

        ExtractImages extract = new ExtractImages(inputFilename);
        if (password != null) {
            extract.setPassword(password);
        }

        if (extract.openPDFFile()) {
            final int pageCount = extract.getPageCount();
            for (int page = 1; page <= pageCount; page++) {
                final int imageCount = extract.getImageCount(page);
                for (int image = 1; image <= imageCount; image++) {
                    final BufferedImage imageFromPage = extract.getImage(page, image, outputImageAsDisplayedOnPage);
                    
                    // Do something with wordList here
                }
            }
        } else {
            LogWriter.writeLog("Unable to open file.");
        }


        extract.closePDFfile();
    }

    public static void extractClippedImagesFromPDF() throws PdfException {

        // Simple static method to output all images to a directory
        // ExtractClippedImages.writeAllClippedImagesToDirs("/path/to/input.pdf", "/path/to/output/", "png", new String[]{"100","fixedHeight"});

        // Clipped image extraction with more control, you can use the images as they are retrieved
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";

        ExtractClippedImages extract = new ExtractClippedImages(inputFilename);
        if (password != null) {
            extract.setPassword(password);
        }

        if (extract.openPDFFile()) {
            final int pageCount = extract.getPageCount();
            for (int page = 1; page <= pageCount; page++) {
                final int imageCount = extract.getImageCount(page);
                for (int image = 1; image <= imageCount; image++) {
                    final BufferedImage clippledImage = extract.getClippedImage(page, image);
                    
                    // Do something with wordList here
                }
            }
        } else {
            LogWriter.writeLog("Unable to open file.");
        }


        extract.closePDFfile();
    }

    public static void convertPdfPagesToImages() throws PdfException {

        // Simple static method to output all pages as image to a directory
        // ConvertPagesToImages.writeAllPagesAsImagesToDir("/path/to/input.pdf", "/path/to/output/", "png", 1.0f);

        // Page to Image conversion with more control, you can use the images as the pages are converted
        final float pageScaling = 1.0f;
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";

        final ConvertPagesToImages convert = new ConvertPagesToImages(inputFilename);
        if (password != null) {
            convert.setPassword(password);
        }

        if (convert.openPDFFile()) {
            convert.setPageScaling(pageScaling);
            final int pageCount = convert.getPageCount();
            for (int page = 1; page <= pageCount; page++) {
                final BufferedImage image = convert.getPageAsImage(page);
                
                // Do something with image here
            }
            convert.closePDFfile();
        } else {
            LogWriter.writeLog("Unable to open file.");
        }
    }

    public static void printPdfPages() throws PdfException, PrintException {

        // Simple static method to print all pages in the given PDF using the specified printer
        // PrintPdfPages.printPDF("/path/to/input.pdf", "PrinterName");

        // Print PDF file with more control b y specifying a print range, in this example each page is printed separately
        final String printerName = "printerName";
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";

        final PrintPdfPages print = new PrintPdfPages(inputFilename);
        if (password != null) {
            print.setPassword(password);
        }

        if (print.openPDFFile()) {
            final int pageCount = print.getPageCount();
            for (int page = 1; page <= pageCount; page++) {
                print.printPage(printerName, page);
            }
            print.closePDFfile(DecoderOptions.isRunningOnWindows);
        } else {
            LogWriter.writeLog("Unable to open file.");
        }
    }

    public static void extractMetaDataFromPdf() throws PdfException {

        // Simple static method to output all metadata to the console.
        // JpedalExamples.extractMetaDataFromPdf();

        // Output metadata with more control, such as limiting the data types and handling the data separately
        final String password = null;
        final String inputFilename = "/path/to/input.pdf";


        final PdfUtilities extract = new PdfUtilities(inputFilename);
        if (password != null) {
            extract.setPassword(password);
        }

        if (extract.openPDFFile()) {
            final String[] dataTypes = {"fields", "xml", "pagesizes", "outline", "fonts", "pagecount"};
            final StringBuilder text = new StringBuilder();
            for (final String dataType : dataTypes) {

                switch (dataType.toLowerCase()) {
                    case "fields":
                        text.append("Fields\n").append(JPedal.getMetadataFields(extract));
                        break;
                    case "xml":
                        text.append("XML\n").append(JPedal.getXML(extract));
                        break;
                    case "pagesizes":
                        text.append("PageSizes\n").append(Arrays.toString(JPedal.getPageSizes(extract)));
                        break;
                    case "outline":
                        text.append("Outline\n").append(Arrays.toString(JPedal.getOutline(inputFilename)));
                        break;
                    case "fonts":
                        text.append("FontsPerPage\n").append(JPedal.getFonts(extract));
                        break;
                    case "xobjects":
                        text.append("XObjectsPerPage\n").append(JPedal.getXObjects(extract));
                        break;
                    case "pagecount":
                        text.append("PageCount\n").append(extract.getPageCount());
                        break;
                }
            }
            
            // Do something with wordList here
            System.out.println(text);
        } else {
            LogWriter.writeLog("Unable to open file.");
        }
        
        extract.closePDFfile();
    }

    public static void extractEmbeddedFiles() throws PdfException {
        // Simple static method to extract embedded files from a PDF
        final String inputFile = "inputFile.pdf";
        final String outputFolder = "outputFolder";

        ExtractEmbeddedFiles extract = new ExtractEmbeddedFiles(inputFile);

        //extract.setPassword("password");

        if (extract.openPDFFile()) {
            if (extract.containsEmbeddedFiles()) {
                extract.extractEmbeddedFiles(outputFolder);
            }
            if (extract.containsFilesAttachments()) {
                extract.extractFileAttachments(outputFolder);
            }
        }
        extract.closePDFfile();
    }

    public static void splitOnePdf() throws IOException {
        // Simple static method to split a pdf file
        // The first file contains pages 1-pageToSplitAt inclusive, the second contains all other pages
        // The original file is left untouched by this process.

        final int pageToSplitAt = 3; // Example to split the pdf at page 3
        final String inputFile = "inputFile.pdf";
        final String outputFolder = "outputFolder";

        PdfManipulator.splitInHalf(new File(inputFile), new File(outputFolder), pageToSplitAt);
    }

    public static void optimizePdf() throws IOException {
        // Simple static method to optimize a PDF file with all default optimizations

        final String inputFile = "inputFile.pdf";
        final String outputFile = "outputFile.pdf";

        PdfOptimizer.optimizePDF(new File(inputFile), new File(outputFile));
    }
}
