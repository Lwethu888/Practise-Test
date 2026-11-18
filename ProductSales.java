package com.mycompany.productsalesapp;

/**
 * ProductSales implements IProduct and calculates the
 * total, average, maximum and minimum product sales.
 */
public class ProductSales implements IProduct {

    // Adds up every value in the 2D array
    @Override
    public int TotalSales(int[][] productSales) {
        int total = 0;
        // Outer loop goes through each year (row)
        for (int year = 0; year < productSales.length; year++) {
            // Inner loop goes through each quarter (column) in that year
            for (int quarter = 0; quarter < productSales[year].length; quarter++) {
                total += productSales[year][quarter];
            }
        }
        return total;
    }

    // Average = total sales divided by the number of sales
    @Override
    public double AverageSales(int[][] productSales) {
        int count = 0;
        // Count how many sales values there are
        for (int year = 0; year < productSales.length; year++) {
            count += productSales[year].length;
        }
        // Avoid dividing by zero if the array is empty
        if (count == 0) {
            return 0;
        }
        // Reuse TotalSales (no duplicate code) and cast to double for decimals
        return (double) TotalSales(productSales) / count;
    }

    // Finds the highest sale
    @Override
    public int MaxSale(int[][] productSales) {
        // Start with the first value, then compare every other value to it
        int max = productSales[0][0];
        for (int year = 0; year < productSales.length; year++) {
            for (int quarter = 0; quarter < productSales[year].length; quarter++) {
                if (productSales[year][quarter] > max) {
                    max = productSales[year][quarter];
                }
            }
        }
        return max;
    }

    // Finds the lowest sale
    @Override
    public int MinSale(int[][] productSales) {
        int min = productSales[0][0];
        for (int year = 0; year < productSales.length; year++) {
            for (int quarter = 0; quarter < productSales[year].length; quarter++) {
                if (productSales[year][quarter] < min) {
                    min = productSales[year][quarter];
                }
            }
        }
        return min;
    }
}