package core;

import jerseycomm.Comm;
import model.Address;
import model.Report;

import java.util.List;

import static java.lang.System.exit;

public class Contract {
    public static Report doTheMath(List<Address> addresses) {
        if (!Comm.fillGeoCode(addresses)) {
            System.err.println("Could not continue because not all addresses were geocoded.");
            exit(-42);
        }

        return new Report(Comm.requestRoute(addresses));
    }
}
