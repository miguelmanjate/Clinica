package mz.ciuem.uclinica.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.auth.dao.PermissionDao;
import mz.ciuem.uclinica.auth.service.PermissionService;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("permissionService")
public class PermissionServiceImpl extends GenericServiceImpl<Permission> implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> buscarPelaAutorizacao(Authorization auth) {
		return permissionDao.buscarPelaAutorizacao(auth);
	}

	@Override
	public Permission buscarPermissaoPorDesignacao(Authorization auth) {
		return permissionDao.buscarPermissaoPorDesignacao(auth);
	}



}
