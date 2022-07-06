package Lab01;

public class Currency implements CurrencyInterface{

    private final String name;
    private final double conversionFactor;
    private final String currencyCode;
    private final double currencyRate;

    public Currency(String name, double conversionFactor, String currencyCode, double currencyRate){
        this.name = name;
        this.conversionFactor = conversionFactor;
        this.currencyCode = currencyCode;
        this.currencyRate = currencyRate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Override
    public double getCurrencyRate() {
        return currencyRate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", conversionFactor=" + conversionFactor +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyRate=" + currencyRate +
                '}';
    }

}
