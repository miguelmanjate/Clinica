package mz.ciuem.uclinica.auth.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.auth.Role;
import mz.ciuem.uclinica.auth.dao.RoleDao;
import mz.ciuem.uclinica.auth.service.RoleService;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("roleService")
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService{
	
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role findByName(String rolename) {
		return roleDao.findByname(rolename);
	}

}
