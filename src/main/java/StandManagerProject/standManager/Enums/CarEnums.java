package StandManagerProject.standManager.Enums;

public class CarEnums {

    public enum Traction {
        AWD, FWD, RWD, FOUR_WD
    }

    public enum Country {
        UNITED_STATES, UNITED_KINGDOM, CHINA, GERMANY, FRANCE, ITALY, SWEDEN, JAPAN, SPAIN, SOUTH_KOREA, CZECH_REPUBLIC, ROMANIA
    }

    public enum Seats {
        UNKNOWN ,ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE
    }

    public enum Fuel {
        BIODIESEL, ELECTRICITY, ETHANOL, HYDROGEN, NATURAL_GAS, PROPANE, RENEWABLE_DIESEL, SUSTAINABLE_AVIATION_FUEL
    }

    public enum Type {
        QUADRICYCLE, A_MINI, B_SMALL, C_MEDIUM, D_LARGE, E_EXECUTIVE, F_LUXURY, J_SPORT_UTILITY, M_MULTI_PURPOSE, S_SPORTS
    }

    public enum Doors {
        UNKNOWN, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN
    }

    public enum Status {
        UNKNOWN, ARRIVING, IN_STOCK, PROMISE, SOLD
    }

}

