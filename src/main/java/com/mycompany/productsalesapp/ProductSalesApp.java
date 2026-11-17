package com.mycompany.productsalesapp;

/**
 * Main class: stores the product sales data and prints the sales report.
 */
public class ProductSalesApp {

    public static void main(String[] args) {

        // 2D array: each row is a year, each column is a quarter
        int[][] productSales = {
            {300, 150, 700},   // Year 1
            {250, 200, 600}    // Year 2
        };

        // 1D arrays for the row and column headings
        String[] years = {"Year 1", "Year 2"};
        String[] quarters = {"Quarter 1", "Quarter 2", "Quarter 3"};

        // Object used to do the calculations
        ProductSales sales = new ProductSales();

        // ---------- Report heading ----------
        System.out.println("PRODUCT SALES REPORT - 2025");
        System.out.println("------------------------------------------------");

        // Print the column headings
        System.out.printf("%-10s", "");
        for (int q = 0; q < quarters.length; q++) {
            System.out.printf("%-12s", quarters[q]);
        }
        System.out.println();

        // Print each row: the year name, then its sales per quarter
        for (int year = 0; year < productSales.length; year++) {
            System.out.printf("%-10s", years[year]);
            for (int quarter = 0; quarter < productSales[year].length; quarter++) {
                System.out.printf("%-12d", productSales[year][quarter]);
            }
            System.out.println();
        }

        System.out.println("------------------------------------------------");

        // ---------- Calculated results ----------
        System.out.printf("Total sales:   %d%n", sales.TotalSales(productSales));
        System.out.printf("Average sales: %.0f%n", sales.AverageSales(productSales));
        System.out.printf("Maximum sale:  %d%n", sales.MaxSale(productSales));
        System.out.printf("Minimum sale:  %d%n", sales.MinSale(productSales));
        System.out.println("------------------------------------------------");
    }
}
