package com.example.mspl_connect.AdminRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.AdminEntity.EventsWithStatus_Entity;


@Repository
public interface EventCardDetails_Repo extends JpaRepository<EventsWithStatus_Entity, String>{

//	@Query(nativeQuery = true, value="select id, announced_date, event_title, event_date, from_time, to_time, event_desc, event_location, event_pic, event_status,announcement_converted_flag from(select id, announced_date, event_title, event_date, from_time, to_time, event_desc, event_location, event_pic, announcement_converted_flag, case when event_date = curdate() AND CURTIME() BETWEEN STR_TO_DATE(from_time, '%h:%i,%p') AND STR_TO_DATE(to_time, '%h:%i,%p') THEN 'Ongoing' when event_date > curdate() OR (event_date = CURDATE() AND CURTIME() < STR_TO_DATE(from_time, '%h:%i,%p')) THEN 'Upcoming'  when event_date < curdate() OR (event_date = CURDATE() AND CURTIME() > STR_TO_DATE(to_time, '%h:%i,%p')) THEN 'Completed' end as event_status from events_table)AS subquery order by CASE event_status WHEN 'Upcoming' THEN 1  WHEN 'Ongoing' THEN 2 WHEN 'Completed' THEN 3 ELSE 5 END, CASE WHEN event_status = 'Completed' THEN event_date ELSE NULL END DESC")
//	List<EventsWithStatus_Entity> fetchEventDetailsOnCard();
	
	@Query(nativeQuery = true, value="""
			
			SELECT 
    id, 
    announced_date, 
    event_title, 
    event_date, 
    from_time, 
    to_time, 
    event_desc, 
    event_location, 
    event_pic, 
    event_status,
    announcement_converted_flag
FROM (
    SELECT 
        id, 
        announced_date, 
        event_title, 
        event_date, 
        from_time, 
        to_time, 
        event_desc, 
        event_location, 
        event_pic, 
        announcement_converted_flag,
        CASE 
            WHEN event_date::date = CURRENT_DATE 
                 AND CURRENT_TIME BETWEEN TO_TIMESTAMP(from_time, 'HH:MI AM')::TIME 
                                     AND TO_TIMESTAMP(to_time, 'HH:MI AM')::TIME 
            THEN 'Ongoing'
            WHEN event_date::date > CURRENT_DATE 
                 OR (event_date::date = CURRENT_DATE AND CURRENT_TIME < TO_TIMESTAMP(from_time, 'HH:MI AM')::TIME) 
            THEN 'Upcoming'
            WHEN event_date::date < CURRENT_DATE 
                 OR (event_date::date = CURRENT_DATE AND CURRENT_TIME > TO_TIMESTAMP(to_time, 'HH:MI AM')::TIME) 
            THEN 'Completed'
        END AS event_status
    FROM events_table
) AS subquery 
ORDER BY 
    CASE event_status 
        WHEN 'Upcoming' THEN 1  
        WHEN 'Ongoing' THEN 2 
        WHEN 'Completed' THEN 3 
        ELSE 5 
    END,
    CASE 
        WHEN event_status = 'Completed' THEN event_date 
        ELSE NULL 
    END DESC;
			
			""")
	List<EventsWithStatus_Entity> fetchEventDetailsOnCard();

}
