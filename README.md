This project uses Spring Boot, Apache Camel, Stanford NLP, MongoDB, and Apache PDFBox.

Running the Application

Start MongoDB:
Make sure you have MongoDB running locally or configured correctly in the application.properties.
#Place PDFs:
Place some PDFs in the pdf_input folder (or the folder you configured in application.properties).
Run the Application:
Start the Spring Boot application. Apache Camel will automatically process the PDFs in the folder,
extract text using PDFBox, analyze it with Stanford NLP, and store the extracted tags in MongoDB.

mvn spring-boot:run
