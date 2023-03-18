package lab2_2;

import java.util.Objects;

public class Address {
    private String road;
    private String state;
    private String cirty;
    private String zio;
    private String houseNumber;

    public Address(String road, String state, String cirty, String zio, String houseNumber) {
        this.road = road;
        this.state = state;
        this.cirty = cirty;
        this.zio = zio;
        this.houseNumber = houseNumber;
    }


    public String getRoad() {
        return this.road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCirty() {
        return this.cirty;
    }

    public void setCirty(String cirty) {
        this.cirty = cirty;
    }

    public String getZio() {
        return this.zio;
    }

    public void setZio(String zio) {
        this.zio = zio;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    

    @Override
    public String toString() {
        return "{" +
            " road='" + getRoad() + "'" +
            ", state='" + getState() + "'" +
            ", cirty='" + getCirty() + "'" +
            ", zio='" + getZio() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            "}";
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(road, address.road) && Objects.equals(state, address.state) && Objects.equals(cirty, address.cirty) && Objects.equals(zio, address.zio) && Objects.equals(houseNumber, address.houseNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(road, state, cirty, zio, houseNumber);
    }
}
