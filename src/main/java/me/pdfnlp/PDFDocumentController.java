package me.tstefanov.pdfnlp.controller;

import com.example.pdfnlp.model.PDFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class PDFDocumentController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public List<PDFDocument> getDocuments() {
        return mongoTemplate.findAll(PDFDocument.class);
    }
}
