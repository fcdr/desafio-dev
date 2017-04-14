package jerseycomm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseGeoCode implements java.io.Serializable {
    private List<ResultsGeoCode> results = new ArrayList<>();

    public ResponseGeoCode() {}

    public List<ResultsGeoCode> getResults() {
        return results;
    }

    public void setResults(List<ResultsGeoCode> results) {
        this.results = results;
    }
}
