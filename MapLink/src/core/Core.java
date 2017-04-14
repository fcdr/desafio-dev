package core;

import model.Address;

import java.util.ArrayList;

public class Core {
    public static void main(String[] args) {
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Av Giovanni Gronchi", "6829", "ap 182 gardenia", "Sao Paulo", "SP"));
        addresses.add(new Address("Av Dr Joao Caio Silva", "920", "W11", "Paulinia", "SP"));
        addresses.add(new Address("R Manoel Antonio Maciel", "1067", null, "Aramina", "SP"));

        for (Address a : addresses) System.out.println(a.toString()); System.out.println("\n-+-+-+-+-+-");

        System.out.println(Contract.doTheMath(addresses));
    }
}
