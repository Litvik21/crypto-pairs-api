package crypro.cryptopairsapi.service.util;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import crypro.cryptopairsapi.service.CryptoCurrencyService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

@Component
public class CsvFileGenerator {
    private final CryptoCurrencyService service;

    public CsvFileGenerator(CryptoCurrencyService service) {
        this.service = service;
    }

    public void writeDataToCsv(Writer writer) {
        try {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            List<String> currencies = List.of("BTC", "ETH", "XRP");
            for (String currency : currencies) {
                csvPrinter.printRecord(currency, service.getMinPrice(currency).getPrice(),
                        service.getMaxPrice(currency).getPrice());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}