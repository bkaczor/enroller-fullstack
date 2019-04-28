package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	// wstrzykniecie instancji interfejsu - w App mam @Bean i to zaciągam tutaj przez @Autowired
	// deklarujemy co chcemy a nie to  co to jest - mamy interfejs wiec mozna se to przykryc czyms innym
	// IoC - dependency injection za pomoca Beanow, serwisów itp
	// kontener IoC w sposob prawie magiczny stworzy to coś i to nie bedzie nullem
	@Autowired
	PasswordEncoder passwordEncoder;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}
	
	public Collection<Participant> getAll() {
		return connector.getSession().createCriteria(Participant.class).list();
	}
	
	public Participant findByLogin(String login) {
		return (Participant) connector.getSession().get(Participant.class, login);
	}

	public void add(Participant participant) {
		String plainPassword = participant.getPassword(); // wyciagam haslo
		String hashedPassword = this.passwordEncoder.encode(plainPassword); //haszuje
		participant.setPassword(hashedPassword); // nadpisuje
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
	}
	
	public void delete(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(participant);
		transaction.commit();
	}
	
	public void update(Participant participant) {
        Transaction transaction = connector.getSession().beginTransaction();
        connector.getSession().update(participant);
        transaction.commit();
    }
}
