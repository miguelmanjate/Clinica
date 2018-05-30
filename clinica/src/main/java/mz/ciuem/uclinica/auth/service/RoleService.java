package mz.ciuem.uclinica.auth.service;


import mz.ciuem.uclinica.auth.Role;
import mz.ciuem.uclinica.service.GenericService;

public interface RoleService extends GenericService<Role>{

	Role findByName(String rolename);

}
