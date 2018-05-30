package mz.ciuem.uclinica.auth.dao;

import java.util.List;

import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.dao.GenericDao;

public interface PermissionDao extends GenericDao<Permission>{

	List<Permission> buscarPelaAutorizacao(Authorization roleAdmin);

	Permission buscarPermissaoPorDesignacao(Authorization auth);


}