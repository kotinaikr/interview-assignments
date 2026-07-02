package com.housing.javaapi.util;

import com.housing.javaapi.model.HouseRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvReaderUtil {

    public List<HouseRecord> loadData() {

        List<HouseRecord> records = new ArrayList<>();

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    new ClassPathResource("HousePriceDataset.csv").getInputStream()));

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");

                HouseRecord record = new HouseRecord();

                record.setSquareFootage(Double.parseDouble(values[0]));
                record.setBedrooms(Integer.parseInt(values[1]));
                record.setBathrooms(Double.parseDouble(values[2]));
                record.setYearBuilt(Integer.parseInt(values[3]));
                record.setLotSize(Double.parseDouble(values[4]));
                record.setDistanceToCityCenter(Double.parseDouble(values[5]));
                record.setSchoolRating(Double.parseDouble(values[6]));
                record.setPrice(Double.parseDouble(values[7]));

                records.add(record);

            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;

    }

}