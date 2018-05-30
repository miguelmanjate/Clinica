package mz.ciuem.uclinica.test.auth;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.auth.dao.PermissionDao;
import mz.ciuem.uclinica.test.GenericTest;

@Transactional
public class PermissionDaoTest extends GenericTest {

	@Autowired
	private PermissionDao permissionDao;

	@Test
	public void createPermissionTest() {
		
	
		long totalPermission = permissionDao.count();

		Permission permission = new Permission();
		permission.setAuthorization(Authorization.ROLE_LISTAR_RECIBOS);
		permissionDao.create(permission);

		//Assert.assertEquals(totalPermission + 1, permissionDao.count());

	}

//	@Test
//	public void actualicaoDaDesignacaoDaPermissaoTest() {
//
//		Permission permission = new Permission();
//		permission.setAuthorization(Authorization.ROLE_AGENDAR_CONSULTA);
//		permissionDao.create(permission);
//		permission.setAuthorization(Authorization.ROLE_AGENDAR_CONSULTA);
//		permissionDao.update(permission);
//
//		Assert.assertEquals(permission.getAuthorization(), Authorization.ROLE_AGENDAR_CONSULTA);
//
//	}
//
//	@Test
//	public void buscarPermissionPeloNome() {
//
//		List<Permission> permissions = permissionDao.buscarPelaAutorizacao(Authorization.ROLE_AGENDAR_CONSULTA);
//
//		
//		int quantidadeActual = permissions == null ? 0 : permissions.size();
//		Permission permission = new Permission();
//		permission.setAuthorization(Authorization.ROLE_AGENDAR_CONSULTA);
//		permissionDao.saveOrUpdate(permission);
//
//		Assert.assertEquals(quantidadeActual + 1, permissionDao.buscarPelaAutorizacao(Authorization.ROLE_AGENDAR_CONSULTA).size());
//
//	}

}
