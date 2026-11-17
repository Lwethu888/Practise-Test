package com.mycompany.productsalesapp;

/**
 * Interface that lists the methods every product sales class must have.
 */
public interface IProduct {

    // Returns the total of all sales in the 2D array
    int TotalSales(int[][] productSales);

    // Returns the average of all sales
    double AverageSales(int[][] productSales);

    // Returns the highest sale
    int MaxSale(int[][] productSales);

    // Returns the lowest sale
    int MinSale(int[][] productSales);
}