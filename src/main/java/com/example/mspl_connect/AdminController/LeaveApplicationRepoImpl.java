package com.example.mspl_connect.AdminController;

import java.util.List;


import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;
import com.example.mspl_connect.AdminRepo.LeaveApplicationWithProfileRepo;
import com.example.mspl_connect.AdminEntity.LeaveApplicationWithProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import jakarta.persistence.PersistenceContext;

@Repository
public class LeaveApplicationRepoImpl implements LeaveApplicationWithProfileRepo {
	
    @PersistenceContext
    private EntityManager entityManager;
	
	@Override
    public List<LeaveApplicationWithProfile> getNewleaveRequestWithProfile(String empId) {
        String sql = "SELECT la.*,ed.profile_pic_path,concat(ed.f_name,' ',ed.l_name) as sender_name FROM leave_application la inner join employee_details ed on la.empid=ed.empid where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and  la.empid != :empId and la.way=1";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId);
        
        return query.getResultList();
    }

    @Override
    public List<LeaveApplicationWithProfile> getNewleaveRequestWithProfileForSA1() {
        String sql = "SELECT la.*,ed.profile_pic_path,concat(ed.f_name,' ',ed.l_name) as sender_name FROM leave_application la inner join employee_details ed on la.empid=ed.empid where la.way=1";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);

        return query.getResultList();
    }
    
    
    @Override
    public List<LeaveApplicationWithProfile> getleaveRequestByEmpID(String empId) {
        String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name FROM leave_application la "
                   + "INNER JOIN employee_details ed ON la.empid = ed.empid "
        		   + "LEFT JOIN  employee_details ed1 on la.sender_id = ed1.empid "
                   + "WHERE (ed.team_lead_name = :empId OR ed.team_co_name = :empId) AND la.way = 1";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId); // Setting the parameter

        return query.getResultList();
    }
    
    @Override
    public List<LeaveApplicationWithProfile> findByEmpidNot(String empId) {
        /*String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name FROM leave_application la "
        		     + " inner join employee_details ed on la.empid=ed.empid"
        		     + " LEFT JOIN  employee_details ed1 on la.sender_id = ed1.empid "
        		     + " where la.empid != :empId and la.way=1 order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId); */
    	
    	String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name FROM leave_application la "
   		     + " inner join employee_details ed on la.empid=ed.empid"
   		     + " LEFT JOIN  employee_details ed1 on la.sender_id = ed1.empid "
   		     + " where la.way=1 order by la.id desc";

	   Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);

        return query.getResultList();
    }
    
    @Override
    public List<LeaveApplicationWithProfile> getProccessedleaveRequest(String empId) {
        String sql = "select la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name from leave_application la "
        		+ "inner join employee_details ed on la.empid = ed.empid "
        		+ "LEFT JOIN  employee_details ed1 on la.sender_id = ed1.empid "
        		+ "where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and la.approvedstatus='Approved' order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId); // Setting the parameter

        return query.getResultList();
    }
    
    @Override
    public List<LeaveApplicationWithProfile> getProccessedleaveRequestFoeSA(String empId) {
        /*String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name FROM leave_application la"
        		+ " inner join employee_details ed on la.empid=ed.empid"
        		+ " LEFT JOIN employee_details ed1 on la.sender_id = ed1.empid "
        		+ " where la.empid != :empId and approvedstatus='Approved' order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId); */
    	
    	String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name FROM leave_application la"
        		+ " inner join employee_details ed on la.empid=ed.empid"
        		+ " LEFT JOIN employee_details ed1 on la.sender_id = ed1.empid "
        		+ " where approvedstatus='Approved' order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);

        return query.getResultList();
    }
    
    @Override
    public List<LeaveApplicationWithProfile> getRejectedleaveRequest(String empId) {
        String sql = "select la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name  from leave_application la"
        		+ " inner join employee_details ed on la.empid = ed.empid"
        		+ " LEFT JOIN employee_details ed1 on la.sender_id = ed1.empid "
        		+ " where (ed.team_lead_name=:empId or ed.team_co_name=:empId) and la.approvedstatus='Rejected' order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId); // Setting the parameter

        return query.getResultList();
    }
    
    @Override
    public List<LeaveApplicationWithProfile> getRejectedleaveRequestForSA(String empId) {
        /*String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name  FROM leave_application la"
        		+ " inner join employee_details ed on la.empid=ed.empid "
        		+ " LEFT JOIN employee_details ed1 on la.sender_id = ed1.empid"
        		+ " where la.empid != :empId and la.approvedstatus='Rejected' order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);
        query.setParameter("empId", empId); */
    	
    	String sql = "SELECT la.*,ed.profile_pic_path,concat(ed1.f_name,' ',ed1.l_name) as sender_name  FROM leave_application la"
        		+ " inner join employee_details ed on la.empid=ed.empid "
        		+ " LEFT JOIN employee_details ed1 on la.sender_id = ed1.empid"
        		+ " where la.approvedstatus='Rejected' order by la.id desc";

        Query query = entityManager.createNativeQuery(sql, LeaveApplicationWithProfile.class);

        return query.getResultList();
    }
    
    

}
