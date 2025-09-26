package com.example.mspl_connect.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mspl_connect.Entity.ProjectChartResponse;
import com.example.mspl_connect.Entity.TeamProgress;
import com.example.mspl_connect.Repository.Project_Chart_Repo;
import com.example.mspl_connect.Repository.TeamProgressRepository;
import com.example.mspl_connect.Repository.TeamProgressSummary;

@Service
public class TeamProgressService {

    @Autowired
    private TeamProgressRepository teamProgressRepository;
    
    @Autowired
	private Project_Chart_Repo project_Chart_Repo;

    public List<TeamProgressSummary> getTeamProgressByProjectId(String projectId) {
        return teamProgressRepository.findTeamProgressByProjectId(projectId);
    }	
    public int getProjectCompletedPercent(String projectId) {
    	return teamProgressRepository.getProjectCompletedPercent(projectId);
    }
    public List<TeamProgress> getProjectByprjId(String projectId){
    	
    	return teamProgressRepository.getProjectByprjId(projectId);
    }
    
    public List<ProjectChartResponse> getProjectCompletedPercentWithIndividualStatus(String projectId) {
        List<Object[]> rawResults = project_Chart_Repo.getProjectPercentWithIndividualStatus(projectId);

        // Calculate the total sum of completed work
        int totalSum = rawResults.stream()
            .map(row -> ((BigDecimal) row[2]).intValue()) // Convert BigDecimal to int
            .mapToInt(Integer::intValue)
            .sum();

        // Convert raw results to ProjectChartResponse
        return rawResults.stream()
            .map(row -> new ProjectChartResponse(
                (String) row[0],  // emp_name
                (String) row[1],  // email
                ((BigDecimal) row[2]).intValue(), // Convert BigDecimal to int
                ((Double) row[3]).intValue(),
                totalSum
            ))
            .collect(Collectors.toList());
    }

}
