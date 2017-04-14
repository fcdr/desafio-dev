package jerseycomm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TollRoute implements java.io.Serializable {
    private PriceRoute prices;

    public TollRoute() {}

    public PriceRoute getPrices() {
        return prices;
    }

    public void setPrices(PriceRoute prices) {
        this.prices = prices;
    }
}
