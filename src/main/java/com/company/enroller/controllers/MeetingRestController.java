package com.company.enroller.controllers;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;
import com.company.enroller.persistence.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/meetings")
public class MeetingRestController {

    @Autowired
    MeetingService meetingService;

    @Autowired
    ParticipantService participantService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getMeetings() {

        Collection<Meeting> meetings = meetingService.getAll();
        return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") long id) {
	     Meeting meeting = meetingService.findById(id);
	     if (meeting == null) {
	         return new ResponseEntity(HttpStatus.NOT_FOUND);
	     }
	     return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	 }
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	 public ResponseEntity<?> newMeeting(@RequestBody Meeting meeting) {
		Meeting foundMeeting = meetingService.findById(meeting.getId());
		if (foundMeeting != null) {
		
			return new ResponseEntity("Unable to create. A meeting with this ID " + meeting.getId() + " already exist.", HttpStatus.CONFLICT);
			}
		meetingService.addNewMeeting(meeting);
		return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public ResponseEntity<?> deleteMeeting(@PathVariable("id")long id) {
		Meeting meeting= meetingService.findById(id);
		if (meeting == null) {
		
			return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
		meetingService.deleteMeeting(meeting);
		return new ResponseEntity<Meeting>(HttpStatus.NO_CONTENT);
		
	}
}
