package com.query.dsl.user.search.bootstrap;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.query.dsl.user.search.domain.Address;
import com.query.dsl.user.search.domain.Country;
import com.query.dsl.user.search.domain.Employee;
import com.query.dsl.user.search.domain.Position;
import com.query.dsl.user.search.domain.State;
import com.query.dsl.user.search.domain.Status;
import com.query.dsl.user.search.repository.CountryRepository;
import com.query.dsl.user.search.repository.EmployeeRepository;
import com.query.dsl.user.search.repository.PositionRepository;
import com.query.dsl.user.search.repository.StateRepository;
import com.query.dsl.user.search.repository.StatusRepository;

@Component
public class BootStrapInit implements ApplicationListener<ContextRefreshedEvent> {
	
	private final static String[] FIRST_NAMES = {"Jim", "Alexandra", "Alexander", "Brianna", 
			"Brooklynn", "Bianca", "Benjamin", "Bruno", "Christopher", "Charles", "Charlotte", "Chloe", "Dorothy", 
			"Danica", "Daisy", "Donald", "Derek", "Fernando", "Fabian", "Franklin", "Fiona", "Fernanda", "Felicia", "Franchesca", 
			"Emma", "Evangeline", "Edith", "Emily", "Elena", "Erick", "Emmitt", "Ethan", "Emilio"};
	
	private final static String[] LAST_NAMES = {"Giovanni", "Grayson", "Graham", "Grady", "Gage", "Gilbert", "Harper", "Irving", "Jackson", 
			"Knox", "Leighton", "Marshall", "Nash", "Omer", "Oshea", "Pierce", "Quigley", "Remington", "Stanley", "Thatcher", "Usman", 
			"Vance", "Winston", "Xander", "Yoshiro", "Zane"};
	
	private final static String[] ADDRESSES = {"5 Nicolls Ave.", "69 E. Grandrose St.", "84 Bayport Court", "86 Hill Street", 
			"8448 Bohemia Street", "77 Old Armstrong Court", "957 Poor House Lane", "7738 Brickell Dr.", "30 Rocky River Ave.", 
			"9727 North Jones Ave.", "67 Joy Ridge Road", "9 Manor Court", "472 Country Court", "98 Edgewood St.", "7087 Old Rockledge Ave.", 
			"9020 Goldfield Drive", "7207 Delaware St."};
	
	private final static String[][] CITIES = {{"Madison", "Wisconsin", "53001"}, {"El Paso","Texas", "73301"}, {"Houston", "Texas", "77010"}, {"Cincinnati", "Ohio", "45203"}, 
			{"Orlando","Florida", "32809"}, {"Plano", "Texas", "75025"}, {"Los Angeles", "California", "90024"}, {"New York", "New York", "10028"}, 
			{"Atlanta", "Georgia", "30319"}
	};
	
	private final static String[] STATES = {"Alabama" , "Alaska" ,"Arizona" , "Arkansas" , "California" , "Colorado" , "Connecticut" , 
			"Delaware" , "Florida" , "Georgia" , "Hawaii" , "Idaho" , "Illinois Indiana" , "Iowa" , "Kansas" , "Kentucky" , "Louisiana" , 
			"Maine" , "Maryland" , "Massachusetts" , "Michigan" , "Minnesota" , "Mississippi" , "Missouri" , "Montana", "Nebraska" , 
			"Nevada" , "New Hampshire" , "New Jersey" , "New Mexico" , "New York" , "North Carolina" , "North Dakota" , "Ohio" , 
			"Oklahoma" , "Oregon" ,"Pennsylvania", "Rhode Island" , "South Carolina" , "South Dakota" , "Tennessee" , "Texas" , 
			"Utah" , "Vermont" , "Virginia" , "Washington" , "West Virginia" , "Wisconsin" , "Wyoming"
	};
	
	private final static String COUNTRY = "United States";

	private final StatusRepository statusRepository;
	private final PositionRepository positionRepository;
	private final EmployeeRepository employeeRepository;
	private final CountryRepository countryRepository;
	private final StateRepository stateRepository;
	private Random random;
	
	public BootStrapInit(StatusRepository statusRepository, PositionRepository positionRepository,
			EmployeeRepository employeeRepository, CountryRepository countryRepository,
			StateRepository stateRepository) {
		super();
		this.statusRepository = statusRepository;
		this.positionRepository = positionRepository;
		this.employeeRepository = employeeRepository;
		this.countryRepository = countryRepository;
		this.stateRepository = stateRepository;
		this.random = new Random(Calendar.getInstance().getTimeInMillis());
	}

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if(this.positionRepository.count() <= 0) {
			this.loadPositions();
		}
		
		if(this.statusRepository.count() <= 0) {
			this.loadStatuses();
		}
		
		if(countryRepository.count() <= 0) {
			this.loadCountries();
		}
		
		if(stateRepository.count() <= 0) {
			this.loadStates();
		}
		
		this.loadEmployees();
	}
	
	private void loadPositions() {
		List<Position> positions = new ArrayList<>();
		
		Position position = new Position();
		position.setId(Long.valueOf(1));
		position.setPosition("Janitor");
		positions.add(position);
		
		Position position1 = new Position();
		position1.setId(Long.valueOf(2));
		position1.setPosition("Software Engineer");
		positions.add(position1);
		
		Position position2 = new Position();
		position2.setId(Long.valueOf(3));
		position2.setPosition("Administrator");
		positions.add(position2);
		
		Position position3 = new Position();
		position3.setId(Long.valueOf(3));
		position3.setPosition("Director");
		positions.add(position3);
		
		Position position4 = new Position();
		position4.setId(Long.valueOf(3));
		position4.setPosition("IT Technician");
		positions.add(position4);
		
		Position position5 = new Position();
		position5.setId(Long.valueOf(3));
		position5.setPosition("Web Designer");
		positions.add(position5);
		
		Position position6 = new Position();
		position6.setId(Long.valueOf(3));
		position6.setPosition("Office Manager");
		positions.add(position6);
		
		/*Position position7 = new Position();
		position7.setId(Long.valueOf(3));
		position7.setPosition("CEO");
		positions.add(position7);*/
		
		this.positionRepository.saveAll(positions);
	}
	
	private void loadStatuses() {
		
		ArrayList<Status> statuses = new ArrayList<>();
		
		Status status = new Status();
		status.setId(Long.valueOf(1));
		status.setStatus("INACTIVE");
		statuses.add(status);
		
		Status status1 = new Status();
		status1.setId(Long.valueOf(2));
		status1.setStatus("ACTIVE");
		statuses.add(status1);
		
		/*Status status2 = new Status();
		status2.setId(Long.valueOf(3));
		status2.setStatus("LAYOFF");
		statuses.add(status2);
		
		Status status3 = new Status();
		status3.setId(Long.valueOf(3));
		status3.setStatus("SABBATICAL");
		statuses.add(status3);*/
		
		this.statusRepository.saveAll(statuses);
	}
	
	private void loadCountries() {
		Country country = new Country();
		country.setName(COUNTRY);
		this.countryRepository.save(country);
	}
	
	private void loadStates() {
		List<State> states = new ArrayList<>(); 
		List<String> statesList = Arrays.asList(STATES);
		Country country = this.countryRepository.findByName(COUNTRY).get(0);
		statesList.forEach(state -> states.add(State.builder().name(state).country(country).build()));
		this.stateRepository.saveAll(states);
	}
	
	private String getRandomFirstName() {
		return FIRST_NAMES[this.getRandomNumber(FIRST_NAMES.length)];		
	}
	
	private String getRandomLastName() {
		return LAST_NAMES[this.getRandomNumber(LAST_NAMES.length)];		
	}
	
	private String getRandomAddress() {
		return ADDRESSES[this.getRandomNumber(ADDRESSES.length)];
	}
	
	private Address getAddress() {
		Address address = new Address();
		address.setAddress1(this.getRandomAddress());
		String[] strings = CITIES[this.getRandomNumber(CITIES.length)];
		String city = strings[0];
		State state = this.stateRepository.findByName(strings[1]).get(0);
		address.setCity(city);
		address.setState(state);
		address.setPostalCode(strings[2]);
		address.setCountry(this.countryRepository.findByName(COUNTRY).get(0));
		address.setIsMainAddress(true);
		return address;
	}
	
	private Position getRandomPosition(List<Position> positions) {
		return positions.get(this.getRandomNumber(positions.size()));
	}
	
	private Status getRandomStatus(List<Status> statuses ) {
		return statuses.get(this.getRandomNumber(statuses.size()));
	}
	
	private Date getRandomStartDate() {
		Calendar calendar = Calendar.getInstance();
		Integer r = this.getRandomNumber(10);
		calendar.add(Calendar.YEAR, -this.getRandomNumber(10));
		return calendar.getTime();
	}
	
	public Employee getRandomEmployee(List<Position> positionList, List<Status> statusList) {
		Employee employee = new Employee();
		employee.setAddress(this.getAddress());
		employee.setStartDate(this.getRandomStartDate());
		employee.setFirstName(this.getRandomFirstName());
		employee.setLastName(this.getRandomLastName());
		employee.setPosition(this.getRandomPosition(positionList));
		employee.setStatus(this.getRandomStatus(statusList));		
		return employee;
	}
	
	public void loadEmployees() {
		
		this.employeeRepository.deleteAll();
		
		Iterable<Position> positions = this.positionRepository.findAll();
		List<Position> positionList = new ArrayList<>(); 
		positions.forEach(position -> positionList.add(position));
		
		Iterable<Status> statuses = this.statusRepository.findAll();
		List<Status> statusList = new ArrayList<>();
		statuses.forEach(status -> statusList.add(status));
		
		List<Employee> employees = new ArrayList<>();
		
		for(int i = 0; i < 100; i++) {
			employees.add(this.getRandomEmployee(positionList, statusList));
		}
		
		this.employeeRepository.saveAll(employees);
	}
	
	private Integer getRandomNumber(Integer upperBound) {		
		return random.nextInt(upperBound);
	}

}
