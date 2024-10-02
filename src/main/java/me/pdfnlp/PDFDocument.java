package me.tstefanov.pdfnlp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "pdf_documents")
public class PDFDocument {

    @Id
    private String id;
    private String fileName;
    private String content;
    private List<String> tags;

}
