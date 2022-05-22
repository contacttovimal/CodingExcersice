package careercup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class HashMapRefrenceDemo {
	public static void main(String[] args) {
		HashMapCityMap cityDataMap = new HashMapCityMap();
		Country country = new Country("India");
		State maharastraState = new State("Maharastra", country);
		City puneCity = new City("Pune",maharastraState);
		City mumbaiCity = new City("Mumbai",maharastraState);
		State gujaratState = new State("Gujarat", country);
		City ahmedabadCity = new City("ahmedabad",gujaratState );
		City barodaCity = new City("Baroda",gujaratState);
		cityDataMap.put(puneCity.getCityName(), puneCity);
		cityDataMap.put(mumbaiCity.getCityName(), mumbaiCity);
		cityDataMap.put(ahmedabadCity.getCityName(), ahmedabadCity);
		cityDataMap.put(barodaCity.getCityName(), barodaCity);
		cityDataMap.printCities(maharastraState.getStateName());
		cityDataMap.printCityDetai(barodaCity.getCityName());
		cityDataMap.printStates(country.getCountryName());
	}

}

class HashMapCityMap extends HashMap{
	HashMap<String,Country> countryMap = new HashMap<String,Country>();
	HashMap<String,State> stateMap = new HashMap<String,State>();
	HashMap<String,City> cityMap = new HashMap<String, City>();
	HashMap<String,Set<City>> stateCityMap = new HashMap<String,Set<City>>();
	HashMap<String,Set<State>> countryStateMap = new HashMap<String,Set<State>>();
	@Override
	public Object put(Object key, Object value) {
		if(key != null){
			City cityObject = (City)value;
			State stateObject = cityObject.getState();
			if(cityObject.getState()!= null){
				stateMap.put(stateObject.getStateName(), stateObject);
				if(stateObject.getCountry()!= null){
					Country countryObject = stateObject.getCountry();
					countryMap.put(countryObject.getCountryName(), countryObject);
				}
				adjustCitiStateMap(cityObject,DATAMAP.ADD);
				adjustCountryStateMap(stateObject, DATAMAP.ADD);
			}
			return cityMap.put((String)key, (City)value);
		}
		return null;
	}
	@Override
	public Object remove(Object key) {
		
		if(key != null){
			City cityObject = cityMap.remove((String)key);
			if(cityObject!= null && cityObject.getState() != null) {
				State stateObject = stateMap.remove(cityObject.getState().getStateName());
				if(stateObject  != null && stateObject.getCountry() != null){
					Country countryObject = countryMap.remove(stateObject.getCountry().getCountryName());
				}
				adjustCitiStateMap(cityObject,DATAMAP.REMOVE);
				adjustCountryStateMap(stateObject, DATAMAP.REMOVE);
			}
			return cityObject;
		}
		return null;
	}
	private void adjustCitiStateMap(City cityObject,DATAMAP mapOperation){
		switch(mapOperation){
		case  ADD:
			if(cityObject != null && cityObject.getState()!= null && cityObject.getState().getStateName() != null){
				Set<City> citySet = stateCityMap.get(cityObject.getState().getStateName());
				if(citySet == null){
					citySet = new HashSet<City>(10);
					stateCityMap.put(cityObject.getState().getStateName(), citySet);
				}
				citySet.add(cityObject);
				State stateObject = cityObject.getState();
				if(stateObject  != null && stateObject.getCountry() != null && stateObject.getCountry().getCountryName() != null ){
					Set<State> stateSet = countryStateMap.get(stateObject.getCountry().getCountryName());
					if(stateSet == null){
						stateSet = new HashSet<State>();
						countryStateMap.put(stateObject.getCountry().getCountryName(),stateSet);
					}
					stateSet.add(stateObject);
				}
			}
			break;
		case REMOVE:
			if (cityObject != null && cityObject.getState() != null
					&& cityObject.getState().getStateName() != null) {
				Set<City> citySet = stateCityMap.get(cityObject.getState().getStateName());
				if (citySet != null) {
					citySet.remove(cityObject);
				}
			/*	State stateObject = cityObject.getState();
				if (stateObject != null && stateObject.getCountry() != null
						&& stateObject.getCountry().getCountryName() != null) {
					Set<State> stateSet = countryStateMap.get(stateObject.getCountry().getCountryName());
					if (stateSet != null) {
						stateSet.remove(stateObject);
					}
				}*/
			}
			break;
			default:
				break;
		}
	}
	private void adjustCountryStateMap(State stateObject,DATAMAP mapOperation){
		switch(mapOperation){
		case  ADD:
			if(stateObject != null && stateObject.getStateName()!= null){
				Set<City> citySet = stateCityMap.get(stateObject.getStateName());
				if(citySet == null){
					citySet = new HashSet<City>();
					stateCityMap.put(stateObject.getStateName(),citySet );
				}
				Country countryObject = stateObject.getCountry();
				if(countryObject   != null &&  countryObject.getCountryName() != null ){
					Set<State> stateSet = countryStateMap.get(countryObject.getCountryName());
					if(stateSet == null){
						stateSet = new HashSet<State>();
						countryStateMap.put(countryObject.getCountryName(),stateSet );
					}
					stateSet.add(stateObject);
				}
			}
			break;
		case REMOVE:
			if (stateObject != null && stateObject.getStateName() != null) {
				Set<City> citySet = stateCityMap.remove(stateObject.getStateName());
				Country countryObject = stateObject.getCountry();
				if (countryObject != null && countryObject.getCountryName() != null) {
					Set<State> stateSet = countryStateMap.remove(countryObject.getCountryName());
				}
			}
			break;
			default:
				break;
		}
	}
	public void printCities(String state){
		Set<City> citySet = stateCityMap.get(state);
		if(citySet== null || citySet.size() == 0){
			return;
		}
		for (Iterator iterator = citySet.iterator(); iterator.hasNext();) {
			City city = (City) iterator.next();
			System.out.println(city.getCityName());
			
		}
	}
	public void printStates(String country){
		Set<State> stateSet = countryStateMap.get(country);
		if(stateSet== null || stateSet.size() == 0){
			return;
		}
		for (Iterator iterator = stateSet.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			System.out.println(state.getStateName());
			
		}
	}
	public void printCityDetai(String cityName){
		City city = cityMap.get(cityName);
		if(city == null){
			return ;
		}
		System.out.println("City : " + city.getCityName());
		if(city.getState() != null){
			System.out.println("State : " + city.getState().getStateName());
		}
		if(city.getState().getCountry()!= null){
			System.out.println("country : " + city.getState().getCountry().getCountryName());
		}
		
	}
	
}

enum DATAMAP{ADD,REMOVE}

class Country{
	private String countryName;
	public Country(String name) {
		this.countryName=name;
	}
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}

class State {
	public State(String stateName,Country country){
		this.stateName = stateName;
		this.country=country;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	private Country country;
	private String stateName;
}

class City{
	public City(String cityName,State state){
		this.cityName=cityName;
		this.state = state;
	}
	private State state;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	String cityName;
}