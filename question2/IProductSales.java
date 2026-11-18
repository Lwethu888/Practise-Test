package com.mycompany.productsalesapp.question2;
// ↑ "package" = the folder this file lives in. It must match the folder name.

/**
 * An INTERFACE is like a CONTRACT or a to-do list.
 * It only says WHAT methods must exist, not HOW they work.
 * Any class that "implements" this interface MUST write all these methods.
 */
public interface IProductSales {

    // Gives back the whole table (2D array) of sales numbers
    int[][] GetProductSales();

    // Gives back all the sales added together
    int GetTotalSales();

    // Gives back HOW MANY sales are 500 or more
    int GetSalesOverLimit();

    // Gives back HOW MANY sales are below 500
    int GetSalesUnderLimit();

    // Gives back how many years we processed (how many rows in the table)
    int GetProductsProcessed();

    // Gives back the average (can have decimals, so we use double)
    double GetAverageSales();
}