package cysewska.com.services.invoices;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import cysewska.com.models.entities.InvoiceEntity;
import cysewska.com.models.entities.OrderClothEntity;
import cysewska.com.repositories.InvoiceRepository;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Ola on 2016-10-25.
 */
@Component
public class CreateInvoices {

    @Autowired
    InvoiceRepository invoiceRepository;
    InvoiceEntity invoiceEntity;

    private PdfPCell addCellWithBorder(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(4);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.BOX);
        return cell;
    }

    private PdfPCell getCell(String text, int alignment, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(0);
        cell.setLeading(15f, 0f);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    private File findFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz miejsce zapisu");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        fileChooser.setInitialFileName(invoiceEntity.getName());
        File file = fileChooser.showSaveDialog(new Stage());
        return file;
    }

    private void createContractorTable(Document document, List<String> ownerData) throws DocumentException {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        table.addCell(getCell(ownerData.get(0), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL)));
        table.addCell(getCell("Niekłonice \t\t\t" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));

        table.addCell(getCell(ownerData.get(1), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));

        table.addCell(getCell(ownerData.get(2), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell(invoiceEntity.getOrderEntity().getDepartmentEntity().getBranchEntity().getName(), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));

        table.addCell(getCell(ownerData.get(3), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell(invoiceEntity.getOrderEntity().getDepartmentEntity().getAddress().toUpperCase(), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));

        table.addCell(getCell(ownerData.get(4), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell(invoiceEntity.getOrderEntity().getDepartmentEntity().getZip() + "  " + invoiceEntity.getOrderEntity().getDepartmentEntity().getCity().toUpperCase(),
                PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));

        table.addCell(getCell(ownerData.get(5), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell(invoiceEntity.getOrderEntity().getDepartmentEntity().getCountry().toUpperCase(), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));

        table.addCell(getCell(ownerData.get(6), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
        table.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD)));

        document.add(table);
    }

    private void addTittle(Document document, String tittleDocument) throws DocumentException {
        Paragraph paragraph1 = new Paragraph("");
        paragraph1.setSpacingBefore(50);
        document.add(paragraph1);

        Paragraph title1 = new Paragraph(tittleDocument + "\t\t\t\t\t\t " + invoiceEntity.getName(),
                FontFactory.getFont(FontFactory.HELVETICA,
                        16, Font.BOLDITALIC));
        title1.setAlignment(Element.ALIGN_CENTER);
        document.add(title1);

        Paragraph paragraph2 = new Paragraph();
        paragraph2.setSpacingBefore(50);
        document.add(paragraph2);
    }

    private void addTableWithProducts(String tabPosition, String total, String tabName, String tabQuantity, String tabTotal, Document document) throws DocumentException {

        PdfPTable tableWithProducts = new PdfPTable(4);
        tableWithProducts.setWidthPercentage(90);

        tableWithProducts.addCell(addCellWithBorder("" + tabPosition, PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tableWithProducts.addCell(addCellWithBorder("" + tabName, PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tableWithProducts.addCell(addCellWithBorder("" + tabQuantity, PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));
        tableWithProducts.addCell(addCellWithBorder("" + tabTotal, PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));


        double totalValue = 0;
        int count = 1;
        for (OrderClothEntity orderClothEntity : invoiceEntity.getOrderEntity().getOrderClothEntities()) {
            //  table.addCell(getCell(ownerData.get(4), PdfPCell.ALIGN_LEFT,   FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL)));
            System.out.println(count + " " + orderClothEntity.getQuantity() + "  " + orderClothEntity.getClothEntity().getPricePl() * orderClothEntity.getQuantity());
            tableWithProducts.addCell(addCellWithBorder("" + count, PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
            tableWithProducts.addCell(addCellWithBorder(orderClothEntity.getClothEntity().getModelEntity().getModel() + count, PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
            tableWithProducts.addCell(addCellWithBorder("" + orderClothEntity.getQuantity(), PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
            tableWithProducts.addCell(addCellWithBorder("" + orderClothEntity.getClothEntity().getPricePl() * orderClothEntity.getQuantity(), PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));

            totalValue = totalValue + (orderClothEntity.getClothEntity().getPricePl() * orderClothEntity.getQuantity());
            count++;
        }

        tableWithProducts.addCell(getCell("", PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
        tableWithProducts.addCell(getCell("", PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.NORMAL)));
        tableWithProducts.addCell(getCell(total, PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));

        tableWithProducts.addCell(addCellWithBorder("" + totalValue, PdfPCell.ALIGN_CENTER, FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD)));


        document.add(tableWithProducts);
    }

    private void addInfoAboutProducts(Document document, String netto, String brutto, String quantity, String palets) throws DocumentException {
        Paragraph paragraph4 = new Paragraph();
        paragraph4.setSpacingBefore(50);
        document.add(paragraph4);

        Paragraph exw = new Paragraph("EXW - KOSZALIN", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
        exw.setAlignment(Element.ALIGN_LEFT);
        Paragraph weightNetto = new Paragraph(netto +" " +  invoiceEntity.getWeightNetto() + " kg", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
        weightNetto.setAlignment(Element.ALIGN_LEFT);
        Paragraph weightBrutto = new Paragraph(brutto +" "+ invoiceEntity.getWeightBrutto() + " kg", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
        weightBrutto.setAlignment(Element.ALIGN_LEFT);
        Paragraph pallets = new Paragraph(quantity +" "+ invoiceEntity.getQuantityOfPallet() + " " + palets, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL));
        pallets.setAlignment(Element.ALIGN_LEFT);

        document.add(exw);
        document.add(weightNetto);
        document.add(weightBrutto);
        document.add(pallets);

        Paragraph paragraph3 = new Paragraph();
        paragraph3.setSpacingBefore(50);
        document.add(paragraph3);

        Paragraph text = new Paragraph("The exporter of the products covered by this document declares, that except where otherwise clearly" +
                "indicated, these products are of Poland preferential origin.", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC));
        text.setAlignment(Element.ALIGN_LEFT);
        document.add(text);

        Paragraph paragraph5 = new Paragraph();
        paragraph5.setSpacingBefore(50);
        document.add(paragraph5);
    }

    public void createInvoice() throws DocumentException, FileNotFoundException {
        invoiceEntity = invoiceRepository.findAll().stream().max(Comparator.comparing(InvoiceEntity::getId)).get();
        List<String> ownerData = Arrays.asList("EGEN spółka z o.o.", "NIEKŁONICE 23", "76-024 ŚWIESZYNO",
                "POLAND", "BANK PEKAO S.A I/Oddział Koszalin", "SWIFT - PKOPPLPW", "PL 342 424 34 23234 34234342");
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(findFile()));
        document.open();
        createContractorTable(document, ownerData);
String total = null;
        String tittleDocument;
        String tabName = null;
        String tabQuantity = null;
        String tabTotal = null;
        String tabPosition = null;
        String netto, brutto,quantity,  palets = null;

        if (invoiceEntity.getLanguage().equals("POLSKI")) {
            tittleDocument = "Faktura";
            tabName = "Nazwa ubrania";
            tabQuantity = "Ilość";
            tabTotal = "Całkowita wartość";
            tabPosition = " Pozycja";
            total = "Razem: ";
            netto = "Waga netto:";
            brutto = "Waga brutto:";
            quantity = "Ilość palet:";
            if (invoiceEntity.getQuantityOfPallet()==1) {
                palets = "paleta";
            }
            if (invoiceEntity.getQuantityOfPallet()>=2 || invoiceEntity.getQuantityOfPallet()<=4) {

                palets = " palety";
            }
            if (invoiceEntity.getQuantityOfPallet()>=5 ) {
                palets = " palet";
            }


            } else {
            tabPosition = " Position";
            tittleDocument = "Invoice";
            tabName = "Cloth name";
            tabQuantity = "Quantity";
            tabTotal = "Total value";
            total = "Total: ";
            netto = "Weight netto:";
            brutto = "Weight brutto:";
            quantity = "Quantity of palets:";
            if (invoiceEntity.getQuantityOfPallet()==1) {
                palets = "palet";
            }
            else {
                palets = " palets";
            }
        }
        addTittle(document, tittleDocument);
        addTableWithProducts(tabPosition, total, tabName, tabQuantity, tabTotal, document);
        addInfoAboutProducts(document,netto, brutto,quantity,  palets );
        addSignTable(document);
        document.close();
    }

    private void addSignTable(Document document) throws DocumentException {
        PdfPTable signsTable = new PdfPTable(3);
        signsTable.setWidthPercentage(100);
        signsTable.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        signsTable.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        signsTable.addCell(getCell("Niekłonice \t\t\t" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()), PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL)));
        signsTable.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        signsTable.addCell(getCell("", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        signsTable.addCell(getCell("Krzysztof Cysewski", PdfPCell.ALIGN_LEFT, FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD)));
        document.add(signsTable);
    }
}

