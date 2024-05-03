package com.oneteam.empsystem.repo.reposimpl;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.Project;
import com.oneteam.empsystem.repo.repos.ProjectRepo;

import java.util.Set;


public class ProjectRepoImpl extends GenericRepoImpl<Project, Long> implements ProjectRepo {

    public ProjectRepoImpl() {
        super(Project.class); // you don't need to use GenericRepoImpl in all time, ust use proImpl and it parse it's type
    }

    /*
    public void remove(Long id) {
        try(Session session = st_getCurrentSession()){
            st_beginTransaction();
            Project project = session.get(Project.class, id);
            if (project != null) {
                session.delete(project);
            }
            st_commitTransaction();
        }catch (Exception e) {
            if (st_isActiveTransaction()) {
                st_rollbackTransaction();
            }
            System.err.println(e.getMessage());
        }
    }
    */

    @Override
    public Set<Employee> getEmployees(Long projectId) {
        return findById(projectId).getEmployees();
    }
}
