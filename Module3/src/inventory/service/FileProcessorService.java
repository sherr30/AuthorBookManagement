package inventory.service;

import inventory.factory.ProductFactory;
import inventory.model.Product;
import inventory.repository.InventoryRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class FileProcessorService
        implements Runnable {

    private final String brand;

    private final Set<String>
            processedFiles =
            new HashSet<>();

    public FileProcessorService(
            String brand) {

        this.brand = brand;
    }

    @Override
    public void run() {

        while (true) {

            try {

                File folder =
                        new File("data");

                File[] files =
                        folder.listFiles();

                if (files != null) {

                    for (File file : files) {

                        if (file.getName()
                                .toLowerCase()
                                .contains(
                                        brand.toLowerCase())

                                &&

                                !processedFiles.contains(
                                        file.getName())) {

                            processFile(file);

                            processedFiles.add(
                                    file.getName());
                        }
                    }
                }

                Thread.sleep(30000);

            } catch (Exception e) {

                System.out.println(
                        "Thread Error : "
                                + e.getMessage());
            }
        }
    }

    private void processFile(
            File file) {

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line =
                    br.readLine()) != null) {

                try {

                    String[] data =
                            line.split(",");

                    Product product;

                    if (brand.equalsIgnoreCase(
                            "nike")) {

                        product =
                                ProductFactory
                                        .createNikeProduct(
                                                data);

                    } else {

                        product =
                                ProductFactory
                                        .createPumaProduct(
                                                data);
                    }

                    InventoryRepository
                            .getInstance()
                            .addProduct(product);

                } catch (Exception e) {

                    System.out.println(
                            "Invalid Record Skipped : "
                                    + line);
                }
            }

            System.out.println(
                    file.getName()
                            + " processed successfully.");

        } catch (Exception e) {

            System.out.println(
                    "File Processing Error : "
                            + e.getMessage());
        }
    }
}