package lab2;

import java.util.List;

public class StocksPortfolio {
    private List<Stock> stocks;
    private sotckmarket IStockMarketService;

    public StocksPortfolio(sotckmarket IStockMarketService){
        this.IStockMarketService = IStockMarketService;
    }

    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    public double getTotalValue(){
        double total = 0;
        for(Stock stock : this.stocks){
            total += stock.getQuantity() * this.IStockMarketService.lookUpPrice(stock.getLabel());
        }
        return total;
    }
}
