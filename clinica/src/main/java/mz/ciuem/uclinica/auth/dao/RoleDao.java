package mz.ciuem.uclinica.auth.dao;

import mz.ciuem.uclinica.auth.Role;
import mz.ciuem.uclinica.dao.GenericDao;

public interface RoleDao extends GenericDao<Role>{

	Role findByname(String rolename);

}
