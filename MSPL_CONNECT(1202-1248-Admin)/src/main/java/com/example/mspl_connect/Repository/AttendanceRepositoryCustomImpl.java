package com.example.mspl_connect.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.mspl_connect.Entity.AttendanceDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AttendanceRepositoryCustomImpl implements AttendanceRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	// Static block to initialize the monthMap
    private static final Map<String, Integer> monthMap = new HashMap<>();
    
    static {
        monthMap.put("January", 1);
        monthMap.put("February", 2);
        monthMap.put("March", 3);
        monthMap.put("April", 4);
        monthMap.put("May", 5);
        monthMap.put("June", 6);
        monthMap.put("July", 7);
        monthMap.put("August", 8);
        monthMap.put("September", 9);
        monthMap.put("October", 10);
        monthMap.put("November", 11);
        monthMap.put("December", 12);
    }

    
    @Override
    public List<AttendanceDTO> findAttendanceByDateAndEmployee(String date, String employeeId) {
    	System.out.println("lll"+date);
        // SQL query to retrieve attendance records for a specific date and employeeId
        String sql = "SELECT " +
                "eid, " +
                "pd AS date, " +
                "MIN(CASE WHEN pt = min_pt THEN pt ELSE NULL END) AS in_time, " +
                "CASE " +
                "    WHEN pd = CURDATE() THEN '-' " +
                "    ELSE MAX(CASE WHEN pt = max_pt THEN pt ELSE NULL END) " +
                "END AS out_time " +
                "FROM ( " +
                "    SELECT " +
                "        eid, " +
                "        pd, " +
                "        pt, " +
                "        (SELECT MIN(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS min_pt, " +
                "        (SELECT MAX(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS max_pt " +
                "    FROM attendance a " +
                "    WHERE eid = ? AND pd = ? " + // Filter by specific date
                ") subquery " +
                "GROUP BY eid, pd " +
                "ORDER BY pd;"; // This will return only the records for the given date

        // Fetch the records using the updated query
        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId, date}, new AttendanceRowMapperWithoutName());
        System.out.println("Fetched records: " + records);

        // Calculate total duration and overtime
        records.forEach(dto -> {
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);

                // Calculate overtime
                String overtime = calculateOvertime(duration);
                dto.setOvertime(overtime);
            } else {
                dto.setTotalHours("-");
                dto.setOvertime("-");
            }
        });

        return records;
    }

    @Override
    public List<AttendanceDTO> findAttendanceByyearAndEmployeeCalendar(int year, String employeeId) {
        String query = "SELECT new com.example.AttendanceDTO(a.eid, a.date, a.inTime, a.outTime, a.totalHours, a.overtime, a.employeeName) "
                     + "FROM Attendance a WHERE YEAR(a.date) = :year AND a.eid = :employeeId";
        return entityManager.createQuery(query, AttendanceDTO.class)
                .setParameter("year", year)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }
    
  /*  @Override
    public List<AttendanceDTO> findAttendanceByMonthAndEmployee(String month, String employeeId) {
        Integer monthInt = monthMap.get(month);
        
        if (monthInt == null) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        int year = java.time.LocalDate.now().getYear();

        String sql = "SELECT " +
                "subquery.eid, " +
                "subquery.pd AS date, " +
                "MIN(CASE WHEN subquery.pt = subquery.min_pt THEN subquery.pt ELSE NULL END) AS in_time, " +
                "CASE " +
                "    WHEN subquery.pd = CURDATE() THEN '-' " +
                "    ELSE MAX(CASE WHEN subquery.pt = subquery.max_pt THEN subquery.pt ELSE NULL END) " +
                "END AS out_time, " +
                "CONCAT(e.f_Name, ' ', e.l_Name) AS employee_name " +
                "FROM ( " +
                "    SELECT " +
                "        a.eid, " +
                "        a.pd, " +
                "        a.pt, " +
                "        (SELECT MIN(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS min_pt, " +
                "        (SELECT MAX(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS max_pt " +
                "    FROM attendance a " +
                "    WHERE a.eid = ? AND MONTH(a.pd) = ? AND YEAR(a.pd) = ? " +
                ") subquery " +
                "JOIN employee_details e ON e.empId = subquery.eid " +
                "GROUP BY subquery.eid, subquery.pd, e.f_Name, e.l_Name " +
                "ORDER BY subquery.pd;";

        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId, monthInt, year}, new AttendanceRowMapperWithName());

        records.forEach(dto -> {
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);
                
                String overtime = calculateOvertime(duration);
                dto.setOvertime(overtime);
            } else {
                dto.setTotalHours("-");
                dto.setOvertime("-");
            }
        });

        return records;
    }*/
   
    
    
    @Override
    public List<AttendanceDTO> findAttendanceByMonthAndEmployee(String month, String employeeId) {
        Integer monthInt = monthMap.get(month);

        if (monthInt == null) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        int year = java.time.LocalDate.now().getYear();

        String sql = "WITH attendance_min_max AS ( " +
                "    SELECT " +
                "        eid, " +
                "        pd, " +
                "        MIN(pt) AS min_pt, " +
                "        MAX(pt) AS max_pt " +
                "    FROM attendance " +
                "    WHERE eid = ? AND MONTH(pd) = ? AND YEAR(pd) = ? " +
                "    GROUP BY eid, pd " +
                ") " +
                "SELECT " +
                "    am.eid, " +
                "    am.pd AS date, " +
                "    MIN(CASE WHEN a.pt = am.min_pt THEN a.pt ELSE NULL END) AS in_time, " +
                "    CASE " +
                "        WHEN am.pd = CURDATE() THEN '-' " +
                "        ELSE MAX(CASE WHEN a.pt = am.max_pt THEN a.pt ELSE NULL END) " +
                "    END AS out_time, " +
                "    CONCAT(e.f_Name, ' ', e.l_Name) AS employee_name " +
                "FROM attendance_min_max am " +
                "JOIN attendance a ON a.eid = am.eid AND a.pd = am.pd " +
                "JOIN employee_details e ON e.empId = am.eid " +
                "GROUP BY am.eid, am.pd, e.f_Name, e.l_Name " +
                "ORDER BY am.pd;";


        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId, monthInt, year}, new AttendanceRowMapperWithName());

        records.forEach(dto -> {
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);

                String overtime = calculateOvertime(duration);
                dto.setOvertime(overtime);

                // Calculate undertime
                double totalHoursWorked = parseDurationToHours(duration);
                String standardHoursStr = "09:00:00"; // Standard working hours as HH:mm:ss

                if (duration != null && !"-".equals(duration)) {
                    if (totalHoursWorked < parseDurationToHours(standardHoursStr)) {
                        String undertime = calculateUndertime(totalHoursWorked, standardHoursStr);
                        dto.setUndertime(undertime);
                    } else {
                        dto.setUndertime("-");
                    }
                } else {
                    dto.setUndertime("-");
                }
            } else {
                dto.setTotalHours("-");
                dto.setOvertime("-");
                dto.setUndertime("-");
            }
        });

        return records;
    }

    
    @Override
    public List<AttendanceDTO> findAttendanceByMonthAndEmployee(String month, String employeeId, int year) {
        Integer monthInt = monthMap.get(month);

        if (monthInt == null) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        /*String sql = "SELECT " +
                "subquery.eid, " +
                "subquery.pd AS date, " +
                "MIN(CASE WHEN subquery.pt = subquery.min_pt THEN subquery.pt ELSE NULL END) AS in_time, " +
                "CASE " +
                "    WHEN subquery.pd = CURDATE() THEN '-' " +
                "    ELSE MAX(CASE WHEN subquery.pt = subquery.max_pt THEN subquery.pt ELSE NULL END) " +
                "END AS out_time, " +
                "CONCAT(e.f_Name, ' ', e.l_Name) AS employee_name " +
                "FROM ( " +
                "    SELECT " +
                "        a.eid, " +
                "        a.pd, " +
                "        a.pt, " +
                "        (SELECT MIN(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS min_pt, " +
                "        (SELECT MAX(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS max_pt " +
                "    FROM attendance a " +
                "    WHERE a.eid = ? AND MONTH(a.pd) = ? AND YEAR(a.pd) = ? " +
                ") subquery " +
                "JOIN employee_details e ON e.empId = subquery.eid " +
                "GROUP BY subquery.eid, subquery.pd, e.f_Name, e.l_Name " +
                "ORDER BY subquery.pd;";*/
        String sql = 
        	    "WITH MinMaxAttendance AS ( " +
        	    "    SELECT " +
        	    "        eid, " +
        	    "        pd, " +
        	    "        MIN(pt) AS min_pt, " +
        	    "        MAX(pt) AS max_pt " +
        	    "    FROM attendance " +
        	    "    WHERE eid = ? AND MONTH(pd) = ? AND YEAR(pd) = ? " +
        	    "    GROUP BY eid, pd " +
        	    ") " +
        	    "SELECT " +
        	    "    mma.eid, " +
        	    "    mma.pd AS date, " +
        	    "    MIN(CASE WHEN a.pt = mma.min_pt THEN a.pt ELSE NULL END) AS in_time, " +
        	    "    CASE " +
        	    "        WHEN mma.pd = CURDATE() THEN '-' " +
        	    "        ELSE MAX(CASE WHEN a.pt = mma.max_pt THEN a.pt ELSE NULL END) " +
        	    "    END AS out_time, " +
        	    "    CONCAT(e.f_Name, ' ', e.l_Name) AS employee_name " +
        	    "FROM attendance a " +
        	    "JOIN MinMaxAttendance mma ON a.eid = mma.eid AND a.pd = mma.pd " +
        	    "JOIN employee_details e ON e.empId = mma.eid " +
        	    "GROUP BY mma.eid, mma.pd, e.f_Name, e.l_Name " +
        	    "ORDER BY mma.pd;";


        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId, monthInt, year}, new AttendanceRowMapperWithName());

        records.forEach(dto -> {
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);

                String overtime = calculateOvertime(duration);
                dto.setOvertime(overtime);

                // Calculate undertime
                double totalHoursWorked = parseDurationToHours(duration);
                String standardHoursStr = "09:00:00"; // Standard working hours as HH:mm:ss

                if (duration != null && !"-".equals(duration)) {
                    if (totalHoursWorked < parseDurationToHours(standardHoursStr)) {
                        String undertime = calculateUndertime(totalHoursWorked, standardHoursStr);
                        dto.setUndertime(undertime);
                    } else {
                        dto.setUndertime("-");
                    }
                } else {
                    dto.setUndertime("-");
                }
            } else {
                dto.setTotalHours("-");
                dto.setOvertime("-");
                dto.setUndertime("-");
            }
        });

        return records;
    }
    
 // Convert duration string (e.g., "08:30:15") to hours as a double
    private double parseDurationToHours(String duration) {
        if (duration == null || duration.equals("-")) {
            return 0.0;
        }

        String[] parts = duration.split(":");

        if (parts.length == 3) {
            try {
                double hours = Double.parseDouble(parts[0]);
                double minutes = Double.parseDouble(parts[1]) / 60.0;
                double seconds = Double.parseDouble(parts[2]) / 3600.0;
                return hours + minutes + seconds;
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }

        // Handle the case where the duration is in "HH:mm" format
        if (parts.length == 2) {
            try {
                double hours = Double.parseDouble(parts[0]);
                double minutes = Double.parseDouble(parts[1]) / 60.0;
                return hours + minutes;
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }

        return 0.0;
    }
    
    
 // Calculate undertime based on total worked hours and standard hours
    private String calculateUndertime(double totalHoursWorked, String standardHoursStr) {
        double standardHours = parseDurationToHours(standardHoursStr);
        
        if (totalHoursWorked < standardHours) {
            double undertime = standardHours - totalHoursWorked;
            return formatHoursToHHMMSS(undertime);
        } else {
            return "-";
        }
    }

    // Convert hours to HH:mm:ss format
    private String formatHoursToHHMMSS(double hours) {
        int intHours = (int) hours;
        int intMinutes = (int) ((hours - intHours) * 60);
        int intSeconds = (int) (((hours - intHours) * 60 - intMinutes) * 60);

        return String.format("%02d:%02d:%02d", intHours, intMinutes, intSeconds);
    }


    private String calculateOvertime(String totalHours) {
        if ("-".equals(totalHours)) {
            return "-";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            LocalTime workedTime = LocalTime.parse(totalHours, formatter);
            LocalTime standardTime = LocalTime.of(9, 0); // 9 hours

            Duration workedDuration = Duration.between(LocalTime.MIDNIGHT, workedTime);
            Duration standardDuration = Duration.between(LocalTime.MIDNIGHT, standardTime);

            long overtimeSeconds = workedDuration.minus(standardDuration).toSeconds();

            if (overtimeSeconds > 0) {
                long hours = overtimeSeconds / 3600;
                long minutes = (overtimeSeconds % 3600) / 60;
                long seconds = overtimeSeconds % 60;
                return String.format("%02d:%02d:%02d", hours, minutes, seconds);
            } else {
                return "-";
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return "Invalid time";
        }
    }
    
    @Override
    public List<AttendanceDTO> findAttendanceByyearAndEmployee(int year, String employeeId) {
    	System.out.println("heyy");
        /*String sql = "SELECT " +
                "eid, " +
                "pd AS date, " +
                "MIN(CASE WHEN pt = min_pt THEN pt ELSE NULL END) AS in_time, " +
                "CASE " +
                "    WHEN pd = CURDATE() THEN '-' " +
                "    ELSE MAX(CASE WHEN pt = max_pt THEN pt ELSE NULL END) " +
                "END AS out_time " +
                "FROM ( " +
                "    SELECT " +
                "        eid, " +
                "        pd, " +
                "        pt, " +
                "        (SELECT MIN(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS min_pt, " +
                "        (SELECT MAX(pt) FROM attendance WHERE eid = a.eid AND pd = a.pd) AS max_pt " +
                "    FROM attendance a " +
                "    WHERE eid = ? AND YEAR(pd) = ? " +
                ") subquery " +
                "GROUP BY eid, pd " +
                "ORDER BY pd;";*/
    	
    	//String sql = " SELECT eid, DATE(pdt) AS date, MIN(pt) AS in_time, MAX(pt) AS out_time FROM attendance WHERE eid = ? and year(pd)= ? GROUP BY DATE(pdt) ORDER BY date ASC;";
    	String sql = "SELECT " +
                "eid, " +
                "pd AS date, " +
                "MIN(pt) AS in_time, " +
                "CASE " +
                "    WHEN pd = CURDATE() THEN '-' " +
                "    ELSE MAX(pt) " +
                "END AS out_time " +
                "FROM attendance " +
                "WHERE eid = ? AND YEAR(pd) = ? " +
                "GROUP BY eid, pd " +
                "ORDER BY pd;";


        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId, year}, new AttendanceRowMapperWithoutName());
        System.out.println("Fetched records:==== " + records);
        // Calculate total duration
        records.forEach(dto -> {
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);
            } else {
                dto.setTotalHours("-");
            }
        });

        return records;
    }
    //findAttendanceByyearAndEmployee
    @Override
    public List<AttendanceDTO> findAttendanceByYearAndEmpId(int year, String employeeId,String fromDate,String toDate) {
    	System.out.println("heyy");
        //String sql = " SELECT eid, DATE(pdt) AS date, MIN(pt) AS in_time, MAX(pt) AS out_time FROM attendance WHERE eid = ? and year(pd)= ? GROUP BY DATE(pdt) ORDER BY date ASC;";
    	String sql = " SELECT a.eid, DATE(a.pdt) AS date, MIN(a.pt) AS in_time, MAX(a.pt) AS out_time, CONCAT(e.f_name,' ', e.l_name) AS employee_name FROM attendance a INNER JOIN employee_details e ON a.eid = e.empid WHERE a.eid = ? AND YEAR(a.pd) = ? AND a.pd BETWEEN ? AND ? GROUP BY a.eid, DATE(a.pdt), e.f_name, e.l_name ORDER BY date ASC ";

    	
        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId, year,fromDate, toDate}, new AttendanceRowMapperWithName());
        System.out.println("Fetched records:==== " + records);
        // Calculate total duration
        records.forEach(dto -> {
        	System.out.println("ggggggggggggggggg");
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);
            } else {
                dto.setTotalHours("-");
            }
        });

        return records;
    }
    
    private String calculateDuration(String inTime, String outTime) {
        if ("-".equals(inTime) || "-".equals(outTime)) {
            return "-";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            LocalTime start = LocalTime.parse(inTime, formatter);
            LocalTime end = LocalTime.parse(outTime, formatter);

            // Calculate the duration between start and end
            Duration duration = Duration.between(start, end);

            // Format the duration to HH:mm:ss
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            long seconds = duration.toSecondsPart();
            
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return "Invalid time";
        }
    }

    

   /* private static class AttendanceRowMapper implements RowMapper<AttendanceDTO> {
        @Override
        public AttendanceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        	 AttendanceDTO dto = new AttendanceDTO();
             String eid = rs.getString("eid");
             String date = rs.getString("date");
             String inTime = rs.getString("in_time");
             String outTime = rs.getString("out_time");
            String employeeName = rs.getString("employee_name");
           //  dto.setEmployeeName(rs.getString("employee_name")); 
             
             dto.setEid(eid);
             dto.setDate(date);
             dto.setInTime(inTime);
             dto.setOutTime(outTime);
             dto.setEmployeeName(employeeName);
        //   System.out.println("Mapping row - eid:== " + eid + ", date: " + date + ", inTime: " + inTime + ", outTime: " + outTime+",name:"+employeeName);
            
            return dto;
        }
    }*/
    
    
    
    private static class AttendanceRowMapperWithName implements RowMapper<AttendanceDTO> {
        @Override
        public AttendanceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            AttendanceDTO dto = new AttendanceDTO();
            dto.setEid(rs.getString("eid"));
            dto.setDate(rs.getString("date"));
            dto.setInTime(rs.getString("in_time"));
            dto.setOutTime(rs.getString("out_time"));
            dto.setEmployeeName(rs.getString("employee_name")); // Include employee name
            return dto;
        }
    }

    private static class AttendanceRowMapperWithoutName implements RowMapper<AttendanceDTO> {
        @Override
        public AttendanceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            AttendanceDTO dto = new AttendanceDTO();
            dto.setEid(rs.getString("eid"));
            dto.setDate(rs.getString("date"));
            dto.setInTime(rs.getString("in_time"));
            dto.setOutTime(rs.getString("out_time"));
            return dto;
        }
    }
    
    public List<AttendanceDTO> findAttendanceByEmployee(String employeeId) {
    	String sql = "SELECT " +
	            "eid, " +
	            "pd AS date, " +
	            "MIN(CASE WHEN pt = min_pt THEN pt ELSE NULL END) AS in_time, " +
	            "CASE " +
	            "    WHEN pd = CURDATE() THEN '-' " +
	            "    ELSE MAX(CASE WHEN pt = max_pt THEN pt ELSE NULL END) " +
	            "END AS out_time " +
	            "FROM ( " +
	            "    SELECT " +
	            "        eid, " +
	            "        pd, " +
	            "        pt, " +
	            "        MIN(pt) OVER (PARTITION BY eid, pd) AS min_pt, " +
	            "        MAX(pt) OVER (PARTITION BY eid, pd) AS max_pt " +
	            "    FROM attendance " +
	            "    WHERE eid = ? " +
	            ") subquery " +
	            "GROUP BY eid, pd " +
	            "ORDER BY pd;";

        List<AttendanceDTO> records = jdbcTemplate.query(sql, new Object[]{employeeId}, new AttendanceRowMapperWithoutName());

        // Calculate total duration for each record
        records.forEach(dto -> {
            if (dto.getInTime() != null && dto.getOutTime() != null) {
                String duration = calculateDuration(dto.getInTime(), dto.getOutTime());
                dto.setTotalHours(duration);
            } else {
                dto.setTotalHours("-");
            }
        });

        return records;
    }
    
    //public Integer getMissPunchByEmpId(String eid)

}
