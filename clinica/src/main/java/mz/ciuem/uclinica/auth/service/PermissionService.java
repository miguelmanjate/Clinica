package mz.ciuem.uclinica.auth.service;

import java.util.List;

import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.service.GenericService;

public interface PermissionService  extends GenericService<Permission>{

	List<Permission> buscarPelaAutorizacao(Authorization auth);
	
	Permission buscarPermissaoPorDesignacao(Authorization auth);

}