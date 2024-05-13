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
//            extractClippedImagesFromPDF();
//            extractImagesFromPDF();
//            openPdfViewer();
//            printPdfPages();
//            extractMetaDataFromPdf();
//            extractTextFromPages();
//            extractWordListFromPages();
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
        ExtractImages.writeAllImagesToDir("/path/to/input.pdf",
                "/path/to/output/", "png", true, true);

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
        ExtractClippedImages.writeAllClippedImagesToDirs("/path/to/input.pdf",
                "/path/to/output/", "png", new String[]{"100","fixedHeight"});

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

        //Simple static method
        //ConvertPagesToImages.writeAllPagesAsImagesToDir("/path/to/input.pdf", "/path/to/output/", "png", 1.0f);

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
            }
            convert.closePDFfile();
        } else {
            LogWriter.writeLog("Unable to open file.");
        }
    }

    public static void printPdfPages() throws PdfException, PrintException {

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
}
