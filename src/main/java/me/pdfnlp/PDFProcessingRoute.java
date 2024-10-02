package me.tstefanov.pdfnlp.routes;

import com.example.pdfnlp.model.PDFDocument;
import com.example.pdfnlp.service.NLPService;
import com.example.pdfnlp.service.PDFExtractionService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class PDFProcessingRoute extends RouteBuilder {

    @Autowired
    private PDFExtractionService pdfExtractionService;

    @Autowired
    private NLPService nlpService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void configure() throws Exception {
        from("file:{{input.pdf.directory}}?noop=true")
            .routeId("pdfProcessingRoute")
            .process(exchange -> {
                File file = exchange.getIn().getBody(File.class);
                
                // Extract text from PDF
                String content = pdfExtractionService.extractTextFromPDF(file);

                // Perform NLP tagging
                List<String> tags = nlpService.extractTags(content);

                // Save to MongoDB
                PDFDocument document = new PDFDocument();
                document.setFileName(file.getName());
                document.setContent(content);
                document.setTags(tags);

                mongoTemplate.save(document);

                // Logging the document saving process
                log.info("Processed and saved document: " + file.getName());
            })
            .log("PDF processing completed for ${file:name}")
            .end();
    }
}
