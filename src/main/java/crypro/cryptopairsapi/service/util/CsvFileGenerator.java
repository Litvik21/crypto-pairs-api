package crypro.cryptopairsapi.service.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

@Component
public class CsvFileGeneratorUtil {
    public void writeDataToCsv(Writer writer) {
        try {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
