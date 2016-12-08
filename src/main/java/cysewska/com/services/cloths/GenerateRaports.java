package cysewska.com.services.cloths;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cysewska.com.models.entities.TextileEntity;
import cysewska.com.repositories.TextilRepository;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ola on 2016-10-25.
 */
@Component
public class GenerateRaports {

    @Autowired
    TextilRepository textilRepository;

    private PdfPCell addCellWithBorder(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(4);
        cell.setLeading(15f, 0f);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.BOX);
        return cell;
    }

    public void generate() throws FileNotFoundException, DocumentException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz miejsce zapisu");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName("Raport " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        File file = fileChooser.showSaveDialog(new Stage());
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        Paragraph title2 = new Paragraph("Niekłonice,  " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                FontFactory.getFont(FontFactory.HELVETICA,
                        12, Font.NORMAL));
        title2.setAlignment(Element.ALIGN_RIGHT);
        document.add(title2);

        Paragraph paragraph3 = new Paragraph();
        paragraph3.setSpacingBefore(50);
        document.add(paragraph3);

        Paragraph title1 = new Paragraph("Stany magazynowe tkanin " ,
                FontFactory.getFont(FontFactory.HELVETICA,
                        16, Font.UNDERLINE));
        title1.setAlignment(Element.ALIGN_CENTER);
        document.add(title1);


        Paragraph paragraph2 = new Paragraph();
        paragraph2.setSpacingBefore(50);
        document.add(paragraph2);

        PdfPTable tableWithProducts = new PdfPTable(3);
        tableWithProducts.setWidthPercentage(90);

        tableWithProducts.addCell(addCellWithBorder("Pozycja ", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tableWithProducts.addCell(addCellWithBorder("Nazwa tkaniny", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tableWithProducts.addCell(addCellWithBorder("Ilość tkaniny", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        int i=1;
        for (TextileEntity textileEntity : textilRepository.findAll()) {

            tableWithProducts.addCell(addCellWithBorder(""+i, PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
            i++;
            tableWithProducts.addCell(addCellWithBorder(textileEntity.getName(), PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
            tableWithProducts.addCell(addCellWithBorder(""+ textileEntity.getTextileQuantity(), PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));

        }

        document.add(tableWithProducts);
        document.close();

    }
}
