import org.jpedal.examples.images.ConvertPagesToImages;
import org.jpedal.examples.images.ExtractClippedImages;
import org.jpedal.examples.text.ExtractTextAsWordlist;
import org.jpedal.examples.viewer.Viewer;
import org.jpedal.exception.PdfException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

public class JPedalExamples {
    public static void main(String[] args) {
        try {
            pdfToImage();
//            pdfViewer();
//            extractWords();
//            extractImages();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public static void pdfToImage() throws PdfException {
        ConvertPagesToImages.writeAllPagesAsImagesToDir("/path/to/input.pdf",
                "/path/to/output/", "png", 1.33f);
    }

    public static void pdfViewer() {
        JFrame frame = new JFrame();
        JPanel rootContainer = new JPanel();

        Viewer viewer = new Viewer(rootContainer,null);
        viewer.setupViewer();

        frame.add(rootContainer, BorderLayout.CENTER);
        frame.setTitle("Viewer");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void extractWords() throws PdfException {
        List<String> wordList = new ArrayList<String>();
        ExtractTextAsWordlist extract = new ExtractTextAsWordlist("/path/to/input.pdf");

        if (extract.openPDFFile()) {
            int pageCount = extract.getPageCount();
            for (int page = 1; page <= pageCount; page++) {

                wordList = extract.getWordsOnPage(page);
            }
        }

        // Do something with wordList here
        wordList.forEach(System.out::println);

        extract.closePDFfile();
    }

    public static void extractImages() throws PdfException {
        ExtractClippedImages.writeAllClippedImagesToDirs("/path/to/input.pdf",
                "/path/to/output/", "png", new String[]{"100","fixedHeight"});
    }
}
