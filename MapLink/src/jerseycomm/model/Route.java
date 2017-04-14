package jerseycomm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route implements java.io.Serializable {
    private SummaryRoute summary;

    public Route() {}

    public SummaryRoute getSummary() {
        return summary;
    }

    public void setSummary(SummaryRoute summary) {
        this.summary = summary;
    }
}
