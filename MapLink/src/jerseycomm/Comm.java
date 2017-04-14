package jerseycomm;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import jerseycomm.model.*;
import model.Address;

import java.util.List;

public class Comm {
    private static ClientResponse getRequest(String url) {
        try {
            Client client = Client.create();

            WebResource webResource = client.resource(url);

            ClientResponse response = webResource.type("application/json").get(ClientResponse.class);

            if (Integer.toString(response.getStatus()).charAt(0) != '2')
                throw new RuntimeException("Oooooops! HTTP error code: " + response.getStatus());

            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static LocationGeoCode requestGeoCode(String addr) {
        try {
            String url = "http://api.maplink.com.br/v0/search?q=" + addr + "&types=address,street&limit=1";

            ClientResponse response = getRequest(Util.signIt(url));

            ResponseGeoCode responseGeoCode = null;
            if (response != null) {
                responseGeoCode = response.getEntity(ResponseGeoCode.class);
                response.close();

                if (responseGeoCode != null && responseGeoCode.getResults() != null && !responseGeoCode.getResults().isEmpty())
                    return responseGeoCode.getResults().get(0).getLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean fillGeoCode(List<Address> addresses) {
        boolean latLonFound = true;

        if (addresses != null) {
            for (int i=0; i < addresses.size() && latLonFound; i++) {
                Address address = addresses.get(i);
                String addr = address.getAddress(true);

                LocationGeoCode loc = requestGeoCode(Util.parseToURL(addr));
                if (loc != null) {
                    address.setLat(loc.getLat());
                    address.setLon(loc.getLng());
                } else {
                    addr = address.getAddress(false);
                    loc = requestGeoCode(Util.parseToURL(addr));
                    if (loc != null) {
                        address.setLat(loc.getLat());
                        address.setLon(loc.getLng());
                    } else {
                        System.err.println("Could not find lat & lon for address " + address);
                    }
                }
            }
        }

        return latLonFound;
    }

    public static SummaryRoute requestRoute(List<Address> addresses) {
        try {
            if (addresses != null && addresses.size() > 1) {
                String url = "https://api.maplink.com.br/v0/route?costadjustment.tollroad=-100" +
                        "&result=summary.duration,summary.distance,summary.tolls";

                for (int i=0; i < addresses.size(); i++) {
                    url += "&waypoint." + i + ".latlng=" + addresses.get(i).getLatLonCSV();
                }

                url = Util.signIt(url);
                ClientResponse response = getRequest(url);

                ResponseRoute responseRoute = null;
                if (response != null) {
                    responseRoute = response.getEntity(ResponseRoute.class);
                    response.close();

                    if (!responseRoute.getRoutes().isEmpty()) {
                        Route route = responseRoute.getRoutes().get(0);

                        return route.getSummary();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
